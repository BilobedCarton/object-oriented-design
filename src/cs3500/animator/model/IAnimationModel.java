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
   *
   * @return the List of AnimationAction used.
   */
  List<AnimationAction> getActions();

  /**
   * Gets the list of Shapes used in this animation.
   *
   * @return the List of Shape used.
   */
  List<Shape> getShapes();

  /**
   * Adds the given action to this model's list of not updated actions. These are the actions whose
   * original states have not been updated to reflect the shape at the time of action.
   *
   * @param action is the AnimationAction to be added.
   * @throws IllegalArgumentException if the corresponding shape does not exist in this model.
   */
  void addAction(AnimationAction action);

  /**
   * Adds the given shape to this model's list of shapes at the bottom layer.
   *
   * @param shape is the Shape to be added.
   * @throws IllegalArgumentException if the shape has the same name as one already in the model.
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Adds the given shape to this model's list of shapes at the given layer.
   * @param shape is the shape to be added
   * @param layer is the layer the shape should be added at.
   * @throws IllegalArgumentException if the shape has the name name as one already in the model of the given layer
   *                                  is invalid. Or if the given layer is negative.
   */
  void addShape(Shape shape, int layer) throws IllegalArgumentException;

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
   * @param s    is the Shape we are checking.
   * @return a copy of the shape with data updated to the given tick.
   * @throws IllegalArgumentException if the given shape does not exist in this model.
   */
  Shape getShapeStateAt(int tick, Shape s) throws IllegalArgumentException;

  /**
   * Updates all the not updated actions to properly reflect their state at the moment of action.
   */
  void updateActions();
}
