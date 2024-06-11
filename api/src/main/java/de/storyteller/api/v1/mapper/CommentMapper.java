package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Comment;
import de.storyteller.api.service.UserService;
import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CommentMapper {

    @Autowired
    protected UserService userService;
    public Comment toComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        comment.setContent(addCommentRequest.getContent());
        comment.setAuthor(userService.getUserId());
        comment.setChapterId(addCommentRequest.getChapterId());
        comment.setBlockId(addCommentRequest.getBlockId());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setChapterId(comment.getChapterId());
        commentDTO.setBlockId(comment.getBlockId());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        return commentDTO;
    }
}
