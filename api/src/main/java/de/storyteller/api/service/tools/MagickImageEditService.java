package de.storyteller.api.service.tools;

import java.io.IOException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for editing images.
 */
@Slf4j
@Service
public class MagickImageEditService implements ImageEditService {

  private final int SM_HEIGHT = 300;
  private final int LG_HEIGHT = 800;

  private double ASPECT_RATIO = 1.6;
  private ConvertCmd cmd = new ConvertCmd();
  private String LIB_PATH = "/usr/bin";

  public MagickImageEditService() {
    cmd.setSearchPath(LIB_PATH);
  }

  /**
   * Scale an image to a small size.
   * @param inFile Input file.
   * @param outFile Output file.
   * @throws IOException if an I/O error occurs.
   * @throws InterruptedException if the process is interrupted.
   * @throws IM4JavaException if an error occurs during image processing.
   */
  @Override
  public void scaleToSm(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException {
    scaleImage(cmd, SM_HEIGHT, inFile, outFile);
  }

  /**
   * Crop an image to a large size.
   * @param inFile Input file.
   * @param outFile Output file.
   * @throws IOException if an I/O error occurs.
   * @throws InterruptedException if the process is interrupted.
   * @throws IM4JavaException if an error occurs during image processing.
   */
  @Override
  public void cropToLg(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException {
    cropImage(cmd, LG_HEIGHT, inFile, outFile);
  }

  /**
   * Crop an image to a large size.
   * @param cmd ConvertCmd
   * @param DEST_HEIGHT Destination height
   * @param inputFilePath Input file path
   * @param outputFilePath Output file path
   * @throws IM4JavaException if an error occurs during image processing.
   * @throws IOException if an I/O error occurs.
   * @throws InterruptedException if the process is interrupted.
   */
  public void cropImage(ConvertCmd cmd, int DEST_HEIGHT, String inputFilePath,
                        String outputFilePath)
      throws IM4JavaException, IOException, InterruptedException {

    Info imageInfo = new Info(inputFilePath, true);

    int width = imageInfo.getImageWidth();
    int height = imageInfo.getImageHeight();

    int DEST_WIDTH = (int) (DEST_HEIGHT / ASPECT_RATIO);

    IMOperation op = new IMOperation();
    op.addImage();
    op.gravity("center");
    op.resize(DEST_WIDTH, DEST_HEIGHT, "^");
    op.extent(DEST_WIDTH, DEST_HEIGHT);
    op.addImage();
    cmd.run(op, inputFilePath, outputFilePath);
  }

  /**
   * Scale an image to a small size.
   * @param cmd ConvertCmd
   * @param DEST_HEIGHT Destination height
   * @param inputFilePath Input file path
   * @param outputFilePath Output file path
   * @throws IOException if an I/O error occurs.
   * @throws InterruptedException if the process is interrupted.
   * @throws IM4JavaException if an error occurs during image processing.
   */
  private void scaleImage(ConvertCmd cmd, int DEST_HEIGHT, String inputFilePath,
                     String outputFilePath)
      throws IOException, InterruptedException, IM4JavaException {

    int DEST_WIDTH = (int) (DEST_HEIGHT / ASPECT_RATIO);

    IMOperation op = new IMOperation();
    op.addImage();
    op.resize(DEST_WIDTH, DEST_HEIGHT, "!");
    op.addImage();
    cmd.run(op, inputFilePath, outputFilePath);
  }
}
