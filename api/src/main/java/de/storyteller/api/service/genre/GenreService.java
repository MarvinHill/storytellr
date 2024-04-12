package de.storyteller.api.service.genre;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import de.storyteller.api.v1.dto.genre.GenreDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GenreService {
    @Transactional
    GenreDTO createGenre(AddGenreRequest genre);
    @Transactional
    void deleteGenre(String id);
    @Transactional
    List<GenreDTO> getAllGenres();
}
