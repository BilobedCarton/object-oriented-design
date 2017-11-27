package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents an action that takes place over time. Could be for an animation, could be for game,
 * etc.
 */
public interface ITimedAction {

  /**
   * Runs a step of this action. e.g. All that should be done in one tick of the overarching
   * program. So an action that takes place over ten ticks will execute ten times.
   */
  void execute();

  /**
   * Runs the final step of this action. e.g. executes the exact final changes to the target,
   * putting it in the exact desired final state.
   */
  void executeFinal();

  /**
   * Converts this action into a string.
   *
   * @param ticksPerSecond is the number of ticks that occur per second.
   * @return the String representing this action.
   */
  String toString(double ticksPerSecond);

  /**
   * Gets the int representing the starting tick of this action.
   *
   * @return the int that is the starting tick.
   */
  int getStartTick();

  /**
   * Gets the int representing the ending tick of this action.
   *
   * @return the int that is the ending tick.
   */
  int getEndTick();

  /**
   * We make sure all ITimedAction can be converted into svg format.
   *
   * @param ticksPerSecond is the number of ticks executed by the model per second.
   * @return the svg string representing this ITimedAction.
   */
  String toSVG(double ticksPerSecond, boolean loop);

  /**
   * Gets the shape or animation piece this action executes its changes upon.
   * @return the IAnimationPiece used by this action.
   */
  IAnimationPiece getShape();

  /**
   * Sets the target shape of this action to the given one. This should only be used if the shape
   * has the same name as the current target shape.
   *
   * @param s is the new target Shape of this action.
   * @throws IllegalArgumentException if s has a different name from the current target shape.
   */
  void setShape(IAnimationPiece s);

  /**
   * Updates this action's original values with the current state of the animation.
   * This is for making sure we have the correct original values when the animation begins.
   */
  void updateOriginalValues();

  /**
   * Sets the original values of this AnimationAction to the given Shape's current corresponding
   * values.
   *
   * @param s is the shape we are getting the values of.
   * @throws IllegalArgumentException if the given shape is not the shape modified by this action.
   */
  void setOriginalValues(IAnimationPiece s) throws IllegalArgumentException;
}
