package cs3500.animator.view.graphics;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import cs3500.animator.control.InteractiveAnimationController;

public class InteractiveAnimationGraphicsFrame extends BasicAnimationGraphicsFrame {
  private JButton startButton, pauseButton, unpauseButton, resetButton;
  private ToggleButton loopingToggle;
  private JPanel buttonPanel;

  public InteractiveAnimationGraphicsFrame(int width, int height) {
    super(width, height);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    // buttons
    startButton = new JButton("Start");
    buttonPanel.add(startButton);

    pauseButton = new JButton("Pause");
    buttonPanel.add(pauseButton);

    unpauseButton = new JButton("Resume");
    buttonPanel.add(unpauseButton);

    resetButton = new JButton("Reset");
    buttonPanel.add(resetButton);

    loopingToggle = new ToggleButton("Disable looping", "Enable  looping");
    buttonPanel.add(loopingToggle);

    this.pack();
  }

  public void setButtonActions(InteractiveAnimationController controller) {
    startButton.addActionListener((ActionEvent e) -> {controller.startAnimation();});
    pauseButton.addActionListener((ActionEvent e) -> {controller.pauseAnimation();});
    unpauseButton.addActionListener((ActionEvent e) -> {controller.startAnimation();});
    resetButton.addActionListener((ActionEvent e) -> {controller.reset();});
    loopingToggle.addActionListener((ActionEvent e) -> {
      controller.toggleLooping();
      loopingToggle.toggle();
    });
  }
}
