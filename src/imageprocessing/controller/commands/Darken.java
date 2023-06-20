package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;


/**
 * create a new darkened image by modifying the images that already exists in the program with
 * the given increment.
 * Then load this new image to the program and give it a new name.
 */
public class Darken extends ChangeImageAndLoad {
  protected int decrement;

  /**
   * Constructs a Darken command.
   *
   * @param decrement     the decrement of each pixel component to darken
   * @param imageName     the name of the image to be used to modify
   * @param destImageName the name of the new image
   */
  public Darken(int decrement, String imageName, String destImageName)
          throws IllegalArgumentException {
    super(imageName, destImageName);
    this.decrement = decrement;
  }

  @Override
  public void process(ImagesModel images) {
    Image original;
    Image newImage;

    try {
      original = images.getAnImage(imageName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Image given not exists.");
    }

    newImage = original.darken(decrement);

    images.addImage(this.destImageName, newImage);
  }

  @Override
  public String getCommandName() {
    return "Darken";
  }
}
