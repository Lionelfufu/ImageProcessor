package imageprocessing.model;

/**
 * This interface represents an Image which is an Image consisting of pixels. It is and rectangle
 * picture.
 */
public interface Image {

  /**
   * Returns the width of the image.
   *
   * @return an integer which is the width of the image
   */
  int getWidth();

  /**
   * Returns the height of the image.
   *
   * @return an integer which is the height of the image
   */
  int getHeight();

  /**
   * Returns the max value of the image which is the largest brightness that the image can reach.
   *
   * @return an integer which is the max value of the iamge
   */
  int getMaxValue();


  /**
   * Returns an Image in 3D array of integer form.
   *
   * @return a 3D array
   */
  int[][][] getImage();


  /**
   * Return a string which is text that can be used to write a PPM file of this PPMImage.
   *
   * @return a String
   */
  String toPPMText();

  /**
   * returns an greyScaled image according to the given component.
   *
   * @param component the component used to greyscale
   * @return an Image
   */

  Image greyScale(String component);


  /**
   * Returns a horizontal flipped Image.
   *
   * @return a Image
   */
  Image horizontalFlip();

  /**
   * Returns a vertical flipped Image.
   *
   * @return a Image
   */
  Image verticalFlip();

  /**
   * Returns a brightened Image with the given increment.
   *
   * @return a Image
   */
  Image brighten(int increment);

  /**
   * Returns a darkened Image with the given decrement.
   *
   * @return a Image
   */
  Image darken(int decrement);

  /**
   * returns a Image after filtering by the given matrix.
   *
   * @param filterMatrix the matrix used to filter the image
   * @return an Image
   */
  Image filter(float[][] filterMatrix);


  /**
   * Returns an Image representing the Image after color transformation.
   *
   * @param matrix the matrix used to color transformation which must be a 3x3 matrix
   * @return an Image
   */
  Image colorTransform(float[][] matrix);
}
