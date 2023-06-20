package controllertest;

import org.junit.Test;


import java.io.IOException;
import java.io.StringReader;


import imageprocessing.controller.ImageController;
import imageprocessing.controller.ImageControllerImpl;
import imageprocessing.model.ImagesModel;
import imageprocessing.model.ImagesModelImpl;

import imageprocessing.view.ImageView;
import imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is a tester for ImageControllerImpl.
 */
public class ImageControllerImplTest {

  /**
   * Tests if the user's commands can be correctly pass to the controller.
   */
  @Test
  public void testPassCorrectCommand() throws IOException {

    //Test1 for Load
    Appendable inputThatTheModelReceived = new StringBuilder();
    Readable userInput = new StringReader("load res/Images/red.ppm p1");
    Appendable output = new StringBuilder();

    ImageView view = new ImageViewImpl(output);

    ImagesModel model = new MockImagesModelImpl(inputThatTheModelReceived);

    ImageController controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Command to execute is Load\n", inputThatTheModelReceived.toString());

    //Test2
    userInput = new StringReader("save res/Images p1");

    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();
    assertEquals("Command to execute is Load\n"
            + "Command to execute is Save\n", inputThatTheModelReceived.toString());

    //Test3
    userInput = new StringReader("brighten 5 p1 p2");

    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();
    assertEquals("Command to execute is Load\n"
            + "Command to execute is Save\n"
            + "Command to execute is Brighten\n", inputThatTheModelReceived.toString());

    //Test3
    userInput = new StringReader("darken 5 p1 p3");

    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();
    assertEquals("Command to execute is Load\n"
            + "Command to execute is Save\n"
            + "Command to execute is Brighten\n"
            + "Command to execute is Darken\n", inputThatTheModelReceived.toString());

    //Test4
    userInput = new StringReader("horizontal-flip p1 p4");

    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();
    assertEquals("Command to execute is Load\n"
            + "Command to execute is Save\n"
            + "Command to execute is Brighten\n"
            + "Command to execute is Darken\n"
            + "Command to execute is HorizontalFlip\n", inputThatTheModelReceived.toString());

    //Test5
    userInput = new StringReader("vertical-flip p1 p5");

    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();
    assertEquals("Command to execute is Load\n"
            + "Command to execute is Save\n"
            + "Command to execute is Brighten\n"
            + "Command to execute is Darken\n"
            + "Command to execute is HorizontalFlip\n"
            + "Command to execute is VerticalFlip\n", inputThatTheModelReceived.toString());

    //Test6
    userInput = new StringReader("red-component p1 p6 "
            + "green-component p1 p7 "
            + "blue-component p1 p8 "
            + "value-component p1 p9 "
            + "intensity-component p1 p10 "
            + "luma-component p1 p11 ");

    controller = new ImageControllerImpl(userInput, view, model);
    controller.run();

    assertEquals("Command to execute is Load\n" +
            "Command to execute is Save\n" +
            "Command to execute is Brighten\n" +
            "Command to execute is Darken\n" +
            "Command to execute is HorizontalFlip\n" +
            "Command to execute is VerticalFlip\n" +
            "Command to execute is GreyScaleRed\n" +
            "Command to execute is GreyScaleGreen\n" +
            "Command to execute is GreyScaleBlue\n" +
            "Command to execute is GreyScaleMaxValue\n" +
            "Command to execute is GreyScaleIntensity\n" +
            "Command to execute is GreyScaleLuma\n", inputThatTheModelReceived.toString());

    userInput = new StringReader("blur p1 p12 "
            + "sharpen p1 p13 "
            + "greyscale p1 p14 "
            + "sepia p1 p15 ");

    controller = new ImageControllerImpl(userInput, view, model);
    controller.run();

    assertEquals("Command to execute is Load\n" +
            "Command to execute is Save\n" +
            "Command to execute is Brighten\n" +
            "Command to execute is Darken\n" +
            "Command to execute is HorizontalFlip\n" +
            "Command to execute is VerticalFlip\n" +
            "Command to execute is GreyScaleRed\n" +
            "Command to execute is GreyScaleGreen\n" +
            "Command to execute is GreyScaleBlue\n" +
            "Command to execute is GreyScaleMaxValue\n" +
            "Command to execute is GreyScaleIntensity\n" +
            "Command to execute is GreyScaleLuma\n" +
            "Command to execute is Blur\n" +
            "Command to execute is Sharpen\n" +
            "Command to execute is GreyScale\n" +
            "Command to execute is Sepia\n", inputThatTheModelReceived.toString());


  }

  /**
   * This class test if the controller give the correct when fail to execute a command.
   *
   * @throws IOException when fail to append a message
   */
  @Test
  public void ExecuteFailTest() throws IOException {
    Readable userInput = new StringReader("horizontal-flip p1 p2");
    Appendable output = new StringBuilder();

    ImageView view = new ImageViewImpl(output);

    ImagesModel model = new ImagesModelImpl();

    ImageController controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Execution failed.\n", output.toString());

    //test2
    userInput = new StringReader("vertical-flip p1 p2");
    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Execution failed.\n"
            + "Execution failed.\n", output.toString());

    //test3
    userInput = new StringReader("brighten 5 p1 p2");
    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Execution failed.\n"
            + "Execution failed.\n"
            + "Execution failed.\n", output.toString());

    //test4
    userInput = new StringReader("load abc p1");
    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Execution failed.\n"
                    + "Execution failed.\n"
                    + "Execution failed.\n"
                    + "The given file not found!\n"
            , output.toString());

    //test5
    userInput = new StringReader("save abc p1");
    controller = new ImageControllerImpl(userInput, view, model);

    controller.run();

    assertEquals("Execution failed.\n" +
            "Execution failed.\n" +
            "Execution failed.\n" +
            "The given file not found!\n" +
            "Fail to save the given image!\n", output.toString());


  }


}
