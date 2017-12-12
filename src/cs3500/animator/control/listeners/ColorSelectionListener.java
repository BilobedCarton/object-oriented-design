package cs3500.animator.control.listeners;


import java.awt.Color;
import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

import cs3500.animator.control.InteractiveAnimationController;

/**
 * Represents a listener that acts upon selection in a list.
 */
public class ColorSelectionListener implements ISelectionListener {
  InteractiveAnimationController controller;
  JList colorStates;

  /**
   * Creates a new {@code ShapeSelectionListener} object.
   *
   * @param controller is the controller whose shapes will be modified.
   */
  public ColorSelectionListener(InteractiveAnimationController controller) {
    this.controller = controller;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    List<Object> selectedObjects = colorStates.getSelectedValuesList();
    Color sendColor = null;
    String colorString = (String)selectedObjects.get(0);
    switch(colorString) {
      case "Black":
        sendColor = Color.BLACK;
        break;
      case "Red":
        sendColor = Color.red;
        break;
      case "Blue":
        sendColor = Color.blue;
        break;
      case "Green":
        sendColor = Color.green;
        break;
      case "Yellow":
        sendColor = Color.yellow;
        break;
      case "White":
        sendColor = Color.white;
        break;
      case "Orange":
        sendColor = Color.orange;
        break;
    }
    this.controller.setSelectedColor(sendColor);
  }

  @Override
  public void setJList(JList jList) {
    this.colorStates = jList;
  }
}
