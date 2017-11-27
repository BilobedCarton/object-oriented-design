package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.model.shapes.Shape;

/**
 * This represents an action undertaken upon a shape during an animation.
 */
public abstract class AnimationAction implements ITimedAction {
  private final int timeStart;
  private final int timeEnd;
  private IAnimationPiece shape;

  /**
   * Creates a new {@code AnimationAction} object.
   *
   * @param timeStart is the time when this animation should begin.
   * @param timeEnd   is the time when this animation should finish.
   * @param shape     is the shape this action is being executed upon.
   * @throws IllegalArgumentException if the given times are invalid or the shape is null.
   */
  AnimationAction(int timeStart, int timeEnd, Shape shape) {
    if (timeStart < 0 || timeEnd < 0 || timeEnd < timeStart) {
      throw new IllegalArgumentException("AnimationAction(int, int, Shape) -- "
              + "Given times are negative or end is less than start.");
    }
    if (shape == null) {
      throw new IllegalArgumentException("AnimationAction(int, int, Shape) -- Given Shape is null");
    }
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
    this.shape = shape;
  }

  public abstract void execute();

  public abstract void executeFinal();

  public abstract String toString(double ticksPerSecond);

  @Override
  public abstract void updateOriginalValues();

  @Override
  public abstract void setOriginalValues(IAnimationPiece s) throws IllegalArgumentException;

  @Override
  public int getStartTick() {
    return timeStart;
  }

  @Override
  public int getEndTick() {
    return timeEnd;
  }

  @Override
  public IAnimationPiece getShape() {
    return shape;
  }

  @Override
  public void setShape(IAnimationPiece s) {
    if (!s.getName().equals(this.shape.getName())) {
      throw new IllegalArgumentException("AnimationAction.setShape(Shape) -- Shape s has different "
              + "name from current target Shape.");
    }
    this.shape = s;
  }

  @Override
  public abstract String toSVG(double ticksPerSecond, boolean loop);
}
