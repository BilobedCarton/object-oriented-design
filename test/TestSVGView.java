import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.view.ViewFactory;

import static org.junit.Assert.assertEquals;


public class TestSVGView {
  SimpleAnimation buildModel = new SimpleAnimation();
  Shape R1 = new ShapeBuilder().setColor(Color.red).setName("R1").setPosition(
          0,0).setSize(800,800).setTimeSpan(1,12).build(
                  ShapeBuilder.ShapeType.RECTANGLE);
  Shape R2 = new ShapeBuilder().setColor(Color.red).setName("R2").setPosition(
          0,0).setSize(300,300).setTimeSpan(1,12).build(
          ShapeBuilder.ShapeType.RECTANGLE);
  Shape O1 = new ShapeBuilder().setColor(Color.red).setName("O1").setPosition(
          100,100).setSize(10,10).setTimeSpan(1,12).build(
          ShapeBuilder.ShapeType.OVAL);
  ITimedAction M1 = new AnimationActionBuilder().setTargetShape(R2).setTargetPosition(
          200,200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  ITimedAction M2 = new AnimationActionBuilder().setTargetShape(R2).setTargetPosition(
          100,200).setTimeTicks(10, 12).build(AnimationActionBuilder.AnimationActionType.MOVE);
  ITimedAction M3 = new AnimationActionBuilder().setTargetShape(O1).setTargetPosition(
          200,200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  ITimedAction RS1 = new AnimationActionBuilder().setTargetShape(R2).setTargetSize(
          200,200).setTimeTicks(5, 10).build(
                  AnimationActionBuilder.AnimationActionType.SCALE);
  ITimedAction RS2 = new AnimationActionBuilder().setTargetShape(O1).setTargetSize(
          60,60).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.SCALE);
  ITimedAction C1 = new AnimationActionBuilder().setTargetShape(R2).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);
  ITimedAction C2 = new AnimationActionBuilder().setTargetShape(O1).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);

  /**
   * Tests that our correctly outputs as a shape is created.
   */
  @Test
  public void testAddShapes() {
    ViewFactory()
  }
}
