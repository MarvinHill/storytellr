package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.genre.AddGenreRequest;
import de.storyteller.api.dto.genre.GenreDTO;
import de.storyteller.api.service.genre.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;
    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping("/add")
    public ResponseEntity<GenreDTO> addGenre(@Valid @RequestBody AddGenreRequest genre) {
        return ResponseEntity.ok(genreService.createGenre(genre));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable String id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }

}
