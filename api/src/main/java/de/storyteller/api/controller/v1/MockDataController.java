package de.storyteller.api.controller.v1;

import de.storyteller.api.service.ExampleDataGenerator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mock")
public class MockDataController {
  @Autowired
  ExampleDataGenerator exampleDataGenerator;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/gen")
  public ResponseEntity<HttpStatusCode> generateMockData(){
    exampleDataGenerator.generate();
    return ResponseEntity.ok().build();
  }
}
