package modeltest;

import org.junit.Test;

import imageprocessing.model.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is a tester for ImageUtil Class.
 */
public class ImageUtilTest {

  /**
   * Tests if the readPPMtoInt method produce the correct 3D array of integers.
   */
  @Test
  public void readPPMtoIntTest() {

    int[][][] imageProduced = ImageUtil.readPPMtoInt("res/Images/red.ppm");

    int[][][] imageExpected = new int[][][]
        {{{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}}};

    assertEquals(imageExpected, imageProduced);

    int[][][] imageProduced2 = ImageUtil.readPPMtoInt("res/Images/green.ppm");

    int[][][] imageExpected2 = new int[][][]
        {{{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}}};

    assertEquals(imageExpected2, imageProduced2);

    int[][][] imageProduced3 = ImageUtil.readPPMtoInt("res/Images/blue.ppm");

    int[][][] imageExpected3 = new int[][][]
        {{{0, 0, 162}, {0, 0, 162}, {0, 0, 162}, {0, 0, 162}},
        {{0, 0, 162}, {0, 0, 162}, {0, 0, 162}, {0, 0, 162}},
        {{0, 0, 162}, {0, 0, 162}, {0, 0, 162}, {0, 0, 162}},
        {{0, 0, 162}, {0, 0, 162}, {0, 0, 162}, {0, 0, 162}}};

    assertEquals(imageExpected3, imageProduced3);

    int[][][] imageProduced4 = ImageUtil.readPPMtoInt("res/Images/mix.ppm");

    int[][][] imageExpected4 = new int[][][]
        {{{255, 0, 0}, {255, 0, 0}, {0, 0, 60}, {0, 0, 60}},
        {{255, 0, 0}, {255, 0, 0}, {0, 0, 60}, {0, 0, 60}},
        {{0, 0, 0}, {0, 0, 0}, {0, 85, 0}, {0, 85, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 85, 0}, {0, 85, 0}}};

    assertEquals(imageExpected4, imageProduced4);
  }

  /**
   * Tests if the getMaxValue method produce the correct max value.
   */
  @Test
  public void getMaxValueTest() {

    int maxValueProduced = ImageUtil.getMaxValue("res/Images/red.ppm");
    int maxValueExpect = 255;
    assertEquals(maxValueExpect, maxValueProduced);

    int maxValueProduced2 = ImageUtil.getMaxValue("res/Images/green.ppm");
    int maxValueExpect2 = 255;
    assertEquals(maxValueExpect2, maxValueProduced2);

  }

  /**
   * Tests if the readBufferedImageToInt method read an Image to 3D array of integer correctly.
   * And if try to use this method to read a .ppm
   */
  @Test
  public void readBufferedImageToIntTest() {

    int[][][] imageProduced = ImageUtil.readBufferedImageToInt("res/Images/red2.png");

    int[][][] imageExpected = new int[][][]
        {{{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}}};

    assertEquals(imageExpected, imageProduced);

    try {
      int[][][] imageProduced2 = ImageUtil.readBufferedImageToInt("res/Images/red.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot read the input stream.", e.getMessage());
    }

  }

}
