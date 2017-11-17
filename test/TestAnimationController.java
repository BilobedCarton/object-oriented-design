import org.junit.Test;

import java.awt.Color;

import cs3500.animator.control.AnimationController;
import cs3500.animator.control.IAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A testing class for the AnimationController class.
 */
public class TestAnimationController {
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
   * Tests that we can get the view.
   */
  @Test
  public void testGetView() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);

    assertEquals(testCont.getView(), testView);
  }

  /**
   * Tests that we can get the model.
   */
  @Test
  public void testGetModel() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);

    assertEquals(testCont.getModel().toString(),
            new ReadOnlySimpleAnimation(buildModel).toString());
  }

  /**
   * Tests that we can get the speed.
   */
  @Test
  public void testGetSpeed() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);

    assertEquals(testCont.getSpeed(),5, .01);
  }

  /**
   * Tests that we can get the ticks.
   */
  @Test
  public void testGetTickStart() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);

    assertEquals(testCont.getCurrTick(),1);
  }

  /**
   * Tests that we can run an update.
   */
  @Test
  public void testrunUpdate() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);
    testCont.runUpdate();
    assertNotEquals(testCont.getCurrTick(),1);
  }


  /**
   * Tests that get the timer as its made.
   */
  @Test
  public void testGetTimerAtMake() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);

    assertEquals(testCont.getTimer().isRunning(),false);
  }

  /**
   * Tests thatwe can get the timer as its running, and it is different.
   */
  @Test
  public void testGetTimerStartRunning() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);
    testCont.go();
    assertEquals(testCont.getTimer().isRunning(),true);
  }

  /**
   * Tests that we can change the speed.
   */
  @Test
  public void testChangeSpeed() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);
    testCont.changeSpeed(12);
    assertEquals(testCont.getSpeed(),12, .01);
  }


  /**
   * Tests that we can reset the ticks.
   */
  @Test
  public void testReset() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);
    testCont.runUpdate();
    testCont.runUpdate();
    testCont.reset(true);
    assertEquals(testCont.getCurrTick(),1);
  }

  /**
   * Tests that we can reset the timer.
   */
  @Test
  public void testResetTimer() {
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    IAnimationController testCont = new AnimationController(buildModel, testView, 5);
    testCont.go();
    assertEquals(testCont.getTimer().isRunning(),true);
    testCont.reset(true);
    assertEquals(testCont.getTimer().isRunning(),false);
  }
}
