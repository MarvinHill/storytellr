package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for a poll.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO {
  @NotBlank
  String id;
  @NotBlank
  String owner;
  @NotBlank
  String question;
  @NotBlank
  List<PollOptionDTO> options;
}
