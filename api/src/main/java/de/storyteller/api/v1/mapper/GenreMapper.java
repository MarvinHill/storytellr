package de.storyteller.api.v1.mapper;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import de.storyteller.api.v1.dto.genre.GenreDTO;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.GenreRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class GenreMapper {
    @Autowired
    protected GenreRepository genreRepository;

    private final String ID_PLACEHOLDER = "550e8400-e29b-41d4-a716-446655440000";
    public abstract Genre toGenre(GenreDTO genreDTO);
    public abstract GenreDTO toGenreDTO(Genre genre);
    @Mapping(target = "id", constant = ID_PLACEHOLDER)
    public abstract Genre toGenre(AddGenreRequest addGenreRequest);

}
