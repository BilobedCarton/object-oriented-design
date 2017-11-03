import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;

import static org.junit.Assert.assertEquals;

public class TestAnimationModel {
  SimpleAnimation model = new SimpleAnimation();

  @Test
  public void testAdd() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    AnimationAction action = AnimationActionBuilder.initialize()
            .setTimeTicks(0, 20)
            .setTargetPosition(12, 12)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    model.addShape(rect);
    model.addAction(action);
    assertEquals(model.toString(), "Shapes:\n" + "Name: Test\n" + "Type: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 0.0 Height: 0.0, Color: (0,0,0)\n"
            + "Appears at t=0.0s\n" + "Disappears at t=0.0s\n" + "\n"
            + "Shape Test moves from (0.0,0.0) to (12.0,12.0) from t=0.0s to t=20.0s\n\n");
  }

  @Test
  public void testRunCycle() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .setTimeSpan(0, 20)
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    model.addShape(rect);
    AnimationAction colorShift = AnimationActionBuilder.initialize()
            .setTimeTicks(5, 10)
            .setTargetColor(new Color(40, 40, 40))
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    model.addAction(colorShift);
    model.runCycle(5);
    assertEquals(rect.getColor(), new Color(8, 8, 8));
    model.runCycle(6);
    assertEquals(rect.getColor(), new Color(16, 16, 16));
    model.runCycle(10);
    assertEquals(rect.getColor(), new Color(16, 16, 16));
  }

  @Test
  public void testRunAnimation() {
    Shape rect = ShapeBuilder.initialize()
            .setName("TestRect")
            .setColor(new Color(0, 0, 0))
            .setTimeSpan(0, 40)
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    Shape oval = ShapeBuilder.initialize()
            .setName("TestOval")
            .setColor(new Color(0, 0, 0))
            .setTimeSpan(30, 70)
            .build(ShapeBuilder.ShapeType.OVAL);
    AnimationAction move = AnimationActionBuilder.initialize()
            .setTimeTicks(20, 30)
            .setTargetPosition(20, 40)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    AnimationAction scale = AnimationActionBuilder.initialize()
            .setTimeTicks(10, 15)
            .setTargetSize(20, 10)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.SCALE);
    model.addShape(rect);
    model.addShape(oval);
    model.addAction(move);
    model.addAction(scale);

    for (int i = 0; i <= 30; i++) {
      model.runCycle(i);
    }

    assertEquals(rect.getPosX(), 20, 0);
    assertEquals(rect.getPosY(), 40, 0);
    assertEquals(oval.getSizeX(), 20, 0);
    assertEquals(oval.getSizeY(), 10, 0);
  }
}
