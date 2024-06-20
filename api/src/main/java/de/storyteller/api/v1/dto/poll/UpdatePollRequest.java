package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePollRequest {
  @NotBlank(message = "Poll id is required")
  String pollId;
  @NotBlank(message = "Content is required")
  String question;

}
