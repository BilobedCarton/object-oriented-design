package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a readonly version of an IAnimationModel
 */
public interface IReadOnlyAnimationModel {

  /**
   * Get the list of shapes in the model.
   * @return the List of Shapes in the model.
   */
  List<Shape> getShapes();

  /**
   * Gets the state of a shape at the given tick.
   * @param tick is the tick we want to check the shape at.
   * @param s is the Shape we are checking.
   * @return a copy of the shape with data updated to the given tick.
   * @throws IllegalArgumentException if the given shape does not exist in this model.
   */
  Shape getShapeStateAt(int tick, Shape s) throws IllegalArgumentException;

  /**
   * Get the list of actions in the model.
   * @return the List of AnimationActions in the model.
   */
  List<AnimationAction> getActions();

  /**
   * Convert the model into a string.
   * @return the string representing the model.
   */
  String toString();
}
