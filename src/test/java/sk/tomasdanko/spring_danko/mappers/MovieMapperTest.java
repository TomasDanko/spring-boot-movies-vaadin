package sk.tomasdanko.spring_danko.mappers;

import org.junit.jupiter.api.Test;
import sk.tomasdanko.spring_danko.model.Director;
import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {

    @Test
    public void movieTodMovieDtoIsSame() {
        Movie movie = new Movie();
        movie.setName("Dracula");
        movie.setId(1L);
        Set<String> set = new HashSet<>();
        set.add("Danko");

        MovieDto movieDto = MovieMapper.INSTANCE.movieToMovieDto(movie);
        assertEquals(movie.getId(), movieDto.getId());
        assertEquals(movie.getName(), movieDto.getName());
        assertArrayEquals(movie.getDirectors().toArray(),
                movieDto.getDirectorsOfTheMovie().toArray());
    }


}