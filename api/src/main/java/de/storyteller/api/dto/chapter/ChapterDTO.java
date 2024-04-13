package de.storyteller.api.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object for a chapter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private String id;
    private String chapterTitle;
    private String content;
    private LocalDateTime lastModified;
}
