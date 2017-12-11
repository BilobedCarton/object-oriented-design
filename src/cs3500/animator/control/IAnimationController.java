package cs3500.animator.control;

import javax.swing.Timer;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.IView;

/**
 * Represents the controller for an animation program that uses various views: TextView - Outputs
 * the data as a piece of text. SVGView - Outputs the data in SVG format. VisualView - Renders the
 * data using swing graphical components. InteractiveView - Renders the data using swing graphical
 * components. Outputs the data in SVG format. Allows for some user interaction.
 */
public interface IAnimationController {

  /**
   * Gets the readonly form of the associated model for this animation controller.
   *
   * @return this controller's model as an IReadOnlyAnimationModel.
   */
  IReadOnlyAnimationModel getModel();

  /**
   * Gets the associated view for this animation controller.
   *
   * @return this controller's view.
   */
  IView getView();

  /**
   * Gets the associated speed for this animation controller.
   *
   * @return this controller's speed.
   */
  double getSpeed();

  /**
   * Gets the current tick of this controller.
   *
   * @return the int representing the current tick.
   */
  int getCurrTick();

  /**
   * Run the animation software. i.e. pass control to the controller and let it update the view.
   */
  void goStart();

  /**
   * A getter for our timer.
   * @return the timer object.
   */
  Timer getTimer();

  /**
   * Runs a single cycle on the model and updates the view.
   */
  void runUpdate();

  /**
   * Changes the speed of this controller. i.e. the ticksPerSecond.
   *
   * @param ticksPerSecond is the new value for ticksPerSecond.
   */
  void changeSpeed(double ticksPerSecond);

  /**
   * Resets the animation to the beginning.
   *
   * @param originalVisibility tells us whether or not to reset the visibilities of the shapes.
   */
  void reset(boolean originalVisibility);

  /**
   * Gets the value of the last tick of this animation.
   * @return the int representing the value of the last tick.
   */
  int getLastTick();
}
