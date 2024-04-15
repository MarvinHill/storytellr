package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.EditChapterRequest;
import de.storyteller.api.service.chapter.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling chapter related requests
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController {
    private final ChapterService chapterService;

    /**
     * Get all chapters
     * @return List of all chapters
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<?> getAllChapters(){
        return ResponseEntity.ok(chapterService.getAllChapters());
    }

    /**
     * Add a new chapter if the user is authenticated and owns the book
     * @param chapter Chapter to be added
     * @return Chapter added
     */
    @PreAuthorize("isAuthenticated() && @sAuthService.userOwnsBook(#chapter.bookId)")
    @PostMapping("/add")
    public ResponseEntity<?> addChapter(@Valid @RequestBody AddChapterRequest chapter){
        return ResponseEntity.ok(chapterService.createChapter(chapter));
    }

    /**
     * Update a chapter if the user is authenticated and owns the book
     * @param chapter Chapter to be updated
     * @return Chapter updated
     */
    @PreAuthorize("isAuthenticated() && @sAuthService.userOwnsBook(#chapter.bookId)")
    @PutMapping("/update")
    public ResponseEntity<?> updateChapter(@Valid @RequestBody EditChapterRequest chapter){
        return ResponseEntity.ok(chapterService.updateChapter(chapter));
    }

    /**
     * Get a chapter by its id
     * @param id Id of the chapter
     * @return Chapter with the given id
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getChapterById(@PathVariable String id){
        return ResponseEntity.ok(chapterService.getChapterById(id));
    }
}
