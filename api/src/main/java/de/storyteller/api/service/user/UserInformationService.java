package de.storyteller.api.service.user;

import java.net.ConnectException;

/**
 * Service for getting user information.
 */
public interface UserInformationService {
  /**
   * Get the name of a user by their id.
   * @param id User id.
   * @return User name.
   * @throws ConnectException if the there is a connection error.
   */
  String idToName(String id) throws ConnectException;
}
