package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;

/**
 * create a new blurred image by modifying the images that already exists in the program.
 * Then load this new image to the program and give it a new name.
 */
public class Blur extends ChangeImageAndLoad {


  public Blur(String imageName, String destImageName) {
    super(imageName, destImageName);
  }

  @Override
  public void process(ImagesModel images) throws IllegalArgumentException {
    Image original;
    Image newImage;
    float[][] filterMatrix = {
            {1.0f / 16.0f, 1.0f / 8.0f, 1.0f / 16.0f},
            {1.0f / 8.0f, 1.0f / 4.0f, 1.0f / 8.0f},
            {1.0f / 16.0f, 1.0f / 8.0f, 1.0f / 16.0f}
    };


    try {
      original = images.getAnImage(imageName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Image given not exists.");
    }

    newImage = original.filter(filterMatrix);

    images.addImage(this.destImageName, newImage);

  }

  @Override
  public String getCommandName() {
    return "Blur";
  }


}
