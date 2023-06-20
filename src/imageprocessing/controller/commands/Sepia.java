package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;

/**
 * create a new sepia image by modifying the images that already exists in the program.
 * Then load this new image to the program and give it a new name.
 */
public class Sepia extends ChangeImageAndLoad {

  public Sepia(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void process(ImagesModel images) throws IllegalArgumentException {
    Image original;
    Image newImage;
    float[][] matrix = {
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
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
    return "Sepia";
  }
}
