package de.storyteller.api.service.tools;

import java.io.IOException;
import org.im4java.core.IM4JavaException;

/**
 * Service for editing images.
 */
public interface ImageEditService {
  /**
   * Scale an image to a small size.
   * @param inFile Input file.
   * @param outFile Output file.
   * @throws IOException If an I/O error occurs.
   * @throws InterruptedException If the process is interrupted.
   * @throws IM4JavaException If an error occurs during image processing.
   */
  void scaleToSm(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;

  /**
   * Crop an image to a large size.
   * @param inFile Input file.
   * @param outFile Output file.
   * @throws IOException If an I/O error occurs.
   * @throws InterruptedException If the process is interrupted.
   * @throws IM4JavaException If an error occurs during image processing.
   */
  void cropToLg(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException;
}
