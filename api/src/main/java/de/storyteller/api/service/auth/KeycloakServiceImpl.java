package de.storyteller.api.service.auth;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * This class is an implementation of the KeycloakService. It provides methods to get information about users from the keycloak api
 */
@Service("KeycloakService")
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements  KeycloakService{

  @Value("${keycloak.admin.api.host.uri}")
  String KEYCLOAK_ADMIN_API_URI;

  private final TokenService tokenService;

  /**
   * This method returns the username of a user with the given id.
   * @param id the id of the user
   * @return the username of the user
   */
  @Override
  public String getUsername(String id) {

    try {

    String token = tokenService.getToken();

    RestClient client = RestClient.create();
    log.debug("URL: " + KEYCLOAK_ADMIN_API_URI + "/users/" + id);
    ResponseEntity<String> response = client.get()
        .uri(KEYCLOAK_ADMIN_API_URI + "/users/" + id)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(String.class);

    ObjectMapper mapper = new ObjectMapper();

      JsonNode json = mapper.readTree(response.getBody());
      return json.get("username").asText();
    }
    catch (Exception e){
      log.error(e.getMessage());
      return "";
    }
  }
}
