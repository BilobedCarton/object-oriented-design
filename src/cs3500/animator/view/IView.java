package cs3500.animator.view;

import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents a view for an animation program.
 */
public interface IView {

  /**
   * Gets the model being rendered by this view.
   * @return this view's corresponding IAnimationModel.
   */
  ReadOnlySimpleAnimation getModel();

  /**
   * Updates the view with the changes to the model.
   * @param currTick is the current tick of the animation.
   */
  void update(int currTick);

  /**
   * Starts the timer and makes the frame visible.
   */
  void start();

  /**
   * Determines whether or not this view is interactive while running the animation.
   * @return the boolean telling us if this is interactive.
   */
  boolean isInteractive();
}
