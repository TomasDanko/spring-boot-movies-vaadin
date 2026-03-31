package sk.tomasdanko.spring_danko.controller.rest;

import org.springframework.web.bind.annotation.*;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;
import sk.tomasdanko.spring_danko.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    public MovieDto getMovieById(@PathVariable("id") Long movieId) {

        return movieService.getMovieById(movieId);
    }

    @GetMapping("/movies")
    List<MovieDto> getMovies(@RequestParam(required = false)String name) {
        if (name != null && name.isEmpty()) {
            return movieService.findByName(name);
        }else{
            return movieService.getAllMovies();
        }
    }

    @PostMapping("/movies")
    public MovieDto addMovie(@RequestBody MovieDto movieDto){
        return movieService.addMovie(movieDto);
    }

    @PutMapping("/movies/{id}")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto, @PathVariable("id") Long movieId){
        return movieService.updateMovie(movieDto, movieId);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable ("id") Long id){
        movieService.deleteMovie(id);
    }
}
