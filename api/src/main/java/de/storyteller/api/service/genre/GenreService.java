package de.storyteller.api.service.genre;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;


import java.util.List;


public interface GenreService {

    GenreDTO createGenre(AddGenreRequest genre);

    void deleteGenre(String id);

    List<GenreDTO> getAllGenres();
}
