package de.storyteller.api.dto.book;

import de.storyteller.api.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotBlank(message = "Title should not be blank")
    private String title;
    @NotNull(message = "A genre must be set")
    private Genre genre;
    @NotBlank(message = "Author should not be blank")
    private String author;
    @NotBlank(message = "Description should not be blank")
    private String description;
    private String catchphrase;
    private Set<Long> chapterIds;
    private String cover;
}
