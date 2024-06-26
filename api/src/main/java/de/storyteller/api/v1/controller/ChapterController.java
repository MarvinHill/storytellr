package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
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
     * Get all chapters of a book
     * @param bookId Id of the book
     * @return List of all chapters of the book
     */
    @PreAuthorize("isAuthenticated() && (@sAuthService.userOwnsBook(#bookId) || @sAuthService.isAdmin())")
    @GetMapping("/all/{bookId}")
    public ResponseEntity<?> getChaptersByBookId(@PathVariable String bookId){
        return ResponseEntity.ok(chapterService.getChaptersByBookId(bookId));
    }

    /**
     * Get all published chapters of a book
     * @param bookId Id of the book
     * @return List of all published chapters of the book
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{bookId}/published")
    public ResponseEntity<?> getPublishedChaptersByBookId(@PathVariable String bookId){
        return ResponseEntity.ok(chapterService.getPublishedChaptersByBookId(bookId));
    }

    /**
     * Add a new chapter if the user is authenticated and owns the book
     * @param chapter Chapter to be added
     * @return Chapter added
     */
    @PreAuthorize("isAuthenticated() && (@sAuthService.userOwnsBook(#chapter.bookId) || @sAuthService.isAdmin())")
    @PostMapping("/add")
    public ResponseEntity<?> addChapter(@Valid @RequestBody AddChapterRequest chapter){
        return ResponseEntity.ok(chapterService.createChapter(chapter));
    }

    /**
     * Update a chapter if the user is authenticated and owns the book
     * @param chapter Chapter to be updated
     * @return Chapter updated
     */
    @PreAuthorize("isAuthenticated() && (@sAuthService.userOwnsBook(#chapter.bookId) || @sAuthService.isAdmin())")
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



    /**
     * Get the first n published chapters of a book
     * @param bookId Id of the book
     * @param count Number of chapters to get
     * @return List of the first n published chapters of the book
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{bookId}/published/{count}")
    public ResponseEntity<?> getFirstNPublishedChapters(@PathVariable String bookId, @PathVariable int count){
        return ResponseEntity.ok(chapterService.getFirstNPublishedChapters(bookId, count));
    }
}
