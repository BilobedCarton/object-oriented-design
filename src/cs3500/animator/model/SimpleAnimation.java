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
 * This represents an animation that involves shapesToAdd and various actionsToAdd taken upon these
 * shapesToAdd. The actionsToAdd consist of: * ColorShiftAction - Change the color of a shape over
 * time. * MoveAction - Move the shape from one place to another over time. * ScaleAction - Change
 * the size of a shape from one size to another. The shapesToAdd consist of: * Rectangle - a
 * rectangular shape. * Oval - a oblong shape.
 */
public class SimpleAnimation implements IAnimationModel {
  private List<AnimationAction> updatedActions;
  private List<AnimationAction> actionsToBeUpdated;
  private List<List<Shape>> shapes;

  /**
   * Creates a new {@code SimpleAnimation} object.
   *
   * @throws IllegalArgumentException if ticksPerSecond is less than or eqaul to zero.
   */
  public SimpleAnimation() throws IllegalArgumentException {
    updatedActions = new ArrayList<AnimationAction>();
    actionsToBeUpdated = new ArrayList<AnimationAction>();
    shapes = new ArrayList<List<Shape>>();
  }

  @Override
  public List<AnimationAction> getActions() {
    this.updatedActions.addAll(this.actionsToBeUpdated);
    return Collections.unmodifiableList(this.updatedActions);
  }

  @Override
  public List<Shape> getShapes() {
    List<Shape> shapeList = new ArrayList<>();
    for (List<Shape> list : this.shapes) {
      shapeList.addAll(list);
    }
    return Collections.unmodifiableList(shapeList);
  }

  @Override
  public void addAction(AnimationAction action) {
    if (!this.getShapes().contains(action.getShape())) {
      throw new IllegalArgumentException("SimpleAnimation.getShapeStateAt(int, Shape) -- "
              + "The given shape does not exist in this model.");
    }
    this.actionsToBeUpdated.add(action);
  }

  @Override
  public void pushShape(Shape shape) {
    if (findShape(shape.getName(), this.getShapes()) != null) {
      throw new IllegalArgumentException("SimpleAnimation.addShape(Shape) -- Shape with the given "
              + "name already exists in this model.");
    }
    List<Shape> shapeList = new ArrayList<Shape>();
    shapeList.add(shape);
    shapes.add(0, shapeList);
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    this.addShape(shape, 0);
  }

  @Override
  public void addShape(Shape shape, int layer) throws IllegalArgumentException {
    if (findShape(shape.getName(), this.getShapes()) != null) {
      throw new IllegalArgumentException("SimpleAnimation.addShape(Shape) -- Shape with the given "
              + "name already exists in this model.");
    }
    if (layer < 0) {
      throw new IllegalArgumentException("The given layer does not exist (less than 0).");
    }
    while (this.shapes.size() <= layer) {
      this.shapes.add(new ArrayList<Shape>());
    }

    this.shapes.get(layer).add(shape);
  }

  @Override
  public void runCycle(int currTick) {
    this.updateActions();
    for (AnimationAction action : updatedActions) {
      if (action.getStartTick() == currTick) {
        action.updateOriginalValues();
      } else if (action.getStartTick() < currTick && action.getEndTick() > currTick) {
        action.execute();
      } else if (action.getEndTick() == currTick) {
        action.executeFinal();
      }
    }
  }

  /**
   * Converts this object into a string.
   *
   * @return the String representing this object.
   */
  public String toString(double ticksPerSecond) {
    this.updateActions();
    String str = "Shapes:\n";
    for (List<Shape> layer : this.shapes) {
      for (Shape shape : layer) {
        str += shape.toString(ticksPerSecond) + "\n";
      }
    }
    for (AnimationAction action : this.updatedActions) {
      str += action.toString(ticksPerSecond);
    }

    return str;
  }


  @Override
  public void removeBG() {
    shapes.remove(0);
  }

  /**
   * Finds the shape in the given list with the given name.
   *
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


  @Override
  public Shape getShapeStateAt(int tick, Shape s) throws IllegalArgumentException {
    if (!this.getShapes().contains(s)) {
      throw new IllegalArgumentException("SimpleAnimation.getShapeStateAt(int, Shape) -- "
              + "The given shape does not exist in this model.");
    }
    // Create a identical copy to modify.
    Shape sCopy = ShapeBuilder.copy(s);

    // Create a list of actions that correspond to our new shape
    List<AnimationAction> actionsOnS = new ArrayList<AnimationAction>();

    for (AnimationAction a : this.updatedActions) {
      if (a.getShape().getName().equals(s.getName())) {
        actionsOnS.add(a);
        a.setShape(sCopy);
        a.updateOriginalValues();
      }
    }

    for (int i = 0; i <= tick; i++) {
      for (AnimationAction a : actionsOnS) {
        if (i == a.getStartTick()) {
          a.updateOriginalValues();
        } else if (i > a.getStartTick() && i < a.getEndTick()) {
          a.execute();
        } else if (i == a.getEndTick()) {
          a.executeFinal();
        }
      }
    }

    // Set actions back to original shape.
    for (AnimationAction a : actionsOnS) {
      a.setShape(s);
    }

    return sCopy;
  }

  @Override
  public void updateActions() {
    List<AnimationAction> newUpdatedActions = new ArrayList<AnimationAction>();
    for (AnimationAction a : actionsToBeUpdated) {
      a.setOriginalValues(this.getShapeStateAt(a.getStartTick(), a.getShape()));
      newUpdatedActions.add(a);
    }
    this.updatedActions.addAll(newUpdatedActions);
    this.actionsToBeUpdated.removeAll(newUpdatedActions);
  }

  /**
   * Represents a builder for a new SimpleAnimation.
   */
  public static final class Builder implements TweenModelBuilder<IAnimationModel> {
    private IAnimationModel simpleAnimation = new SimpleAnimation();

    /**
     * Initializes the builder to begin construction of a new model.
     *
     * @return the new builder.
     */
    public static TweenModelBuilder<IAnimationModel> initialize() {
      return new Builder();
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(String name, float cx, float cy,
                                                      float xRadius, float yRadius, float red,
                                                      float green, float blue, int startOfLife,
                                                      int endOfLife, int layer) {
      this.simpleAnimation.addShape(ShapeBuilder.initialize()
              .setName(name)
              .setPosition(cx, cy)
              .setSize(xRadius, yRadius)
              .setColor(new Color(red, green, blue))
              .setTimeSpan(startOfLife, endOfLife)
              .build(ShapeBuilder.ShapeType.OVAL), layer);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(String name, float lx, float ly,
                                                           float width, float height, float red,
                                                           float green, float blue, int startOfLife,
                                                           int endOfLife, int layer) {
      this.simpleAnimation.addShape(ShapeBuilder.initialize()
              .setName(name)
              .setPosition(lx, ly)
              .setSize(width, height)
              .setColor(new Color(red, green, blue))
              .setTimeSpan(startOfLife, endOfLife)
              .build(ShapeBuilder.ShapeType.RECTANGLE), layer);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                      float moveToX, float moveToY, int startTime,
                                                      int endTime) {
      this.simpleAnimation.addAction(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, simpleAnimation.getShapes()))
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
      this.simpleAnimation.addAction(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, simpleAnimation.getShapes()))
              .setTargetColor(new Color(newR, newG, newB))
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx,
                                                               float fromSy, float toSx, float toSy,
                                                               int startTime, int endTime) {
      this.simpleAnimation.addAction(AnimationActionBuilder.initialize()
              .setTargetShape(SimpleAnimation.findShape(name, simpleAnimation.getShapes()))
              .setTargetSize(toSx, toSy)
              .setTimeTicks(startTime, endTime)
              .build(AnimationActionBuilder.AnimationActionType.SCALE));
      return this;
    }

    @Override
    public IAnimationModel build() {
      simpleAnimation.updateActions();
      return simpleAnimation;
    }
  }
}
