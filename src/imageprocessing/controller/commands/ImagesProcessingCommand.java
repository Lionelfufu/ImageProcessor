package imageprocessing.controller.commands;

import imageprocessing.model.ImagesModel;

/**
 * This class represents a Command that the model can execute.
 */
public interface ImagesProcessingCommand {


  /**
   * Process the given ImagesModel to finish this command.
   *
   * @param images an ImagesModel
   * @throws IllegalArgumentException when can not process the command to the images Model
   */
  void process(ImagesModel images) throws IllegalArgumentException;

  /**
   * Returns the name of the command.
   *
   * @return a String
   */
  String getCommandName();
}
