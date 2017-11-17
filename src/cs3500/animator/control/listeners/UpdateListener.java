package cs3500.animator.control.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.animator.control.IAnimationController;

/**
 * Represents a listener that runs update on every action.
 */
public class UpdateListener implements ActionListener {
  IAnimationController controller;

  /**
   * Creates a new {@code UpdateListener} object.
   *
   * @param controller is the controller that will run the update when an action is performed.
   */
  public UpdateListener(IAnimationController controller) {
    this.controller = controller;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    controller.runUpdate();
  }
}
