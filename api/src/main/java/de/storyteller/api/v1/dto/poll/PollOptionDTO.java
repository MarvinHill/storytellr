package de.storyteller.api.v1.dto.poll;

import lombok.Data;

@Data
public class PollOptionDTO {
  String pollOptionId;
  String pollId;
  String content;
  long voteCount;
}
