package de.storyteller.api.dto.book;

import de.storyteller.api.model.Chapter;
import de.storyteller.api.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Data transfer object for a book
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String title;
    private String genreId;
    private String author;
    private String description;
    private String catchphrase;
    private List<String> chapterIds;
    private List<String> tags;
    private String cover;
    private boolean isPublic;
    private boolean adultContent;
    private boolean commentsDeactivated;
    private boolean finished;
}
