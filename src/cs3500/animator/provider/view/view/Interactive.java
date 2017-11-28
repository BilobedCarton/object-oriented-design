package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.IViewModel;

/**
 * An interface for interactive views. All concrete user interactive views must implement this
 * to ensure proper functionality when integrated with the rest of the program.
 */
public interface Interactive {
  /**
   * Simply flip the current state of the game - if called when playing; pause. If called when
   * paused; play.
   */
  void togglePlayback();

  /**
   * Restart the animation. Regardless of current state, rewind to beginning, and start.
   */
  void restart();

  /**
   * Enable or disable looping functionality.
   */
  void toggleLooping();

  /**
   * Set the speed of the animation.
   * @param ticksPerSec       desired ticks per second to change animation to
   */
  void setSpeed(int ticksPerSec);

  /**
   * Get the speed of the animation, in ticks per second.
   * @return        the speed of the animation, in ticks per second
   */
  int getSpeed();

  /**
   * Show the shape selection, where the user can select individual shapes.
   */
  void showShapeSelection();

  /**
   * Utility function to get the current animation in SVG format.
   */
  String getStateAsSvg();

  /**
   * Get the currently selected shapes.
   * @return      names of the currently selected shapes.
   */
  List<String> getSelectedShapes();

  /**
   * Changes the view model of this view.
   * @param model     the model to change this viewmodel to
   */
  void setViewModel(IViewModel model);

  /**
   * Provide the view with an action listener for
   * a button that should cause the program to
   * process a command. This is so that when a button
   * is pressed, control goes to the action listener.
   */
  void addListeners(ActionListener ar, ChangeListener cr, ListSelectionListener lr);

  /**
   * Makes this view visible.
   */
  void makeVisible();

  /**
   * View the current animation in Java Swing.
   * @param view            the provided view
   * @param ticksPerSec     the desired ticks per second
   */
  void viewAsSwing(IViewModel view, int ticksPerSec);

  /**
   * Get the current tick that the view is displaying.
   * @return      the current tick that the view is displaying.
   */
  int getCurrentTick();
}
