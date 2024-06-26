package de.storyteller.api.v1.controller;


import de.storyteller.api.service.ExampleDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling mock data generation
 */
@RestController
@RequestMapping("/api/v1/mock")
public class MockDataController {
    @Autowired
    ExampleDataGenerator exampleDataGenerator;

    /**
     * Generate mock data if the user is authenticated and has the authority to generate mock data
     *
     * @return HttpStatusCode
     */
    @PreAuthorize("isAuthenticated() && @sAuthService.isAdmin()")
    @GetMapping("/gen")
    public ResponseEntity<HttpStatusCode> generateMockData() {
        exampleDataGenerator.generate();
        return ResponseEntity.ok().build();
    }

    /**
     * Generate mock data for books if the user is authenticated and has the authority to generate mock data
     *
     * @return HttpStatusCode
     */
    @PreAuthorize("isAuthenticated() && @sAuthService.isAdmin()")
    @GetMapping("/gen/genres")
    public ResponseEntity<HttpStatusCode> generateGenres() {
        exampleDataGenerator.generateGenres();
        return ResponseEntity.ok().build();
    }
}
