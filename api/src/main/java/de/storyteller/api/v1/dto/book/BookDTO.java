package de.storyteller.api.v1.dto.book;

import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String authorName;
    private String description;
    private String catchphrase;
    private List<String> chapterIds;
    private List<String> tags;
    private CoverUriDTO cover;
    private boolean isPublic;
    private boolean adultContent;
    private boolean commentsDeactivated;
    private boolean finished;
}
