package imageprocessing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import imageprocessing.controller.commands.Blur;
import imageprocessing.controller.commands.Brighten;
import imageprocessing.controller.commands.Darken;
import imageprocessing.controller.commands.GreyScale;
import imageprocessing.controller.commands.GreyScaleBlue;
import imageprocessing.controller.commands.GreyScaleGreen;
import imageprocessing.controller.commands.GreyScaleIntensity;
import imageprocessing.controller.commands.GreyScaleLuma;
import imageprocessing.controller.commands.GreyScaleMaxValue;
import imageprocessing.controller.commands.GreyScaleRed;
import imageprocessing.controller.commands.HorizontalFlip;
import imageprocessing.controller.commands.ImagesProcessingCommand;
import imageprocessing.controller.commands.Load;
import imageprocessing.controller.commands.Save;
import imageprocessing.controller.commands.Sepia;
import imageprocessing.controller.commands.Sharpen;
import imageprocessing.controller.commands.VerticalFlip;
import imageprocessing.model.ImagesModel;
import imageprocessing.view.GUIView;


/**
 * This class is an implementation of the ImageController interface. It can be used to control the
 * image processing program and GUI.
 */
public class GUIControllerImpl implements ActionListener, ImageController {
  private GUIView view;
  private ImagesModel model;

  private Map<String, ImagesProcessingCommand> commands;

  /**
   * Constructs a GUIControllerImpl with the given view and model.
   *
   * @param view  the view to construct
   * @param model the model to construct
   */
  public GUIControllerImpl(GUIView view, ImagesModel model) {
    this.view = view;
    this.model = model;
    this.commands = new HashMap<>();

    commands.put("greyscale red button", new GreyScaleRed("imageToWork",
            "imageToWork"));

    commands.put("greyscale green button", new GreyScaleGreen("imageToWork",
            "imageToWork"));

    commands.put("greyscale blue button", new GreyScaleBlue("imageToWork",
            "imageToWork"));

    commands.put("greyscale value button", new GreyScaleMaxValue("imageToWork",
            "imageToWork"));

    commands.put("greyscale intensity button", new GreyScaleIntensity("imageToWork",
            "imageToWork"));

    commands.put("greyscale luma button", new GreyScaleLuma("imageToWork",
            "imageToWork"));

    commands.put("greyscale button", new GreyScale("imageToWork",
            "imageToWork"));

    commands.put("sepia button", new Sepia("imageToWork",
            "imageToWork"));

    commands.put("blur button", new Blur("imageToWork",
            "imageToWork"));

    commands.put("sharpen button", new Sharpen("imageToWork",
            "imageToWork"));

    commands.put("horizontal flip button", new HorizontalFlip("imageToWork",
            "imageToWork"));

    commands.put("vertical flip button", new VerticalFlip("imageToWork",
            "imageToWork"));


  }

  @Override
  public void run() {
    this.view.addListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int retvalue;
    JFileChooser chooser;
    switch (e.getActionCommand()) {
      case "load button":
        //view
        chooser = new JFileChooser(".");
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("JPG, JPEG, PPM, BMP & PNG Images",
                        new String[]{"jpeg", "ppm", "bmp", "png", "jpg"});
        chooser.setFileFilter(filter);

        retvalue = chooser.showOpenDialog(this.view.getFileToLoad());

        if (retvalue == 0) {
          File f = chooser.getSelectedFile();
          this.view.getFileToLoad().setText(f.getAbsolutePath());

          //model
          try {
            ImagesProcessingCommand command = new Load(f.getAbsolutePath(),
                    "imageToWork");
            model.execute(command);
            view.renderNewImage(model.getAnImage("imageToWork"));
            view.renderMessage("Successfully load an image");
          } catch (IllegalArgumentException a) {
            view.renderMessage("Execution failed.");
          }
        }
        break;
      case "save button":
        //view
        chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        retvalue = chooser.showOpenDialog(this.view.getPathToSave());

        if (retvalue == 0) {
          File f = chooser.getSelectedFile();
          this.view.getPathToSave().setText(f.getAbsolutePath());

          //model
          try {
            ImagesProcessingCommand command = new Save(f.getAbsolutePath() + "/" +
                    this.view.getImageNameToSave().getText(), "imageToWork");
            model.execute(command);
            view.renderMessage("Successfully save an image");
          } catch (IllegalArgumentException a) {
            view.renderMessage("Execution failed.");
          }
        }
        break;
      case "quit button":
        System.exit(0);
        break;
      case "brighten button":
        try {
          int increment = Integer.parseInt(this.view.getIncrement().getText());
          ImagesProcessingCommand command = new Brighten(increment, "imageToWork",
                  "imageToWork");
          model.execute(command);
          view.renderNewImage(model.getAnImage("imageToWork"));
          view.renderMessage("Successfully brighten the image");
        } catch (NumberFormatException a) {
          view.renderMessage("Please enter an integer to brighten the image.");
        } catch (IllegalArgumentException a) {
          view.renderMessage("Please load an image.");
        }
        break;
      case "darken button":
        try {
          int decrement = Integer.parseInt(this.view.getDecrement().getText());
          ImagesProcessingCommand command = new Darken(decrement, "imageToWork",
                  "imageToWork");
          model.execute(command);
          view.renderNewImage(model.getAnImage("imageToWork"));
          view.renderMessage("Successfully darken the image");
        } catch (NumberFormatException a) {
          view.renderMessage("Please enter an integer to darken the image.");
        } catch (IllegalArgumentException a) {
          view.renderMessage("Please load an image.");
        }
        break;

      default:
        try {
          ImagesProcessingCommand command = commands.get(e.getActionCommand());
          model.execute(command);
          view.renderNewImage(model.getAnImage("imageToWork"));
          view.renderMessage(String.format("Successfully %s the image.",
                  command.getCommandName()));
        } catch (IllegalArgumentException a) {
          view.renderMessage("Execution failed.");
        }
        break;


    }

  }
}
