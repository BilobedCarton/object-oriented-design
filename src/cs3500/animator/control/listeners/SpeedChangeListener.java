package cs3500.animator.control.listeners;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.control.IAnimationController;

/**
 * Represents a change listener that updates the controller's speed in ticksPerSecond.
 */
public class SpeedChangeListener implements ChangeListener {
  IAnimationController controller;

  /**
   * Creates a new {@code SpeedChangeListener} object.
   * @param controller is the controller whose speed we change.
   */
  public SpeedChangeListener(IAnimationController controller) {
    this.controller = controller;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
      controller.changeSpeed(source.getValue());
    }
  }
}
