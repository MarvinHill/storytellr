package de.storyteller.api.service.user;

import de.storyteller.api.service.auth.TokenService;
import java.net.ConnectException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Service for getting user information.
 */
@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationServiceImpl implements  UserInformationService{

  RestClient keycloakServiceAccount;
  TokenService tokenService;

  /**
   * Get the name of a user by their id.
   * @param id User id.
   * @return User name.
   * @throws ConnectException if the there is a connection error.
   */
  @Override
  public String idToName(String id) throws ConnectException {
    return "";
  }


}
