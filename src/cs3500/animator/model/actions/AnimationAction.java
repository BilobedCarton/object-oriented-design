package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Shape;

/**
 * This represents an action undertaken upon a shape during an animation.
 */
public abstract class AnimationAction implements ITimedAction {
  private final int timeStart;
  private final int timeEnd;
  private final Shape shape;
  private double ticksPerSecond;

  /**
   * Creates a new {@code AnimationAction} object.
   * @param timeStart is the time when this animation should begin.
   * @param timeEnd is the time when this animation should finish.
   * @param shape is the shape this action is being executed upon.
   * @param ticksPerSecond is the number of ticks per second of this animation action.
   * @throws IllegalArgumentException if the given times are invalid or the shape is null.
   */
  AnimationAction(int timeStart, int timeEnd, Shape shape, double ticksPerSecond) {
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
    this.ticksPerSecond = ticksPerSecond;
  }

  public abstract void execute();

  public abstract String toString();

  @Override
  public int getStartTick() {
    return timeStart;
  }

  @Override
  public int getEndTick() {
    return timeEnd;
  }

  @Override
  public double getTicksPerSecond() { return ticksPerSecond; }

  @Override
  public void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("AnimationAction.setTicksPerSecond(double) -- "
              + "ticksPerSecond is less than or equal to zero.");
    }
    this.ticksPerSecond = ticksPerSecond;
  }

  /**
   * Return the shape this action is executed upon.
   * @return the shape.
   */
  public Shape getShape() {
    return shape;
  }
}
