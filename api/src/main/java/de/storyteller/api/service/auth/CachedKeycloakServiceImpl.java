package de.storyteller.api.service.auth;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class is an implementation of the KeycloakService. It caches the author of a given id for a certain amount of time.
 */
@Service("CachedKeycloakService")
@RequiredArgsConstructor
@Slf4j
public class CachedKeycloakServiceImpl implements  KeycloakService{
  private final KeycloakServiceImpl keycloakService;

  private final int cacheTimeMin = 15;

  private Map<String, AuthorTimeRecord> cache = new HashMap();


  /**
   * This method returns the username of a user with the given id. It first checks if the username is already in the cache.
   * @param id the id of the user
   * @return the username of the user
   */
  @Override
  public String getUsername(String id) {
    if(cache.containsKey(id) && cache.get(id).timestamp.plusMinutes(cacheTimeMin).isAfter(LocalDateTime.now())){
      return cache.get(id).author;
    }
    else if(cache.containsKey(id) && cache.get(id).timestamp.plusMinutes(cacheTimeMin).isBefore(LocalDateTime.now())) {
      cache.remove(id);
    }
    String username = keycloakService.getUsername(id);
    cache.put(id, new AuthorTimeRecord(LocalDateTime.now(), username));
    return username;
  }
  @Data
  @AllArgsConstructor
  private class AuthorTimeRecord {
    LocalDateTime timestamp;
    String author;
  }
}
