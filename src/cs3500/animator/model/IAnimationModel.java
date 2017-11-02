package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a model for running an animation involving shapes and various actions executed upon
 * those shapes.
 */
public interface IAnimationModel {

  /**
   * Gets the list of AnimationActions used in this animation.
   * @return the List\<AnimationAction\> used.
   */
  List<AnimationAction> getActions();

  /**
   * Gets the list of Shapes used in this animation.
   * @return the List\<Shape\> used.
   */
  List<Shape> getShapes();

  /**
   * Adds the given action to this model's list of actions.
   * @param action is the AnimationAction to be added.
   */
  void addAction(AnimationAction action);

  /**
   * Adds the given shape to this model's list of shapes.
   * @param shape is the Shape to be added.
   * @throws IllegalArgumentException if the shape has the same name as one already in the model.
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Checks to see if this animation has any more actions to undertake or shapes to render.
   * @param currTick is the current tick of this animation.
   * @return a boolean where true means there is more to do and false means there isn't.
   */
  boolean animationIncomplete(int currTick);

  /**
   * Runs a cycle of this animation.
   * @param currTick is the current tick of this animation.
   */
  void runCycle(int currTick);

  /**
   * Converts this object into a string.
   * @param ticksPerSecond is the number of ticks executed per second by this animation program.
   * @return the String representing this object.
   */
  String toString(double ticksPerSecond);
}
