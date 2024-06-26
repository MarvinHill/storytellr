package de.storyteller.api.v1.dto.poll;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating a poll.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePollRequest {
  String owner;
  String question;
  List<PollOptionDTO> options;
}
