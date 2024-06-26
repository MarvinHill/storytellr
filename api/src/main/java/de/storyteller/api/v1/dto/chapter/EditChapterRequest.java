package de.storyteller.api.v1.dto.chapter;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Request object to edit a chapter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditChapterRequest {
    @NotNull(message = "Chapter ID is required")
    private String id;
    @NotNull(message = "Book ID is required")
    private String bookId;
    @NotNull(message = "Chapter title is required")
    private String chapterTitle;
    private String content;
    @NotNull(message = "Last modified is required")
    private LocalDateTime lastModified;

    private boolean published;
}
