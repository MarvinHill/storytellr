package de.storyteller.api.mapper;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;
import de.storyteller.api.model.Genre;
import de.storyteller.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class GenreMapper {

    private final GenreRepository genreRepository;

    public abstract Genre toGenre(GenreDTO genreDTO);
    public abstract GenreDTO toGenreDTO(Genre genre);
    @Mapping(target = "id", ignore = true)
    public abstract Genre toGenre(AddGenreRequest addGenreRequest);

}
