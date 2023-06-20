package imageprocessing.model;

import java.util.Map;

import imageprocessing.controller.commands.ImagesProcessingCommand;

/**
 * This interface represents the Images Model consisting of Images.
 */
public interface ImagesModel {

  /**
   * Use this model to execute a given command.
   *
   * @param c a ImagesProcessingCommand needed to be executed
   * @throws IllegalArgumentException when can not execute the given command
   */
  void execute(ImagesProcessingCommand c) throws IllegalArgumentException;

  /**
   * Returns a Map of String and Images, which is all Images including in the models.
   *
   * @return Map of String and Images
   */
  Map<String, Image> getAllImages();

  /**
   * Get an Image with the given name.
   *
   * @param imageName a String representing the name of the image which is the key of the Map
   * @return an Image
   * @throws IllegalArgumentException can not find the given name of image
   */
  Image getAnImage(String imageName) throws IllegalArgumentException;

  /**
   * Add a new Image to the map with the given imageName.
   *
   * @param imageName the name of the iamge
   * @param image     the image to be added
   */
  void addImage(String imageName, Image image);
}
