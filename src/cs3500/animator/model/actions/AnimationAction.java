package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Shape;

/**
 * This represents an action undertaken upon a shape during an animation.
 */
public abstract class AnimationAction implements ITimedAction {
  private final int timeStart;
  private final int timeEnd;
  private final Shape shape;

  /**
   * Creates a new {@code AnimationAction} object.
   * @param timeStart is the time when this animation should begin.
   * @param timeEnd is the time when this animation should finish.
   * @param shape is the shape this action is being executed upon.
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

  public abstract String toString(double ticksPerSecond);

  /**
   * Update the original values of this action. i.e. if moving, update original x and y with the
   * current location of the shape.
   */
  public abstract void updateOriginalValues();

  @Override
  public int getStartTick() {
    return timeStart;
  }

  @Override
  public int getEndTick() {
    return timeEnd;
  }

  /**
   * Return the shape this action is executed upon.
   * @return the shape.
   */
  public Shape getShape() {
    return shape;
  }
}
