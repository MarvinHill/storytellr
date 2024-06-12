package de.storyteller.api.v1.dto.cover;

import org.springframework.stereotype.Component;

@Component
public class CoverUtils {
  public CoverUriDTO defaultCover(){
    return new CoverUriDTO(
        "assets/images/cover-original.png",
        "assets/images/cover-sm.png",
        "assets/images/cover-lg.png");
  }
}
