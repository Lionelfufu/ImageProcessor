package imageprocessing.model;



/**
 * This ImageImpl class represents an PPM Image which is an Image consisting of pixels. In this
 * class, a 3D array of integers is used to store the information of the image. Each pixel
 * can be represented by 3 integers which are its 3 component, red, green and blue,
 * for example, int[0][0][0] represents red component of the pixel in position(0, 0),
 * int[0][0][1] represents green component of the pixel in position(0, 0)
 * and int[0][0][2] represents blue component of the pixel in position(0, 0). Thus every
 * int[i][j][0], int[i][j][1], and int[i][j][2] represents a pixel.
 * ImageImpl is a rectangle picture. Its width is its number of pixels of width and its height is
 * its number pixels of its height. The maxValue of an Image represents its largest brightness
 * of a components of a pixel can reach.
 */

public class ImageImpl implements Image {
  private int[][][] image;
  private int width;
  private int height;
  private int maxValue;

  /**
   * Construct an Image according to the given filename.
   *
   * @param filename the path of the PPM file
   * @throws IllegalArgumentException when fail to read the given file
   */
  public ImageImpl(String filename) throws IllegalArgumentException {
    if ("ppm".equalsIgnoreCase(filename.substring(filename.length() - 3, filename.length()))) {
      this.image = ImageUtil.readPPMtoInt(filename);
      this.height = this.image.length;
      this.width = this.image[0].length;
      this.maxValue = ImageUtil.getMaxValue(filename);
    } else {
      this.image = ImageUtil.readBufferedImageToInt(filename);
      this.height = this.image.length;
      this.width = this.image[0].length;
      this.maxValue = 255;
    }

  }


  /**
   * Construct an Image according to the given image in 3D array of integer form, width, height
   * and maxValue.
   *
   * @param image    3D array of integer
   * @param width    an integer
   * @param height   an integer
   * @param maxValue an integer
   */
  public ImageImpl(int[][][] image, int width, int height, int maxValue) {
    this.image = image;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }


  @Override
  public int getWidth() {
    return Integer.valueOf(this.width);
  }

  @Override
  public int getHeight() {
    return Integer.valueOf(this.height);
  }

  @Override
  public int getMaxValue() {
    return Integer.valueOf(this.maxValue);
  }

  @Override
  public int[][][] getImage() {
    return this.image;

  }

  /**
   * Return a string which is text that can be used to write a PPM file of this ImageImpl.
   *
   * @return a String
   */
  @Override
  public String toPPMText() {
    String text = "";
    int width = getWidth();
    int height = getHeight();
    int maxValue = getMaxValue();
    text = text + "P3" + "\n";
    text = text + Integer.toString(width) + " " + Integer.toString(height) + "\n";
    text = text + Integer.toString(maxValue) + "\n";

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j][0]; //red
        int g = image[i][j][1]; //Green
        int b = image[i][j][2]; //Blue
        text = text + Integer.toString(r) + " " + Integer.toString(g) + " " + Integer.toString(b)
                + "  ";
        if (j == image[0].length - 1) {
          text = text + "\n";
        }
      }
    }

    return text;
  }

  /**
   * returns an greyScaled image according to the given component.
   *
   * @param component the component used to greyscale, which should be one of red, green, blue, max,
   *                  intensity or luma
   * @return an Image
   */


  @Override
  public Image greyScale(String component) {
    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int value;
        int r = image[i][j][0];
        int g = image[i][j][1];
        int b = image[i][j][2];
        if (component.equals("red")) {
          value = r;
        } else if (component.equals("green")) {
          value = g;
        } else if (component.equals("blue")) {
          value = b;
        } else if (component.equals("max")) {
          value = Math.max(r, Math.max(g, b));
        } else if (component.equals("intensity")) {
          value = Math.round((r + g + b) / 3);
        } else if (component.equals("luma")) {
          value = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
        } else {
          throw new IllegalArgumentException("Invalid component.");
        }

        newImage[i][j][0] = value;
        newImage[i][j][1] = value;
        newImage[i][j][2] = value;
      }
    }

    return new ImageImpl(newImage, width, height, maxValue);
  }


  /**
   * Returns a horizontal flipped Image.
   *
   * @return a Image
   */
  public Image horizontalFlip() {
    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        newImage[i][j][0] = image[i][width - j - 1][0];
        newImage[i][j][1] = image[i][width - j - 1][1];
        newImage[i][j][2] = image[i][width - j - 1][2];
      }
    }
    return new ImageImpl(newImage, width, height, maxValue);

  }

  /**
   * Returns a vertical flipped Image.
   *
   * @return a Image
   */
  public Image verticalFlip() {
    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        newImage[i][j][0] = image[height - i - 1][j][0];
        newImage[i][j][1] = image[height - i - 1][j][1];
        newImage[i][j][2] = image[height - i - 1][j][2];
      }
    }
    return new ImageImpl(newImage, width, height, maxValue);

  }

  /**
   * Returns a brightened Image with the given decrement.
   *
   * @param increment an integer
   * @return a Image
   */
  public Image brighten(int increment) {
    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j][0];
        int g = image[i][j][1];
        int b = image[i][j][2];
        if (r + increment <= maxValue) {
          newImage[i][j][0] = r + increment;
        } else {
          newImage[i][j][0] = maxValue;
        }

        if (g + increment <= maxValue) {
          newImage[i][j][1] = g + increment;
        } else {
          newImage[i][j][1] = maxValue;
        }

        if (b + increment <= maxValue) {
          newImage[i][j][2] = b + increment;
        } else {
          newImage[i][j][2] = maxValue;
        }
      }
    }

    return new ImageImpl(newImage, width, height, maxValue);

  }

  /**
   * Returns a darkened Image with the given decrement.
   *
   * @param decrement an integer
   * @return a Image
   */
  public Image darken(int decrement) {
    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j][0];
        int g = image[i][j][1];
        int b = image[i][j][2];
        if (r - decrement >= 0) {
          newImage[i][j][0] = r - decrement;
        } else {
          newImage[i][j][0] = 0;
        }

        if (g - decrement >= 0) {
          newImage[i][j][1] = g - decrement;
        } else {
          newImage[i][j][1] = 0;
        }

        if (b - decrement >= 0) {
          newImage[i][j][2] = b - decrement;
        } else {
          newImage[i][j][2] = 0;
        }
      }
    }

    return new ImageImpl(newImage, width, height, maxValue);

  }

  @Override
  public Image filter(float[][] filterMatrix) {
    if ((filterMatrix.length != filterMatrix[0].length)
            || (filterMatrix.length % 2 == 0)) {
      throw new IllegalArgumentException("The given matrix is invalid");
    }

    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = getAFilteredComponent(i, j, k, image, filterMatrix);
        }
      }
    }

    return new ImageImpl(newImage, width, height, maxValue);

  }

  /**
   * returns a number representing a filtered component of a pixel. For example, if the given i = 1,
   * j = 1, k = 0, that means this method will return the red component on position(1, 1) of the
   * given imageArray after filtering by the given filterMatrix.
   *
   * @param i            the height position
   * @param j            the width position
   * @param k            represents the component,where 0 represents red, 1 represents green
   *                     and 2 represents blue
   * @param imageArray   the original image in the form of 3D array of integers
   * @param filterMatrix the matrix used to filter
   * @return an integer
   */
  private int getAFilteredComponent(int i, int j, int k, int[][][] imageArray,
                                   float[][] filterMatrix) {
    if ((filterMatrix.length != filterMatrix[0].length)
            || (filterMatrix.length % 2 == 0)) {
      throw new IllegalArgumentException("The given matrix is invalid");
    }


    float component = 0;
    int midIndexOfMatrix = (filterMatrix.length - 1) / 2;

    for (int x = 0; x < filterMatrix.length; x++) {
      for (int y = 0; y < filterMatrix[0].length; y++) {
        int xPositionToMove = x - midIndexOfMatrix;
        int yPositionToMove = y - midIndexOfMatrix;
        int xPosition = i + xPositionToMove;
        int yPosition = j + yPositionToMove;

        if (xPosition >= 0
                && xPosition < imageArray.length
                && yPosition >= 0
                && yPosition < imageArray[0].length) {
          component = component + filterMatrix[x][y] * imageArray[xPosition][yPosition][k];
        }
      }
    }

    int result = Math.round(component);

    if (result > 255) {
      return 255;
    } else if (result < 0) {
      return 0;
    } else {
      return result;
    }


  }

  @Override
  public Image colorTransform(float[][] matrix) {
    if (matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("Invalid matrix.");
    }

    int[][][] newImage = new int[height][width][3];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j][0];
        int g = image[i][j][1];
        int b = image[i][j][2];
        float newR = matrix[0][0] * r + matrix[0][1] * g + matrix[0][2] * b;
        float newG = matrix[1][0] * r + matrix[1][1] * g + matrix[1][2] * b;
        float newB = matrix[2][0] * r + matrix[2][1] * g + matrix[2][2] * b;

        newImage[i][j][0] = Math.max(Math.min(Math.round(newR), 255), 0);
        newImage[i][j][1] = Math.max(Math.min(Math.round(newG), 255), 0);
        newImage[i][j][2] = Math.max(Math.min(Math.round(newB), 255), 0);

      }
    }

    return new ImageImpl(newImage, width, height, maxValue);

  }


}




