package cs3500.animator.control;

public interface IInteractiveAnimationController extends IAnimationController {
  /**
   * Starts the timer and begins the animation updating.
   */
  void startAnimation();

  /**
   * Stops and resets the timer, pausing the animation.
   */
  void pauseAnimation();

  /**
   * A getter for the looping property.
   *
   * @return the looping boolean.
   */
  boolean getLooping();

  /**
   * Toggles looping behavior on this controller.
   */
  void toggleLooping();

  /**
   * Set the shapes currently selected for visibility changes.
   *
   * @param shapeStates is the array of slected shapes with their current visibility status.
   */
  void setSelectedShapes(String[] shapeStates);

  /**
   * Marks the selected shapes with the given visibility.
   *
   * @param visible is the target visibility of the selected shapes.
   */
  void markSelectedShapesVisibility(boolean visible);
}
