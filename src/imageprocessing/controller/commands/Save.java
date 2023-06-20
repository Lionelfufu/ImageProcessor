package imageprocessing.controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import imageprocessing.model.ImageUtil;
import imageprocessing.model.ImagesModel;

import imageprocessing.model.Image;

/**
 * The save command for the model. It can be used to save the image with the given name to
 * the specified path which should include the name of the file.
 */
public class Save implements ImagesProcessingCommand {
  private String imagePath;
  private String imageName;



  /**
   * Construct a save image command with the given image path and image name.
   *
   * @param imagePath the path to save the image
   * @param imageName the image of the image to save
   */
  public Save(String imagePath, String imageName) throws IllegalArgumentException {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }



  /**
   * Save an image with imageName to the Model.
   *
   * @param images an ImagesModel
   */
  @Override
  public void process(ImagesModel images) throws IllegalArgumentException {
    try {
      Image image = images.getAnImage(imageName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Can't find " + imageName + "!");
    }

    String[] splitImageName = imagePath.split("\\.");
    String fileFormat = splitImageName[splitImageName.length - 1];
    if ("ppm".equalsIgnoreCase(fileFormat)) {
      File newFile = new File(imagePath);
      Image imageToSave = images.getAnImage(imageName);
      String stringToWrite = imageToSave.toPPMText();

      try {
        FileWriter fileWriter = new FileWriter(imagePath);
        fileWriter.write(stringToWrite);
        fileWriter.close();
      } catch (IOException e) {
        throw new IllegalArgumentException("Cannot write the string to the new file.");
      }

    } else {
      Image imageToSave = images.getAnImage(imageName);

      try {
        ImageUtil.saveOtherImage(imageToSave, imagePath, fileFormat);
      } catch (IOException e) {
        throw new IllegalArgumentException("Cannot save the image.");
      }

    }
  }

  @Override
  public String getCommandName() {
    return "Save";
  }
}
