package de.storyteller.api.v1.mapper;

import de.storyteller.api.model.Comment;
import de.storyteller.api.service.UserService;
import de.storyteller.api.service.auth.KeycloakService;
import de.storyteller.api.v1.dto.comment.AddCommentRequest;
import de.storyteller.api.v1.dto.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mapper for comments
 */
@Component
public class CommentMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    @Qualifier("CachedKeycloakService")
    protected KeycloakService keycloakService;
    /**
     * Maps an AddCommentRequest to a Comment
     * @param addCommentRequest the AddCommentRequest to map
     * @return the mapped Comment
     */
    public Comment toComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        comment.setContent(addCommentRequest.getContent());
        comment.setAuthorId(userService.getUserId());
        comment.setAuthorName(keycloakService.getUsername(comment.getAuthorId()));
        comment.setChapterId(addCommentRequest.getChapterId());
        comment.setBlockId(addCommentRequest.getBlockId());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

    /**
     * Maps a Comment to a CommentDTO
     * @param comment the Comment to map
     * @return the mapped CommentDTO
     */
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setAuthorId(comment.getAuthorId());
        commentDTO.setAuthorName(comment.getAuthorName());
        commentDTO.setChapterId(comment.getChapterId());
        commentDTO.setBlockId(comment.getBlockId());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        return commentDTO;
    }
}
