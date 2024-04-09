package de.storyteller.api.service.cover;


import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    @Transactional
    Optional<String> save(MultipartFile multipartFile, String bookId);

    @Transactional
    Optional<String> savePreview(MultipartFile multipartFile);
}
