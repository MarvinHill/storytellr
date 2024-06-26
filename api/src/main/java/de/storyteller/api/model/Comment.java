package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Model class for a comment
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private String content;
    private String authorId;
    private String authorName;
    private String chapterId;
    private String blockId;
    private LocalDateTime createdAt;
}
