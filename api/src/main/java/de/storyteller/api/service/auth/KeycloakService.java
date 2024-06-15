package de.storyteller.api.service.auth;

/**
 * Provides methods to get information about users from the keycloak api
 */
public interface KeycloakService {

  /**
  * Get the username of a user by its id
  * @param id the id of the user
  * @return the username of the user
  */
  String getUsername(String id);
}
