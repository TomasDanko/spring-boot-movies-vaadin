package sk.tomasdanko.spring_danko.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sk.tomasdanko.spring_danko.model.Movie;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "directorsOfTheMovie", source = "directors")
    MovieDto movieToMovieDto(Movie movie);

    @Mapping(target = "directors", source = "directorsOfTheMovie")
    Movie movieDtoToMovie(MovieDto movieDto);


}
