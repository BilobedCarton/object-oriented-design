import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.Shape;

import static org.junit.Assert.assertEquals;

public class TestAnimationActions {

  // Test the various animation actions we can undertake.
  @Test
  public void testColorShiftUp() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Rectangle")
            .setColor(new Color(0, 0, 0))
            .setPosition(0, 0)
            .setSize(10, 10)
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction colorShift = AnimationActionBuilder.initialize()
            .setTargetColor(new Color(10, 40, 100))
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    colorShift.execute();
    assertEquals(rect.getColor(), new Color(1, 4, 10));
  }

  @Test
  public void testColorShiftDown() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Rectangle")
            .setColor(new Color(100, 50, 200))
            .setPosition(0, 0)
            .setSize(10, 10)
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction colorShift = AnimationActionBuilder.initialize()
            .setTargetColor(new Color(0, 0, 0))
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    colorShift.execute();
    assertEquals(rect.getColor(), new Color(90, 45, 180));
  }

  @Test
  public void testScale() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Oval")
            .setColor(new Color(0, 0, 0))
            .setPosition(0, 0)
            .setSize(40, 10)
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction scale = AnimationActionBuilder.initialize()
            .setTargetSize(20, 40)
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.SCALE);
    scale.execute();
    assertEquals(oval.getSizeX(), 38, 0);
    assertEquals(oval.getSizeY(), 13, 0);
  }

  @Test
  public void testMove() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Oval")
            .setColor(new Color(0, 0, 0))
            .setPosition(0, 40)
            .setSize(10, 10)
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction move = AnimationActionBuilder.initialize()
            .setTargetPosition(20, 10)
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    move.execute();
    assertEquals(oval.getPosX(), 2, 0);
    assertEquals(oval.getPosY(), 37, 0);
  }

  @Test
  public void testColorShiftToString() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction colorShift = AnimationActionBuilder.initialize()
            .setTargetColor(new Color(40, 90, 160))
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    assertEquals(colorShift.toString(),
            "Shape Test changes color from (0,0,0) to (40,90,160) from t=0.0s to t=10.0s\n");
  }

  @Test
  public void testMoveToString() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction move = AnimationActionBuilder.initialize()
            .setTargetPosition(20, 40)
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    assertEquals(move.toString(),
            "Shape Test moves from (0.0,0.0) to (20.0,40.0) from t=0.0s to t=10.0s\n");
  }

  @Test
  public void testScaleToString() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .setTicksPerSecond(1)
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction scale = AnimationActionBuilder.initialize()
            .setTargetSize(400, 20)
            .setTicksPerSecond(1)
            .setTimeTicks(0, 10)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.SCALE);
    assertEquals(scale.toString(), "Shape Test scales from Width: 0.0 Height: 0.0 to Width:"
            + " 400.0 Height: 20.0 from t=0.0s to t=10.0s\n");
  }
}
