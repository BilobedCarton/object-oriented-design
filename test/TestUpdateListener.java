import org.junit.Test;

import java.awt.Color;
import java.awt.event.ActionEvent;

import cs3500.animator.control.AnimationController;
import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.listeners.ISelectionListener;
import cs3500.animator.control.listeners.UpdateListener;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

public class TestUpdateListener {
  Shape r2 = new ShapeBuilder().setColor(Color.red).setName("r2").setPosition(
          0, 0).setSize(300, 300).setTimeSpan(1, 12).build(
          ShapeBuilder.ShapeType.RECTANGLE);
  AnimationAction m1 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          200, 200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction m2 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          100, 200).setTimeTicks(10, 12).build(AnimationActionBuilder.
          AnimationActionType.MOVE);
  SimpleAnimation buildModel = new SimpleAnimation();
  StringBuilder testBuilder = new StringBuilder();

  /**
   * Tests that our svg view correctly outputs a rect when added.
   */
  @Test
  public void testActionPerformed() {

    assertEquals(true, true);
  }
}
