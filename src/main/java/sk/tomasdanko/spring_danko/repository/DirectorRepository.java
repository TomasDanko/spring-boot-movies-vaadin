package sk.tomasdanko.spring_danko.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.tomasdanko.spring_danko.model.Director;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
}
