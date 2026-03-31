package sk.tomasdanko.spring_danko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.tomasdanko.spring_danko.repository.MovieRepository;

@Controller
public class MovieController {

    MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";

    }
}
