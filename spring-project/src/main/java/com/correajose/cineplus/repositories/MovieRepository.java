package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Optional<Movie> findByTitle(String title);
    boolean existsByReleaseDateAndTitle(LocalDate releaseDate, String title);
}
