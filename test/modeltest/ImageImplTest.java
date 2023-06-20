package modeltest;

import org.junit.Test;


import imageprocessing.model.Image;
import imageprocessing.model.ImageImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is a tester for PPMImage model.
 */
public class ImageImplTest {

  /**
   * Test if the PPMImage constructs correctly.
   */
  @Test
  public void constructPPMImage() {
    ImageImpl image = new ImageImpl("res/Images/red.ppm");
    assertEquals(4, image.getWidth());
    assertEquals(4, image.getHeight());
    assertEquals(255, image.getMaxValue());
    int[][][] imageExpected = new int[][][]
        {{{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}, {255, 0, 0}}};
    assertEquals(imageExpected, image.getImage());

    ImageImpl image2 = new ImageImpl("res/Images/green.ppm");
    assertEquals(4, image2.getWidth());
    assertEquals(4, image2.getHeight());
    assertEquals(255, image2.getMaxValue());
    int[][][] imageExpected2 = new int[][][]
        {{{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}},
        {{0, 113, 0}, {0, 113, 0}, {0, 113, 0}, {0, 113, 0}}};
    assertEquals(imageExpected2, image2.getImage());

  }


  /**
   * Test if a given file not exists when constructs a  PPMImage, it will throw Exception.
   */
  @Test
  public void constructPPMImageException() {
    try {
      ImageImpl image = new ImageImpl("abc");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The given file abc not found!", e.getMessage());
    }
  }

  /**
   * Tests if the toPPMText method works correctly.
   */
  @Test
  public void toPPMTextTest() {
    ImageImpl image = new ImageImpl("res/Images/red.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    ImageImpl image2 = new ImageImpl("res/Images/green.ppm");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n";
    assertEquals(stringExpected2, image2.toPPMText());
  }

  /**
   * Tests if the greyScaleRed works correctly.
   */
  @Test
  public void greyScaleRedTest() {
    Image image = new ImageImpl("res/Images/red.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("red");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 255 255  255 255 255  255 255 255  255 255 255  \n" +
            "255 255 255  255 255 255  255 255 255  255 255 255  \n" +
            "255 255 255  255 255 255  255 255 255  255 255 255  \n" +
            "255 255 255  255 255 255  255 255 255  255 255 255  \n";
    assertEquals(stringExpected2, image2.toPPMText());


  }

  /**
   * Tests if the greyScaleGreen works correctly.
   */
  @Test
  public void greyScaleGreenTest() {
    Image image = new ImageImpl("res/Images/green.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n" +
            "0 113 0  0 113 0  0 113 0  0 113 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("green");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "113 113 113  113 113 113  113 113 113  113 113 113  \n" +
            "113 113 113  113 113 113  113 113 113  113 113 113  \n" +
            "113 113 113  113 113 113  113 113 113  113 113 113  \n" +
            "113 113 113  113 113 113  113 113 113  113 113 113  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the greyScaleBlue works correctly.
   */
  @Test
  public void greyScaleBlueTest() {
    Image image = new ImageImpl("res/Images/blue.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0 0 162  0 0 162  0 0 162  0 0 162  \n" +
            "0 0 162  0 0 162  0 0 162  0 0 162  \n" +
            "0 0 162  0 0 162  0 0 162  0 0 162  \n" +
            "0 0 162  0 0 162  0 0 162  0 0 162  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("blue");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "162 162 162  162 162 162  162 162 162  162 162 162  \n" +
            "162 162 162  162 162 162  162 162 162  162 162 162  \n" +
            "162 162 162  162 162 162  162 162 162  162 162 162  \n" +
            "162 162 162  162 162 162  162 162 162  162 162 162  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the greyScaleMax works correctly.
   */
  @Test
  public void greyScaleMaxTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("max");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 255 255  255 255 255  60 60 60  60 60 60  \n" +
            "255 255 255  255 255 255  60 60 60  60 60 60  \n" +
            "0 0 0  0 0 0  85 85 85  85 85 85  \n" +
            "0 0 0  0 0 0  85 85 85  85 85 85  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the greyScaleIntensity works correctly.
   */
  @Test
  public void greyScaleIntensityTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("intensity");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "85 85 85  85 85 85  20 20 20  20 20 20  \n" +
            "85 85 85  85 85 85  20 20 20  20 20 20  \n" +
            "0 0 0  0 0 0  28 28 28  28 28 28  \n" +
            "0 0 0  0 0 0  28 28 28  28 28 28  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the greyScaleLuma works correctly.
   */
  @Test
  public void greyScaleLumaTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.greyScale("luma");
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
            "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
            "0 0 0  0 0 0  60 60 60  60 60 60  \n" +
            "0 0 0  0 0 0  60 60 60  60 60 60  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the horizontalFlip works correctly.
   */
  @Test
  public void horizontalFlipTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.horizontalFlip();
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0 0 60  0 0 60  255 0 0  255 0 0  \n" +
            "0 0 60  0 0 60  255 0 0  255 0 0  \n" +
            "0 85 0  0 85 0  0 0 0  0 0 0  \n" +
            "0 85 0  0 85 0  0 0 0  0 0 0  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the verticalFlip works correctly.
   */
  @Test
  public void verticalFlipTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.verticalFlip();
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the brighten works correctly.
   */
  @Test
  public void brightenTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.brighten(5);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 5 5  255 5 5  5 5 65  5 5 65  \n" +
            "255 5 5  255 5 5  5 5 65  5 5 65  \n" +
            "5 5 5  5 5 5  5 90 5  5 90 5  \n" +
            "5 5 5  5 5 5  5 90 5  5 90 5  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the darken works correctly.
   */
  @Test
  public void darkenTest() {
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.darken(5);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "250 0 0  250 0 0  0 0 55  0 0 55  \n" +
            "250 0 0  250 0 0  0 0 55  0 0 55  \n" +
            "0 0 0  0 0 0  0 80 0  0 80 0  \n" +
            "0 0 0  0 0 0  0 80 0  0 80 0  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }

  /**
   * Tests if the filter method works correctly with the sharpen matrix.
   */
  @Test
  public void SharpenTest() {

    float[][] filterMatrix = {
            {-1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f,},
            {-1.0f / 8.0f, 1.0f / 4.0f, 1.0f / 4.0f, 1.0f / 4.0f, -1.0f / 8.0f,},
            {-1.0f / 8.0f, 1.0f / 4.0f, 1.0f, 1.0f / 4.0f, -1.0f / 8.0f,},
            {-1.0f / 8.0f, 1.0f / 4.0f, 1.0f / 4.0f, 1.0f / 4.0f, -1.0f / 8.0f,},
            {-1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f, -1.0f / 8.0f,}
    };

    Image image = new ImageImpl("res/Images/mix.ppm");

    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.filter(filterMatrix);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 15  64 0 105  0 0 105  \n" +
            "255 0 0  255 0 15  64 21 105  0 21 105  \n" +
            "64 0 0  64 21 0  0 149 15  0 149 15  \n" +
            "0 0 0  0 21 0  0 149 0  0 149 0  \n";
    assertEquals(stringExpected2, image2.toPPMText());
  }

  /**
   * Tests if the filter method works correctly with the blur matrix.
   */
  @Test
  public void BlurTest() {
    float[][] filterMatrix = {
            {1.0f / 16.0f, 1.0f / 8.0f, 1.0f / 16.0f},
            {1.0f / 8.0f, 1.0f / 4.0f, 1.0f / 8.0f},
            {1.0f / 16.0f, 1.0f / 8.0f, 1.0f / 16.0f}
    };
    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.filter(filterMatrix);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "143 0 0  143 0 11  48 0 34  0 0 34  \n" +
            "143 0 0  143 5 11  48 16 34  0 16 34  \n" +
            "48 0 0  48 16 4  16 48 11  0 48 11  \n" +
            "0 0 0  0 16 0  0 48 0  0 48 0  \n";
    assertEquals(stringExpected2, image2.toPPMText());

  }


  /**
   * Test if the colorTransform method work correctly with GreyScale matrix.
   */
  @Test
  public void GreyScaleTest() {

    float[][] matrix = {
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f}
    };


    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.colorTransform(matrix);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
            "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
            "0 0 0  0 0 0  61 61 61  61 61 61  \n" +
            "0 0 0  0 0 0  61 61 61  61 61 61  \n";
    assertEquals(stringExpected2, image2.toPPMText());


  }

  /**
   * Test if the colorTransform method work correctly with sepia matrix.
   */
  @Test
  public void SepiaTest() {

    float[][] matrix = {
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
    };


    Image image = new ImageImpl("res/Images/mix.ppm");
    String stringExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";
    assertEquals(stringExpected, image.toPPMText());

    Image image2 = image.colorTransform(matrix);
    String stringExpected2 = "P3\n" +
            "4 4\n" +
            "255\n" +
            "100 89 69  100 89 69  11 10 8  11 10 8  \n" +
            "100 89 69  100 89 69  11 10 8  11 10 8  \n" +
            "0 0 0  0 0 0  65 58 45  65 58 45  \n" +
            "0 0 0  0 0 0  65 58 45  65 58 45  \n";
    assertEquals(stringExpected2, image2.toPPMText());


  }


}
