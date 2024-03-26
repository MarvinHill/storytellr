package de.storyteller.api.controller.v1;

import de.storyteller.api.dto.book.AddBookRequest;
import de.storyteller.api.dto.chapter.AddChapterRequest;
import de.storyteller.api.dto.chapter.ChapterDTO;
import de.storyteller.api.service.chapter.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController {
    private final ChapterService chapterService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllChapters(){
        return ResponseEntity.ok(chapterService.getAllChapters());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addChapter(@Valid @RequestBody AddChapterRequest chapter){
        return ResponseEntity.ok(chapterService.createChapter(chapter));
    }
}
