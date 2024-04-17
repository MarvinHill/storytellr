package de.storyteller.api.service.cover;


import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    Optional<CoverUriDTO> save(MultipartFile multipartFile, String bookId);

    Optional<CoverUriDTO> savePreview(MultipartFile multipartFile);
}
