package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.IViewModel;

public interface Interactive {
  void togglePlayback();

  void restart();

  void toggleLooping();

  void setSpeed(int ticksPerSec);

  int getSpeed();

  void showShapeSelection();

  void printCurrentAsSvg();

  List<String> getSelectedShapes();

  void setViewModel(IViewModel model);

  /**
   * Provide the view with an action listener for
   * a button that should cause the program to
   * process a command. This is so that when a button
   * is pressed, control goes to the action listener
   */
  void addListeners(ActionListener ar, ChangeListener cr, ListSelectionListener lr);

  void makeVisible();

  void viewAsSwing(IViewModel view, int ticksPerSec);
}
