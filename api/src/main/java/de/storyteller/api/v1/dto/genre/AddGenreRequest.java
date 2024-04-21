package de.storyteller.api.v1.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to add a genre
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGenreRequest {
    @NotBlank(message = "Name is required")
    private String name;
}
