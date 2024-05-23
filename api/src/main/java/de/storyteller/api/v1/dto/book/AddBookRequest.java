package de.storyteller.api.v1.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request object to add a book
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotBlank(message = "Title should not be blank")
    @Size(max = 100, message = "Catchphrase should not be longer than 100 characters")
    private String title;

    @NotNull(message = "A genre must be set")
    private String genreId;
    @NotBlank(message = "Description should not be blank")
    @Size(max = 500, message = "Catchphrase should not be longer than 500 characters")
    private String description;
    @Size(max = 200, message = "Catchphrase should not be longer than 200 characters")
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
