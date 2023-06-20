
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import imageprocessing.controller.GUIControllerImpl;
import imageprocessing.controller.ImageController;
import imageprocessing.controller.ImageControllerImpl;

import imageprocessing.model.ImagesModel;
import imageprocessing.model.ImagesModelImpl;

import imageprocessing.view.GUIView;
import imageprocessing.view.GUIViewImpl;
import imageprocessing.view.ImageView;
import imageprocessing.view.ImageViewImpl;

/**
 * The entrance of the ImageProcessing Program.
 */
public class ImageProcessingProgram {
  /**
   * The main method of the program. It parses program arguments from the user to do the
   * corresponding actions.
   *
   * @param args Program arguments.
   * @throws IOException when fail to append a message
   */
  public static void main(String[] args) throws IOException {
    ImageView view = new ImageViewImpl(System.out);
    ImagesModel model = new ImagesModelImpl();
    ImageController controller;


    if ((args.length == 2) && args[0].equals("-file")) {
      try {
        Reader input = new FileReader(args[1]);
        controller = new ImageControllerImpl(input, view, model);
        controller.run();
      } catch (FileNotFoundException e) {
        System.out.println("The given file no found!");
      }
    } else if ((args.length == 1) && args[0].equals("-text")) {
      controller = new ImageControllerImpl(new InputStreamReader(System.in),
              view,
              model);
      controller.run();
    } else {
      GUIView guiView = new GUIViewImpl();
      controller = new GUIControllerImpl(guiView, model);
      controller.run();
    }

  }
}
