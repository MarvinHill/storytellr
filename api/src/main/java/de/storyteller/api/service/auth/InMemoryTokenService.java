package de.storyteller.api.service.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.util.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Base64;
import javax.print.attribute.standard.Media;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class InMemoryTokenService implements TokenService {

  private String token;

  @Value("${backend.client.username}")
  String KEYLCOAK_CLIENT_USERNAME;

  @Value("${backend.client.secret}")
  String KEYLCOAK_CLIENT_SECRET;

  @Value("${keycloak.issuer.uri}")
  String KEYCLOAK_ISSUER_URL;


  @Override
  public void setToken(String token) {
    synchronized (this){
      this.token = token;
    }
  }

  @Override
  public String getToken() {
    synchronized (this){
      this.renewToken();
      return token;
    }
  }

  @Override
  public void renewToken() {
    synchronized (this){
      try {
      String encoded = Base64.getEncoder().encodeToString((KEYLCOAK_CLIENT_USERNAME + ":" + KEYLCOAK_CLIENT_SECRET).getBytes());

      RestClient client = RestClient.create();
      log.debug("URL: " + KEYCLOAK_ISSUER_URL + "/protocol/openid-connect/token");
      ResponseEntity<String> response = client.post()
          .uri(KEYCLOAK_ISSUER_URL + "/protocol/openid-connect/token")
          .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
          .header(HttpHeaders.AUTHORIZATION, "Basic " + encoded)
          .body("grant_type=client_credentials")
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .toEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        String newToken = json.get("access_token").asText();
        this.setToken(newToken);
        log.info("New access token: " + newToken);
      }
      catch (Exception e) {
        log.error("Failed to renew keycloak token", e);
      }
    }

  }


}
