package de.storyteller.api.service.auth;

/**
 * Service to manage the token for the API
 */
public interface TokenService {
  /**
   * Set the token for the API
   *
   * @param token the token to set
   */
  void setToken(String token);

  /**
   * Get the token for the API
   *
   * @return the token
   */
  String getToken();

  /**
   * Renew the current token
   */
  void renewToken();

}


