package com.correajose.cineplus.exceptions.movie;

import java.time.LocalDate;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(LocalDate releaseDate , String title) {
        super("A movie with the title '" + title + "' and release date '" + releaseDate + "' already exists");
    }
}
