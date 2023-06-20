package imageprocessing.view;

import imageprocessing.model.Image;
import imageprocessing.model.ImageUtil;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;

/**
 * This class represents a Histogram of an Image which is a table showing the relationship between
 * the values(brightness 0 - 255) and the frequency(the occurances of a value).
 */
public class Histogram extends JPanel {
  private int width = 256;
  private int height;
  private Color color;

  private Map<Integer, Integer> histogram;

  /**
   * Constructs a Histogram with the given image and a String representing the component to use to
   * make the histogram.
   *
   * @param image a image to use
   * @param type  a String(red, green, blue or intensity)
   */
  public Histogram(Image image, String type) {
    Objects.requireNonNull(image);

    if (type.equals("red")) {
      this.color = Color.red;
    } else if (type.equals("green")) {
      this.color = Color.green;
    } else if (type.equals("blue")) {
      this.color = Color.blue;
    } else if (type.equals("intensity")) {
      this.color = Color.gray;
    }

    histogram = ImageUtil.imageToHistogram(image, type);

    int maxY = 0;
    for (int i = 0; i < 256; i++) {
      if (histogram.get(i) > maxY) {
        maxY = histogram.get(i);
      }
    }

    this.height = maxY;

    this.setPreferredSize(new Dimension(this.width, this.height));
    this.setBackground(Color.white);


  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(this.color);
    int x = 0;
    for (int i = 0; i < 256; i++) {
      int frequency = this.histogram.get(i);
      int y = this.height - frequency;
      g.fillRect(x, y, 1, frequency);
      x = x + 1;
    }

  }

}
