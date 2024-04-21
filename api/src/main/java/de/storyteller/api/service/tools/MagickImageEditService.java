package de.storyteller.api.service.tools;

import java.io.File;
import java.io.IOException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
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

  @Override
  public void scaleToSm(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException {
    scaleImage(cmd, SM_HEIGHT, inFile, outFile);
  }

  @Override
  public void cropToLg(String inFile, String outFile)
      throws IOException, InterruptedException, IM4JavaException {
    cropImage(cmd, LG_HEIGHT, inFile, outFile);
  }

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
