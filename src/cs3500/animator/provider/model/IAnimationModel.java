package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.animation.IAnimation;
import cs3500.animator.model.shape.IShape;

/**
 * Describes the public facing methods of a basic 2D animation model. The model registers shapes
 * of type IShape based on a unique name identifier. Model can produce textual description of scene
 * however it relies on the shape to provide its own description when represented as a string.
 */
public interface IAnimationModel {
  /**
   * Add a IShape to the model, given a unique name. No duplicates names allowed.
   * @param name the unique name identifier for the shape
   * @param shape the initial appearance of the shape being animated
   * @param appears a time >= 0 that the shape appears in the scene
   *
   * @throws IllegalArgumentException if name is not unique, shape appears at an invalid time, or
   *      if shape has an invalid duration
   */
  void addShape(String name, IShape shape, int appears, int disappears)
          throws IllegalArgumentException;

  /**
   * Add an animation to a shape of the given name. Animations of the same type may not have
   * overlapping times. Animations of varying types may have overlapping times.
   * @param animations animations to be added
   * @throws IllegalArgumentException if the name is not found, the type is unrecognized, the time
   *      is invalid, or the duration is invalid
   */
  void addAnimations(IAnimation... animations)
          throws IllegalArgumentException;

  /**
   * Creates a textual representation of the scene contained in the model. This description has two
   * sections. In the first, each shape is declared by its name, type, starting appearance as
   * determined by the shapes internal representation, and appearance/disapperance times. In the
   * second section, each animation is listed by what shape it changes, how it changes it, and the
   * animations start/stop times. Refer to Assignment 5 Section 2.1 for example output.
   * @return string representing the above
   */
  String getDescriptionAsString();

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
