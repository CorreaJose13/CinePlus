package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.movie.MovieFunctionRequestDTO;
import com.correajose.cineplus.dtos.movie.MovieFunctionResponseDTO;
import com.correajose.cineplus.exceptions.movie.MovieFunctionAlreadyExistsException;
import com.correajose.cineplus.models.movie.Movie;
import com.correajose.cineplus.models.movie.MovieFunction;
import com.correajose.cineplus.models.room.Room;
import com.correajose.cineplus.repositories.MovieFunctionRepository;
import com.correajose.cineplus.repositories.MovieRepository;
import com.correajose.cineplus.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.correajose.cineplus.utils.ErrorMessages.notFound;

@Service
public class MovieFunctionService implements ICrudService<MovieFunctionRequestDTO,MovieFunctionRequestDTO, MovieFunctionResponseDTO>{

    @Autowired
    private MovieFunctionRepository movieFunctionRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public MovieFunction find(int id) {
        return movieFunctionRepository.findById(id).orElseThrow(() -> notFound("movie function","id",id));
    }

    public MovieFunctionResponseDTO findById(int id) {
        MovieFunction movieFunction = find(id);
        return modelMapper.map(movieFunction, MovieFunctionResponseDTO.class);
    }

    @Transactional
    public MovieFunctionResponseDTO update(int id, MovieFunctionRequestDTO body) {
        MovieFunction movieFunction = find(id);
        validateMovieFunction(body);
        movieFunction.setFunctionDate(body.getFunctionDate());
        movieFunction.setFunctionTime(body.getFunctionTime());
        return modelMapper.map(movieFunctionRepository.save(movieFunction),MovieFunctionResponseDTO.class);
    }

    public MovieFunctionResponseDTO delete(int id) {
        MovieFunction movieFunction = find(id);
        movieFunctionRepository.delete(movieFunction);
        return modelMapper.map(movieFunction, MovieFunctionResponseDTO.class);
    }

    public List<MovieFunctionResponseDTO> list() {
        List<MovieFunction> movieFunctions = movieFunctionRepository.findAll();
        return movieFunctions.stream().map(u -> modelMapper.map(u, MovieFunctionResponseDTO.class)).toList();
    }

    public MovieFunctionResponseDTO create(MovieFunctionRequestDTO body) {
        Movie movie = movieRepository.findById(body.getMovieId())
                .orElseThrow(() -> notFound("movie", "id", body.getMovieId()));
        Room room = roomRepository.findById(body.getRoomId())
                .orElseThrow(() -> notFound("movie", "id", body.getRoomId()));
        validateMovieFunction(body);
        MovieFunction movieFunction = new MovieFunction();
        movieFunction.setMovie(movie);
        movieFunction.setRoom(room);
        movieFunction.setFunctionDate(body.getFunctionDate());
        movieFunction.setFunctionTime(body.getFunctionTime());
        return modelMapper.map(movieFunctionRepository.save(movieFunction), MovieFunctionResponseDTO.class);
    }

    private void validateMovieFunction(MovieFunctionRequestDTO body){
        boolean functionExists = movieFunctionRepository.existsByFunctionDateAndFunctionTime(body.getFunctionDate(), body.getFunctionTime());
        if (functionExists) {
            throw new MovieFunctionAlreadyExistsException(body.getFunctionDate(), body.getFunctionTime());
        }
    }
}
