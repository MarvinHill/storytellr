package de.storyteller.api.service.comment;

import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;

import java.util.List;

public interface CommentService {
     CommentDTO addComment(AddCommentRequest addCommentRequest);
     List<CommentDTO> getCommentsByChapter(String chapterId);
        List<CommentDTO> getCommentsByBlockId(String chapterId, String blockId);
}
