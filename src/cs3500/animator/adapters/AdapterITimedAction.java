package cs3500.animator.adapters;

import cs3500.animator.model.actions.ColorShiftAction;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.actions.ScaleAction;
import cs3500.animator.provider.model.animation.AnimationType;
import cs3500.animator.provider.model.animation.IAnimation;
import cs3500.animator.provider.model.shape.IShape;

/**
 * Adapts a ITimedAction into the form of an IAnimation.
 */
public class AdapterITimedAction implements IAnimation {
  private ITimedAction animationAction;

  public AdapterITimedAction(ITimedAction animationAction) {
    this.animationAction = animationAction;
  }

  @Override
  public AnimationType getType() {
    if (this.animationAction instanceof ColorShiftAction) {
      return AnimationType.CHANGE_COLOR;
    } else if (this.animationAction instanceof MoveAction) {
      return AnimationType.MOVE;
    } else if (this.animationAction instanceof ScaleAction) {
      return AnimationType.SCALE;
    } else {
      throw new IllegalArgumentException("Animation type not recognized.");
    }
  }

  @Override
  public int getStartTime() {
    return animationAction.getStartTick();
  }

  @Override
  public int getEndTime() {
    return animationAction.getEndTick();
  }

  @Override
  public boolean isOverlapping(IAnimation other) {
    return false;
  }

  @Override
  public String getShapeName() {
    return animationAction.getShape().getName();
  }

  @Override
  public void setInitialShape(IShape shape) {
    animationAction.setShape(((AdapterIAnimationPiece)shape).getAnimationPiece());
  }

  @Override
  public IShape getInitialShape() {
    return new AdapterIAnimationPiece(animationAction.getShape());
  }

  @Override
  public boolean isDuringLifetime(int appears, int disappears) {
    return (this.getEndTime() <= disappears && this.getStartTime() >= appears);
  }

  @Override
  public IShape shapeAtTime(IShape initialShape, int t) {
    return null;
  }

  @Override
  public String toString(String units) {
    return animationAction.toString(Double.parseDouble(units));
  }

  @Override
  public String transitionInfo() {
    return null;
  }
}
