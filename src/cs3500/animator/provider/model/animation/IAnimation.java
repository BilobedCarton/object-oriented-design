package cs3500.animator.provider.model.animation;

import java.util.Comparator;

import cs3500.animator.provider.model.shape.IShape;

/**
 * Represents an animation to be attached to a shape.
 */
public interface IAnimation {
  /**
   * Returns the type of animation this IAnimation performs.
   * @return animation type
   */
  AnimationType getType();

  /**
   * Returns the time the animation starts.
   * @return time of animation start
   */
  int getStartTime();

  /**
   * Returns the time the animation ends.
   * @return time of animation end
   */
  int getEndTime();

  /**
   * Determines if this animation overlaps with another one.
   * @param other the animation to compare against
   * @return true if overlapping, false otherwise
   */
  boolean isOverlapping(IAnimation other);

  /**
   * Return name of the shape this animation is attached to.
   * @return the name of the shape
   */
  String getShapeName();

  /**
   * Set the initial shape of the animation. Needed to provide context in cases where an animation
   * works on an unknown parameter, like the dimensionality of a shape.
   * @param shape initial shape
   */
  void setInitialShape(IShape shape);

  /**
   * Produces the initial shape that this animation applies to.
   * @return    the initial shape that this animation applies to
   */
  IShape getInitialShape();

  /**
   * Determines if this animation occurs during the lifetime of a shape.
   * @param appears time the shape appears
   * @param disappears time the shape disappears
   * @return true if the animation occurs fully during the lifetime of the shape, else false
   */
  boolean isDuringLifetime(int appears, int disappears);

  /**
   * Returns the state of the shape at time t as affected by this animation.
   * @param initialShape the starting object to modify
   * @param t the time of the world
   * @return the initial shape with a modified field based on this animation
   */
  IShape shapeAtTime(IShape initialShape, int t);

  /**
   * Return a string representation of the animation with the given unit type. Calling default
   * toString should return this same representation with no unit strings.
   * @param units the units of time, as a String
   * @return a string representing the animation with given units
   */
  String toString(String units);

  /**
   * Transition info an animation. This is different for each
   * different type of information.
   * @return the unique transition info of this animation
   */
  String transitionInfo();

  /**
   * Get a comparator in order to sort animations by time.
   * @return a comparator that compares the time of two animations
   */
  static Comparator<IAnimation> getTimeComparator() {
    return (a1, a2) -> {
      int a1Start = a1.getStartTime();
      int a2Start = a2.getStartTime();
      if (a1Start == a2Start) {
        return 0;
      } else {
        return a1Start > a2Start ? 1 : -1;
      }
    };
  }
}
