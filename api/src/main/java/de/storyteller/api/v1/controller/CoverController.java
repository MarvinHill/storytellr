package de.storyteller.api.v1.controller;

import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import de.storyteller.api.service.cover.CoverService;

import java.text.ParseException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling cover related requests
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cover")
public class CoverController {

    private final CoverService coverService;

    /**
     * Generate a preview image
     *
     * @param file Image file
     * @return Image path
     */
    @PreAuthorize("permitAll()")
    @PostMapping(value = "/previewImage", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> generatePreview(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<CoverUriDTO> responsePath = coverService.savePreview(file);
        if (responsePath.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(responsePath.get());
    }

    /**
     * Commit an image
     *
     * @param file   Image file
     * @param bookId Id of the book to add the image to
     * @return Image path
     * @throws ParseException if the file is empty
     */
    @PreAuthorize("isAuthenticated() && (@sAuthService.userOwnsBook(#bookId) || @sAuthService.isAdmin())")
    @PostMapping(value = "/commitImage", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> commitImage(@RequestParam("file") MultipartFile file, @RequestParam("bookId") String bookId)
            throws ParseException {
        if (file.isEmpty() || bookId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<CoverUriDTO> responsePath = coverService.save(file, bookId);
        if (responsePath.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(responsePath);

    }

}
