package de.storyteller.api.service.genre;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;
import de.storyteller.api.model.Genre;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    @Transactional
    GenreDTO createGenre(AddGenreRequest genre);
    @Transactional
    void deleteGenre(String id);
    @Transactional
    List<GenreDTO> getAllGenres();
}
