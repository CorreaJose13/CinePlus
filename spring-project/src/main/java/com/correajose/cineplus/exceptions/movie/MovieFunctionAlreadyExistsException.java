package com.correajose.cineplus.exceptions.movie;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieFunctionAlreadyExistsException extends RuntimeException {
    public MovieFunctionAlreadyExistsException(LocalDate functionDate, LocalTime functionTime) {
        super("A movie function with the date '" + functionDate + "' and time '" + functionTime + "' already exists");
    }
}
