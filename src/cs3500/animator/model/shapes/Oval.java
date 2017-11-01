package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * This represents an oval.
 */
public class Oval extends Shape {

  /**
   * Creates a {@code Oval} object.
   * @param name is the name of the Oval object.
   * @param posX is the position in the x axis of this Oval object.
   * @param posY is the position in the y axis of this Oval object.
   * @param color is the color of this Oval object.
   * @param sizeX is the size in the x axis of this Oval object.
   * @param sizeY is the size in the y axis of this Oval object.
   * @param timeAppear is the tick when this Oval first appears.
   * @param timeDisappear is the tick when this Oval disappears.
   */
  Oval(String name, double posX, double posY, Color color, double sizeX, double sizeY,
              int timeAppear, int timeDisappear) {
    super(name, posX, posY, color, sizeX, sizeY, timeAppear, timeDisappear);
  }

  @Override
  public void render() {
    // empty.
  }

  @Override
  public String toString(double ticksPerSecond) {
    return "Name: " + this.getName() + "\n" + "Type: oval\n"
            + "Lower-left corner: (" + this.getPosX() + "," + this.getPosY() + "), Width: "
            + this.getSizeX() + " Height: " + this.getSizeY() + ", Color: ("
            + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")\n" + "Appears at t="
            + this.getAppearTick() * ticksPerSecond + "s\n" + "Disappears at t="
            + this.getDisappearTick() * ticksPerSecond + "s\n";
  }
}
