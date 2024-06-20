package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeletePollRequest {
  @NotBlank(message = "Poll id is required")
  String pollId;
}
