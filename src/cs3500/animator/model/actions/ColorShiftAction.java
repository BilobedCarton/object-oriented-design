package cs3500.animator.model.actions;

import java.awt.Color;

import cs3500.animator.model.shapes.Shape;

/**
 * Represents an action changing the color of the target shape.
 */
public class ColorShiftAction extends AnimationAction {
  private final Color targetColor;
  private final Color originalColor;

  /**
   * Creates a new {@code ColorShiftAction} object.
   * @param timeStart is the time this action begins.
   * @param timeEnd is the time this action ends.
   * @param shape is the shape this action is executed upon.
   * @param ticksPerSecond is the number of ticks per second of this ColorShiftAction.
   * @param targetColor is the color this action changes the shape to.
   */
  ColorShiftAction(
          int timeStart, int timeEnd, Shape shape, double ticksPerSecond, Color targetColor) {
    super(timeStart, timeEnd, shape, ticksPerSecond);
    this.targetColor = targetColor;
    this.originalColor = shape.getColor();
  }

  @Override
  public void execute() {
    this.getShape().recolor(
            new Color(this.getShape().getColor().getRed()
                            + ((this.targetColor.getRed() - this.originalColor.getRed())
                            / (this.getEndTick() - this.getStartTick())),
                    this.getShape().getColor().getGreen()
                            + ((this.targetColor.getGreen() - this.originalColor.getGreen())
                            / (this.getEndTick() - this.getStartTick())),
                    this.getShape().getColor().getBlue()
                            + ((this.targetColor.getBlue() - this.originalColor.getBlue())
                            / (this.getEndTick() - this.getStartTick()))));
  }

  @Override
  public String toString() {
    return "Shape " + this.getShape().getName() + " changes color from ("
            + this.getShape().getColor().getRed() + "," + this.getShape().getColor().getGreen()
            + "," + this.getShape().getColor().getBlue() + ") to (" + this.targetColor.getRed()
            + "," + this.targetColor.getGreen() + "," + this.targetColor.getBlue() + ") from t="
            + this.getStartTick() / this.getTicksPerSecond() + "s to t="
            + this.getEndTick() / this.getTicksPerSecond() + "s\n";
  }

  /**
   * Get the target color of this colorshift.
   * @return the target Color.
   */
  public Color getTargetColor() {
    return targetColor;
  }
}