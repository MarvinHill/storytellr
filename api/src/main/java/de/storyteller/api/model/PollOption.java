package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for a poll option
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollOption {
  private String pollOptionId;
  private String pollId;
  private String content;
  private long voteCount;
}
