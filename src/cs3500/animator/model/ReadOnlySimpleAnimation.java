package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.IAnimationPiece;
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
    this.model.updateActions();
  }

  @Override
  public List<IAnimationPiece> getShapes() {
    return model.getShapes();
  }

  @Override
  public List<IAnimationPiece> getVisibleShapes(int currTick) {
    List<IAnimationPiece> visibleShapes = new ArrayList<IAnimationPiece>();
    for (IAnimationPiece s : this.getShapes()) {
      if (s.getAppearTick() <= currTick && s.getDisappearTick() > currTick && s.isVisible()) {
        visibleShapes.add(s);
      }
    }

    return visibleShapes;
  }

  @Override
  public List<ITimedAction> getActions() {
    return model.getActions();
  }

  @Override
  public String toString() {
    return model.toString();
  }
}
