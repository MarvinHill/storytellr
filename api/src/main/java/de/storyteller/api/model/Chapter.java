package de.storyteller.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for a chapter
 */
@Document(collection = "chapters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    @Id
    private String id;

    private String chapterTitle;

    private String content;

    private LocalDateTime lastModified;

    private String bookId;

    private boolean published;

    List<Comment> comments = new ArrayList<>();


}
