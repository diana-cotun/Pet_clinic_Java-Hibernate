package org.example.utils;

import org.example.exception.InvalidDateTimeExeption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {

    public static Integer generateBadgeId() {
        Random random = new Random();
        Integer badgeId = random.nextInt(1000,9999);
        return badgeId;
    }

    public static String generateCollarId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index <= 3; index++) {
            char randomChar = (char) random.nextInt(65, 91);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static LocalDateTime convertDateTime(String appointmentDate, String appointmentTime) throws InvalidDateTimeExeption {
        try {
            LocalDate localDate = LocalDate.parse(appointmentDate);
            LocalTime localTime = LocalTime.parse(appointmentTime);
            return LocalDateTime.of(localDate, localTime);
        } catch (Exception e) {
            throw new InvalidDateTimeExeption();
        }

    }

    public static LocalDate convertDateFromString(String holidayDate) throws InvalidDateTimeExeption {
        try {
            return LocalDate.parse(holidayDate);
        } catch (Exception e) {
            throw new InvalidDateTimeExeption();
        }
    }


}

