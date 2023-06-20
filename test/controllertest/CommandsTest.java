package controllertest;

import org.junit.Test;

import imageprocessing.controller.commands.Brighten;
import imageprocessing.controller.commands.Darken;
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
import imageprocessing.controller.commands.VerticalFlip;
import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;
import imageprocessing.model.ImagesModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is a tester for all commands. It tests if all commands in the commmands package works
 * correctly for the model.
 */
public class CommandsTest {

  /**
   * Test for the command Load.
   */
  @Test
  public void LoadTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/red.ppm";
    String imageName = "red";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);

    try {
      model.getAnImage("red");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find red!", e.getMessage());
    }

    loadCommand.process(model);

    Image imageLoaded = model.getAnImage("red");

    assertEquals("P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
            "255 0 0  255 0 0  255 0 0  255 0 0  \n", imageLoaded.toPPMText());
  }

  /**
   * Test for the command Save.
   */
  @Test
  public void SaveTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/red.ppm";
    String imageName = "red";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    String pathName2 = "res/Images/red2.ppm";
    String imageName2 = "red2";
    try {
      ImagesProcessingCommand loadCommand2 = new Load(pathName2, imageName2);
    } catch (IllegalArgumentException e) {
      assertEquals("The given file res/Images/red2.ppm not found!", e.getMessage());
    }


    //save
    String pathName3 = "res/Images/red2.ppm";
    String imageName3 = "red";
    ImagesProcessingCommand saveCommand = new Save(pathName3, imageName3);
    saveCommand.process(model);


    //After this red save, it can load
    String pathName4 = "res/Images/red2.ppm";
    String imageName4 = "red2";
    ImagesProcessingCommand loadCommand4 = new Load(pathName4, imageName4);
    loadCommand4.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n",
            model.getAnImage("red2").toPPMText());


  }

  /**
   * Test if the command Save can save an Image to format other than ppm format.
   */
  @Test
  public void SaveToOtherFormatTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/red.ppm";
    String imageName = "red";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    //save to png format
    String pathName2 = "res/Images/red2.png";
    ImagesProcessingCommand saveCommand = new Save(pathName2, imageName);
    saveCommand.process(model);

    //load the saved png image
    ImagesProcessingCommand loadCommand2 = new Load(pathName2, "red2");
    loadCommand2.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n",
            model.getAnImage("red2").toPPMText());


    //save to jpeg format
    String pathName3 = "res/Images/red3.jpeg";
    ImagesProcessingCommand saveCommand2 = new Save(pathName3, imageName);
    saveCommand2.process(model);

    //load the saved jpeg image
    ImagesProcessingCommand loadCommand3 = new Load(pathName3, "red3");
    loadCommand3.process(model);


    //save to bmp format
    String pathName4 = "res/Images/red4.bmp";
    ImagesProcessingCommand saveCommand3 = new Save(pathName4, imageName);
    saveCommand3.process(model);

    //load the saved bmp image
    ImagesProcessingCommand loadCommand4 = new Load(pathName4, "red4");
    loadCommand4.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n" +
                    "255 0 0  255 0 0  255 0 0  255 0 0  \n",
            model.getAnImage("red4").toPPMText());


  }

  /**
   * Test for the command GreyScaleRed.
   */
  @Test
  public void GreyScaleRedTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleRed(name, destName);
    command.process(model);
    assertEquals("P3\n" +
            "4 4\n" +
            "255\n" +
            "255 255 255  255 255 255  0 0 0  0 0 0  \n" +
            "255 255 255  255 255 255  0 0 0  0 0 0  \n" +
            "0 0 0  0 0 0  0 0 0  0 0 0  \n" +
            "0 0 0  0 0 0  0 0 0  0 0 0  \n", model.getAnImage("mix2").toPPMText());


  }


  /**
   * Test for the command GreyScaleBlue.
   */
  @Test
  public void GreyScaleBlueTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleBlue(name, destName);
    command.process(model);
    assertEquals("P3\n" +
            "4 4\n" +
            "255\n" +
            "0 0 0  0 0 0  60 60 60  60 60 60  \n" +
            "0 0 0  0 0 0  60 60 60  60 60 60  \n" +
            "0 0 0  0 0 0  0 0 0  0 0 0  \n" +
            "0 0 0  0 0 0  0 0 0  0 0 0  \n", model.getAnImage("mix2").toPPMText());


  }


  /**
   * Test for the command GreyScaleGreen.
   */
  @Test
  public void GreyScaleGreenTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleGreen(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "0 0 0  0 0 0  0 0 0  0 0 0  \n" +
                    "0 0 0  0 0 0  0 0 0  0 0 0  \n" +
                    "0 0 0  0 0 0  85 85 85  85 85 85  \n" +
                    "0 0 0  0 0 0  85 85 85  85 85 85  \n",
            model.getAnImage("mix2").toPPMText());

  }


  /**
   * Test for the command GreyScaleMaxValue.
   */
  @Test
  public void GreyScaleMaxValueTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleMaxValue(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 255 255  255 255 255  60 60 60  60 60 60  \n" +
                    "255 255 255  255 255 255  60 60 60  60 60 60  \n" +
                    "0 0 0  0 0 0  85 85 85  85 85 85  \n" +
                    "0 0 0  0 0 0  85 85 85  85 85 85  \n",
            model.getAnImage("mix2").toPPMText());

  }

  /**
   * Test for the command GreyScaleLuma.
   */
  @Test
  public void GreyScaleLumaTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleLuma(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
                    "54 54 54  54 54 54  4 4 4  4 4 4  \n" +
                    "0 0 0  0 0 0  60 60 60  60 60 60  \n" +
                    "0 0 0  0 0 0  60 60 60  60 60 60  \n",
            model.getAnImage("mix2").toPPMText());

  }


  /**
   * Test for the command GreyScaleIntensity.
   */
  @Test
  public void GreyScaleIntensityTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new GreyScaleIntensity(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "85 85 85  85 85 85  20 20 20  20 20 20  \n" +
                    "85 85 85  85 85 85  20 20 20  20 20 20  \n" +
                    "0 0 0  0 0 0  28 28 28  28 28 28  \n" +
                    "0 0 0  0 0 0  28 28 28  28 28 28  \n",
            model.getAnImage("mix2").toPPMText());

  }

  /**
   * Test for the command HorizontalFlip.
   */
  @Test
  public void HorizontalFlipTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new HorizontalFlip(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "0 0 60  0 0 60  255 0 0  255 0 0  \n" +
                    "0 0 60  0 0 60  255 0 0  255 0 0  \n" +
                    "0 85 0  0 85 0  0 0 0  0 0 0  \n" +
                    "0 85 0  0 85 0  0 0 0  0 0 0  \n",
            model.getAnImage("mix2").toPPMText());

  }

  /**
   * Test for the command VerticalFlip.
   */
  @Test
  public void VerticalFlipTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new VerticalFlip(name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n",
            model.getAnImage("mix2").toPPMText());

  }


  /**
   * Test for the command Brighten.
   */
  @Test
  public void BrightenTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    int increment = 5;
    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new Brighten(increment, name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 5 5  255 5 5  5 5 65  5 5 65  \n" +
                    "255 5 5  255 5 5  5 5 65  5 5 65  \n" +
                    "5 5 5  5 5 5  5 90 5  5 90 5  \n" +
                    "5 5 5  5 5 5  5 90 5  5 90 5  \n",
            model.getAnImage("mix2").toPPMText());

  }


  /**
   * Test for the command Darken.
   */
  @Test
  public void DarkenTest() {
    ImagesModel model = new ImagesModelImpl();
    String pathName = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand loadCommand = new Load(pathName, imageName);
    loadCommand.process(model);

    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
                    "0 0 0  0 0 0  0 85 0  0 85 0  \n",
            model.getAnImage("mix").toPPMText());

    int decrement = 5;
    String name = "mix";
    String destName = "mix2";
    ImagesProcessingCommand command = new Darken(decrement, name, destName);
    command.process(model);
    assertEquals("P3\n" +
                    "4 4\n" +
                    "255\n" +
                    "250 0 0  250 0 0  0 0 55  0 0 55  \n" +
                    "250 0 0  250 0 0  0 0 55  0 0 55  \n" +
                    "0 0 0  0 0 0  0 80 0  0 80 0  \n" +
                    "0 0 0  0 0 0  0 80 0  0 80 0  \n",
            model.getAnImage("mix2").toPPMText());

  }
}
