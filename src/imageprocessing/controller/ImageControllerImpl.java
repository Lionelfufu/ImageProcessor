package imageprocessing.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

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
import imageprocessing.controller.commands.Save;
import imageprocessing.controller.commands.Sepia;
import imageprocessing.controller.commands.Sharpen;
import imageprocessing.controller.commands.VerticalFlip;
import imageprocessing.model.ImagesModel;
import imageprocessing.view.ImageView;
import imageprocessing.controller.commands.Load;


/**
 * This class is an implementation of the ImageController interface. It can be used to control the
 * ImageProcessing program.
 */
public class ImageControllerImpl implements ImageController {
  private Readable in;
  private ImageView view;
  private ImagesModel model;

  private Map<String, Function<Scanner, ImagesProcessingCommand>> commands;

  /**
   * Construct an ImageControllerImpl with the given Readable, ImageView and ImagesModel.
   *
   * @param in    a Readable message from user to parse
   * @param view  a ImageView
   * @param model a ImagesModel
   */
  public ImageControllerImpl(Readable in, ImageView view, ImagesModel model) {
    this.in = Objects.requireNonNull(in);
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
    this.commands = new HashMap<>();

    commands.put("load", (Scanner s) -> {
      return new Load(s.next(), s.next());
    });

    commands.put("save", (Scanner s) -> {
      return new Save(s.next(), s.next());
    });

    commands.put("red-component", (Scanner s) -> {
      return new GreyScaleRed(s.next(), s.next());
    });

    commands.put("green-component", (Scanner s) -> {
      return new GreyScaleGreen(s.next(), s.next());
    });

    commands.put("blue-component", (Scanner s) -> {
      return new GreyScaleBlue(s.next(), s.next());
    });

    commands.put("value-component", (Scanner s) -> {
      return new GreyScaleMaxValue(s.next(), s.next());
    });

    commands.put("intensity-component", (Scanner s) -> {
      return new GreyScaleIntensity(s.next(), s.next());
    });

    commands.put("luma-component", (Scanner s) -> {
      return new GreyScaleLuma(s.next(), s.next());
    });

    commands.put("horizontal-flip", (Scanner s) -> {
      return new HorizontalFlip(s.next(), s.next());
    });

    commands.put("vertical-flip", (Scanner s) -> {
      return new VerticalFlip(s.next(), s.next());
    });

    commands.put("blur", (Scanner s) -> {
      return new Blur(s.next(), s.next());
    });

    commands.put("sharpen", (Scanner s) -> {
      return new Sharpen(s.next(), s.next());
    });

    commands.put("sepia", (Scanner s) -> {
      return new Sepia(s.next(), s.next());
    });

    commands.put("greyscale", (Scanner s) -> {
      return new GreyScale(s.next(), s.next());
    });

    commands.put("brighten", (Scanner s) -> {
      return new Brighten(s.nextInt(), s.next(), s.next());
    });

    commands.put("darken", (Scanner s) -> {
      return new Darken(s.nextInt(), s.next(), s.next());
    });


  }


  /**
   * Start the ImageProcessing program. This method will parse the inputs from the user and then
   * do the corresponding actions.
   *
   * @throws IOException when fail to append a message
   */
  @Override
  public void run() throws IOException {

    Scanner scan = new Scanner(in);

    while (scan.hasNext()) {
      ImagesProcessingCommand c;
      String in = scan.next();

      if (in.equalsIgnoreCase("q")) {
        return;
      }

      Function<Scanner, ImagesProcessingCommand> function = commands.getOrDefault(in,
              null);

      if (function == null) {
        this.view.renderMessage("Command not supported.");
      } else {
        try {
          c = function.apply(scan); //get the command
          model.execute(c);
          if (in.equals("load") || in.equals("save")) {
            view.renderMessage(String.format("Successfully %s.", c.getCommandName()));
          } else {
            view.renderMessage(String.format("Successfully add a %s image.", c.getCommandName()));
          }
        } catch (IllegalArgumentException e) {
          if (in.equals("load")) {
            view.renderMessage("The given file not found!");
          } else if (in.equals("save")) {
            view.renderMessage("Fail to save the given image!");
          } else {
            view.renderMessage("Execution failed.");
          }
        }
      }
    }

  }
}
