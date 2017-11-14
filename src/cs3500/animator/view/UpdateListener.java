package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a listener that runs update on every action.
 */
public class UpdateListener implements ActionListener {
  IView view;

  /**
   * Creates a new {@code UpdateListener} object.
   * @param view is the view that will be updated when an action is performed.
   */
  public UpdateListener(IView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    view.update();
  }
}
