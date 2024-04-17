package de.storyteller.api.service.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.im4java.core.IM4JavaException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageEditService {
  void scaleToSm(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;
  void cropToLg(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;
}
