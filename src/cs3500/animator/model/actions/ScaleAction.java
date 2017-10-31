package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Shape;

/**
 * This represents an action changing the size of the target shape.
 */
public class ScaleAction extends AnimationAction {
  private final double targetSizeX;
  private final double targetSizeY;
  private final double originalSizeX;
  private final double originalSizeY;

  /**
   * Creates a new {@code ScaleAction} object.
   * @param timeStart is the time this action begins.
   * @param timeEnd is the time this action ends.
   * @param shape is the shape this action executes upon.
   * @param ticksPerSecond is the number of ticks per second of this ColorShiftAction.
   * @param targetSizeX is the target sizeX we are changing the shape to.
   * @param targetSizeY is the target sizeY we are changing the shape to.
   */
  ScaleAction(int timeStart, int timeEnd, Shape shape, double ticksPerSecond, double targetSizeX,
              double targetSizeY) {
    super(timeStart, timeEnd, shape, ticksPerSecond);
    this.targetSizeX = targetSizeX;
    this.targetSizeY = targetSizeY;
    this.originalSizeX = shape.getSizeX();
    this.originalSizeY = shape.getSizeY();
  }

  @Override
  public void execute() {
    this.getShape().resize(
            this.getShape().getSizeX() + ((this.targetSizeX - this.originalSizeX)
                    / (this.getEndTick() - this.getStartTick())),
            this.getShape().getSizeY() + ((this.targetSizeY - this.originalSizeY)
                    / (this.getEndTick() - this.getStartTick())));
  }

  @Override
  public String toString() {
    return "Shape " + this.getShape().getName() + " scales from Width: "
            + this.getShape().getSizeX() + " Height: " + this.getShape().getSizeY() + " to Width: "
            + this.targetSizeX + " Height: " + this.targetSizeY + " from t="
            + this.getStartTick() / this.getTicksPerSecond() + "s to t="
            +  this.getEndTick() / this.getTicksPerSecond() + "s\n";
  }

  /**
   * Gets the target size in the x dimension.
   * @return the double representing the target width or size in the x dimension.
   */
  public double getTargetSizeX() {
    return targetSizeX;
  }

  /**
   * Gets the target size in the y dimension.
   * @return the double representing the target height or size in the y dimension.
   */
  public double getTargetSizeY() {
    return targetSizeY;
  }
}
