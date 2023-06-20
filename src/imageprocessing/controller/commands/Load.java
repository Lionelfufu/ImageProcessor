package imageprocessing.controller.commands;

import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;
import imageprocessing.model.ImageImpl;


/**
 * The Load command to the model. It can be used to load an image from the specified path and
 * refer it to henceforth in the program by the given image name.
 */
public class Load implements ImagesProcessingCommand {

  private String imageName;
  private Image image;


  /**
   * Construct a Load image command with the given image path and image name.
   *
   * @param imagePath the path of an image file
   * @param imageName the given image name to refer the image
   * @throws IllegalArgumentException the given imagePath can not find an image file
   */
  public Load(String imagePath, String imageName) throws IllegalArgumentException {

    this.image = new ImageImpl(imagePath);
    this.imageName = imageName;

  }

  /**
   * Load the image to the Model.
   *
   * @param images an ImagesModel
   */
  @Override
  public void process(ImagesModel images) {
    images.addImage(this.imageName, this.image);
  }


  @Override
  public String getCommandName() {
    return "Load";
  }


}
