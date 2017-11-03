import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;


public class TestSVGView {
  Shape R1 = new ShapeBuilder().setColor(Color.red).setName("R1").setPosition(
          0,0).setSize(800,800).setTimeSpan(1,12).build(
                  ShapeBuilder.ShapeType.RECTANGLE);
  Shape R2 = new ShapeBuilder().setColor(Color.red).setName("R2").setPosition(
          0,0).setSize(300,300).setTimeSpan(1,12).build(
          ShapeBuilder.ShapeType.RECTANGLE);
  Shape O1 = new ShapeBuilder().setColor(Color.red).setName("O1").setPosition(
          100,100).setSize(10,10).setTimeSpan(1,12).build(
          ShapeBuilder.ShapeType.OVAL);
  AnimationAction M1 = new AnimationActionBuilder().setTargetShape(R2).setTargetPosition(
          200,200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction M2 = new AnimationActionBuilder().setTargetShape(R2).setTargetPosition(
          100,200).setTimeTicks(10, 12).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction M3 = new AnimationActionBuilder().setTargetShape(O1).setTargetPosition(
          200,200).setTimeTicks(5, 10).build(AnimationActionBuilder.AnimationActionType.MOVE);
  AnimationAction RS1 = new AnimationActionBuilder().setTargetShape(R2).setTargetSize(
          200,200).setTimeTicks(5, 10).build(
                  AnimationActionBuilder.AnimationActionType.SCALE);
  AnimationAction RS2 = new AnimationActionBuilder().setTargetShape(O1).setTargetSize(
          60,60).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.SCALE);
  AnimationAction C1 = new AnimationActionBuilder().setTargetShape(R2).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);
  AnimationAction C2 = new AnimationActionBuilder().setTargetShape(O1).setTargetColor(
          Color.black).setTimeTicks(5, 10).build(
          AnimationActionBuilder.AnimationActionType.COLORSHIFT);

  /**
   * Tests that our svg view correctly outputs a rect when added.
   */
  @Test
<<<<<<< HEAD
  public void testAddShapesR() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"R2\" x=\"0.0\" y=\"0.0\""
            +" width=\"300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            +"<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            +"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            +"visibility\" to=\"hidden\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse when added.
   */
  @Test
  public void testAddShapesO() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(O1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"O1\" cx=\"100.0\" cy=\"100.0\""
            +" rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            +"<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            +"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            +"visibility\" to=\"hidden\" />\n</ellipse>\n</svg>");
  }

  /**
   * Tests that our svg view correctly rescales the view when a large shape added a rect when added.
   */
  @Test
  public void testAddShapesRWindowScale() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"800\" height=\"800\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"R1\" x=\"0.0\" y=\"0.0\""
            +" width=\"800.0\" height=\"800.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            +"<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            +"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            +"visibility\" to=\"hidden\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a rect with actions that change the starting pos.
   */
  @Test
  public void testAddShapesRMoves2() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R2);
    buildModel.addAction(M1);
    buildModel.addAction(M2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"R2\" x=\"0.0\" y=\"0.0\""
            +" width=\"300.0\" height=\"300.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n\t"
            +"<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" to=\""
            +"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeName=\""
            +"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\""
            +" dur=\"5000.0ms\" attributeName=\"x\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n"
            +"\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" "
            +"attributeName=\"y\" from=\"0.0\" to=\"200.0\" fill=\"freeze\" />\n\t<animate attrib"
            +"uteType=\"xml\" begin=\"10000.0ms\" dur=\"2000.0ms\" attributeName=\"x\" from=\"20"
            +"0.0\" to=\"100.0\" fill=\"freeze\" />\n\t<animate attributeType=\"xml\" begin=\"10"
            +"000.0ms\" dur=\"2000.0ms\" attributeName=\"y\" from=\"200.0\" to=\"200.0\" fill=\"f"
            +"reeze\" />\n</rect>\n</svg>");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse with a move action.
   */
  @Test
  public void testAddShapesOMove() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(O1);
    buildModel.addAction(M3);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"O1\" cx=\"100.0\" cy=\"100.0\""
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
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R2);
    buildModel.addAction(C1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"R2\" x=\"0.0\" y=\"0.0\" width=\""
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
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(O1);
    buildModel.addAction(C2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"O1\" cx=\"100.0\" cy=\"100.0\""
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
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R2);
    buildModel.addAction(RS1);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<rect id=\"R2\" x=\"0.0\" y=\"0.0\" width=\""
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
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(O1);
    buildModel.addAction(RS2);
    IView testView = new SVGView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.update();
    assertEquals(testBuilder.toString(),"<svg width=\"700\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n<ellipse id=\"O1\" cx=\"100.0\" cy=\"100.0\""
            + " rx=\"10.0\" ry=\"10.0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" attributeName=\"visibility\" "
            + "to=\"visible\" />\n\t<animate attributeType=\"xml\" begin=\"12000.0ms\" attributeN"
            + "ame=\"visibility\" to=\"hidden\" />\n\t<animate attributeType=\"xml\" begin=\"500"
            + "0.0ms\" dur=\"5000.0ms\" attributeName=\"rx\" from=\"10.0\" to=\"60.0\" fill=\"free"
            + "ze\" />\n\t<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" attri"
            + "buteName=\"ry\" from=\"10.0\" to=\"60.0\" fill=\"freeze\" />\n</ellipse>\n</svg>");
=======
  public void testAddShapes() {
>>>>>>> master
  }
}
