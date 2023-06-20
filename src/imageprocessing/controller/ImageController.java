package imageprocessing.controller;

import java.io.IOException;

/**
 * This interface represents the controller for the ImageProcessing Program.
 */
public interface ImageController {

  /**
   * A method for the controller to handle both inputs and outputs of the program. I will accept the
   * inputs from user and parse them and then returns the corresponding outputs or execute the
   * corresponding actions.
   *
   * @throws IOException when fail to append a message
   */
  void run() throws IOException;
}
