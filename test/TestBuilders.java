import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.actions.ColorShiftAction;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.actions.ScaleAction;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

public class TestBuilders {
  ShapeBuilder baseShapeBuilder = ShapeBuilder
          .initialize()
          .setName("Test")
          .setColor(new Color(255, 255, 255))
          .setTicksPerSecond(1)
          .setPosition(10, 5)
          .setSize(3, 4)
          .setTimeSpan(0, 10);
  AnimationActionBuilder baseActionBuilder = AnimationActionBuilder
          .initialize()
          .setTimeTicks(5, 10)
          .setTicksPerSecond(1);

  // Test the ShapeBuilder
  @Test
  public void testBuildRectangle() {
    Shape rect = baseShapeBuilder.build(ShapeBuilder.ShapeType.RECTANGLE);
    assertEquals(rect.getPosX(), 10.0, 0);
    assertEquals(rect.getPosY(), 5.0, 0);
    assertEquals(rect.getColor(), new Color(255, 255, 255));
    assertEquals(rect.getSizeX(), 3.0, 0);
    assertEquals(rect.getSizeY(), 4.0, 0);
    assertEquals(rect.getTicksPerSecond(), 1, 0);
    assertEquals(rect.getAppearTick(), 0);
    assertEquals(rect.getDisappearTick(), 10);
    assertEquals(rect.getClass(), Rectangle.class);
  }

  @Test
  public void testBuildOval() {
    Shape oval = baseShapeBuilder.build(ShapeBuilder.ShapeType.OVAL);
    assertEquals(oval.getPosX(), 10.0, 0);
    assertEquals(oval.getPosY(), 5.0, 0);
    assertEquals(oval.getColor(), new Color(255, 255, 255));
    assertEquals(oval.getSizeX(), 3.0, 0);
    assertEquals(oval.getSizeY(), 4.0, 0);
    assertEquals(oval.getTicksPerSecond(), 1, 0);
    assertEquals(oval.getAppearTick(), 0);
    assertEquals(oval.getDisappearTick(), 10);
    assertEquals(oval.getClass(), Oval.class);
  }

  @Test
  public void testCopyShape() {
    Shape rect = baseShapeBuilder.build(ShapeBuilder.ShapeType.RECTANGLE);
    Shape copy = ShapeBuilder.copy(rect);
    assertEquals(rect.getPosX(), copy.getPosX(), 0);
    assertEquals(rect.getPosY(), copy.getPosY(), 0);
    assertEquals(rect.getColor(), copy.getColor());
    assertEquals(rect.getSizeX(), copy.getSizeX(), 0);
    assertEquals(rect.getSizeY(), copy.getSizeY(), 0);
    assertEquals(rect.getTicksPerSecond(), copy.getTicksPerSecond(), 0);
    assertEquals(rect.getAppearTick(), copy.getAppearTick());
    assertEquals(rect.getDisappearTick(), copy.getDisappearTick());
    assertEquals(rect.getClass(), copy.getClass());
  }

  // Test the AnimationActionBuilder
  @Test
  public void testBuildColorShift() {
    Shape rect = baseShapeBuilder.build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction colorShift = baseActionBuilder
            .setTargetColor(new Color(100, 100 ,100))
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    assertEquals(colorShift.getStartTick(), 5);
    assertEquals(colorShift.getEndTick(), 10);
    assertEquals(colorShift.getTicksPerSecond(), 1, 0);
    assertEquals(colorShift.getShape().getName(), rect.getName());
    assertEquals(((ColorShiftAction) colorShift).getTargetColor(), new Color(100, 100, 100));
  }

  @Test
  public void testBuildMove() {
    Shape rect = baseShapeBuilder.build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction move = baseActionBuilder
            .setTargetPosition(40, 20)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    assertEquals(move.getStartTick(), 5);
    assertEquals(move.getEndTick(), 10);
    assertEquals(move.getTicksPerSecond(), 1, 0);
    assertEquals(move.getShape().getName(), rect.getName());
    assertEquals(((MoveAction) move).getTargetX(), 40, 0);
    assertEquals(((MoveAction) move).getTargetY(), 20, 0);
  }

  @Test
  public void testBuildScale() {
    Shape oval = baseShapeBuilder.build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction scale = baseActionBuilder
            .setTargetSize(200, 100)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.SCALE);
    assertEquals(scale.getStartTick(), 5);
    assertEquals(scale.getEndTick(), 10);
    assertEquals(scale.getTicksPerSecond(), 1, 0);
    assertEquals(scale.getShape().getName(), oval.getName());
    assertEquals(((ScaleAction) scale).getTargetSizeX(), 200, 0);
    assertEquals(((ScaleAction) scale).getTargetSizeY(), 100, 0);
  }

  @Test
  public void testCopyAction() {
    Shape rect = baseShapeBuilder.build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction colorShift = baseActionBuilder
            .setTargetColor(new Color(100, 100 ,100))
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    AnimationAction copy = AnimationActionBuilder.copy(colorShift);
    assertEquals(colorShift.getStartTick(), copy.getStartTick());
    assertEquals(colorShift.getEndTick(), copy.getEndTick());
    assertEquals(colorShift.getTicksPerSecond(), copy.getTicksPerSecond(), 0);
    assertEquals(colorShift.getShape().getName(), copy.getShape().getName());
    assertEquals(((ColorShiftAction) colorShift).getTargetColor(),
            ((ColorShiftAction) copy).getTargetColor());

  }
}