package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.chapter.AddChapterRequest;
import de.storyteller.api.v1.dto.chapter.EditChapterRequest;
import de.storyteller.api.service.chapter.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<?> getAllChapters(){
        return ResponseEntity.ok(chapterService.getAllChapters());
    }

    @PreAuthorize("isAuthenticated() && @sAuthService.userOwnsBook(#chapter.bookId)")
    @PostMapping("/add")
    public ResponseEntity<?> addChapter(@Valid @RequestBody AddChapterRequest chapter){
        return ResponseEntity.ok(chapterService.createChapter(chapter));
    }
    @PreAuthorize("isAuthenticated() && @sAuthService.userOwnsBook(#chapter.bookId)")
    @PutMapping("/update")
    public ResponseEntity<?> updateChapter(@Valid @RequestBody EditChapterRequest chapter){
        return ResponseEntity.ok(chapterService.updateChapter(chapter));
    }
}
