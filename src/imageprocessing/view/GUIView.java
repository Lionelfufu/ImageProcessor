package imageprocessing.view;


import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;


import imageprocessing.model.Image;

/**
 * The GUIView interface. To implement this interface to make a GUI to use.
 */
public interface GUIView {

  /**
   * Render a message to the user.
   *
   * @param message the message to render.
   */
  void renderMessage(String message);

  /**
   * Render a new image to the user, which is the image being worked by the user.
   *
   * @param image the image being work
   */
  void renderNewImage(Image image);

  /**
   * return the JTextField including the name of the image to save the image.
   *
   * @return a JTextField
   */
  JTextField getImageNameToSave();

  /**
   * Make all components add a listener to the controller.
   *
   * @param controller the controller for controlling the GUI
   */
  void addListeners(ActionListener controller);

  /**
   * Returns the JLabel including the name of the file to load.
   *
   * @return a JLabel
   */
  JLabel getFileToLoad();


  /**
   * Returns the JLabel including the path of the file to save.
   *
   * @return a JLabel
   */
  JLabel getPathToSave();

  /**
   * return the JTextField including the increment to brighten the image.
   *
   * @return a JTextField
   */
  JTextField getIncrement();

  /**
   * return the JTextField including the decrement to darken the image.
   *
   * @return a JTextField
   */
  JTextField getDecrement();


}
