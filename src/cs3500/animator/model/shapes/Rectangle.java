package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents a rectangular shape.
 */
public class Rectangle extends Shape {

  /**
   * Creates a {@code Rectangle} object.
   * @param name is the name of the Rectangle object.
   * @param posX is the position in the x axis of this Rectangle object.
   * @param posY is the position in the y axis of this Rectangle object.
   * @param color is the color of this Rectangle object.
   * @param sizeX is the size in the x axis of this Rectangle object.
   * @param sizeY is the size in the y axis of this Rectangle object.
   * @param timeAppear is the tick when this Rectangle first appears.
   * @param timeDisappear is the tick when this Rectangle disappears.
   * @param ticksPerSecond is the number of ticks executed on this Rectangle per second.
   */
  Rectangle(String name, double posX, double posY, Color color, double sizeX, double sizeY,
              int timeAppear, int timeDisappear, double ticksPerSecond) {
    super(name, posX, posY, color, sizeX, sizeY, timeAppear, timeDisappear, ticksPerSecond);
  }

  @Override
  public void render() {
    // empty.
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n" + "Type: rectangle\n"
            + "Lower-left corner: (" + this.getPosX() + "," + this.getPosY() + "), Width: "
            + this.getSizeX() + " Height: " + this.getSizeY() + ", Color: ("
            + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")\n" + "Appears at t="
            + this.getAppearTick() * this.getTicksPerSecond() + "s\n" + "Disappears at t="
            + this.getDisappearTick() * this.getTicksPerSecond() + "s\n";
  }
}
