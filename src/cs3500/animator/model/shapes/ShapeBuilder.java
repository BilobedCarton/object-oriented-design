package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents a builder to build Shape objects.
 */
public class ShapeBuilder {
  private String name = null;
  private double posX = 0;
  private double posY = 0;
  private Color color = null;
  private double sizeX = 0;
  private double sizeY = 0;
  private int appearTick = 0;
  private int disappearTick = 0;

  /**
   * Represents a type of Shape object.
   */
  public enum ShapeType {
    RECTANGLE, OVAL
  }

  /**
   * Initializes the ShapeBuilder to build a new shape.
   * @return a new ShapeBuilder.
   */
  public static ShapeBuilder initialize() {
    return new ShapeBuilder();
  }

  /**
   * Set the name of the new Shape to be built.
   * @param name the name of the Shape.
   * @return this ShapeBuilder.
   */
  public ShapeBuilder setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Set the position of the new Shape to be built.
   * @param x is the x coordinate of the shape.
   * @param y is the y coordinate of the shape.
   * @return this ShapeBuilder.
   */
  public ShapeBuilder setPosition(double x, double y) {
    this.posX = x;
    this.posY = y;
    return this;
  }

  /**
   * Set the color of the new Shape to be built.
   * @param c is the color of the shape.
   * @return this ShapeBuilder.
   */
  public ShapeBuilder setColor(Color c) {
    this.color = c;
    return this;
  }

  /**
   * Set the size of the new Shape to be built.
   * @param sizeX is the size in the x axis of the shape.
   * @param sizeY is the size in the y axis of the shape.
   * @return this ShapeBuilder.
   */
  public ShapeBuilder setSize(double sizeX, double sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    return this;
  }

  /**
   * Set the lifespan of the new Shape to be built.
   * @param appearTick is the tick when the shape appears.
   * @param disappearTick is the tick when the shape disappears.
   * @return this ShapeBuilder.
   */
  public ShapeBuilder setTimeSpan(int appearTick, int disappearTick) {
    this.appearTick = appearTick;
    this.disappearTick = disappearTick;
    return this;
  }

  /**
   * Creates a new shape of the given type without any set values.
   * @param type the type of shape we want.
   * @return the Shape we have created.
   * @throws IllegalArgumentException if the given shape type is not recognized
   *                                  or a parameter is invalid.
   */
  public Shape build(ShapeType type) throws IllegalArgumentException {
    if (this.name == null) {
      throw new IllegalArgumentException("ShapeBuilder.build(ShapeType) -- name is null.");
    }
    if (this.color == null) {
      throw new IllegalArgumentException("ShapeBuilder.build(ShapeType) -- color is null.");
    }
    if (this.sizeX < 0 || this.sizeY < 0) {
      throw new IllegalArgumentException("ShapeBuilder.build(ShapeType) -- "
              + "sizeX or sizeY is less than 0");
    }
    if (this.appearTick < 0 || this.disappearTick < 0 || this.disappearTick < this.appearTick) {
      throw new IllegalArgumentException("ShapeBuilder.build(ShapeType) -- "
              + "appearTick or disappearTick is less than zero, or disappearTick is less than "
              + "appearTick.");
    }
    switch (type) {
      case OVAL:
        return new Oval(this.name, this.posX, this.posY, this.color, this.sizeX, this.sizeY,
                this.appearTick, this.disappearTick);
      case RECTANGLE:
        return new Rectangle(this.name, this.posX, this.posY, this.color, this.sizeX, this.sizeY,
                this.appearTick, this.disappearTick);
      default:
        throw new IllegalArgumentException("ShapeBuilder.buildShape(ShapeType) "
                + "-- ShapeType not recognized.");
    }
  }

  /**
   * Copies the given shape and creates a new one with identical parameters.
   * @param shape the shape to be copied.
   * @return the new copy of the given shape.
   * @throws IllegalArgumentException if the given shape is not a recognizable shape.
   */
  public static Shape copy(Shape shape) throws IllegalArgumentException {
    ShapeBuilder builder = ShapeBuilder.initialize()
            .setName(shape.getName())
            .setColor(shape.getColor())
            .setPosition(shape.getPosX(), shape.getPosY())
            .setSize(shape.getSizeX(), shape.getSizeY())
            .setTimeSpan(shape.getAppearTick(), shape.getDisappearTick());
    if (shape.getClass() == Oval.class) {
      return builder.build(ShapeType.OVAL);
    }
    else if (shape.getClass() == Rectangle.class) {
      return builder.build(ShapeType.RECTANGLE);
    }
    else {
      throw new IllegalArgumentException("ShapeBuilder.copy(Shape) -- shape is not recognizable.");
    }
  }
}
