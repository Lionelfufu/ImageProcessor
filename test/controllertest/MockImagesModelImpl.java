package controllertest;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import imageprocessing.controller.commands.ImagesProcessingCommand;
import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;


/**
 * This class represents a mock ImagesModelImpl model. This class is created for testing if the
 * controller pass in the correct value as expected.
 */
public class MockImagesModelImpl implements ImagesModel {

  private final Appendable appendable;

  /**
   * Creates a mock ImagesModelImpl model.
   */
  public MockImagesModelImpl(Appendable log) {
    this.appendable = Objects.requireNonNull(log);
  }

  @Override
  public void execute(ImagesProcessingCommand c) throws IllegalStateException {
    try {
      this.appendable.append(String.format("Command to execute is %s\n",
              c.getCommandName()));
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed.");
    }

  }

  @Override
  public Map<String, Image> getAllImages() {
    return null;
  }

  @Override
  public Image getAnImage(String imageName) throws IllegalStateException {
    try {
      this.appendable.append(String.format("image name is %s\n",
              imageName));
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed.");
    }
    return null;
  }

  @Override
  public void addImage(String imageName, Image image) throws IllegalStateException {

    try {
      this.appendable.append(String.format("image name is %s\n" + "Image is:\n" + "%s",
              imageName, image.toPPMText()));
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed.");
    }

  }
}
