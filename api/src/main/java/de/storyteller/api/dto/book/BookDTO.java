package de.storyteller.api.dto.book;

import de.storyteller.api.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private UUID id;
    private String title;
    private UUID genreId;
    private String author;
    private String description;
    private String catchphrase;
    private String cover;
}
