package de.storyteller.api.v1.controller;


import de.storyteller.api.service.ExampleDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mock")
public class MockDataController {
  @Autowired
  ExampleDataGenerator exampleDataGenerator;

  @PreAuthorize("isAuthenticated() && hasAuthority('MOCK_ADMIN')")
  @GetMapping("/gen")
  public ResponseEntity<HttpStatusCode> generateMockData(){
    exampleDataGenerator.generate();
    return ResponseEntity.ok().build();
  }

}
