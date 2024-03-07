package org.example.service;

import org.example.entities.PetOwner;
import org.example.repositories.PetOwnerRepository;
import org.example.repositories.PetRepository;
import org.example.validator.PetOwnerValidator;

import java.util.List;
import java.util.Optional;

public class PetOwnerService {

    private final PetOwnerRepository petOwnerRepository = new PetOwnerRepository();
    private final PetOwnerValidator petOwnerValidator = new PetOwnerValidator();
    private final InputOutputService inputOutputService = new InputOutputService();


    public void addNewPetOwner() {
        Optional<PetOwner> petOwner = createPetOwner();
        if (petOwner.isPresent()) {
            petOwnerRepository.save(petOwner.get());
            inputOutputService.displayToConsole("New pet owner has been added successfully!");
        }
    }
// acelasi nivel de abstractizare / single

    public void displayAllPetOwners() {
        List<PetOwner> petOwnerList = petOwnerRepository.getAllPetOwners();
        inputOutputService.displayPetOwners(petOwnerList);
    }

    public void removePetOwner() {
        inputOutputService.displayToConsole("What is the pet owner phone number that you want to delete?");
        String phoneNumber = inputOutputService.getUserInput();
        Optional<PetOwner> petOwner = petOwnerRepository.findByPhoneNumber(phoneNumber);
        if (petOwner.isPresent()) {
            petOwnerRepository.delete(petOwner.get());
            inputOutputService.displayToConsole("The pet owner was deleted");
        } else {
            inputOutputService.displayToConsole("Sorry, this pet owner does not exist");
        }
    }

    private Optional<PetOwner> createPetOwner() {
        PetOwner petOwner = new PetOwner();
        inputOutputService.displayToConsole("What is the pet owner first name?");
        petOwner.setFirstName(inputOutputService.getUserInput());
        inputOutputService.displayToConsole("What is the pet owner last name?");
        petOwner.setLastName(inputOutputService.getUserInput());
        int count = 3;
        while (count > 0) {
            inputOutputService.displayToConsole("What is the pet owner phone number?");
            String phoneNumber = inputOutputService.getUserInput();
            if (!petOwnerValidator.isPhoneNumberValid(phoneNumber)) {
                inputOutputService.displayToConsole("Sorry, the pet owner phone number is not valid!");
                if (count == 1) {
                    inputOutputService.displayToConsole("You have introduced three time wrong phone number. Please start over");
                }
                count--;
            } else {
                petOwner.setPhoneNumber(phoneNumber);
                return Optional.of(petOwner);
            }
        }
        return Optional.empty();
    }

}
