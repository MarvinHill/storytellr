package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePollRequest {
  @NotBlank(message = "Book id is required")
  String bookId;
}
