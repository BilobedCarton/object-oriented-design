package cs3500.animator.provider.model;

/**
 * Contains the x and y coordinates of a 2D plane. Represents a point.
 */
public interface ICoordinate {
  /**
   * Returns the x-component of the point.
   * @return xCoord
   */
  double getXCoord();

  /**
   * Returns teh y-component of the point.
   * @return yCoord
   */
  double getYCoord();

  /**
   * Add two Coordinates together and return their sum as a new Coordinate.
   * @param other other coordinate to sum with (can be negative)
   * @return Coordinate representing the sum of the two coordinates
   */
  ICoordinate add(ICoordinate other);

  /**
   * Add to a coordinate and return the sum as a new coordinate.
   * @param x value to be added to this coordinate x
   * @param y value to be added to this coordinate y
   * @return Coordinate representing teh sum of the two components sum with x and y respectively
   */
  ICoordinate add(double x, double y);
}
