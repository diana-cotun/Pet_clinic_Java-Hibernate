package org.example.service;

import org.example.entities.Holiday;
import org.example.entities.Vet;
import org.example.exception.InvalidDateTimeExeption;
import org.example.exception.NoVetFoundForBadgeIdException;
import org.example.repositories.HolidayRepository;
import org.example.repositories.VetRepository;
import org.example.utils.Utils;

import java.time.LocalDate;
import java.util.Optional;

public class HolidayService {

    private final InputOutputService inputOutputService = new InputOutputService();
    private final HolidayRepository holidayRepository = new HolidayRepository();
    private final VetRepository vetRepository = new VetRepository();

    public void addNewHoliday() {
        try {
            Vet selectedVed = fetchVet();
            LocalDate holidayDate = createHolidayDate();
            Holiday holiday = createHoliday(selectedVed, holidayDate);
            holidayRepository.save(holiday);
            inputOutputService.displayToConsole("Vet: " + selectedVed.getLastName() + " " + selectedVed.getFirstName()
                    + ", successfully added new holiday!");
        } catch (NoVetFoundForBadgeIdException e) {
            inputOutputService.displayToConsole("Sorry the badge id doesn't have any vet!");
        } catch (InvalidDateTimeExeption e) {
            inputOutputService.displayToConsole("Sorry the date are not valid!");
        }

    }

    private Holiday createHoliday(Vet vet, LocalDate date) {
        Holiday holiday = new Holiday();
        holiday.setVet(vet);
        holiday.setDate(date);
        return holiday;
    }

    private LocalDate createHolidayDate() throws InvalidDateTimeExeption {
        inputOutputService.displayToConsole("Please enter the holiday date");
        String holidayDate = inputOutputService.getUserInput();
        return Utils.convertDateFromString(holidayDate);
    }

    private Vet fetchVet() throws NoVetFoundForBadgeIdException {
        inputOutputService.displayToConsole("Please enter the vet badge id: ");
        Integer badgeIdSelectedVet = Integer.valueOf(inputOutputService.getUserInput());
        Optional<Vet> optionalVet = vetRepository.findVetByBadgeId(badgeIdSelectedVet);
        if (optionalVet.isEmpty()) {
            throw new NoVetFoundForBadgeIdException();
        }
        return optionalVet.get();
    }


}




