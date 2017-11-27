package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.IAnimationPiece;

/**
 * Represents a model for running an animation involving shapes and various actions executed upon
 * those shapes.
 */
public interface IAnimationModel {

  /**
   * Gets the list of AnimationActions used in this animation.
   *
   * @return the List of AnimationAction used.
   */
  List<ITimedAction> getActions();

  /**
   * Gets the list of IAnimationPieces used in this animation.
   *
   * @return the List of Shape used.
   */
  List<IAnimationPiece> getShapes();

  /**
   * Adds the given action to this model's list of not updated actions. These are the actions whose
   * original states have not been updated to reflect the shape at the time of action.
   *
   * @param action is the AnimationAction to be added.
   * @throws IllegalArgumentException if the corresponding shape does not exist in this model.
   */
  void addAction(ITimedAction action);

  /**
   * Adds the given shape to this model's list of shapes.
   *
   * @param shape is the IAnimationPiece to be added.
   * @throws IllegalArgumentException if the shape has the same name as one already in the model.
   */
  void addShape(IAnimationPiece shape) throws IllegalArgumentException;

  /**
   * Runs a cycle of this animation. Updates all not updated actions prior to the cycle run.
   *
   * @param currTick is the current tick of this animation.
   */
  void runCycle(int currTick);

  /**
   * Gets the state of a shape at the given tick.
   *
   * @param tick is the tick we want to check the shape at.
   * @param s    is the IAnimationPiece we are checking.
   * @return a copy of the animation piece with data updated to the given tick.
   * @throws IllegalArgumentException if the given shape does not exist in this model.
   */
  IAnimationPiece getShapeStateAt(int tick, IAnimationPiece s) throws IllegalArgumentException;

  /**
   * Updates all the not updated actions to properly reflect their state at the moment of action.
   */
  void updateActions();
}
