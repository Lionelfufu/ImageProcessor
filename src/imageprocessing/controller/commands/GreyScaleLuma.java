package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;


/**
 * create a new luma grey scaled image by modifying the images that already exists in the program.
 * Then load this new image to the program and give it a new name.
 */
public class GreyScaleLuma extends ChangeImageAndLoad {

  /**
   * Constructs a GreyScaleLuma command.
   *
   * @param imageName     the name of the image to be used to modify
   * @param destImageName the name of the new image
   */
  public GreyScaleLuma(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void process(ImagesModel images) throws IllegalArgumentException {
    Image original;
    Image newImage;

    try {
      original = images.getAnImage(imageName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Image given not exists.");
    }

    newImage = original.greyScale("luma");

    images.addImage(this.destImageName, newImage);
  }

  @Override
  public String getCommandName() {
    return "GreyScaleLuma";
  }
}
