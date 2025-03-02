package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.movie.MovieFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MovieFunctionRepository extends JpaRepository<MovieFunction, Integer> {
    boolean existsByFunctionDateAndFunctionTime(LocalDate functionDate, LocalTime functionTime);
}
