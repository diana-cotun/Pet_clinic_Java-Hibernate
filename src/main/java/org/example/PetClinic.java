package org.example;

import org.example.service.*;

public class PetClinic {

    private final VetService vetService = new VetService();
    private final PetService petService = new PetService();
    private final PetOwnerService petOwnerService = new PetOwnerService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final HolidayService holidayService = new HolidayService();
    private final InputOutputService inputOutputService = new InputOutputService();

    public void start() {
        while (true) {
            inputOutputService.displayMenu();
            String userInput = inputOutputService.getUserInput();
            process(userInput);
        }
    }

    private void process(String userInput) {
        switch (userInput) {

            case "1" -> vetService.addNewVet();
            case "2" -> vetService.displayAllVet();
            case "3" -> holidayService.addNewHoliday();
            case "4" -> petOwnerService.addNewPetOwner();
            case "5" -> petOwnerService.displayAllPetOwners();
            case "6" -> petOwnerService.removePetOwner();
            case "7" -> petService.addNewPet();
            case "8" -> petService.displayAllPets();
            case "9" -> petService.updatePetDetails();
            case "10" -> petService.removePet();
            case "11" -> appointmentService.addNewAppointment();

            case "0" -> {
                inputOutputService.displayToConsole("Have a nice day!");
                System.exit(0);
            }
            default -> inputOutputService.displayToConsole("Invalid option!");
        }
    }







}
