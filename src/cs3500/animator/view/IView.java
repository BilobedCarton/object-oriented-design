package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;

/**
 * Represents a view for an animation program.
 */
public interface IView {

  /**
   * Gets the model being rendered by this view.
   * @return this view's corresponding IAnimationModel.
   */
  IAnimationModel getModel();

  /**
   * Gets the number of ticks executed per second by the overarching program.
   * @return the ticksPerSecond.
   */
  double getTicksPerSecond();

  /**
   * Sets the ticksPerSecond property of this view.
   * @param ticksPerSecond is the new ticksPerSecond.
   * @throws IllegalArgumentException if ticksPerSecond is less than or equal to zero.
   */
  void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException;

  /**
   * Updates the view with the changes to the model.
   */
  void update();
}
