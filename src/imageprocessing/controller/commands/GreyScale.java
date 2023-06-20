package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;

/**
 * create a new greyscaled image by modifying the images that already exists in the program.
 * Then load this new image to the program and give it a new name.
 */
public class GreyScale extends ChangeImageAndLoad {

  public GreyScale(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void process(ImagesModel images) throws IllegalArgumentException {
    Image original;
    Image newImage;
    float[][] matrix = {
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f}
    };


    try {
      original = images.getAnImage(imageName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Image given not exists.");
    }

    newImage = original.colorTransform(matrix);

    images.addImage(this.destImageName, newImage);

  }

  @Override
  public String getCommandName() {
    return "GreyScale";
  }
}
