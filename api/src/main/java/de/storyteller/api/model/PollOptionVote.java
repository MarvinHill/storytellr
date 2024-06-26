package de.storyteller.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class for a poll option vote. Needed to keep track of which user voted for which poll option
 */
@Document(collection = "pollvotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollOptionVote {
  @Id
  private String id;
  private String pollId;
  private String pollOptionId;
  private String userId;
}
