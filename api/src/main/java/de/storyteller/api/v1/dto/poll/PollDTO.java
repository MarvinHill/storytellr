package de.storyteller.api.v1.dto.poll;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PollDTO {
  String id;
  String owner;
  String bookId;
  String question;
  List<PollOptionDTO> options;
}
