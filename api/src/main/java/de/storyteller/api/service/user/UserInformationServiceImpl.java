package de.storyteller.api.service.user;

import de.storyteller.api.service.auth.TokenService;
import java.net.ConnectException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationServiceImpl implements  UserInformationService{

  RestClient keycloakServiceAccount;
  TokenService tokenService;

  @Override
  public String idToName(String id) throws ConnectException {
    return "";
  }


}
