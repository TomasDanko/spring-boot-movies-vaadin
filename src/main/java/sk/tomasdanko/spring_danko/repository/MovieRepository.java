package sk.tomasdanko.spring_danko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByName(String name);
}
