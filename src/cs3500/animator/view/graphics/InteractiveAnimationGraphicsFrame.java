package cs3500.animator.view.graphics;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.BoxLayout;

import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.control.listeners.ShapeSelectionListener;
import cs3500.animator.control.listeners.SpeedChangeListener;

/**
 * Represents a graphics frame for use by an InteractiveViewO for an animation.
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

  /**
   * Creates a new {@code InteractiveAnimationGraphicsFrame} object.
   *
   * @param width  is the width of the frame.
   * @param height is the height of the frame.
   */
  public InteractiveAnimationGraphicsFrame(int width, int height) {
    super(width, height);

    // buttons
    JPanel buttonPanel = new JPanel();
    JPanel sliderPanel;
    JButton selectShapesButton;

    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

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
  public void setButtonActions(IInteractiveAnimationController controller) {
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
   * Builds the ListDialog object used for this frame's shape selection window.
   *
   * @param controller is the controller linked to the dialog box.
   */
  public void buildListDialog(IInteractiveAnimationController controller) {
    String[] shapeNamesList = new String[controller.getModel().getShapes().size()];
    for (int i = 0; i < controller.getModel().getShapes().size(); i++) {
      shapeNamesList[i] = controller.getModel().getShapes().get(i).getName();
    }
    listDialog =
            new ListDialog(this, controller.getModel(),
                    new ShapeSelectionListener(controller));
    listDialog.setUpButtons(controller);
  }
}
