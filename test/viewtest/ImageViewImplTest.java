package viewtest;

import org.junit.Test;

import java.io.IOException;

import imageprocessing.view.ImageView;
import imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is a tester for ImageViewImpl class.
 */
public class ImageViewImplTest {

  /**
   * Test if the renderMessage() method works correctly.
   */
  @Test
  public void renderMessageTest() throws IOException {
    Appendable out = new StringBuilder();

    ImageView view = new ImageViewImpl(out);

    view.renderMessage("Tianfu Liang");

    assertEquals("Tianfu Liang\n", out.toString());
  }
}
