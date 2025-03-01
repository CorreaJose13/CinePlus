package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.movie.MovieFunctionRequestDTO;
import com.correajose.cineplus.services.MovieFunctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/function")
public class MovieFunctionController implements ICrudController<MovieFunctionRequestDTO,MovieFunctionRequestDTO>{

    @Autowired
    private MovieFunctionService movieFunctionService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<?> find(int id) {
        return buildOkResponse(HttpStatus.OK, movieFunctionService.find(id), "Function with id: " + id + " found");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(int id, MovieFunctionRequestDTO body) {
        return buildOkResponse(HttpStatus.OK, movieFunctionService.update(id,body), "Function updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(int id) {
        return buildOkResponse(HttpStatus.NO_CONTENT, movieFunctionService.delete(id), "Function deleted successfully");
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, movieFunctionService.list(), "List of functions");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid MovieFunctionRequestDTO body) {
        return buildOkResponse(HttpStatus.CREATED, movieFunctionService.create(body), "Function created successfully");
    }
}
