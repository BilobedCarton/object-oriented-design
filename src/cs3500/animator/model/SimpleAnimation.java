package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.util.TweenModelBuilder;

/**
 * This represents an animation.
 */
public class SimpleAnimation implements IAnimationModel {
  private List<AnimationAction> actions;
  private List<Shape> shapes;
  private double ticksPerSecond;

  /**
   * Creates a new {@code SimpleAnimation} object.
   * @throws IllegalArgumentException if ticksPerSecond is less than or eqaul to zero.
   */
  public SimpleAnimation(double ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("SimpleAnimation(int) -- ticksPerSecond must be greater than 0");
    }
    actions = new ArrayList<AnimationAction>();
    shapes = new ArrayList<Shape>();
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public List<AnimationAction> getActions() {
    return actions;
  }

  @Override
  public List<Shape> getShapes() {
    return shapes;
  }

  @Override
  public double getTicksPerSecond() {
    return ticksPerSecond;
  }

  @Override
  public void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("SimpleAnimation.setTicksPerSecond(double) -- "
              + "ticksPerSecond is less than or equal to zero.");
    }
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public SimpleAnimation addAction(AnimationAction action) throws IllegalArgumentException {
    this.actions.add(action);
    return this;
  }

  @Override
  public SimpleAnimation addShape(Shape shape) throws IllegalArgumentException {
    this.shapes.add(shape);
    return this;
  }

  @Override
  public void runAnimation() {
    double currTime = 0;
    while (animationIncomplete(currTime)) {
      runCycle(currTime);
      currTime++;
    }
  }

  @Override
  public boolean animationIncomplete(double currTime) {
    for (Shape shape : shapes) {
      if (shape.getDisappearTick() > currTime * ticksPerSecond) {
        return true;
      }
    }
    for (AnimationAction action : actions) {
      if (action.getEndTick() > currTime * ticksPerSecond) {
        return true;
      }
    }
    return false;
  }

  /**
   * Runs a cycle of this animation.
   * @param currTime is the current time of this animation.
   */
  public void runCycle(double currTime) {
    for (Shape shape : shapes) {
      if (shape.getAppearTick() <= currTime * ticksPerSecond
              && shape.getDisappearTick() > currTime * ticksPerSecond) {
        shape.render();
      }
    }
    for (AnimationAction action : actions) {
      if (action.getStartTick() == currTime * ticksPerSecond) {
        action.updateOriginalValues();
      }
      if (action.getStartTick() <= currTime * ticksPerSecond
              && action.getEndTick() > currTime * ticksPerSecond) {
        action.execute();
      }
    }
  }

  /**
   * Converts this object into a string.
   * @return the String representing this object.
   */
  public String toString() {
    String str = "Shapes:\n";
    for (Shape shape : shapes) {
      str += shape.toString(ticksPerSecond) + "\n";
    }
    for (AnimationAction action : actions) {
      str += action.toString(ticksPerSecond) + "\n";
    }
    return str;
  }

  /**
   * Finds the shape in the given list with the given name.
   * @param name the name of the shape we want. If we return null, the shape doesn't exist in the
   *             list.
   * @return the Shape we are looking for.
   */
  public static Shape findShape(String name, List<Shape> shapes) {
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    return null;
  }

  /**
   * Represents a builder for a new SimpleAnimation.
   */
  public static final class Builder implements TweenModelBuilder<IAnimationModel> {
    private List<Shape> shapes = new ArrayList<Shape>();
    private List<AnimationAction> actions = new ArrayList<AnimationAction>();

    /**
     * Initializes the builder to begin construction of a new model.
     * @return the new builder.
     */
    public static TweenModelBuilder<IAnimationModel> initialize() {
      return new Builder();
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(String name, float cx, float cy,
                                                      float xRadius, float yRadius, float red,
                                                      float green, float blue, int startOfLife,
                                                      int endOfLife) {
      this.shapes.add(ShapeBuilder.initialize()
              .setName(name)
              .setPosition(cx, cy)
              .setSize(xRadius, yRadius)
              .setColor(new Color(red, green, blue))
              .setTimeSpan(startOfLife, endOfLife)
              .build(ShapeBuilder.ShapeType.OVAL));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(String name, float lx, float ly,
                                                           float width, float height, float red,
                                                           float green, float blue, int startOfLife,
                                                           int endOfLife) {
      this.shapes.add(ShapeBuilder.initialize()
              .setName(name)
              .setPosition(lx, ly)
              .setSize(width, height)
              .setColor(new Color(red, green, blue))
              .setTimeSpan(startOfLife, endOfLife)
              .build(ShapeBuilder.ShapeType.RECTANGLE));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                      float moveToX, float moveToY, int startTime,
                                                      int endTime) {
      this.actions.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapes))
              .setTargetPosition(moveToX, moveToY)
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.MOVE));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(String name, float oldR, float oldG,
                                                             float oldB, float newR, float newG,
                                                             float newB, int startTime,
                                                             int endTime) {
      this.actions.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapes))
              .setTargetColor(new Color(newR, newG, newB))
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx,
                                                               float fromSy, float toSx, float toSy,
                                                               int startTime, int endTime) {
      this.actions.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapes))
              .setTargetSize(toSx, toSy)
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.SCALE));
      return this;
    }

    @Override
    public IAnimationModel build() {
      IAnimationModel animationModel = new SimpleAnimation(1);
      for (Shape s : this.shapes) {
        animationModel.addShape(s);
      }
      for (AnimationAction a : this.actions) {
        animationModel.addAction(a);
      }
      return animationModel;
    }
  }
}
