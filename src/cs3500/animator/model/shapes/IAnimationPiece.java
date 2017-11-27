package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents the model of an object in an animation.
 */
public interface IAnimationPiece {

  /**
   * Gets the name of this IAnimationPiece.
   *
   * @return the String representing the name.
   */
  String getName();

  /**
   * Gets the x coordinate position of this IAnimationPiece.
   *
   * @return the double representing the x position.
   */
  double getPosX();

  /**
   * Gets the y coordinate position of this IAnimationPiece.
   *
   * @return the double representing the y position.
   */
  double getPosY();

  /**
   * Gets the color of this IAnimationPiece.
   *
   * @return the Color representing the color.
   */
  Color getColor();

  /**
   * Gets the x size of this IAnimationPiece.
   *
   * @return the double representing the x size.
   */
  double getSizeX();

  /**
   * Gets the y size of this IAnimationPiece.
   *
   * @return the double representing the x size.
   */
  double getSizeY();

  /**
   * Gets the time of appearance of this IAnimationPiece.
   *
   * @return the int representing the tick this IAnimationPiece appears.
   */
  int getAppearTick();

  /**
   * Gets the time of disappearance of this IAnimationPiece.
   *
   * @return the int representing the tick this IAnimationPiece disappears.
   */
  int getDisappearTick();

  /**
   * Returns the type of shape this is.
   * @return the ShapeType of this Animation piece.
   */
  ShapeType getShapeType();

  /**
   * Determines whether or not this shape is visible when the animation is run.
   *
   * @return the boolean representing the visibility of this shape.
   */
  boolean isVisible();

  /**
   * Sets the visibility of this shape.
   * @param bool is the target visibility of this shape.
   */
  void setVisibility(boolean bool);

  /**
   * Changes the size of this IAnimationPiece.
   *
   * @param sizeX is the desired length in the x dimension. e.g. Width for rectangles, x-radius for
   *              circles.
   * @param sizeY is the desired length in the y dimension. e.g. Height for rectangles, y-radius for
   *              circles.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if the given sizes are negative.
   */
  IAnimationPiece resize(double sizeX, double sizeY) throws IllegalArgumentException;

  /**
   * Changes the color of this IAnimationPiece.
   *
   * @param color is the desired color of this IAnimationPiece.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if color is null.
   */
  IAnimationPiece recolor(Color color) throws IllegalArgumentException;

  /**
   * Relocate this IAnimationPiece. Change its position values. Negative values are currently
   * allowed since we haven't implemented rendering yet.
   *
   * @param x is the desired x coordinate to move this IAnimationPiece to.
   * @param y is the desired y coordinate to move this IAnimationPiece to.
   * @return this Shape.
   */
  IAnimationPiece relocate(double x, double y);

  /**
   * Change the lifespan of this IAnimationPiece. Change the time it appears and time it
   * disappears.
   *
   * @param start is the tick when this IAnimationPiece should first appear.
   * @param end   is the tick when this IAnimationPiece should disappear.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if the given start or end values are less than 0 or end is
   *                                  less than start.
   */
  IAnimationPiece setLifeSpan(int start, int end) throws IllegalArgumentException;

  /**
   * Resets this IAnimationPiece to its original state at time of construction.
   *
   * @param originalVisibility tells us whether or not to set the visibility to the original value.
   */
  void reset(boolean originalVisibility);

  /**
   * We make sure all AnimationPieces can be converted into Strings.
   *
   * @param ticksPerSecond is the number of ticks executed by the model per second.
   * @return the string representing this IAnimationPiece
   */
  String toString(double ticksPerSecond);

  /**
   * We make sure all AnimationPieces can be converted into svg format.
   *
   * @param ticksPerSecond is the number of ticks executed by the model per second.
   * @return the svg string representing this IAnimationPiece
   */
  String toSVG(double ticksPerSecond, boolean loop);

  /**
   * We make sure all AnimationPieces can be converted into svg format via their end.
   *
   * @return the svg string representing this IAnimationPiece's end string
   */
  String svgEnd();
}
