package de.storyteller.api.v1.dto.cover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverUriDTO {
  private String originalImageUri;
  private String smImageUri;
  private String lgImageUri;
}
