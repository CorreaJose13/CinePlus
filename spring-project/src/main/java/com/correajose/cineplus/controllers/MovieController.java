package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.movie.MovieCreateDTO;
import com.correajose.cineplus.dtos.movie.MovieUpdateDTO;
import com.correajose.cineplus.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/movie")
public class MovieController implements ICrudController<MovieCreateDTO, MovieUpdateDTO>{

    @Autowired
    private MovieService movieService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, movieService.list(), "List of movies");
    }
    
    @GetMapping("/find/id/{id}")
    public ResponseEntity<?> find(int id) {
        return buildOkResponse(HttpStatus.OK, movieService.find(id), "Movie with id: " + id + " found");
    }
    
    @GetMapping("/find/title/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable String title) {
        return buildOkResponse(HttpStatus.OK, movieService.findByTitle(title), "Movie with title: " + title + " found");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid MovieCreateDTO body) {
        return buildOkResponse(HttpStatus.CREATED, movieService.create(body), "Movie created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(int id, @Valid MovieUpdateDTO body) {
        return buildOkResponse(HttpStatus.OK, movieService.update(id,body), "Movie updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(int id) {
        return buildOkResponse(HttpStatus.NO_CONTENT, movieService.delete(id), "Movie deleted successfully");
    }

}
