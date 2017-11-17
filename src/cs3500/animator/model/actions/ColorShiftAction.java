package cs3500.animator.model.actions;

import java.awt.Color;

import cs3500.animator.model.shapes.Shape;

/**
 * Represents an action changing the color of the target shape.
 */
public class ColorShiftAction extends AnimationAction {
  private final Color targetColor;
  private Color originalColor;

  /**
   * Creates a new {@code ColorShiftAction} object.
   *
   * @param timeStart   is the time this action begins.
   * @param timeEnd     is the time this action ends.
   * @param shape       is the shape this action is executed upon.
   * @param targetColor is the color this action changes the shape to.
   */
  ColorShiftAction(
          int timeStart, int timeEnd, Shape shape, Color targetColor) {
    super(timeStart, timeEnd, shape);
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
  public void executeFinal() {
    this.getShape().recolor(this.targetColor);
  }

  @Override
  public String toString(double ticksPerSecond) {
    float[] colArrayO = this.getShape().getColor().getColorComponents(null);
    float[] colArrayN = this.getShape().getColor().getColorComponents(null);

    return "Shape " + this.getShape().getName() + " changes color from ("
            + colArrayO[0] + "," + colArrayO[1]
            + "," + colArrayO[2] + ") to (" + colArrayN[0]
            + "," + colArrayN[1] + "," + colArrayN[2] + ") from t="
            + this.getStartTick() / ticksPerSecond + "s to t="
            + this.getEndTick() / ticksPerSecond + "s\n";
  }

  @Override
  public void updateOriginalValues() {
    this.originalColor = this.getShape().getColor();
  }

  @Override
  public void setOriginalValues(Shape s) throws IllegalArgumentException {
    if (this.getShape().getName().equals(s.getName()) == false) {
      throw new IllegalArgumentException("ColorShiftAction.setOriginalValues(Shape) -- "
              + "This action does not execute upon the given shape.");
    }
    this.originalColor = s.getColor();
  }

  /**
   * Get the target color of this colorshift.
   *
   * @return the target Color.
   */
  public Color getTargetColor() {
    return targetColor;
  }

  @Override
  public String toSVG(double ticksPerSecond, boolean loop) {
    String startColor = "rgb(" + originalColor.getRed() + ","
            + originalColor.getGreen() + "," + originalColor.getBlue() + ")";
    String endColor = "rgb(" + targetColor.getRed() + ","
            + targetColor.getGreen() + "," + targetColor.getBlue() + ")";

    String retString;

    if (!loop) {
      retString = "\t<animate attributeType=\"xml\" begin=\""
              + (getStartTick() / ticksPerSecond * 1000) + "ms\" dur=\""
              + ((getEndTick() - getStartTick()) / ticksPerSecond * 1000) + "ms\" attributeName=\"f"
              + "ill\" from=\"" + startColor + "\" to=\"" + endColor + "\" fill=\"freeze\" />\n";
    } else {
      retString = "\t<animate attributeType=\"xml\" begin=\"base.begin+"
              + (getStartTick() / ticksPerSecond * 1000) + "ms\" dur=\""
              + ((getEndTick() - getStartTick()) / ticksPerSecond * 1000) + "ms\" attributeName=\"f"
              + "ill\" from=\"" + startColor + "\" to=\"" + endColor + "\" fill=\"freeze\" />\n";

      retString += "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
              + " attributeName=\"fill\" to=\"" + startColor + "\" fill=\"freeze\" />\n";
    }
    return retString;
  }
}
