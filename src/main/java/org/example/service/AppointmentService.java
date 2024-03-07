package org.example.service;

import org.example.entities.Appointment;
import org.example.entities.Pet;
import org.example.entities.Vet;
import org.example.exception.InvalidDateTimeExeption;
import org.example.exception.InvalidInputExeption;
import org.example.exception.NoPetsFoundForPhoneNumberException;
import org.example.exception.SomethingWentWrongExeption;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.PetRepository;
import org.example.repositories.VetRepository;
import org.example.utils.Utils;
import org.example.validator.AppointmentValidator;
import org.example.validator.DateTimeValidator;

import java.time.LocalDateTime;
import java.util.List;


public class AppointmentService {
    private final InputOutputService inputOutputService = new InputOutputService();
    private final AppointmentRepository appointmentRepository = new AppointmentRepository();
    private final PetRepository petRepository = new PetRepository();
    private final VetRepository vetRepository = new VetRepository();
    private final AppointmentValidator appointmentValidator = new AppointmentValidator();
    private final DateTimeValidator dateTimeValidator = new DateTimeValidator();

    public void addNewAppointment() {
        try {
            Pet selectedPet = getPetForAppointment();
            Vet selectedVet = getVetForAppointment();
            LocalDateTime appointmentDateAndTime = getDateTimeForAppointment();
            if (dateTimeValidator.isBeforeLocalDateTime(appointmentDateAndTime)) {
                inputOutputService.displayToConsole("The date and time should not be set in the past");
                return;
            }
            if (dateTimeValidator.isWeekend(appointmentDateAndTime)) {
                inputOutputService.displayToConsole("Sorry the vet is not working in weekend");
                return;
            }
            if (appointmentValidator.isVetInHoliday(appointmentDateAndTime, selectedVet)) {
                inputOutputService.displayToConsole("Sorry the vet is in holiday");
                return;
            }
            if (appointmentValidator.isNewAppointmentNotValid(appointmentDateAndTime, selectedVet)) {
                inputOutputService.displayToConsole("Sorry the vet is not available at this date and time");
                return;
            }
            Appointment appointment = createAppointmentObject(appointmentDateAndTime, selectedVet, selectedPet);
            save(appointment);
            inputOutputService.displayToConsole("Your appointment has been successfully added");
        } catch (NoPetsFoundForPhoneNumberException exception) {
            inputOutputService.displayToConsole("Sorry the phone number doesn't have any pets!");
        } catch (InvalidInputExeption e) {
            inputOutputService.displayToConsole("Sorry the input is not valid!");
        } catch (InvalidDateTimeExeption e) {
            inputOutputService.displayToConsole("Sorry the date and the time are not valid!");
        } catch (SomethingWentWrongExeption e) {
            inputOutputService.displayToConsole("Sorry, something went wrong! Please try again!");
        }
    }

    private void save(Appointment appointment) throws SomethingWentWrongExeption {
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new SomethingWentWrongExeption();
        }
    }

    private Appointment createAppointmentObject(LocalDateTime dateAndTime, Vet selectedVet, Pet selectedPet) {
        Appointment appointment = new Appointment();
        appointment.setDateTime(dateAndTime);
        appointment.setVet(selectedVet);
        appointment.setPet(selectedPet);
        return appointment;
    }

    private Pet getPetForAppointment() throws NoPetsFoundForPhoneNumberException, InvalidInputExeption {
        inputOutputService.displayToConsole("What is the pet owner phone number?");
        String phoneNumber = inputOutputService.getUserInput();
        List<Pet> petList = petRepository.findByOwnerPhoneNumber(phoneNumber);
        if (petList.isEmpty()) {
            throw new NoPetsFoundForPhoneNumberException();
        }
        inputOutputService.displayPetOptions(petList);
        String petNumber = inputOutputService.getUserInput();
        try {
            return petList.get(Integer.parseInt(petNumber) - 1);
        } catch (Exception e) {
            throw new InvalidInputExeption();
        }
    }

    private Vet getVetForAppointment() throws InvalidInputExeption {
        List<Vet> vetList = vetRepository.getAllVets();
        inputOutputService.displayVetOptions(vetList);
        String vetNumber = inputOutputService.getUserInput();
        try {
            return vetList.get(Integer.parseInt(vetNumber) - 1);
        } catch (Exception e) {
            throw new InvalidInputExeption();
        }
    }

    private LocalDateTime getDateTimeForAppointment() throws InvalidDateTimeExeption {
        inputOutputService.displayToConsole("What is the date of your appointment?");
        String appointmentDate = inputOutputService.getUserInput();
        inputOutputService.displayToConsole("What is the time of your appointment?");
        String appointmentTime = inputOutputService.getUserInput();
        return Utils.convertDateTime(appointmentDate, appointmentTime);
    }

}
