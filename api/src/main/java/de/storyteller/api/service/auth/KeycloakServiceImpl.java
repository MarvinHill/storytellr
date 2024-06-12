package de.storyteller.api.service.auth;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service("KeycloakService")
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements  KeycloakService{

  @Value("${keycloak.host.url}")
  String KEYCLOAK_ISSUER_URL;

  private final TokenService tokenService;

  @Override
  public String getUsername(String id) {

    String token = tokenService.getToken();

    RestClient client = RestClient.create();
    log.debug("URL: " + KEYCLOAK_ISSUER_URL + "/protocol/openid-connect/token");
    ResponseEntity<String> response = client.get()
        .uri(KEYCLOAK_ISSUER_URL + "/users/" + id)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(String.class);

    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode json = mapper.readTree(response.getBody());
      log.debug("Response: " + json.asText());
      return json.asText();
    }
    catch (JsonProcessingException e){
      log.error(e.getMessage());
    }

    return "";
  }
}
