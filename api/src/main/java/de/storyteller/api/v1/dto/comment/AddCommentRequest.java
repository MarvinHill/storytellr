package de.storyteller.api.v1.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    @NotBlank(message = "Content is required")
    private String content;
    @NotBlank(message = "Chapter is required")
    private String chapterId;
    @NotBlank(message = "Block is required")
    private String blockId;
}
