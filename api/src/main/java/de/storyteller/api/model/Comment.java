package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String id;
    private String content;
    private String author;
    private String chapterId;
    private String blockId;
    private LocalDateTime createdAt;
}
