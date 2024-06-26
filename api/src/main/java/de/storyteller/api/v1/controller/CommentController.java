package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import de.storyteller.api.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling comment related requests
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    /**
     * Get all comments of a chapter
     * @param chapterId Id of the chapter
     * @return List of all comments of the chapter
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{chapterId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByChapterId(@PathVariable String chapterId) {
        return ResponseEntity.ok(commentService.getCommentsByChapter(chapterId));
    }

    /**
     * Get all comments of a block
     * @param chapterId Id of the chapter
     * @param blockId Id of the block
     * @return List of all comments of the block
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/{chapterId}/{blockId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByBlockId(@PathVariable String chapterId, @PathVariable String blockId) {
        return ResponseEntity.ok(commentService.getCommentsByBlockId(chapterId, blockId));
    }

    /**
     * Add a new comment if the user is authenticated
     * @param addCommentRequest Comment to be added
     * @return Comment added
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentRequest addCommentRequest) {
        return ResponseEntity.ok(commentService.addComment(addCommentRequest));
    }
}
