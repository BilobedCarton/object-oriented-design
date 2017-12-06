import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimationOrig;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;


public class TestSVGView {
  Shape r1 = new ShapeBuilder().setColor(Color.red).setName("r1").setPosition(
          0, 0).setSize(800, 800).setTimeSpan(1, 12).build(
          ShapeType.RECTANGLE);
  Shape r2 = new ShapeBuilder().setColor(Color.red).setName("r2").setPosition(
          0, 0).setSize(300, 300).setTimeSpan(1, 12).build(
          ShapeType.RECTANGLE);
  Shape o1 = new ShapeBuilder().setColor(Color.red).setName("o1").setPosition(
          100, 100).setSize(10, 10).setTimeSpan(1, 12).build(
          ShapeType.OVAL);
  AnimationAction m1 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          200, 200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction m2 = new AnimationActionBuilder().setTargetShape(r2).setTargetPosition(
          100, 200).setTimeTicks(10, 12).build(AnimationActionBuilder.
          AnimationActionType.MOVE);
  AnimationAction m3 = new AnimationActionBuilder().setTargetShape(o1).setTargetPosition(
          200, 200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction rs1 = new AnimationActionBuilder().setTargetShape(r2).setTargetSize(
          200, 200).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.SCALE);
  AnimationAction rs2 = new AnimationActionBuilder().setTargetShape(o1).setTargetSize(
          60, 60).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.SCALE);
  AnimationAction c1 = new AnimationActionBuilder().setTargetShape(r2).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);
  AnimationAction c2 = new AnimationActionBuilder().setTargetShape(o1).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);

  /**
   * Tests that our svg view correctly outputs a rect when added.
   */
  @Test
  public void testAddShapesR() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\""
            + " width=\"300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            + "visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            + "visibility\" to=\"hidden\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse when added.
   */
  @Test
  public void testAddShapesO() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"o1\" cx=\"100.0\" cy=\"100.0\""
            + " rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            + "visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            + "visibility\" to=\"hidden\" />\n</ellipse>\n</svg>");
  }

  /**
   * Tests that our svg view correctly rescales the view when a large shape added a rect when
   * added.
   */
  @Test
  public void testAddShapesRWindowScale() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"800\" height=\"800\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r1\" x=\"0.0\" y=\"0.0\""
            + " width=\"800.0\" height=\"800.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            + "visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            + "visibility\" to=\"hidden\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a rect with actions that change the starting pos.
   */
  @Test
  public void testAddShapesRMoves2() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(m1);
    buildModel.addAction(m2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\""
            + " width=\"300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            + "visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            + "visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\""
            + " dur=\"5000.0ms\" attributeName=\"x\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n\t<animate attrib"
            + "uteType=\"xml\" begin=\"10000.0ms\" dur=\"2000.0ms\" attributeName=\"x\" from=\"20"
            + "0.0\" to=\"100.0\" fill=\"freeze\" />\n\t<animate attributeType=\"xml\" begin=\"10"
            + "000.0ms\" dur=\"2000.0ms\" attributeName=\"y\" from=\"200.0\" to=\"200.0\" fill=\"f"
            + "reeze\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse with a move action.
   */
  @Test
  public void testAddShapesOMove() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(m3);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"o1\" cx=\"100.0\" cy=\"100.0\""
            + " rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000."
            + "0ms\" dur=\"5000.0ms\" attributeName=\"cx\" from=\"100.0\" to=\"200.0\" fill=\"free"
            + "ze\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" attri"
            + "buteName=\"cy\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n</ellipse>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a rect with a color change action.
   */
  @Test
  public void testAddShapesRCol() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(c1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\" width=\""
            + "300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000"
            + ".0ms\" dur=\"5000.0ms\" attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,"
            + "0)\" fill=\"freeze\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse color.
   */
  @Test
  public void testAddShapesOCol() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(c2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"o1\" cx=\"100.0\" cy=\"100.0\""
            + " rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000."
            + "0ms\" dur=\"5000.0ms\" attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0"
            + ")\" fill=\"freeze\" />\n</ellipse>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a rect with a dimension change action.
   */
  @Test
  public void testAddShapesRDimChange() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(rs1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"r2\" x=\"0.0\" y=\"0.0\" width=\""
            + "300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000."
            + "0ms\" dur=\"5000.0ms\" attributeName=\"width\" from=\"300.0\" to=\"200.0\" fill=\"f"
            + "reeze\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"height\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n</rect>\n"
            + "</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse dimension change.
   */
  @Test
  public void testAddShapesODimChange() {
    SimpleAnimationOrig buildModel = new SimpleAnimationOrig();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(rs2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"o1\" cx=\"100.0\" cy=\"100.0\""
            + " rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"500"
            + "0.0ms\" dur=\"5000.0ms\" attributeName=\"rx\" from=\"10.0\" to=\"60.0\" fill=\"free"
            + "ze\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" attri"
            + "buteName=\"ry\" from=\"10.0\" to=\"60.0\" fill=\"freeze\" />\n</ellipse>\n</svg>");

  }
}