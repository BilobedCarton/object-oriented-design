package cs3500.animator.provider.model;

import java.awt.Shape;
import java.awt.Color;

/**
 * Represents a shape of any arbitrary dimension, at any position, with any color.
 */
public interface IShape {
  /**
   * Changes the color of the shape to the specified new color.
   * @param color the new color
   * @return identical shape with only color changed
   * @throws IllegalArgumentException if the color is invalid
   */
  IShape changeColor(Color color) throws IllegalArgumentException;

  /**
   * Creates a new shape with all parameters identical except with a new position given by the
   * shapes anchor parameter (usually the bottom-left corner or center).
   * @param anchor where to move the anchor to
   * @return identical shape with only anchor moved
   * @throws IllegalArgumentException if move is invalid
   */
  IShape move(Coordinate anchor) throws IllegalArgumentException;

  /**
   * Creates a new shape with all parameters identical except with scaled dimensions.
   * @param xScale scale factor for the x-axis
   * @param yScale scale factor for the y-axis
   * @return identical shape with only scale changed
   * @throws IllegalArgumentException if scale is invalid
   */
  IShape scale(double xScale, double yScale) throws IllegalArgumentException;

  /**
   * Gets scale information for the shape. Provides needed context for printing.
   * @param startXScale starting x scale factor
   * @param startYScale starting y scale factor
   * @param endXScale ending x scale factor
   * @param endYScale ending y scale factor
   * @return a string representing the scale information for this shape and given scale information.
   */
  String getScaleDescription(double startXScale, double startYScale, double endXScale,
                             double endYScale);

  // INVARIANTS

  /**
   * Get the java swing representation of a shape.
   * @return a java swing representation of a shape with properties given by this object.
   */
  Shape getImage();

  /**
   * Get the color of this shape.
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Get the string representation of the type of this shape.
   * @return a string containing the representation of the shape type in text
   */
  String getType();

  /**
   * Get the anchor coordinate of this shape.
   * @return the coordinate held by the shape
   */
  Coordinate getAnchor();

  /**
   * Get the size of the shape in the x axis.
   * @return a double containing the size of the x axis
   */
  double getXSize();

  /**
   * Get the size of the shape in the y axis.
   * @return a double containin the size of the y axis
   */
  double getYSize();
}
