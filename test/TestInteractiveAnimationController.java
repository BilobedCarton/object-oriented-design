import org.junit.Test;

import java.awt.Color;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.view.InteractiveViewO;

import static org.junit.Assert.assertEquals;

public class TestInteractiveAnimationController {

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
   * Tests that we can run an update.
   */
  @Test
  public void testRunUpdate() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);
    testCont.runUpdate();
    assertEquals(testCont.getCurrTick(), 2);
  }

  /**
   * Tests that we can start the animation.
   */
  @Test
  public void testStartAnimation() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);
    assertEquals(testCont.getTimer().isRunning(), false);
    testCont.startAnimation();
    assertEquals(testCont.getTimer().isRunning(), true);
  }

  /**
   * Tests that we can pause the animation.
   */
  @Test
  public void testPauseAnimation() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);

    testCont.startAnimation();
    assertEquals(testCont.getTimer().isRunning(), true);
    testCont.pauseAnimation();
    assertEquals(testCont.getTimer().isRunning(), false);
  }

  /**
   * Tests that we can get the looper.
   */
  @Test
  public void testGetLooper() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);

    assertEquals(testCont.getLooping(), false);
  }

  /**
   * Tests that we can set the looper.
   */
  @Test
  public void testSetLooper() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);

    assertEquals(testCont.getLooping(), false);
    testCont.toggleLooping();
    assertEquals(testCont.getLooping(), true);
  }

  /**
   * Tests that we can set the visibility.
   */
  @Test
  public void testSetVisibility() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);
    InteractiveAnimationController testCont = new InteractiveAnimationController(buildModel,
            testView, 5);
    String[] stingAr = new String[]{"r2"};
    testCont.setSelectedShapes(stingAr);

    assertEquals(r2.isVisible(), true);

    testCont.markSelectedShapesVisibility(false);
    assertEquals(r2.isVisible(), false);
  }
}
