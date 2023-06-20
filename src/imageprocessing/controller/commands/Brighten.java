package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;


/**
 * create a new brightened image by modifying the images that already exists in the program with
 * the given increment.
 * Then load this new image to the program and give it a new name.
 */
public class Brighten extends ChangeImageAndLoad {

  protected int increment;

  /**
   * Constructs a Brighten command.
   *
   * @param increment     the increment of each pixel component to brighten
   * @param imageName     the name of the image to be used to modify
   * @param destImageName the name of the new image
   */
  public Brighten(int increment, String imageName, String destImageName) {
    super(imageName, destImageName);
    this.increment = increment;
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

    newImage = original.brighten(increment);

    images.addImage(this.destImageName, newImage);
  }

  @Override
  public String getCommandName() {
    return "Brighten";
  }
}
