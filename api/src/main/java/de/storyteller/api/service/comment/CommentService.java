package de.storyteller.api.service.comment;

import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;

import java.util.List;

/**
 * Service for handling comments
 */
public interface CommentService {
    /**
     * Add a comment to a chapter or block
     * @param addCommentRequest the request containing the comment
     * @return the added comment
     */
    CommentDTO addComment(AddCommentRequest addCommentRequest);

    /**
     * Get all comments for a chapter
     * @param chapterId the id of the chapter
     * @return the comments
     */
    List<CommentDTO> getCommentsByChapter(String chapterId);

    /**
     * Get all comments for a block
     * @param chapterId the id of the chapter
     * @param blockId the id of the block
     * @return the comments for a block
     */
    List<CommentDTO> getCommentsByBlockId(String chapterId, String blockId);
}
