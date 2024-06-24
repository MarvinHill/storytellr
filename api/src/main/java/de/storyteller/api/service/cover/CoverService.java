package de.storyteller.api.service.cover;


import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for handling cover images.
 */
public interface CoverService {

    /**
     * Save a cover image for a book.
     * @param multipartFile The image file.
     * @param bookId The id of the book.
     * @return The uri of the saved image.
     */
    Optional<CoverUriDTO> save(MultipartFile multipartFile, String bookId);

    /**
     * Save a cover image for a book as preview.
     * @param multipartFile The image file.
     * @return The uri of the saved image.
     */
    Optional<CoverUriDTO> savePreview(MultipartFile multipartFile);
}
