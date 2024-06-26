package de.storyteller.api.v1.auth;

import java.text.ParseException;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Service for getting user information.
 */
@Service
public class AuthUtils {
  public Map<String, Object> getClaims() throws ParseException {
    return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims();
  }
}
