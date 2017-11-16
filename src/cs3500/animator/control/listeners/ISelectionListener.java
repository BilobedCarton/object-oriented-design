package cs3500.animator.control.listeners;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 * Represents a selection listener to be used with a ListDialog.
 */
public interface ISelectionListener extends ListSelectionListener {
  /**
   * Set the JList of this listener object.
   * @param jList is the JList we are related to.
   */
  public void setJList(JList jList);
}
