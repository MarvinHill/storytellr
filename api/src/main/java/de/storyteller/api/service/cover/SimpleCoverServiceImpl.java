package de.storyteller.api.service.cover;

import de.storyteller.api.dto.book.BookDTO;
import de.storyteller.api.dto.book.EditBookRequest;
import de.storyteller.api.mapper.BookMapper;
import de.storyteller.api.service.book.BookService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
public class SimpleCoverServiceImpl implements CoverService {

  @Autowired
  BookService bookService;
  @Autowired
  BookMapper bookMapper;

  private final String INTERNAL_STORAGE_PATH = "/www/html";
  private final String PREVIEW_PATH = "/preview";
  private final String EXTERNAL_STORAGE_PATH = "/datastore";
  private final Map<String, String> mimeTypes = Map.of("image/jpeg", "jpg", "image/png", "png");

  @Override
  public Optional<String> save(MultipartFile multipartFile, String bookId) {
    Optional<String> responseDTO = saveFile(INTERNAL_STORAGE_PATH, EXTERNAL_STORAGE_PATH, multipartFile);
    Optional<BookDTO> optionalBookDTO = bookService.getBookById(UUID.fromString(bookId));
    if (optionalBookDTO.isEmpty() || responseDTO.isEmpty()) return Optional.empty();

    BookDTO bookDTO = optionalBookDTO.get();
    bookService.updateBookCover(bookDTO.getId(), responseDTO.get());
    return responseDTO;
  }

  @Override
  public Optional<String> savePreview(MultipartFile multipartFile) {
    return saveFile(INTERNAL_STORAGE_PATH + PREVIEW_PATH, EXTERNAL_STORAGE_PATH + PREVIEW_PATH,
        multipartFile);
  }

  private Optional<String> saveFile(String internalStoragePath, String externalStoragePath,
                                 MultipartFile multipartFile) {
    UUID tempId = UUID.randomUUID();
    String extention = "";
    if (mimeTypes.containsKey(multipartFile.getContentType())) {
      extention = mimeTypes.get(multipartFile.getContentType());
    } else {
      log.error("mime type not accepted", multipartFile.getContentType());
      return Optional.empty();
    }

    File file = new File(internalStoragePath + "/" + tempId + "." + extention);
    file.mkdirs();

    try {
      file.createNewFile();
    } catch (IOException e){
      log.error("Error creating file", e);
      return Optional.empty();
    }
    catch (SecurityException e){
      log.error("SecurityException when creating file", e);
      return Optional.empty();
    }

    try {
      multipartFile.transferTo(file);
      return Optional.of(externalStoragePath + "/" + tempId + "." + extention);
    } catch (IOException e) {
      log.error("Could not transfer file to external storage", e);
      return Optional.empty();
    }
  }
}
