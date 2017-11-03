package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.util.TweenModelBuilder;

/**
 * This represents an animation that involves shapesToAdd and various actionsToAdd taken upon these shapesToAdd.
 * The actionsToAdd consist of:
 *  * ColorShiftAction - Change the color of a shape over time.
 *  * MoveAction - Move the shape from one place to another over time.
 *  * ScaleAction - Change the size of a shape from one size to another.
 * The shapesToAdd consist of:
 *  * Rectangle - a rectangular shape.
 *  * Oval - a oblong shape.
 */
public class SimpleAnimation implements IAnimationModel {
  private List<AnimationAction> actions;
  private List<Shape> shapes;

  /**
   * Creates a new {@code SimpleAnimation} object.
   * @throws IllegalArgumentException if ticksPerSecond is less than or eqaul to zero.
   */
  public SimpleAnimation() throws IllegalArgumentException {
    actions = new ArrayList<AnimationAction>();
    shapes = new ArrayList<Shape>();
  }

  @Override
  public List<AnimationAction> getActions() {
    return Collections.unmodifiableList(this.actions);
  }

  @Override
  public List<Shape> getShapes() {
    return Collections.unmodifiableList(this.shapes);
  }

  @Override
  public void addAction(AnimationAction action) {
    Shape shape = this.getShapeStateAt(action.getStartTick(), action.getShape());
    action.setOriginalValues(shape);
    this.actions.add(action);
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (findShape(shape.getName(), this.shapes) != null) {
      throw new IllegalArgumentException("SimpleAnimation.addShape(Shape) -- Shape with the given "
              + "name already exists in this model.");
    }
    this.shapes.add(shape);
  }

  @Override
  public boolean animationIncomplete(int currTick) {
    for (Shape shape : shapes) {
      if (shape.getDisappearTick() > currTick) {
        return true;
      }
    }
    for (AnimationAction action : actions) {
      if (action.getEndTick() > currTick) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void runCycle(int currTick) {
    for (Shape shape : shapes) {
      if (shape.getAppearTick() <= currTick && shape.getDisappearTick() > currTick) {
        // TODO remove this, handle rendering in views
        shape.render();
      }
    }
    for (AnimationAction action : actions) {
      if (action.getStartTick() <= currTick && action.getEndTick() > currTick) {
        action.execute();
      }
      if (action.getEndTick() == currTick) {
        action.executeFinal();
      }
    }
  }

  /**
   * Converts this object into a string.
   * @return the String representing this object.
   */
  public String toString(double ticksPerSecond) {
    String str = "Shapes:\n";
    for (Shape shape : this.shapes) {
      str += shape.toString(ticksPerSecond) + "\n";
    }
    for (AnimationAction action : this.actions) {
      str += action.toString(ticksPerSecond);
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
   * Gets the state of a shape at the given tick.
   * @param tick is the tick we want to check the shape at.
   * @param s is the Shape we are checking.
   * @return a copy of the shape with data updated to the given tick.
   * @throws IllegalArgumentException if the given shape does not exist in this model.
   */
  public Shape getShapeStateAt(int tick, Shape s) throws IllegalArgumentException {
    if (this.shapes.contains(s) == false) {
      throw new IllegalArgumentException("SimpleAnimation.getShapeStateAt(int, Shape) -- "
              + "The given shape does not exist in this model.");
    }
    // Create a identical copy to modify.
    Shape sCopy = ShapeBuilder.copy(s);

    for (int i = 0; i < tick; i++) {
      for (AnimationAction a : this.actions) {
        if (a.getShape().getName().equals(s.getName())) {
          a.setShape(sCopy);
          if (a.getStartTick() == i) {
            a.updateOriginalValues();
          }
          if (a.getStartTick() <= i && a.getEndTick() > i) {
            a.execute();
          }
        }
      }
    }

    // Set actions back to original shape.
    for (AnimationAction a : this.actions) {
      if (a.getShape().getName().equals(s.getName())) {
        a.setShape(s);
      }
    }

    return sCopy;
  }

  /**
   * Represents a builder for a new SimpleAnimation.
   */
  public static final class Builder implements TweenModelBuilder<IAnimationModel> {
    private List<Shape> shapesToAdd = new ArrayList<Shape>();
    private List<AnimationAction> actionsToAdd = new ArrayList<AnimationAction>();

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
      this.shapesToAdd.add(ShapeBuilder.initialize()
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
      this.shapesToAdd.add(ShapeBuilder.initialize()
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
      this.actionsToAdd.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapesToAdd))
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
      this.actionsToAdd.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapesToAdd))
              .setTargetColor(new Color(newR, newG, newB))
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx,
                                                               float fromSy, float toSx, float toSy,
                                                               int startTime, int endTime) {
      this.actionsToAdd.add(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, this.shapesToAdd))
              .setTargetSize(toSx, toSy)
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.SCALE));
      return this;
    }

    @Override
    public IAnimationModel build() {
      IAnimationModel animationModel = new SimpleAnimation();
      for (Shape s : this.shapesToAdd) {
        animationModel.addShape(s);
      }
      for (AnimationAction a : this.actionsToAdd) {
        animationModel.addAction(a);
      }
      return animationModel;
    }
  }
}
