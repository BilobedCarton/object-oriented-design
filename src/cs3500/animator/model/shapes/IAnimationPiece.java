package cs3500.animator.model.shapes;

import java.awt.Color;

/**
 * Represents the model of an object in an animation.
 */
public interface IAnimationPiece {

  /**
   * Gets the name of this IAnimationPiece.
   * @return the String representing the name.
   */
  String getName();

  /**
   * Gets the x coordinate position of this IAnimationPiece.
   * @return the double representing the x position.
   */
  double getPosX();

  /**
   * Gets the y coordinate position of this IAnimationPiece.
   * @return the double representing the y position.
   */
  double getPosY();

  /**
   * Gets the color of this IAnimationPiece.
   * @return the Color representing the color.
   */
  Color getColor();

  /**
   * Gets the x size of this IAnimationPiece.
   * @return the double representing the x size.
   */
  double getSizeX();

  /**
   * Gets the y size of this IAnimationPiece.
   * @return the double representing the x size.
   */
  double getSizeY();

  /**
   * Gets the time of appearance of this IAnimationPiece.
   * @return the int representing the tick this IAnimationPiece appears.
   */
  int getAppearTick();

  /**
   * Gets the time of disappearance of this IAnimationPiece.
   * @return the int representing the tick this IAnimationPiece disappears.
   */
  int getDisappearTick();

  /**
   * Gets the number of ticks that happen to this IAnimationPiece per second.
   * @return the double representing the ticks per second.
   */
  double getTicksPerSecond();

  /**
   * Changes the size of this IAnimationPiece.
   * @param sizeX is the desired length in the x dimension. e.g. Width for rectangles,
   *              x-radius for circles.
   * @param sizeY is the desired length in the y dimension. e.g. Height for rectangles,
   *              y-radius for circles.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if the given sizes are negative.
   */
  IAnimationPiece resize(double sizeX, double sizeY) throws IllegalArgumentException;

  /**
   * Changes the color of this IAnimationPiece.
   * @param color is the desired color of this IAnimationPiece.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if color is null.
   */
  IAnimationPiece recolor(Color color) throws IllegalArgumentException;

  /**
   * Relocate this IAnimationPiece. Change its position values.
   * Negative values are currently allowed since we haven't implemented rendering yet.
   * @param x is the desired x coordinate to move this IAnimationPiece to.
   * @param y is the desired y coordinate to move this IAnimationPiece to.
   * @return this Shape.
   */
  IAnimationPiece relocate(double x, double y);

  /**
   * Change the lifespan of this IAnimationPiece. Change the time it appears and time it disappears.
   * @param start is the tick when this IAnimationPiece should first appear.
   * @param end is the tick when this IAnimationPiece should disappear.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if the given start or end values are less than 0
   *                                  or end is less than start.
   */
  IAnimationPiece setLifeSpan(int start, int end) throws IllegalArgumentException;

  /**
   * Sets the ticksPerSecond property to the given value.
   * @param ticksPerSecond is the new number of ticks that execute on this shape per second.
   * @return this IAnimationPiece.
   * @throws IllegalArgumentException if ticksPerSecond is less than or equal to zero.
   */
  IAnimationPiece setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException;

  /**
   * Render this IAnimationPiece.
   */
  void render();

  /**
   * We make sure all AnimationPieces can be converted into Strings.
   */
  String toString();
}