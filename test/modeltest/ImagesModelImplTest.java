package modeltest;


import org.junit.Test;


import imageprocessing.controller.commands.ImagesProcessingCommand;
import imageprocessing.controller.commands.Load;
import imageprocessing.model.Image;
import imageprocessing.model.ImagesModel;
import imageprocessing.model.ImagesModelImpl;
import imageprocessing.model.ImageImpl;

import static org.junit.Assert.assertEquals;


/**
 * This class is a tester for ImagesModelImpl class.
 */
public class ImagesModelImplTest {

  /**
   * Tests if the execute method works correctly.
   */
  @Test
  public void executeTest() {
    String imagePath = "res/Images/mix.ppm";
    String imageName = "mix";
    ImagesProcessingCommand c1 = new Load(imagePath, imageName);
    ImagesModel model = new ImagesModelImpl();


    model.execute(c1);

    Image iamge = model.getAnImage("mix");

    String imageExpected = "P3\n" +
            "4 4\n" +
            "255\n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "255 0 0  255 0 0  0 0 60  0 0 60  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n" +
            "0 0 0  0 0 0  0 85 0  0 85 0  \n";

    assertEquals(imageExpected, ((ImageImpl) iamge).toPPMText());


  }

  /**
   * Tests if the addAnImage method works correctly.
   */
  @Test
  public void addAnImageTest() {
    ImagesModel model = new ImagesModelImpl();
    ImageImpl image = new ImageImpl("res/Images/red.ppm");
    model.addImage("p1", image);

    assertEquals(image, model.getAllImages().get("p1"));


  }

  /**
   * Tests if the getAnImage method works correctly.
   */
  @Test
  public void getAnImageTest() {
    ImagesModel model = new ImagesModelImpl();
    ImageImpl image = new ImageImpl("res/Images/red.ppm");
    model.addImage("p1", image);

    assertEquals(image, model.getAnImage("p1"));

  }


  /**
   * Tests if the ImagesModelImpl constructs correctly.
   */
  @Test
  public void constructImagesModelImpl() {
    ImagesModel model = new ImagesModelImpl();
    ImageImpl image = new ImageImpl("res/Images/red.ppm");
    model.addImage("p1", image);

    assertEquals(image, model.getAnImage("p1"));

  }

}
