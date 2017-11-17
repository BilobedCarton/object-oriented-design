package cs3500.animator.view;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents a view for an animation program.
 */
public interface IView {

  /**
   * Gets the model being rendered by this view.
   *
   * @return this view's corresponding IAnimationModel.
   */
  ReadOnlySimpleAnimation getModel();

  /**
   * Updates the view with the changes to the model.
   *
   * @param currTick is the current tick of the animation.
   */
  void update(int currTick);

  /**
   * Starts the timer and makes the frame visible.
   */
  void start();

  /**
   * Determines whether or not this view is interactive while running the animation.
   *
   * @return the boolean telling us if this is interactive.
   */
  boolean isInteractive();

  /**
   * Set up the various listeners and buttons involved in this view.
   *
   * @param controller is the controller using this view.
   * @throws NotImplementedException if this is being called by a view that is not interactive.
   */
  void setUpInteractivity(InteractiveAnimationController controller) throws NotImplementedException;

  /**
   * Export this view in the corresponding form: text or SVG.
   *
   * @throws NotImplementedException if this is being called by a view that does not export.
   */
  void export(boolean loop) throws NotImplementedException;
}
