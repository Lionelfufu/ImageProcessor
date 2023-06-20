package imageprocessing.model;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Read an image file in the PPM format and return a 3D array of numbers representing the PPM
   * image.
   *
   * @param filename the path of the file
   * @return a 3D array representing the PPM image
   * @throws IllegalArgumentException when cannot find the given filename
   */
  public static int[][][] readPPMtoInt(String filename) throws IllegalArgumentException {
    Scanner sc;
    int[][][] image;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The given file " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    image = new int[height][width][3];

    int maxValue = sc.nextInt();


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[i][j][0] = sc.nextInt(); //red
        image[i][j][1] = sc.nextInt(); //Green
        image[i][j][2] = sc.nextInt(); //Blue
      }
    }

    return image;
  }

  /**
   * This is a method to read the buffered image to a 3D array of integers to store all information
   * of the image.
   *
   * @param filename the name of the image to read
   * @return a 3D array of integers representing the image
   * @throws IllegalArgumentException when cannot find the given filename or cannot read the image
   */
  public static int[][][] readBufferedImageToInt(String filename) throws IllegalArgumentException {

    BufferedImage bufferedImage;
    int height;
    int width;
    int[][][] image;
    InputStream input;

    try {
      input = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The given file " + filename + " not found!");
    }


    try {
      bufferedImage = ImageIO.read(input);
      bufferedImage.getHeight();
    } catch (NullPointerException | IOException e) {
      throw new IllegalArgumentException("Cannot read the input stream.");
    }

    height = bufferedImage.getHeight();
    width = bufferedImage.getWidth();
    image = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = bufferedImage.getRGB(j, i);
        Color color = new Color(rgb);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        image[i][j][0] = r; //red
        image[i][j][1] = g; //Green
        image[i][j][2] = b; //Blue
      }
    }

    return image;

  }

  /**
   * This is a method to save a given Image to a given path with the given file format. This method
   * is created for save Image to format which is not .ppm format.
   *
   * @param image      the image to save
   * @param path       the path to save
   * @param fileFormat the format of the file to save
   * @throws IOException when fail to write the buffered image to the file.
   */
  public static void saveOtherImage(Image image, String path, String fileFormat)
          throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imageArray = image.getImage();

    BufferedImage bufferedImage = new BufferedImage(width, height, 1);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = imageArray[i][j][0];
        int g = imageArray[i][j][1];
        int b = imageArray[i][j][2];

        Color color = new Color(r, g, b);

        bufferedImage.setRGB(j, i, color.getRGB());
      }
    }

    File file = new File(path);

    ImageIO.write(bufferedImage, fileFormat, file);


  }

  /**
   * Read an Image and create a buffered image. Return the buffered image.
   *
   * @param image the image to read
   * @return the bufferedImage created by the image given
   */
  public static BufferedImage readImageToBufferedImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imageArray = image.getImage();

    BufferedImage bufferedImage = new BufferedImage(width, height, 1);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = imageArray[i][j][0];
        int g = imageArray[i][j][1];
        int b = imageArray[i][j][2];

        Color color = new Color(r, g, b);

        bufferedImage.setRGB(j, i, color.getRGB());
      }
    }
    return bufferedImage;

  }

  /**
   * Read an image and used it to create a histogram of the given component, which is the
   * given String.
   *
   * @param image the image to read
   * @param type  String(red, green, blue or intensity)
   * @return a Map representing the data of the histogram of the given image
   */
  public static Map<Integer, Integer> imageToHistogram(Image image, String type) {
    Objects.requireNonNull(image);

    Map<Integer, Integer> redHistogram = new HashMap<>();
    Map<Integer, Integer> greenHistogram = new HashMap<>();
    Map<Integer, Integer> blueHistogram = new HashMap<>();
    Map<Integer, Integer> intensityHistogram = new HashMap<>();

    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imageArray = image.getImage();

    for (int i = 0; i < 256; i++) {
      redHistogram.put(i, 0);
      greenHistogram.put(i, 0);
      blueHistogram.put(i, 0);
      intensityHistogram.put(i, 0);
    }


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = imageArray[i][j][0];
        int g = imageArray[i][j][1];
        int b = imageArray[i][j][2];
        int value = Math.round((r + g + b) / 3);

        redHistogram.put(r, redHistogram.get(r) + 1);
        greenHistogram.put(g, greenHistogram.get(g) + 1);
        blueHistogram.put(b, blueHistogram.get(b) + 1);
        intensityHistogram.put(value, intensityHistogram.get(value) + 1);
      }
    }


    if (type.equals("red")) {
      return redHistogram;
    } else if (type.equals("green")) {
      return greenHistogram;
    } else if (type.equals("blue")) {
      return blueHistogram;
    } else if (type.equals("intensity")) {
      return intensityHistogram;
    } else {
      throw new IllegalArgumentException("No this type support");
    }

  }


  /**
   * Returns the max value of a PPM file according to its filename.
   *
   * @param filename the path of the file
   * @return an integer
   * @throws IllegalArgumentException when can not find the given file
   */
  public static int getMaxValue(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The given file" + filename + "not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    return maxValue;
  }


}

