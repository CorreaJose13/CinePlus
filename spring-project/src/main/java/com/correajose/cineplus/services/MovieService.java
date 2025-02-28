package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.movie.MovieCreateDTO;
import com.correajose.cineplus.dtos.movie.MovieResponseDTO;
import com.correajose.cineplus.dtos.movie.MovieUpdateDTO;
import com.correajose.cineplus.exceptions.movie.MovieAlreadyExistsException;
import com.correajose.cineplus.models.movie.Movie;
import com.correajose.cineplus.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.correajose.cineplus.utils.ErrorMessages.notFound;

@Service
public class MovieService implements ICrudService<MovieCreateDTO,MovieUpdateDTO,MovieResponseDTO>{

    @Autowired
    private MovieRepository movieRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<MovieResponseDTO> list() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(u -> modelMapper.map(u, MovieResponseDTO.class)).toList();
    }

    public Movie find(int id) {
        return movieRepository.findById(id).orElseThrow(() -> notFound("movie","id",id));
    }

    public MovieResponseDTO findById(int id) {
        Movie movie = find(id);
        return modelMapper.map(movie, MovieResponseDTO.class);
    }

    public MovieResponseDTO findByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title).orElseThrow(() -> notFound("movie","title",title));
        return modelMapper.map(movie, MovieResponseDTO.class);
    }

    public MovieResponseDTO create(MovieCreateDTO body) {
        boolean exists = movieRepository.existsByReleaseDateAndTitle(body.getReleaseDate(), body.getTitle());
        if (exists) {
            throw new MovieAlreadyExistsException(body.getReleaseDate(), body.getTitle());
        }
        Movie movie = modelMapper.map(body, Movie.class);
        movie.setEnabled(true);
        return modelMapper.map(movieRepository.save(movie), MovieResponseDTO.class);
    }

    @Transactional
    public MovieResponseDTO update(int id, MovieUpdateDTO body) {
        Movie movie = find(id);
        movie.setTitle(body.getTitle());
        movie.setSynopsis(body.getSynopsis());
        movie.setDuration(body.getDuration());
        movie.setGenre(body.getGenre());
        movie.setEnabled(body.isEnabled());
        return modelMapper.map(movieRepository.save(movie), MovieResponseDTO.class);
    }

    public MovieResponseDTO delete(int id) {
        Movie movie = find(id);
        movieRepository.delete(movie);
        return modelMapper.map(movie, MovieResponseDTO.class);
    }
}
