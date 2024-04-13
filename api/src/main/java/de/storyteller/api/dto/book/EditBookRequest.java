package de.storyteller.api.dto.book;

import de.storyteller.api.model.Chapter;
import de.storyteller.api.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBookRequest {
    @NotNull(message = "An id must be set")
    private String id;
    @NotBlank(message = "Title should not be blank")
    private String title;
    @NotNull(message = "A genre must be set")
    private String genreId;
    @NotBlank(message = "Author should not be blank")
    private String author;
    @NotBlank(message = "Description should not be blank")
    private String description;
    private String catchphrase;
    private List<String> tags;
    private String cover;
}
