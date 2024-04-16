package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;
import de.storyteller.api.service.genre.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling genre related requests
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    /**
     * Get all genres
     * @return List of all genres
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable String id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    /**
     * Add a new genre if the user is authenticated and has the authority to add a genre
     * @param genre Genre to be added
     * @return Genre added
     */
    @PreAuthorize("isAuthenticated() && hasAuthority('GENRE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<GenreDTO> addGenre(@Valid @RequestBody AddGenreRequest genre) {
        return ResponseEntity.ok(genreService.createGenre(genre));
    }

    /**
     * Update a genre if the user is authenticated and has the authority to update a genre
     * @param id If of the Genre to be updated
     * @return Genre updated
     */
    @PreAuthorize("isAuthenticated() && hasAuthority('GENRE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable String id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }

}
