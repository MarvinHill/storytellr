package de.storyteller.api.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class for a poll
 */
@Document(collection = "polls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll {
  @Id
  private String id;
  private String owner;
  private String question;
  private List<PollOption> options = new ArrayList<>();
}
