package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

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
   * Gets the number of ticks executed per second by this model.
   * @return the double representing the ticksPerSecond of this model.
   */
  double getTicksPerSecond();

  /**
   * Sets the ticks executed per second to the given number.
   * @param ticksPerSecond the new number of ticks per second.
   * @throws IllegalArgumentException if ticksPerSecond is less than or equal to zero.
   */
  void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException;

  /**
   * Adds the given action to this model's list of actions.
   * @param action is the AnimationAction to be added.
   * @return this SimpleAnimation.
   */
  SimpleAnimation addAction(AnimationAction action);

  /**
   * Adds the given shape to this model's list of shapes.
   * @param shape is the Shape to be added.
   * @return this SimpleAnimation.
   * @throws IllegalArgumentException if the shape has the same name as one already in the model.
   */
  SimpleAnimation addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Runs the animation, executing actions and rendering shapes.
   */
  void runAnimation();

  /**
   * Checks to see if this animation has any more actions to undertake or shapes to render.
   * @param currTime is the current time of this animation.
   * @return a boolean where true means there is more to do and false means there isn't.
   */
  boolean animationIncomplete(double currTime);

  /**
   * Runs a cycle of this animation.
   * @param currTime is the current time of this animation.
   */
  void runCycle(double currTime);

  /**
   * Converts this object into a string.
   * @return the String representing this object.
   */
  String toString();
}
