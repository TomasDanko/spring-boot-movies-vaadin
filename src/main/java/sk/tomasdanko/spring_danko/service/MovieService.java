package sk.tomasdanko.spring_danko.service;

import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;

import java.util.List;

public interface MovieService {

    Movie createAndAddMovie();
    MovieDto getMovieById(Long id);
    List<MovieDto> getAllMovies();
    List<MovieDto> findByName(String name);
    MovieDto addMovie(MovieDto movieDto);

    MovieDto updateMovie(MovieDto movieDto, Long movieId);

    void deleteMovie(Long id);
}
