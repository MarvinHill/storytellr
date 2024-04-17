package de.storyteller.api.v1.mapper;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import de.storyteller.api.v1.dto.genre.GenreDTO;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper for genres
 */
@Component
@Mapper(componentModel = "spring")
public abstract class GenreMapper {

    @Autowired
    protected GenreRepository genreRepository;

    /**
     * Maps a genreDTO to a genre
     * @param genreDTO the genre to map
     * @return the mapped genre
     */
    public abstract Genre toGenre(GenreDTO genreDTO);

    /**
     * Maps a genre to a genreDTO
     * @param genre the genre to map
     * @return the mapped genreDTO
     */
    public abstract GenreDTO toGenreDTO(Genre genre);

    /**
     * Maps a AddGenreRequest to a genre
     * @param addGenreRequest the genre to map
     * @return the mapped genre
     */
    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenre(AddGenreRequest addGenreRequest);

}
