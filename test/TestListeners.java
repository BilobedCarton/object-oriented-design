import org.junit.Test;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.control.listeners.ShapeSelectionListener;
import cs3500.animator.control.listeners.UpdateListener;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.view.InteractiveView;

import static org.junit.Assert.assertEquals;

public class TestListeners {
  Shape r2 = new ShapeBuilder().setColor(Color.red).setName("r2").setPosition(
          0, 0).setSize(300, 300).setTimeSpan(1, 12).build(
          ShapeType.RECTANGLE);
  AnimationAction m1 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          200, 200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction m2 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          100, 200).setTimeTicks(10, 12).build(AnimationActionBuilder.
          AnimationActionType.MOVE);
  SimpleAnimation buildModel = new SimpleAnimation();
  StringBuilder testBuilder = new StringBuilder();

  /**
   * Tests the update listener's action performed method works.
   */
  @Test
  public void testActionPerformed() {
    buildModel.addShape(r2);
    InteractiveView testView = new InteractiveView(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    IAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);
    UpdateListener listener = new UpdateListener(testCont);
    ActionEvent ev = new ActionEvent(this, 10, "fire");
    assertEquals(testCont.getCurrTick(), 1);

    listener.actionPerformed(ev);
    assertEquals(testCont.getCurrTick(), 2);

  }

  /**
   * Tests the shape selection listeners setjlist method.
   */
  @Test
  public void testSetJList() {
    buildModel.addShape(r2);
    InteractiveView testView = new InteractiveView(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);
    ShapeSelectionListener listener = new ShapeSelectionListener(testCont);

    JList listIt = new JList();
    JList nonList = listener.getJList();
    assertEquals(listener.getJList(), nonList);

    listener.setJList(listIt);
    assertEquals(listener.getJList(), listIt);
  }
}
