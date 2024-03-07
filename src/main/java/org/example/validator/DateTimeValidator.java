package org.example.validator;

import org.example.exception.InvalidDateTimeExeption;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeValidator {

    public boolean isWeekend(LocalDateTime appointmentDateTime) throws InvalidDateTimeExeption {
        try {
            LocalDate appointmentDate = appointmentDateTime.toLocalDate();
            if (appointmentDate.getDayOfWeek() == DayOfWeek.SATURDAY || appointmentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new InvalidDateTimeExeption();
        }
    }

    public boolean isBeforeLocalDateTime(LocalDateTime appointmentDateTime) throws InvalidDateTimeExeption {
        try {
            return appointmentDateTime.isBefore(LocalDateTime.now());
        } catch (Exception e) {
            throw new InvalidDateTimeExeption();
        }
    }

}
