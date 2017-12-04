import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.view.InteractiveViewO;

import static org.junit.Assert.assertEquals;

public class TestInteractiveViewO {

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
   * Tests that we can start the view.
   */
  @Test
  public void testStart() {
    buildModel.addShape(r2);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);

    assertEquals(testView.getFrame().isVisible(), false);
    testView.start();
    assertEquals(testView.getFrame().isVisible(), true);
  }

  /**
   * Tests that it is interactive.
   */
  @Test
  public void testIsInteractive() {
    buildModel.addShape(r2);
    buildModel.addAction(m1);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);

    assertEquals(testView.isInteractive(), true);
  }


  /**
   * Tests exporting svg as non-loop.
   */
  @Test
  public void testExportNonLoop() {
    buildModel.addShape(r2);
    buildModel.addAction(m1);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);

    testView.export(false);
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\""
            + " xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\" width="
            + "\"300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t<animate"
            + " attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\"visible"
            + "\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\"visibil"
            + "ity\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\""
            + "5000.0ms\" attributeName=\"x\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" attributeName="
            + "\"y\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n</rect>\n</svg>");
  }

  /**
   * Tests exporting svg as loop.
   */
  @Test
  public void testExportWithLoop() {
    buildModel.addShape(r2);
    buildModel.addAction(m1);
    InteractiveViewO testView = new InteractiveViewO(new ReadOnlySimpleAnimation(buildModel),
            testBuilder, 1, 500,200);

    testView.export(true);
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect>\n\t<animate id=\"base\" begin=\"0;ba"
            + "se.end\" dur=\"13000.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/"
            + ">\n</rect>\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\" width=\"300.0\" height=\"300.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t<animate attributeType=\"xml\" be"
            + "gin=\"base.begin+1000.0ms\" attributeName=\"visibility\" to=\"visible\" />\n"
            + "\t<animate attributeType=\"xml\" begin=\"base.begin+12000.0ms\" attributeName=\"vis"
            + "ibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"base.begin+500"
            + "0.0ms\" dur=\"5000.0ms\" attributeName=\"x\" from=\"0.0\" to=\"200.0\" fill=\"freez"
            + "e\" />\n\t<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"5000.0m"
            + "s\" attributeName=\"y\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "\t<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\""
            + " to=\"0.0\" fill=\"freeze\" />\n\t<animate attributeType=\"xml\" begin=\"base.end\""
            + " dur=\"1ms\" attributeName=\"y\" to=\"0.0\" fill=\"freeze\" />\n</rect>\n</svg>");
  }
}
