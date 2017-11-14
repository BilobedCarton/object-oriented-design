package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents a shape.
 */
public abstract class Shape implements IAnimationPiece {
  private final String name;
  private double posX;
  private double posY;
  private Color color;
  private double sizeX;
  private double sizeY;
  private int appearTick;
  private int disappearTick;
  private boolean visible = true;
  private final ShapeBuilder.ShapeType type;
  private final OriginalShape originalShape;

  /**
   * Creates a new {@code Shape} object.
   * @param name is the name of the Shape object.
   * @param posX is the position in the x axis of this Shape object.
   * @param posY is the position in the y axis of this Shape object.
   * @param color is the color of this Shape object.
   * @param sizeX is the size in the x axis of this Shape object.
   * @param sizeY is the size in the y axis of this Shape object.
   * @param appearTick is the tick when this Shape first appears.
   * @param disappearTick is the tick when this shape disappears.
   */
  Shape(String name, double posX, double posY, Color color, double sizeX, double sizeY,
        int appearTick, int disappearTick, ShapeBuilder.ShapeType type) {
    this.name = name;
    this.posX = posX;
    this.posY = posY;
    this.color = color;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.appearTick = appearTick;
    this.disappearTick = disappearTick;
    this.type = type;
    this.originalShape = new OriginalShape(this);
  }


  /**
   * Gets the type of this Shape.
   * @return the ShapeType of this shape.
   */
  public ShapeBuilder.ShapeType getType() { return type; }

  @Override
  public String getName() { return name; }

  @Override
  public double getPosX() {
    return posX;
  }

  @Override
  public double getPosY() {
    return posY;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public double getSizeX() {
    return sizeX;
  }

  @Override
  public double getSizeY() {
    return sizeY;
  }

  @Override
  public int getAppearTick() { return appearTick; }

  @Override
  public int getDisappearTick() { return disappearTick; }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setVisibility(boolean bool) {
    this.visible = bool;
  }

  @Override
  public Shape resize(double sizeX, double sizeY) throws IllegalArgumentException {
    if (sizeX < 0 || sizeY < 0) {
      throw new IllegalArgumentException("Shape.resize(double, double) -- "
              + "Given X size or Y size was negative.");
    }
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    return this;
  }

  @Override
  public Shape recolor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("Shape.recolor(Color) -- color is null.");
    }
    this.color = color;
    return this;
  }

  @Override
  public Shape relocate(double x, double y) {
    this.posX = x;
    this.posY = y;
    return this;
  }

  @Override
  public Shape setLifeSpan(int start, int end) throws IllegalArgumentException {
    if (start < 0 || end < 0 || end < start) {
      throw new IllegalArgumentException("Shape.setLifeSpan(int, int) -- start or end is negative."
              + " Or end is less than start.");
    }
    this.appearTick = start;
    this.disappearTick = end;
    return this;
  }

  @Override
  public void reset() {
    this.originalShape.resetShape();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Rectangle || other instanceof Oval) {
      return this.name.equals(((Shape) other).getName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  public abstract String toString(double ticksPerSecond);

  public abstract String toSVG(double ticksPerSecond);

  public abstract String svgEnd();

}
