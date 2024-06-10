package de.storyteller.api.v1.dto.chapter;

import de.storyteller.api.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object for a chapter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private String id;
    private String bookId;
    private String chapterTitle;
    private String content;
    private LocalDateTime lastModified;
    private boolean published;
    List<Comment> comments = new ArrayList<>();
}
