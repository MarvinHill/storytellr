package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePollOptionRequest {
  @NotBlank(message = "Option id is required")
  String pollOptionId;
  @NotBlank(message = "Poll id is required")
  String pollId;
  @NotBlank(message = "Poll option content is required")
  String content;
}
