import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IAnimationModelOrig;
import cs3500.animator.model.SimpleAnimationOrig;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.actions.ColorShiftAction;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.actions.ScaleAction;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeType;

public class TestBuilders {
  ShapeBuilder baseShapeBuilder = ShapeBuilder
          .initialize()
          .setName("Test")
          .setColor(new Color(255, 255, 255))
          .setPosition(10, 5)
          .setSize(3, 4)
          .setTimeSpan(0, 10);
  AnimationActionBuilder baseActionBuilder = AnimationActionBuilder
          .initialize()
          .setTimeTicks(5, 10);

  // Test the ShapeBuilder
  @Test
  public void testBuildRectangle() {
    Shape rect = baseShapeBuilder.build(ShapeType.RECTANGLE);
    assertEquals(rect.getPosX(), 10.0, 0);
    assertEquals(rect.getPosY(), 5.0, 0);
    assertEquals(rect.getColor(), new Color(255, 255, 255));
    assertEquals(rect.getSizeX(), 3.0, 0);
    assertEquals(rect.getSizeY(), 4.0, 0);
    assertEquals(rect.getAppearTick(), 0);
    assertEquals(rect.getDisappearTick(), 10);
    assertEquals(rect.getClass(), Rectangle.class);
  }

  @Test
  public void testBuildOval() {
    Shape oval = baseShapeBuilder.build(ShapeType.OVAL);
    assertEquals(oval.getPosX(), 10.0, 0);
    assertEquals(oval.getPosY(), 5.0, 0);
    assertEquals(oval.getColor(), new Color(255, 255, 255));
    assertEquals(oval.getSizeX(), 3.0, 0);
    assertEquals(oval.getSizeY(), 4.0, 0);
    assertEquals(oval.getAppearTick(), 0);
    assertEquals(oval.getDisappearTick(), 10);
    assertEquals(oval.getClass(), Oval.class);
  }

  @Test
  public void testCopyShape() {
    Shape rect = baseShapeBuilder.build(ShapeType.RECTANGLE);
    Shape copy = ShapeBuilder.copy(rect);
    assertEquals(rect.getPosX(), copy.getPosX(), 0);
    assertEquals(rect.getPosY(), copy.getPosY(), 0);
    assertEquals(rect.getColor(), copy.getColor());
    assertEquals(rect.getSizeX(), copy.getSizeX(), 0);
    assertEquals(rect.getSizeY(), copy.getSizeY(), 0);
    assertEquals(rect.getAppearTick(), copy.getAppearTick());
    assertEquals(rect.getDisappearTick(), copy.getDisappearTick());
    assertEquals(rect.getClass(), copy.getClass());
  }

  // Test the AnimationActionBuilder
  @Test
  public void testBuildColorShift() {
    Shape rect = baseShapeBuilder.build(ShapeType.RECTANGLE);
    AnimationAction colorShift = baseActionBuilder
            .setTargetColor(new Color(100, 100, 100))
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    assertEquals(colorShift.getStartTick(), 5);
    assertEquals(colorShift.getEndTick(), 10);
    assertEquals(colorShift.getShape().getName(), rect.getName());
    assertEquals(((ColorShiftAction) colorShift).getTargetColor(), new Color(100,
            100, 100));
  }

  @Test
  public void testBuildMove() {
    Shape rect = baseShapeBuilder.build(ShapeType.RECTANGLE);
    AnimationAction move = baseActionBuilder
            .setTargetPosition(40, 20)
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.MOVE);
    assertEquals(move.getStartTick(), 5);
    assertEquals(move.getEndTick(), 10);
    assertEquals(move.getShape().getName(), rect.getName());
    assertEquals(((MoveAction) move).getTargetX(), 40, 0);
    assertEquals(((MoveAction) move).getTargetY(), 20, 0);
  }

  @Test
  public void testBuildScale() {
    Shape oval = baseShapeBuilder.build(ShapeType.OVAL);
    AnimationAction scale = baseActionBuilder
            .setTargetSize(200, 100)
            .setTargetShape(oval)
            .build(AnimationActionBuilder.AnimationActionType.SCALE);
    assertEquals(scale.getStartTick(), 5);
    assertEquals(scale.getEndTick(), 10);
    assertEquals(scale.getShape().getName(), oval.getName());
    assertEquals(((ScaleAction) scale).getTargetSizeX(), 200, 0);
    assertEquals(((ScaleAction) scale).getTargetSizeY(), 100, 0);
  }

  @Test
  public void testCopyAction() {
    Shape rect = baseShapeBuilder.build(ShapeType.RECTANGLE);
    AnimationAction colorShift = baseActionBuilder
            .setTargetColor(new Color(100, 100, 100))
            .setTargetShape(rect)
            .build(AnimationActionBuilder.AnimationActionType.COLORSHIFT);
    AnimationAction copy = AnimationActionBuilder.copy(colorShift);
    assertEquals(colorShift.getStartTick(), copy.getStartTick());
    assertEquals(colorShift.getEndTick(), copy.getEndTick());
    assertEquals(colorShift.getShape().getName(), copy.getShape().getName());
    assertEquals(((ColorShiftAction) colorShift).getTargetColor(),
            ((ColorShiftAction) copy).getTargetColor());

  }

  // Test the model builder.
  @Test
  public void testBuildModel() {
    // TODO create more indepth test.
    IAnimationModelOrig model = SimpleAnimationOrig.Builder.initialize()
            .addRectangle("R", 0, 0, 10, 20, 0.3f, 0.3f,
                    0.3f, 0, 10)
            .addOval("O", 10, 20, 5, 5, 0.1f, 0.1f,
                    0.1f, 5, 15)
            .addMove("O", 10, 20, 10, 0,
                    10, 15)
            .addColorChange("R", 0.3f, 0.3f, 0.3f, 0.05f,
                    0.1f, 0.15f, 2, 7)
            .addScaleToChange("O", 5, 5, 20, 10, 8,
                    13)
            .build();
    assertEquals(model.getActions().size(), 3);
    assertEquals(model.getShapes().size(), 2);
    assertEquals(model.getShapes().get(0).getName(), "R");
    assertEquals(model.getShapes().get(0).getColor(), new Color(0.3f, 0.3f, 0.3f));
    assertEquals(model.getShapes().get(1).getName(), "O");
    assertEquals(model.getShapes().get(1).getPosX(), 10, 0);
    assertEquals(model.getShapes().get(1).getPosY(), 20, 0);
    assertEquals(model.getShapes().get(1).getSizeX(), 5, 0);
    assertEquals(model.getShapes().get(1).getSizeY(), 5, 0);

    assertEquals(model.getActions().get(0).getShape().getName(), "O");
    assertEquals(model.getActions().get(1).getShape().getName(), "R");
    assertEquals(model.getActions().get(2).getShape().getName(), "O");

    for (int i = 0; i <= 15; i++) {
      model.runCycle(i);
    }

    assertEquals(model.getShapes().get(0).getName(), "R");
    assertEquals(model.getShapes().get(0).getColor(), new Color(0.05f, 0.1f, 0.15f));
    assertEquals(model.getShapes().get(1).getName(), "O");
    assertEquals(model.getShapes().get(1).getPosX(), 10, 0);
    assertEquals(model.getShapes().get(1).getPosY(), 0, 0.0001);
    assertEquals(model.getShapes().get(1).getSizeX(), 20, 0.0001);
    assertEquals(model.getShapes().get(1).getSizeY(), 10, 0.0001);
  }
}
