package cs3500.animator.model.actions;

import java.awt.Color;

import cs3500.animator.model.shapes.Shape;

/**
 * Represents a builder for AnimationActions.
 */
public class AnimationActionBuilder {
  private Color targetColor = null;
  private double targetPosX = 0;
  private double targetPosY = 0;
  private double targetSizeX = 0;
  private double targetSizeY = 0;
  private int startTime = 0;
  private int endTime = 0;
  private Shape targetShape = null;

  /**
   * Represents a type of AnimationAction.
   */
  public enum AnimationActionType {
    COLORSHIFT, MOVE, SCALE
  }

  /**
   * Initializes the constructor to create a new AnimationAction.
   *
   * @return a new AnimationActionBuilder.
   */
  public static AnimationActionBuilder initialize() {
    return new AnimationActionBuilder();
  }

  /**
   * Sets the target color for a ColorShiftAction.
   *
   * @param c is the new target color.
   * @return this AnimationActionBuilder.
   */
  public AnimationActionBuilder setTargetColor(Color c) {
    this.targetColor = c;
    return this;
  }

  /**
   * Sets the target positions for a MoveAction.
   *
   * @param x is the new target x coordinate.
   * @param y is the new target y coordinate.
   * @return this AnimationActionBuilder.
   */
  public AnimationActionBuilder setTargetPosition(double x, double y) {
    this.targetPosX = x;
    this.targetPosY = y;
    return this;
  }

  /**
   * Sets the target size for a ScaleAction.
   *
   * @param width  is the new target width.
   * @param height is the new target height.
   * @return this AnimationActionBuilder.
   */
  public AnimationActionBuilder setTargetSize(double width, double height) {
    this.targetSizeX = width;
    this.targetSizeY = height;
    return this;
  }

  /**
   * Sets the tick times for a new action.
   *
   * @param start is the new start tick.
   * @param end   is the new end tick.
   * @return this AnimationActionBuilder.
   */
  public AnimationActionBuilder setTimeTicks(int start, int end) {
    this.startTime = start;
    this.endTime = end;
    return this;
  }

  /**
   * Sets the target shape for a new action.
   *
   * @param s is the new target shape.
   * @return this AnimationActionBuilder.
   */
  public AnimationActionBuilder setTargetShape(Shape s) {
    this.targetShape = s;
    return this;
  }

  /**
   * Builds a new AnimationAction of the given type.
   *
   * @param type is the type of AnimationAction being built.
   * @return the new AnimationAction.
   * @throws IllegalArgumentException if the parameters for the new AnimationAction are invalid.
   */
  public AnimationAction build(AnimationActionType type) throws IllegalArgumentException {
    if (this.targetShape == null) {
      throw new IllegalArgumentException("AnimationActionBuilder.build(AnimationActionType) -- "
              + "target shape is null.");
    }
    if (this.startTime < 0 || this.endTime < 0 || this.endTime < this.startTime) {
      throw new IllegalArgumentException("AnimationActionBuilder.build(AnimationActionType) -- "
              + "startTime or endTime is invalid. They must be greater than zero and "
              + "endTime must be greater than startTime.");
    }
    switch (type) {
      case MOVE:
        return new MoveAction(
                this.startTime, this.endTime, this.targetShape, this.targetPosX, this.targetPosY);
      case SCALE:
        if (this.targetSizeX < 0 || this.targetSizeY < 0) {
          throw new IllegalArgumentException("AnimationActionBuilder.build(AnimationActionType) -- "
                  + "targetSizeX or targetSizeY is less than zero.");
        }
        return new ScaleAction(
                this.startTime, this.endTime, this.targetShape, this.targetSizeX, this.targetSizeY);
      case COLORSHIFT:
        if (this.targetColor == null) {
          throw new IllegalArgumentException("AnimationActionBuilder.build(AnimationActionType) -- "
                  + "targetColor is null");
        }
        return new ColorShiftAction(
                this.startTime, this.endTime, this.targetShape, this.targetColor);
      default:
        throw new IllegalArgumentException("AnimationActionBuilder.build(AnimationActionType) -- "
                + "Given type not recognized.");
    }
  }

  /**
   * Copies the given action and creates a new one with the same parameters.
   *
   * @param action is the action to be copied.
   * @return the new action.
   * @throws IllegalArgumentException if the given action is not recognizable.
   */
  public static AnimationAction copy(AnimationAction action) throws IllegalArgumentException {
    AnimationActionBuilder builder = AnimationActionBuilder.initialize()
            .setTimeTicks(action.getStartTick(), action.getEndTick())
            .setTargetShape((Shape)action.getShape());
    if (action.getClass() == ColorShiftAction.class) {
      return builder.setTargetColor(((ColorShiftAction) action).getTargetColor())
              .build(AnimationActionType.COLORSHIFT);
    } else if (action.getClass() == MoveAction.class) {
      return builder.setTargetPosition(
              ((MoveAction) action).getTargetX(), ((MoveAction) action).getTargetY())
              .build(AnimationActionType.MOVE);
    } else if (action.getClass() == ScaleAction.class) {
      return builder.setTargetSize(
              ((ScaleAction) action).getTargetSizeX(), ((ScaleAction) action).getTargetSizeY())
              .build(AnimationActionType.SCALE);
    } else {
      throw new IllegalArgumentException("AnimationActionBuilder.copy(AnimationAction) -- "
              + "action is not recognized.");
    }
  }
}