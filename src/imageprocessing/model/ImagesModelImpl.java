package imageprocessing.model;

import java.util.HashMap;
import java.util.Map;

import imageprocessing.controller.commands.ImagesProcessingCommand;

/**
 * This class represents an Images Model. An Images Model consist of Images and each Images has a
 * name. The name of the Image can be used as a key to find the Image in the Image Model.
 */
public class ImagesModelImpl implements ImagesModel {
  private Map<String, Image> images;

  /**
   * Construct a Images Model.
   */
  public ImagesModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void execute(ImagesProcessingCommand c) throws IllegalArgumentException {
    try {
      c.process(this);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Can not execute this command.");
    }
  }

  @Override
  public Map<String, Image> getAllImages() {
    return images;
  }


  @Override
  public Image getAnImage(String imageName) throws IllegalArgumentException {
    Image image;
    try {
      image = this.images.get(imageName);
      image.toPPMText();
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Cannot find " + imageName + "!");
    }
    return image;
  }

  @Override
  public void addImage(String imageName, Image image) {
    this.images.put(imageName, image);
  }


}
