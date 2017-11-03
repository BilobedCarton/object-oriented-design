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
