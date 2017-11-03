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
   */
  Rectangle(String name, double posX, double posY, Color color, double sizeX, double sizeY,
              int timeAppear, int timeDisappear) {
    super(name, posX, posY, color, sizeX, sizeY, timeAppear, timeDisappear, "rect");
  }

  @Override
  public void render() {
    // empty.
  }

  @Override
  public String toString(double ticksPerSecond) {
    float[] colArray = this.getColor().getColorComponents(null);
    return "Name: " + this.getName() + "\n" + "Type: rectangle\n"
            + "Lower-left corner: (" + this.getPosX() + "," + this.getPosY() + "), Width: "
            + this.getSizeX() + " Height: " + this.getSizeY() + ", Color: ("
            + colArray[0] + "," + colArray[1] + ","
            + colArray[2] + ")\n" + "Appears at t="
            + this.getAppearTick() / ticksPerSecond + "s\n" + "Disappears at t="
            + this.getDisappearTick() / ticksPerSecond + "s\n";
  }

  @Override
  public String toSVG(double ticksPerSecond) {
    String retString = "<rect id=\"" + this.getName() + "\"" + " x=\"" + this.getPosX() + "\" y=\""
            + this.getPosY() + "\" width=\"" + this.getSizeX() + "\" height=\""
            + this.getSizeY() + "\" fill=\"rgb(" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue() + ")\""
            + " visibility=\"hidden\" >\n";
    retString += "\t<animate attributeType=\"xml\" begin=\""+ (getAppearTick()/ticksPerSecond*1000)
            + "ms\" attributeName=\"visibility\" to=\"visible\" />\n";
    retString += "\t<animate attributeType=\"xml\" begin=\""
            +(getDisappearTick()/ticksPerSecond*1000) +"ms\" attributeName=\"visibility\""
            + " to=\"hidden\" />\n";
    return retString;
  }

  @Override
  public String svgEnd() {
    return "</rect>\n";
  }
}
