package org.example.service;

import org.example.entities.Holiday;
import org.example.entities.Vet;
import org.example.exception.InvalidDateTimeExeption;
import org.example.exception.NoVetFoundForBadgeIdException;
import org.example.repositories.VetRepository;
import org.example.utils.Utils;

import java.util.List;
import java.util.Optional;

public class VetService {

    private final InputOutputService inputOutputService = new InputOutputService();
    private final VetRepository vetRepository = new VetRepository();

//    punem prima data metodele publice

    public void addNewVet() {
        Vet vet = createVet();
        vetRepository.save(vet);
        inputOutputService.displayToConsole("New vet has been added successfully!");
    }




    public void displayAllVet() {
        List<Vet> listVet = vetRepository.getAllVets();
        inputOutputService.displayVets(listVet);
    }

    private Vet createVet() {
        Vet vet = new Vet();
        inputOutputService.displayToConsole("What is new vet's first name?");
        vet.setFirstName(inputOutputService.getUserInput());
        inputOutputService.displayToConsole("What is new vet's last name?");
        vet.setLastName(inputOutputService.getUserInput());
        vet.setBadgeId(Utils.generateBadgeId());
        return vet;
    }
}
