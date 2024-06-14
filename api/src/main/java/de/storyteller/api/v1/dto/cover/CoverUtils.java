package de.storyteller.api.v1.dto.cover;

import org.springframework.stereotype.Component;

/**
 * Utility class for covers
 */
@Component
public class CoverUtils {

  /**
   * Get the default cover
   * @return the default cover
   */
  public CoverUriDTO defaultCover(){
    return new CoverUriDTO(
        "assets/images/cover-original.png",
        "assets/images/cover-sm.png",
        "assets/images/cover-lg.png");
  }
}
