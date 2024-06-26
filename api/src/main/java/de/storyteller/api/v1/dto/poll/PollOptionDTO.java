package de.storyteller.api.v1.dto.poll;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO for a poll option.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollOptionDTO {
  @NotBlank
  private String id;
  @NotBlank
  private String content;
  @NotBlank
  private long voteCount;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PollOptionDTO that = (PollOptionDTO) o;
    return Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, voteCount);
  }
}
