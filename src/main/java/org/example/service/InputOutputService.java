package org.example.service;

import org.example.entities.Pet;
import org.example.entities.PetOwner;
import org.example.entities.Vet;
import org.example.repositories.PetOwnerRepository;
import org.example.repositories.PetRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InputOutputService {

    public void displayMenu() {
        displayToConsole("\nWelcome to the Pet Clinic");
        displayToConsole("Please chose one of the following options: ");
        displayToConsole("0 - exit");
        displayToConsole("1 - add new vet");
        displayToConsole("2 - display all vets");
        displayToConsole("3 - add new holiday");
        displayToConsole("4 - add new owner");
        displayToConsole("5 - display all owners");
        displayToConsole("6 - remove owner");
        displayToConsole("7 - add new pet");
        displayToConsole("8 - display all pets");
        displayToConsole("9 - update pet details");
        displayToConsole("10 - remove pet");
        displayToConsole("11 - add New Appointment");
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        displayToConsoleInLine("Your answer: ");
        return scanner.nextLine();
    }

    public void displayToConsole(String text) {
        System.out.println(text);
    }

    public void displayToConsoleInLine(String text) {
        System.out.print(text);
    }

    public void displayVets(List<Vet> listVet) {
        System.out.println("\nWe have the following vets:");
        for (Vet vet : listVet) {
            System.out.println(vet.getFirstName() + " " + vet.getLastName() + " " + vet.getBadgeId());
        }
    }

    public void displayPetOwners(List<PetOwner> petOwnerList) {
        System.out.println("\nWe have the following pet owners:");
        petOwnerList.forEach(this::displayPetOwner);
    }

    private void displayPetOwner(PetOwner petOwner) {
        System.out.println(petOwner.getFirstName() + " " + petOwner.getLastName() + " " + petOwner.getPhoneNumber());
    }

    public void displayAllPets(List<Pet> allPets) {
        System.out.println("\nWe have the following pets:");
        for (Pet pet : allPets) {
            System.out.println("Pet name: " + pet.getPetName() +
                              ", category: " + pet.getPetCategory() +
                              ", collarId: " + pet.getCollarId() +
                              ", owner's name: " + pet.getPetOwner().getFirstName() + " " + pet.getPetOwner().getLastName());
        }
    }

    public void displayPetOptions(List<Pet> petList) {
        System.out.println("Please select the number of your pet:");
        for (int index = 0; index < petList.size(); index++) {
            System.out.println(index + 1 + " " + petList.get(index).getPetName());
        }
    }

    public void displayPetDetails(Pet pet) {
        System.out.println("Please select the number you want to change:");
        System.out.println("1 - name: " + pet.getPetName() + "\n2 - category: " + pet.getPetCategory() + "\n3 - owner's: "
                + pet.getPetOwner().getFirstName() + " " + pet.getPetOwner().getLastName());

    }

    public void displayVetOptions(List<Vet> vetList) {
        System.out.println("Please select the number of your vet:");
        for (int index = 0; index < vetList.size(); index++) {
            System.out.println(index + 1 + " - Dr. " + vetList.get(index).getFirstName() + " " + vetList.get(index).getLastName());
        }
    }
}

