package org.example.validator;

import org.example.entities.Appointment;
import org.example.entities.Holiday;
import org.example.entities.Vet;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.HolidayRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentValidator {
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private HolidayRepository holidayRepository = new HolidayRepository();


    public boolean isVetInHoliday(LocalDateTime appointmentDateAndTime, Vet vet) {
        List<Holiday> holidays = holidayRepository.findHolidaysByVet(vet);
        LocalDate appointmentDate = appointmentDateAndTime.toLocalDate();
        for (Holiday h : holidays) {
            if (h.getDate().equals(appointmentDate)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNewAppointmentNotValid(LocalDateTime newDateTime, Vet vet) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByVet(vet);
        long numberOfConflictedApp = appointments
                .stream()
                .map(Appointment::getDateTime)
                .filter(existingDateTime -> existingDateTime.isAfter(newDateTime.minusMinutes(30)))
                .filter(existingDateTime -> existingDateTime.isBefore(newDateTime.plusMinutes(30)))
                .count();
        System.out.println("numberOfConflictedApp = " + numberOfConflictedApp);
        return numberOfConflictedApp > 0;
    }

//    VARIANTA lucrata la clasa:
//    public boolean isNewAppointmentNotValid(LocalDateTime newDateTime, Vet vet) {
//        long numberOfConflictedApp = vet.getAppointments()
//                .stream()
//                .map(Appointment::getDateTime)
//                .filter(existingDateTime -> existingDateTime.isAfter(newDateTime.minusMinutes(30)))
//                .filter(existingDateTime -> existingDateTime.isBefore(newDateTime.plusMinutes(30)))
//                .count();
//        System.out.println("numberOfConflictedApp = " + numberOfConflictedApp);
//        return numberOfConflictedApp > 0;
//    }


}

