package de.storyteller.api.controller.v1;

import de.storyteller.api.service.cover.CoverService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cover")
public class CoverController {

   @Autowired
   CoverService coverService;

   @PostMapping(value = "/previewImage", produces = MediaType.APPLICATION_JSON_VALUE)
   ResponseEntity<?> generatePreview(@RequestParam("file") MultipartFile file) {
      if (file.isEmpty()){
         return ResponseEntity.badRequest().build();
      }
      Optional<String> responsePath = coverService.savePreview(file);
      if (responsePath.isEmpty()){
         return ResponseEntity.internalServerError().build();
      }
      return ResponseEntity.ok(Map.of("path", responsePath.get()));
   }

   @PostMapping(value ="/{bookId}/commitImage", produces = MediaType.APPLICATION_JSON_VALUE)
   ResponseEntity<?> commitImage(@RequestParam("image") MultipartFile file, @PathVariable String bookId){
      if (file.isEmpty() || bookId.isEmpty()){
         return ResponseEntity.badRequest().build();
      }
      Optional<String> responsePath = coverService.save(file, bookId);
      if (responsePath.isEmpty()){
         return ResponseEntity.internalServerError().build();
      }
      return ResponseEntity.ok(Map.of("path", responsePath.get()));
   }

}
