package de.storyteller.api.service.genre;

import de.storyteller.api.v1.dto.genre.AddGenreRequest;
import de.storyteller.api.v1.dto.genre.GenreDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service for genres
 */
public interface GenreService {

    /**
     * Create a genre
     * @param genre the genre to create
     * @return the created genre
     */
    GenreDTO createGenre(AddGenreRequest genre);

    /**
     * Delete a genre by its id
     * @param id the id of the genre
     */
    void deleteGenre(String id);

    /**
     * Get all genres
     * @return a list of all genres
     */
    List<GenreDTO> getAllGenres();
}
