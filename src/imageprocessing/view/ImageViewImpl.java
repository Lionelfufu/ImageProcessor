package imageprocessing.view;

import java.io.IOException;
import java.util.Objects;

/**
 * This class represent an Image View for the ImageProcessing program.
 */
public class ImageViewImpl implements ImageView {

  private final Appendable out;

  /**
   * Constructs a ImageView with the given Appendable.
   *
   * @param out the place to print the message
   */
  public ImageViewImpl(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  /**
   * Render a given message to the user.
   *
   * @param message the message to render
   * @throws IOException when the Appendable fail to append the given message
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message).append("\n");
    } catch (IOException e) {
      throw new IOException("Failed to render message.");
    }
  }


}
