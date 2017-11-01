package cs3500.animator.controller;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.IView;

/**
 * Represents a controller for an animation program.
 */
public interface IController {

  /**
   * Runs the animation. Doing a loop until the animation is finished updating the model and views.
   */
  void run();

  /**
   * Update the model and view.
   */
  void update();

  /**
   * Gets the view used by this controller.
   * @return this controller's IView.
   */
  IView getView();

  /**
   * Gets the model used by this controller.
   * @return this controller's IAnimationModel.
   */
  IAnimationModel getModel();
}
