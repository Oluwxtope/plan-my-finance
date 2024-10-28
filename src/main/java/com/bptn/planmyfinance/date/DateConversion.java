package com.bptn.planmyfinance.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConversion {
    public static LocalDate convertStringToDateDDMMYYYY(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format!");
        }
    }
}
