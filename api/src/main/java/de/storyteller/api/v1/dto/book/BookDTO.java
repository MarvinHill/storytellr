package de.storyteller.api.v1.dto.book;

import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private UUID id;
    private String title;
    private UUID genreId;
    private UUID author;
    private String description;
    private String catchphrase;
    private List<String> tags;
    private CoverUriDTO cover;
}
