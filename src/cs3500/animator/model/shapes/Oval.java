package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * This represents an oval.
 */
public class Oval extends Shape {

  /**
   * Creates a {@code Oval} object.
   *
   * @param name          is the name of the Oval object.
   * @param posX          is the position in the x axis of this Oval object.
   * @param posY          is the position in the y axis of this Oval object.
   * @param color         is the color of this Oval object.
   * @param sizeX         is the size in the x axis of this Oval object.
   * @param sizeY         is the size in the y axis of this Oval object.
   * @param timeAppear    is the tick when this Oval first appears.
   * @param timeDisappear is the tick when this Oval disappears.
   */
  Oval(String name, double posX, double posY, Color color, double sizeX, double sizeY,
       int timeAppear, int timeDisappear) {
    super(name, posX, posY, color, sizeX, sizeY, timeAppear, timeDisappear,
            ShapeBuilder.ShapeType.OVAL);
  }

  @Override
  public String toString(double ticksPerSecond) {
    float[] colArray = this.getColor().getColorComponents(null);
    return "Name: " + this.getName() + "\n" + "Type: oval\n"
            + "Lower-left corner: (" + this.getPosX() + "," + this.getPosY() + "), Width: "
            + this.getSizeX() + " Height: " + this.getSizeY() + ", Color: ("
            + colArray[0] + "," + colArray[1] + ","
            + colArray[2] + ")\n" + "Appears at t="
            + this.getAppearTick() / ticksPerSecond + "s\n" + "Disappears at t="
            + this.getDisappearTick() / ticksPerSecond + "s\n";
  }


  @Override
  public String toSVG(double ticksPerSecond, boolean loop) {

    String retString = "<ellipse id=\"" + this.getName() + "\"" + " cx=\"" + this.getPosX()
            + "\" cy=\"" + this.getPosY() + "\" rx=\"" + this.getSizeX() + "\" ry=\""
            + this.getSizeY() + "\" fill=\"rgb(" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue() + ")\""
            + " visibility=\"hidden\" >\n";

    if (!loop) {
      retString += "\t<animate attributeType=\"xml\" begin=\""
              + (getAppearTick() / ticksPerSecond * 1000)
              + "ms\" attributeName=\"visibility\" to=\"visible\" />\n";
      retString += "\t<animate attributeType=\"xml\" begin=\""
              + (getDisappearTick() / ticksPerSecond * 1000) + "ms\" attributeName=\"visibility\""
              + " to=\"hidden\" />\n";
    } else {
      retString += "\t<animate attributeType=\"xml\" begin=\"base.begin+"
              + (getAppearTick() / ticksPerSecond * 1000)
              + "ms\" attributeName=\"visibility\" to=\"visible\" />\n";
      retString += "\t<animate attributeType=\"xml\" begin=\"base.begin+"
              + (getDisappearTick() / ticksPerSecond * 1000) + "ms\" attributeName=\"visibility\""
              + " to=\"hidden\" />\n";
    }

    return retString;
  }

  @Override
  public String svgEnd() {
    return "</ellipse>\n";
  }

}
