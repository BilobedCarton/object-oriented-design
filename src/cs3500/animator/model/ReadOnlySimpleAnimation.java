package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a readonly version of an IAnimationModel.
 */
public class ReadOnlySimpleAnimation implements IReadOnlyAnimationModel {
  private IAnimationModel model;

  /**
   * Creates a new {@code ReadOnlySimpleAnimation} object.
   *
   * @param model is the model whose data we are reading.
   */
  public ReadOnlySimpleAnimation(IAnimationModel model) {
    this.model = model;
  }

  @Override
  public List<Shape> getShapes() {
    return model.getShapes();
  }

  @Override
  public Shape getShapeStateAt(int tick, Shape s) throws IllegalArgumentException {
    return model.getShapeStateAt(tick, s);
  }

  @Override
  public List<AnimationAction> getActions() {
    return model.getActions();
  }

  @Override
  public String toString() {
    return model.toString();
  }
}
