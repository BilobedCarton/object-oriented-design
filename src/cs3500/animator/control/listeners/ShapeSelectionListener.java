package cs3500.animator.control.listeners;


import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import cs3500.animator.control.InteractiveAnimationController;

/**
 * Represents a listener that acts upon selection in a list.
 */
public class ShapeSelectionListener implements ISelectionListener {
  InteractiveAnimationController controller;
  JList shapeStates;

  /**
   * Creates a new {@code ShapeSelectionListener} object.
   * @param controller is the controller whose shapes will be modified.
   */
  public ShapeSelectionListener(InteractiveAnimationController controller) {
    this.controller = controller;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    List<Object> selectedObjects = shapeStates.getSelectedValuesList();
    String[] selectedShapes = new String[selectedObjects.size()];
    for (int i = 0; i < selectedObjects.size(); i++) {
      selectedShapes[i] = (String) selectedObjects.get(i);
    }
    this.controller.setSelectedShapes(selectedShapes);
  }

  @Override
  public void setJList(JList jList) {
    this.shapeStates = jList;
  }
}
