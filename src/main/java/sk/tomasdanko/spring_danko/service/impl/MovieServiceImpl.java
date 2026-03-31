package sk.tomasdanko.spring_danko.service.impl;

import org.springframework.stereotype.Service;
import sk.tomasdanko.spring_danko.mappers.MovieMapper;
import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;
import sk.tomasdanko.spring_danko.repository.MovieRepository;
import sk.tomasdanko.spring_danko.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Movie createAndAddMovie() {

        Movie movie = new Movie();
        movie.setName("James Bond");

        return movieRepository.save(movie);
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return movieMapper.movieToMovieDto(movieRepository.findById(id).orElse(null));
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::movieToMovieDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findByName(String name) {
        return movieRepository.findByName(name).stream().map(movieMapper::movieToMovieDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto) {
        return movieMapper.movieToMovieDto(movieRepository
                .save(movieMapper.movieDtoToMovie(movieDto)));
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, Long movieId) {
        movieRepository.findById(movieId).map(movie -> {
                    movie.setName(movieDto.getName());


                    return movieRepository.save(movie);
                }

        ).orElseGet(() -> {
                    movieDto.setId(movieId);
                    return movieRepository.save(movieMapper.movieDtoToMovie(movieDto));
                }
        );
        return null;


    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);

    }
}
