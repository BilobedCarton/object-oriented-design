package cs3500.animator.provider.model;

import java.util.List;

/**
 * Adapter class for the IAnimationModel. This class is a read-only version of the model that
 * can be given to a View. The purpose is to expose only getter methods of the model to prevent
 * mutation of the model from within the view.
 */
public interface IViewModel {
  /**
   * Produces a sorted list of all the animations in this model.
   * @return a sorted list of all the animations in this model
   */
  List<IAnimation> getAnimations();

  /**
   * Calculates and returns a list of shapes that represents the current appearance of every
   * animated shape at a given time t.
   * @param ticks the current time of the animation
   * @return a list of IShapes that represents the instantaneous appearance of the animation
   * @throws IllegalArgumentException if ticks are invalid
   */
  List<IShape> getShapesAtT(int ticks) throws IllegalArgumentException;


  /**
   * Get the last tick of the animation. This number is given by the maximum end time
   * of the shapes held in the model.
   * @return the last tick
   */
  int getEndTick();

  /**
   * Get the suggested width of the visual animation. This number is given by the maximum dimension
   * that any shape reaches during the animation.
   * @return the x-axis width
   */
  int getWidthRequired();

  /**
   * Get the suggested height of the visual animation. This number is given by the maximum dimension
   * that any shape reaches during the animation.
   * @return the y-axis height
   */
  int getHeightRequired();
}
