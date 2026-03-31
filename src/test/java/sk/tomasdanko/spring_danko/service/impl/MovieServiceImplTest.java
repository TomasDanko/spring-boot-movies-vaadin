package sk.tomasdanko.spring_danko.service.impl;

import jakarta.annotation.security.RunAs;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;
import sk.tomasdanko.spring_danko.service.MovieService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceImplTest {

    public static final String JAMES_BOND = "James Bond";
    @Autowired
    MovieService movieService;



    @Test
    @Transactional
    public void getMovieById(){
        Movie movie = new Movie();
        movie.setName(JAMES_BOND);
        movie.setId(1L);
//        Set<String> set = new HashSet<>();
//        set.add("Danko");

        MovieDto movieDto = movieService.getMovieById(1L);

        assertEquals(movie.getId(), movieDto.getId());
        assertEquals(movie.getName(), movieDto.getName());


    }

    @Test
    @Transactional
    public void moviesFromServicesAreSame(){
        Movie movie = new Movie();
        movie.setName(JAMES_BOND);
        movie.setId(1L);

        Movie movie2 = new Movie();
        movie2.setName(JAMES_BOND);
        movie2.setId(2L);

//        List<Movie> movies = new ArrayList<>();
//        movies.add(movie);
//        movies.add(movie2);

        List<MovieDto> movieDtos = movieService.getAllMovies();

        assertEquals(movie.getId(), movieDtos.get(0).getId());
        assertEquals(movie.getName(), movieDtos.get(0).getName());

        assertEquals(movie2.getId(), movieDtos.get(1).getId());
        assertEquals(movie2.getName(), movieDtos.get(1).getName());

    }

    @Test
    @Transactional
    public void movieFromDbIsBond(){
        List<MovieDto> movieDto = movieService.findByName(JAMES_BOND);
        assertEquals(movieDto.get(0).getName(), JAMES_BOND);
        assertEquals(movieDto.get(1).getName(), JAMES_BOND);
    }
}