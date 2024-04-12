package de.storyteller.api.service.cover;

import de.storyteller.api.v1.dto.book.BookDTO;
import de.storyteller.api.v1.dto.cover.CoverUriDTO;
import de.storyteller.api.v1.mapper.BookMapper;
import de.storyteller.api.service.book.BookService;
import de.storyteller.api.service.tools.ImageEditService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Slf4j
@Service
public class SimpleCoverServiceImpl implements CoverService {


  private final BookService bookService;
  private final BookMapper bookMapper;
  private final ImageEditService editService;

  private final String INTERNAL_STORAGE_PATH = "/www/html";
  private final String PREVIEW_PATH = "/preview";
  private final String EXTERNAL_STORAGE_PATH = "/datastore";
  private final Map<String, String> mimeTypes = Map.of("image/jpeg", "jpg", "image/png", "png");

  private final int IMAGE_HEIGHT_MIN = 800;

  @Override
  public Optional<CoverUriDTO> save(MultipartFile multipartFile, String bookId) {
    UUID tempId = UUID.randomUUID();
    Optional<CoverUriDTO> responseDTO =
        saveFile(tempId,INTERNAL_STORAGE_PATH, EXTERNAL_STORAGE_PATH, multipartFile);
    Optional<BookDTO> optionalBookDTO = bookService.getBookById(UUID.fromString(bookId));
    if (optionalBookDTO.isEmpty() || responseDTO.isEmpty()) {
      return Optional.empty();
    }

    BookDTO bookDTO = optionalBookDTO.get();
    bookService.updateBookCover(bookDTO.getId(), responseDTO.get());
    return responseDTO;
  }

  @Override
  public Optional<CoverUriDTO> savePreview(MultipartFile multipartFile) {
    UUID tempId = UUID.randomUUID();
    Optional<CoverUriDTO> optionalUriMap = saveFile(tempId, INTERNAL_STORAGE_PATH + PREVIEW_PATH, EXTERNAL_STORAGE_PATH + PREVIEW_PATH,
        multipartFile);
    return optionalUriMap.isPresent() ? Optional.of(optionalUriMap.get()) : Optional.empty();
  }

  private Optional<CoverUriDTO> saveFile(UUID tempId, String internalStoragePath, String externalStoragePath,
                                    MultipartFile multipartFile) {
    String extention = "";
    if (mimeTypes.containsKey(multipartFile.getContentType())) {
      extention = mimeTypes.get(multipartFile.getContentType());
    } else {
      log.error("mime type not accepted", multipartFile.getContentType());
      return Optional.empty();
    }
    String internalPath = internalStoragePath + "/" + tempId + "-original" + ".jpg";
    File file = new File(internalPath);
    file.mkdirs();

    try {
      file.createNewFile();
    } catch (IOException e) {
      log.error("Error creating file", e);
      return Optional.empty();
    } catch (SecurityException e) {
      log.error("SecurityException when creating file", e);
      return Optional.empty();
    }

    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      log.error("Could not transfer file to external storage", e);
      return Optional.empty();
    }

    try {
      Info imageInfo = new Info(file.getAbsolutePath(), true);
      if (imageInfo.getImageWidth() < (IMAGE_HEIGHT_MIN / 2) || imageInfo.getImageHeight() < IMAGE_HEIGHT_MIN) {
        log.error("Uploaded Image is to small with width {} and height {}",
            imageInfo.getImageWidth(), imageInfo.getImageHeight());
        return Optional.empty();
      }

    } catch (InfoException e) {
      log.error("Could not load image info", e);
    }

    String smInternalPath = internalStoragePath + "/" + tempId + "-sm" + ".jpg";
    String lgInternalPath = internalStoragePath + "/" + tempId + "-lg" + ".jpg";


    String orginalExtPath = externalStoragePath + "/" + tempId + "-original" + ".jpg";
    String smExtPath = externalStoragePath + "/" + tempId + "-sm" + ".jpg";
    String lgExtPath = externalStoragePath + "/" + tempId + "-lg" + ".jpg";

    try {
      editService.cropToLg(internalPath, lgInternalPath);
      editService.scaleToSm(lgInternalPath, smInternalPath);
    }
    catch (Exception e){
      log.error("Error saving image info", e);
      return Optional.empty();
    }
    CoverUriDTO imageUris = new CoverUriDTO();
    imageUris.setOriginalImageUri(orginalExtPath);
    imageUris.setSmImageUri(smExtPath);
    imageUris.setLgImageUri(lgExtPath);
    return Optional.of(imageUris);
  }
}
