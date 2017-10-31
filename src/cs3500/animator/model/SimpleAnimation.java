package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

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
    for (Shape s : shapes) {
      s.setTicksPerSecond(ticksPerSecond);
    }
    for (AnimationAction a : actions) {
      a.setTicksPerSecond(ticksPerSecond);
    }
  }

  @Override
  public SimpleAnimation addAction(AnimationAction action) throws IllegalArgumentException {
    if (action.getTicksPerSecond() != this.ticksPerSecond) {
      throw new IllegalArgumentException("SimpleAnimation.addAction(AnimationAction) -- action's "
              + "ticksPerSecond is different from this animation's ticksPerSecond.");
    }
    this.actions.add(action);
    return this;
  }

  @Override
  public SimpleAnimation addShape(Shape shape) throws IllegalArgumentException {
    if (shape.getTicksPerSecond() != this.ticksPerSecond) {
      throw new IllegalArgumentException("SimpleAnimation.addShape(Shape) -- shape's "
              + "ticksPerSecond is different from this animation's ticksPerSecond.");
    }
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
      str += shape.toString() + "\n";
    }
    for (AnimationAction action : actions) {
      str += action.toString() + "\n";
    }
    return str;
  }
}
