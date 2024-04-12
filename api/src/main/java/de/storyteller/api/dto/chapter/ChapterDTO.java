package de.storyteller.api.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private String id;
    private String bookId;
    private String chapterTitle;
    private String content;
    private LocalDateTime lastModified;
}
