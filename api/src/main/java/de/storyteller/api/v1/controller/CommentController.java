package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import de.storyteller.api.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("permitAll()")
    @GetMapping("/{chapterId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByChapterId(@PathVariable String chapterId){
        return ResponseEntity.ok(commentService.getCommentsByChapter(chapterId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentRequest addCommentRequest){
        return ResponseEntity.ok(commentService.addComment(addCommentRequest));
    }
}
