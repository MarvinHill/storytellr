package de.storyteller.api.v1.dto.book;

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

/**
 * Request object to add a book
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotNull(message = "A genre must be set")
    private String genreId;
    @NotBlank(message = "Author should not be blank")
    private String author;
    @NotBlank(message = "Description should not be blank")
    private String description;
    private String catchphrase;
    private List<String> chapterIds;
    private List<String> tags;
    @NotNull(message = "Public must be set")
    private boolean isPublic;
    @NotNull(message = "Adult content must be set")
    private boolean adultContent;
    @NotNull(message = "Comments deactivated must be set")
    private boolean commentsDeactivated;
    @NotNull(message = "Finished must be set")
    private boolean finished;

}
