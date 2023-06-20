package imageprocessing.controller.commands;

import imageprocessing.model.ImagesModel;

/**
 * This is an abstract command which can be used to create a new image by modifying the images that
 * already exists in the program. Then load this new image to the program and give it a new name.
 */
public abstract class ChangeImageAndLoad implements ImagesProcessingCommand {
  protected String imageName;
  protected String destImageName;


  /**
   * Constructs a ChangeImageAndLoad command.
   *
   * @param imageName     the name of the image to be used to modify
   * @param destImageName the name of the new image
   */

  public ChangeImageAndLoad(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;

  }

  @Override
  public abstract void process(ImagesModel images) throws IllegalArgumentException;

  @Override
  public String getCommandName() {
    return "ChangeImageAndLoad";
  }
}
