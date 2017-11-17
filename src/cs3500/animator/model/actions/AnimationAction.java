package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Shape;

/**
 * This represents an action undertaken upon a shape during an animation.
 */
public abstract class AnimationAction implements ITimedAction {
  private final int timeStart;
  private final int timeEnd;
  private Shape shape;

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

  /**
   * Update the original values of this action. i.e. if moving, update original x and y with the
   * current location of the shape.
   */
  public abstract void updateOriginalValues();

  /**
   * Sets the original values of this AnimationAction to the given Shape's current corresponding
   * values.
   *
   * @param s is the shape we are getting the values of.
   * @throws IllegalArgumentException if the given shape is not the shape modified by this action.
   */
  public abstract void setOriginalValues(Shape s) throws IllegalArgumentException;

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
   *
   * @return the shape.
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Sets the target shape of this action to the given one. This should only be used if the shape
   * has the same name as the current target shape.
   *
   * @param s is the new target Shape of this action.
   * @throws IllegalArgumentException if s has a different name from the current target shape.
   */
  public void setShape(Shape s) {
    if (s.getName().equals(this.shape.getName()) == false) {
      throw new IllegalArgumentException("AnimationAction.setShape(Shape) -- Shape s has different "
              + "name from current target Shape.");
    }
    this.shape = s;
  }

  @Override
  public abstract String toSVG(double ticksPerSecond, boolean loop);
}
