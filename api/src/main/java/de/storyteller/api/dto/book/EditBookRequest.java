package de.storyteller.api.dto.book;

import de.storyteller.api.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBookRequest {
    @NotNull(message = "An id must be set")
    private UUID id;
    @NotBlank(message = "Title should not be blank")
    private String title;
    private UUID genreId;
    @NotBlank(message = "Author should not be blank")
    private String author;
    @NotBlank(message = "Description should not be blank")
    private String description;
    private String catchphrase;
    private String cover;
}
