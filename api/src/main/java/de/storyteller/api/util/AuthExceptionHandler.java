package de.storyteller.api.util;

import de.storyteller.api.service.auth.KeycloakService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthExceptionHandler {

  @Autowired
  @Qualifier("CachedKeycloakService")
  private KeycloakService keycloakService;

  public static String handleAuthNameCall(Function<String, String> func, String author) {
    try {
      return func.apply("test");
    } catch (Exception e) {
      return "";
    }
  }
}
