package cs3500.animator.view.graphics;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.control.listeners.ShapeSelectionListener;
import cs3500.animator.control.listeners.SpeedChangeListener;

public class InteractiveAnimationGraphicsFrame extends BasicAnimationGraphicsFrame {
  private JButton startButton, pauseButton, unpauseButton, restartButton, resetButton,
          selectShapesButton;
  private ToggleButton loopingToggle;
  private JPanel buttonPanel, sliderPanel;
  private JSlider speedSlider;
  private ListDialog listDialog;

  public InteractiveAnimationGraphicsFrame(int width, int height) {
    super(width, height);

    // buttons
    buttonPanel = new JPanel();
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

    loopingToggle = new ToggleButton("Disable looping", "Enable  looping");
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

  public void setButtonActions(InteractiveAnimationController controller) {
    startButton.addActionListener((ActionEvent e) -> {controller.startAnimation();});
    pauseButton.addActionListener((ActionEvent e) -> {controller.pauseAnimation();});
    unpauseButton.addActionListener((ActionEvent e) -> {controller.startAnimation();});
    restartButton.addActionListener((ActionEvent e) -> {
      controller.reset(false);
      controller.startAnimation();
    });
    resetButton.addActionListener((ActionEvent e) -> {controller.reset(true);});
    loopingToggle.addActionListener((ActionEvent e) -> {
      controller.toggleLooping();
      loopingToggle.toggle();
    });
  }

  public void linkSpeedSlider(IAnimationController controller, double ticksPerSecond) {
    speedSlider.setValue((int) ticksPerSecond);
    speedSlider.addChangeListener((new SpeedChangeListener(controller)));
  }

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
}
