package de.storyteller.api.v1.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private String authorId;
    private String authorName;
    private String chapterId;
    private String blockId;
    private LocalDateTime createdAt;
}
