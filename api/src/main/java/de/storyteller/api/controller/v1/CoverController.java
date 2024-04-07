package de.storyteller.api.controller.v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

   @PostMapping(value = "/previewImage", produces = MediaType.IMAGE_JPEG_VALUE)
   ResponseEntity<UUID> generatePreview(@RequestParam("file") MultipartFile file) throws IOException {
      file.getInputStream();
      File f = File.createTempFile("preview",".jpg");
      file.transferTo(f);
      return ResponseEntity.ok();
   }

   @PostMapping("/commitImage")
   ResponseEntity<UUID> commitImage(@RequestParam("image") MultipartFile file){
      return null;
   }

   @GetMapping ("/downloadImage")
   ResponseEntity<UUID> commitImage(@RequestParam("image") MultipartFile file){
      return null;
   }
}
