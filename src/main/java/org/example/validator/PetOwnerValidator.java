package org.example.validator;

import org.example.repositories.PetOwnerRepository;

public class PetOwnerValidator {

    private final PetOwnerRepository petOwnerRepository = new PetOwnerRepository();

    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.matches("^[0-9]{10}")) {
            if (petOwnerRepository.findByPhoneNumber(phoneNumber).isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
