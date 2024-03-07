package org.example.service;

import org.example.entities.Pet;
import org.example.entities.PetOwner;
import org.example.repositories.PetOwnerRepository;
import org.example.repositories.PetRepository;
import org.example.utils.Utils;
import org.example.validator.PetValidator;

import java.util.List;
import java.util.Optional;

public class PetService {

    private final PetRepository petRepository = new PetRepository();
    private final PetValidator petValidator = new PetValidator();
    private final InputOutputService inputOutputService = new InputOutputService();
    private final PetOwnerRepository petOwnerRepository = new PetOwnerRepository();


    public void addNewPet() {
        Optional<Pet> optionalPet = createPet();
        if (optionalPet.isPresent()) {
            petRepository.save(optionalPet.get()); // despachetez optionalul si iau obiectul
            inputOutputService.displayToConsole("New pet has been added successfully!");
        } else {
            inputOutputService.displayToConsole("Pet owner does not exist");
        }
    }

    public void displayAllPets() {
        List<Pet> petList = petRepository.findAllPets();
        inputOutputService.displayAllPets(petList);
    }

    public void removePet() {
        inputOutputService.displayToConsole("What is the pet collar id that you want to delete?");
        String collarId = inputOutputService.getUserInput();
        Optional<Pet> optionalPet = getOptionalPetForRemove(collarId);
        if (optionalPet.isEmpty()) {
            inputOutputService.displayToConsole("Sorry, this pet does not exist");
            return;
        }
        petRepository.delete(optionalPet.get());
        inputOutputService.displayToConsole("The pet was deleted");
    }

    private Optional<Pet> getOptionalPetForRemove(String collarId) {
        return petRepository.findByCollarId(collarId);
    }

    public void updatePetDetails() {
        inputOutputService.displayToConsole("What is the pet collar id that you want to update?");
        String collarId = inputOutputService.getUserInput();
        Optional<Pet> optionalPet = getPetForUpdate(collarId);
        if (optionalPet.isEmpty()) {
            inputOutputService.displayToConsole("Sorry, this pet does not exist");
            return;
        }
        petRepository.update(optionalPet.get());
        inputOutputService.displayToConsole("Update successful!");

    }

    private Optional<Pet> getPetForUpdate(String collarId) {
        Optional<Pet> optionalPet = petRepository.findByCollarId(collarId);
        if (optionalPet.isEmpty()) {
            return Optional.empty();
        }
        inputOutputService.displayPetDetails(optionalPet.get());
        String selectedNumber = inputOutputService.getUserInput();
        inputOutputService.displayToConsole("Please enter the new data");
        String newData = inputOutputService.getUserInput();
        switch (selectedNumber) {
            case "1" -> optionalPet.get().setPetName(newData);
            case "2" -> optionalPet.get().setPetCategory(newData);
        }
        return optionalPet;
    }

    private Optional<Pet> createPet() {
        Pet pet = new Pet();
        inputOutputService.displayToConsole("What is the pet type?");
        pet.setPetCategory(inputOutputService.getUserInput());
        inputOutputService.displayToConsole("What is the pet name?");
        pet.setPetName(inputOutputService.getUserInput());

        do {
            String collarId = Utils.generateCollarId();
            if (petValidator.isCollarIdValid(collarId)) {
                pet.setCollarId(collarId);
            }
        } while (pet.getCollarId().isEmpty());

        inputOutputService.displayToConsole("What is the pet owner's phone number?");
        String phoneNumber = inputOutputService.getUserInput();
        Optional<PetOwner> optionalPetOwner = petOwnerRepository.findByPhoneNumber(phoneNumber);
        if (optionalPetOwner.isEmpty()) {
            return Optional.empty();
        }
        pet.setPetOwner(optionalPetOwner.get());
        return Optional.of(pet); // impachetez obiectul intr-un optional
    }


}
