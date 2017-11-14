package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents the original state of a shape at time of construction.
 */
public class OriginalShape {
  private final Shape shape;
  private final double posX;
  private final double posY;
  private final Color color;
  private final double sizeX;
  private final double sizeY;
  private final int appearTick;
  private final int disappearTick;

  /**
   * Creates a new {@code OriginalShape} object.
   * @param s is the shape whose original values we are getting.
   */
  OriginalShape(Shape s) {
    this.shape = s;
    this.posX = s.getPosX();
    this.posY = s.getPosY();
    this.color = s.getColor();
    this.sizeX = s.getSizeX();
    this.sizeY = s.getSizeY();
    this.appearTick = s.getAppearTick();
    this.disappearTick = s.getDisappearTick();
  }

  /**
   * Resets the linked shapes values to this one's values.
   */
  void resetShape() {
    shape.relocate(this.posX, this.posY);
    shape.recolor(this.color);
    shape.resize(this.sizeX, this.sizeY);
    shape.setLifeSpan(this.appearTick, this.disappearTick);
  }
}
