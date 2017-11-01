package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimation;

/**
 * Represents a view for an animation program.
 */
public interface IView {

  /**
   * Gets the model being rendered by this view.
   * @return this view's corresponding IAnimationModel.
   */
  ReadOnlyAnimation getModel();

  /**
   * Updates the view with the changes to the model.
   */
  void update();
}
