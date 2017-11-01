package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a readonly version of an IAnimationModel.
 */
public class ReadOnlyAnimation {
  private IAnimationModel model;

  /**
   * Creates a new {@code ReadOnlyAnimation} object.
   * @param model is the model whose data we are reading.
   */
  public ReadOnlyAnimation(IAnimationModel model) {
    this.model = model;
  }

  /**
   * Get the list of shapes in the model.
   * @return the List of Shapes in the model.
   */
  public List<Shape> getShapes() {
    return model.getShapes();
  }

  /**
   * Get the list of actions in the model.
   * @return the List of AnimationActions in the model.
   */
  public List<AnimationAction> getActions() {
    return model.getActions();
  }

  /**
   * Convert the model into a string.
   * @return the string representing the model.
   */
  public String toString() {
    return model.toString();
  }
}
