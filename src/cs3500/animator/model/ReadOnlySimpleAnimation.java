package cs3500.animator.model;

import java.util.ArrayList;
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
   * @param model is the model whose data we are reading.
   */
  public ReadOnlySimpleAnimation(IAnimationModel model) {
    this.model = model;
    this.model.updateActions();
  }

  @Override
  public List<Shape> getShapes() {
    return model.getShapes();
  }

  @Override
  public List<Shape> getVisibleShapes(int currTick) {
    List<Shape> visibleShapes = new ArrayList<Shape>();
    for (Shape s : this.getShapes()) {
      if (s.getAppearTick() <= currTick && s.getDisappearTick() > currTick && s.isVisible()) {
        visibleShapes.add(s);
      }
    }

    return visibleShapes;
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
