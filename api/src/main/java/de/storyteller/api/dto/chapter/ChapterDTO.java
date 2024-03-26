package de.storyteller.api.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private UUID id;
    private UUID bookId;
    private String chapterTitle;
    private String content;
    private LocalDateTime lastModified;
}
