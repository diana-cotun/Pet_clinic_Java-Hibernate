package org.example.validator;

import org.example.repositories.PetRepository;

public class PetValidator {

    private final PetRepository petRepository = new PetRepository();

    public boolean isCollarIdValid(String collarId) {
         return petRepository.findByCollarId(collarId).isEmpty();
    }
}
