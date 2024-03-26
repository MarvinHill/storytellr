package de.storyteller.api.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private Long id;
    private Long bookId;
    private String chapterTitle;
    private String content;
    private LocalDateTime lastModified;
}
