package de.storyteller.api.service.cover;


import de.storyteller.api.dto.cover.CoverUriDTO;
import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    @Transactional
    Optional<CoverUriDTO> save(MultipartFile multipartFile, String bookId);

    @Transactional
    Optional<CoverUriDTO> savePreview(MultipartFile multipartFile);
}
