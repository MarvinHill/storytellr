package de.storyteller.api.dto.book;

import de.storyteller.api.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private Genre genre;
    private String author;
    private String description;
    private String catchphrase;
    private Set<Long> chapterIds;
    // Datatype?
    private String cover;
}
