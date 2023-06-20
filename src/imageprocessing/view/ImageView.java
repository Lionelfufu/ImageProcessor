package imageprocessing.view;

import java.io.IOException;

/**
 * This interface represents the view of the ImageProcessing Program.
 */
public interface ImageView {

  /**
   * Renders a message to user.
   *
   * @param message the message to render
   */
  void renderMessage(String message) throws IOException;
}
