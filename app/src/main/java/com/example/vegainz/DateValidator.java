package com.example.vegainz;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface DateValidator {
    boolean isValid(String dateStr);
}

class DateValidatorUsingDateTimeFormatter implements DateValidator {
    // Purpose of this class is to check that given string is in correct date format
    private DateTimeFormatter dateFormatter;

    public DateValidatorUsingDateTimeFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        // This method takes in date as String and checks that it is in wanted format
        try {
            this.dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}