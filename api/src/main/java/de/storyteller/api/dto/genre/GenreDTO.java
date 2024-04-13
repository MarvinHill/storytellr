package de.storyteller.api.dto.genre;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for a genre
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private String id;
    private String name;

}
