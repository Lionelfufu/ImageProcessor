package imageprocessing.view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


import imageprocessing.model.Image;
import imageprocessing.model.ImageUtil;


/**
 * The GUIViewImpl is the GUI can be used by the user.
 */
public class GUIViewImpl extends JFrame implements GUIView {


  //file panel
  private JButton loadButton;
  private JLabel fileToLoad;
  private JButton saveButton;
  private JLabel pathToSave;
  private JTextField imageNameToSave;
  private JButton quitButton;


  //command panel
  private JTextField increment;
  private JButton brightenButton;
  private JTextField decrement;
  private JButton darkenButton;
  private JButton greyscaleButton;
  private JButton sepiaButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton greyscaleRedButton;
  private JButton greyscaleGreenButton;
  private JButton greyscaleBlueButton;
  private JButton greyscaleValueButton;
  private JButton greyscaleIntensityButton;
  private JButton greyscaleLumaButton;
  private JButton verflipButton;
  private JButton horflipButton;

  //Image being worked
  private JLabel imageLabel;


  //Histograms
  private JPanel histogramPanel;
  private JPanel redHistogram;
  private JPanel greenHistogram;
  private JPanel blueHistogram;
  private JPanel intensityHistogram;


  //place to render message to the user
  private JLabel messageField;


  /**
   * Constructs a GUIViewImpl.
   */
  public GUIViewImpl() {

    JPanel mainPanel;

    JPanel filePanel;

    JPanel commandPanel;

    JScrollPane imagePane;


    this.setTitle("Image Processor");
    this.setSize(1500, 1200);

    //mainPanel setting
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    this.add(mainPanel);

    //file panel
    filePanel = new JPanel();
    filePanel.setLayout(new FlowLayout());
    mainPanel.add(filePanel, BorderLayout.PAGE_START);

    loadButton = new JButton("Load");
    loadButton.setActionCommand("load button");
    fileToLoad = new JLabel("Choose a image file.");


    saveButton = new JButton("Save");
    saveButton.setActionCommand("save button");
    pathToSave = new JLabel("Choose a path to save the Image.");
    imageNameToSave = new JTextField("Please enter the name with extension for the image to" +
            " save here.");
    imageNameToSave.setColumns(40);

    quitButton = new JButton("Quit");
    quitButton.setActionCommand("quit button");

    filePanel.add(loadButton);
    filePanel.add(fileToLoad);
    filePanel.add(saveButton);
    filePanel.add(pathToSave);
    filePanel.add(imageNameToSave);
    filePanel.add(quitButton);


    //command panel
    commandPanel = new JPanel();
    commandPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
    commandPanel.setLayout(new BoxLayout(commandPanel, 3));
    mainPanel.add(commandPanel, BorderLayout.LINE_START);
    //increment
    increment = new JTextField("Enter a number for brighten.");
    increment.setPreferredSize(new Dimension(40, 5));
    //brighten button
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten button");
    //decrement
    decrement = new JTextField("Enter a number for darken.");
    decrement.setPreferredSize(new Dimension(40, 5));
    //darken button
    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("darken button");
    //greyscale button
    greyscaleButton = new JButton("GreyScale");
    greyscaleButton.setActionCommand("greyscale button");
    //sepia button
    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia button");
    //blur button
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur button");
    //sharpen button
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen button");
    //greyscale red button
    greyscaleRedButton = new JButton("Red Greyscale");
    greyscaleRedButton.setActionCommand("greyscale red button");
    //greyscale green button
    greyscaleGreenButton = new JButton("Green Greyscale");
    greyscaleGreenButton.setActionCommand("greyscale green button");
    //greyscale blue button
    greyscaleBlueButton = new JButton("Blue Greyscale");
    greyscaleBlueButton.setActionCommand("greyscale blue button");
    //greyscale max value button
    greyscaleValueButton = new JButton("Max Value Greyscale");
    greyscaleValueButton.setActionCommand("greyscale value button");
    //greyscale intensity button
    greyscaleIntensityButton = new JButton("Intensity Greyscale");
    greyscaleIntensityButton.setActionCommand("greyscale intensity button");
    //greyscale luma button
    greyscaleLumaButton = new JButton("Luma Greyscale");
    greyscaleLumaButton.setActionCommand("greyscale luma button");
    //horizontal flip button
    horflipButton = new JButton("Horizontal Flip");
    horflipButton.setActionCommand("horizontal flip button");
    //vertical flip button
    verflipButton = new JButton("Vertical Flip");
    verflipButton.setActionCommand("vertical flip button");
    commandPanel.add(increment);
    commandPanel.add(brightenButton);
    commandPanel.add(decrement);
    commandPanel.add(darkenButton);
    commandPanel.add(greyscaleButton);
    commandPanel.add(sepiaButton);
    commandPanel.add(blurButton);
    commandPanel.add(sharpenButton);
    commandPanel.add(greyscaleRedButton);
    commandPanel.add(greyscaleGreenButton);
    commandPanel.add(greyscaleBlueButton);
    commandPanel.add(greyscaleValueButton);
    commandPanel.add(greyscaleIntensityButton);
    commandPanel.add(greyscaleLumaButton);
    commandPanel.add(horflipButton);
    commandPanel.add(verflipButton);


    //histograms panel
    this.histogramPanel = new JPanel();
    histogramPanel.setPreferredSize(new Dimension(200, 1000));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histograms"));
    this.histogramPanel.setLayout(new BoxLayout(histogramPanel, 3));
    mainPanel.add(histogramPanel, BorderLayout.LINE_END);

    redHistogram = new JPanel();
    redHistogram.setBorder(BorderFactory.createTitledBorder("Red"));
    greenHistogram = new JPanel();
    greenHistogram.setBorder(BorderFactory.createTitledBorder("Green"));
    blueHistogram = new JPanel();
    blueHistogram.setBorder(BorderFactory.createTitledBorder("Blue"));
    intensityHistogram = new JPanel();
    intensityHistogram.setBorder(BorderFactory.createTitledBorder("Intensity"));


    histogramPanel.add(redHistogram);
    histogramPanel.add(greenHistogram);
    histogramPanel.add(blueHistogram);
    histogramPanel.add(intensityHistogram);


    //message field
    this.messageField = new JLabel("Waiting for loading an Image.", SwingConstants.CENTER);
    mainPanel.add(messageField, BorderLayout.PAGE_END);


    //image pane

    this.imageLabel = new JLabel();


    imageLabel = new JLabel("Please load an image.", SwingConstants.CENTER);

    imagePane = new JScrollPane(imageLabel);
    mainPanel.add(imagePane, BorderLayout.CENTER);


    this.setDefaultCloseOperation(3);

    this.setVisible(true);


  }


  @Override
  public void renderMessage(String message) {
    this.messageField.setText(message);

  }

  @Override
  public void renderNewImage(Image image) {
    this.imageLabel.setIcon(new ImageIcon(ImageUtil.readImageToBufferedImage(image)));
    this.imageLabel.setText("");

    histogramPanel.removeAll();
    redHistogram = new Histogram(image, "red");
    greenHistogram = new Histogram(image, "green");
    blueHistogram = new Histogram(image, "blue");
    intensityHistogram = new Histogram(image, "intensity");


    JScrollPane scrollPane1 = new JScrollPane(redHistogram);
    scrollPane1.setBorder(BorderFactory.createTitledBorder("Red"));
    JScrollPane scrollPane2 = new JScrollPane(greenHistogram);
    scrollPane2.setBorder(BorderFactory.createTitledBorder("Green"));
    JScrollPane scrollPane3 = new JScrollPane(blueHistogram);
    scrollPane3.setBorder(BorderFactory.createTitledBorder("Blue"));
    JScrollPane scrollPane4 = new JScrollPane(intensityHistogram);
    scrollPane4.setBorder(BorderFactory.createTitledBorder("Intensity"));

    histogramPanel.add(scrollPane1);
    histogramPanel.add(scrollPane2);
    histogramPanel.add(scrollPane3);
    histogramPanel.add(scrollPane4);

  }

  @Override
  public JLabel getFileToLoad() {
    return fileToLoad;
  }


  @Override
  public JLabel getPathToSave() {
    return pathToSave;
  }

  @Override
  public JTextField getIncrement() {
    return increment;
  }

  @Override
  public JTextField getDecrement() {
    return decrement;
  }

  @Override
  public JTextField getImageNameToSave() {
    return imageNameToSave;
  }


  @Override
  public void addListeners(ActionListener controller) {

    //header
    loadButton.addActionListener(controller);
    saveButton.addActionListener(controller);
    quitButton.addActionListener(controller);

    //all operations button
    increment.addActionListener(controller);
    brightenButton.addActionListener(controller);
    decrement.addActionListener(controller);
    darkenButton.addActionListener(controller);
    greyscaleButton.addActionListener(controller);
    sepiaButton.addActionListener(controller);
    blurButton.addActionListener(controller);
    sharpenButton.addActionListener(controller);
    greyscaleRedButton.addActionListener(controller);
    greyscaleGreenButton.addActionListener(controller);
    greyscaleBlueButton.addActionListener(controller);
    greyscaleValueButton.addActionListener(controller);
    greyscaleIntensityButton.addActionListener(controller);
    greyscaleLumaButton.addActionListener(controller);
    verflipButton.addActionListener(controller);
    horflipButton.addActionListener(controller);

  }

}
