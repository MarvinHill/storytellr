package de.storyteller.api.service.tools;

import jakarta.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.im4java.core.IM4JavaException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageEditService {
  @Transactional
  void scaleToSm(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;
  @Transactional
  void cropToLg(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;
}
