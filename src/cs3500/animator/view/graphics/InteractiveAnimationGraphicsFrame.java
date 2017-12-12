package cs3500.animator.view.graphics;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;

import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.control.listeners.ColorSelectionListener;
import cs3500.animator.control.listeners.ShapeSelectionListener;
import cs3500.animator.control.listeners.SpeedChangeListener;

/**
 * Represents a graphics frame for use by an InteractiveView for an animation.
 */
public class InteractiveAnimationGraphicsFrame extends BasicAnimationGraphicsFrame {
  private JButton startButton;
  private JButton pauseButton;
  private JButton unpauseButton;
  private JButton restartButton;
  private JButton resetButton;
  private ToggleButton loopingToggle;
  private JSlider speedSlider;
  private ListDialog listDialog;
  private ColorDialog colorDialog;
  private JPanel bottomPanel;

  /**
   * Creates a new {@code InteractiveAnimationGraphicsFrame} object.
   *
   * @param width  is the width of the frame.
   * @param height is the height of the frame.
   */
  public InteractiveAnimationGraphicsFrame(int width, int height) {
    super(width, height);

    // buttons
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
    this.add(bottomPanel, BorderLayout.SOUTH);

    JPanel sliderPanel;
    JButton selectShapesButton;
    JButton setBGColor;

    JPanel buttonPanel = new JPanel();
    bottomPanel.add(buttonPanel);
    buttonPanel.setLayout(new FlowLayout());

    startButton = new JButton("Start");
    buttonPanel.add(startButton);

    pauseButton = new JButton("Pause");
    buttonPanel.add(pauseButton);

    unpauseButton = new JButton("Resume");
    buttonPanel.add(unpauseButton);

    restartButton = new JButton("Restart");
    buttonPanel.add(restartButton);

    resetButton = new JButton("Reset");
    buttonPanel.add(resetButton);

    loopingToggle = new ToggleButton("Disable looping",
            "Enable  looping");
    buttonPanel.add(loopingToggle);

    selectShapesButton = new JButton("Toggle shapes");
    selectShapesButton.addActionListener((ActionEvent e) -> {
      listDialog.doModal();
    });
    buttonPanel.add(selectShapesButton);

    setBGColor = new JButton("Change bgColor");
    setBGColor.addActionListener((ActionEvent e) -> {
      colorDialog.doModal();
    });
    buttonPanel.add(setBGColor);

    // Slider
    sliderPanel = new JPanel();
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
    this.add(sliderPanel, BorderLayout.NORTH);

    speedSlider = new JSlider(1, 100);
    speedSlider.setMajorTickSpacing(10);
    speedSlider.setPaintTicks(true);
    speedSlider.setPaintLabels(true);
    sliderPanel.add(new JLabel("Speed in ticks per second:"));
    sliderPanel.add(speedSlider);

    this.pack();
  }

  /**
   * Set up the actions for the frame based upon the given controller.
   *
   * @param controller is the controller to be linked with this frame's input areas.
   */
  public void setButtonActions(InteractiveAnimationController controller) {
    startButton.addActionListener((ActionEvent e) -> {
      controller.startAnimation();
    });
    pauseButton.addActionListener((ActionEvent e) -> {
      controller.pauseAnimation();
    });
    unpauseButton.addActionListener((ActionEvent e) -> {
      controller.startAnimation();
    });
    restartButton.addActionListener((ActionEvent e) -> {
      controller.reset(false);
      controller.startAnimation();
    });
    resetButton.addActionListener((ActionEvent e) -> {
      controller.reset(true);
    });
    loopingToggle.addActionListener((ActionEvent e) -> {
      controller.toggleLooping();
      loopingToggle.toggle();
    });
  }

  /**
   * Link the value and listener for speed slider of this frame.
   *
   * @param controller is the controller whose values are used or changed.
   */
  public void linkSpeedSlider(IAnimationController controller) {
    speedSlider.setValue((int) controller.getSpeed());
    speedSlider.addChangeListener((new SpeedChangeListener(controller)));
  }

  /**
   * Builds the scrubber slider object used for scrubbing through the animation.
   * @param controller is the IAnimationController whose data is linked to this frame.
   */
  public void buildScrubberSlider(InteractiveAnimationController controller) {
    JSlider scrubberSlider = new TickScrubberSlider(controller);
    scrubberSlider.addChangeListener((ChangeEvent e) -> {
      controller.setModelToStateAt(scrubberSlider.getValue());
    });
    scrubberSlider.setMajorTickSpacing(controller.getLastTick() / 10);
    scrubberSlider.setPaintLabels(true);
    scrubberSlider.setPaintTicks(true);
    bottomPanel.add(new JLabel("Tick Scrubber."));
    bottomPanel.add(scrubberSlider);
  }

  /**
   * Builds the ListDialog object used for this frame's shape selection window.
   *
   * @param controller is the controller linked to the dialog box.
   */
  public void buildListDialog(InteractiveAnimationController controller) {
    String[] shapeNamesList = new String[controller.getModel().getShapes().size()];
    for (int i = 0; i < controller.getModel().getShapes().size(); i++) {
      shapeNamesList[i] = controller.getModel().getShapes().get(i).getName();
    }
    listDialog =
            new ListDialog(this, controller.getModel(),
                    new ShapeSelectionListener(controller));
    listDialog.setUpButtons(controller);
  }

  /**
   * Builds the ColorDialog object used for this frame's color selection window.
   *
   * @param controller is the controller linked to the dialog box.
   */
  public void buildColorDialog(InteractiveAnimationController controller) {
    colorDialog =
            new ColorDialog(this, controller.getModel(),
                    new ColorSelectionListener(controller));
    colorDialog.setUpButtons(controller);
  }
}
