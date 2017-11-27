import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;


public class TestTextView {
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
   * Tests that our text view correctly outputs a rect when added.
   */
  @Test
  public void testAddShapesR() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: r2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse when added.
   */
  @Test
  public void testAddShapesO() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: o1\nType: oval\nLower-left corner:"
            + " (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n");
  }


  /**
   * Tests that our svg view correctly outputs a rect with actions that change the starting pos.
   */
  @Test
  public void testAddShapesRMoves2() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(m1);
    buildModel.addAction(m2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: r2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape r2 moves from (0.0,0.0) to (200.0,200.0) from t=5.0s to t=10.0s\n"
            + "Shape r2 moves from (200.0,200.0) to (100.0,200.0) from t=10.0s to t=12.0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse with a move action.
   */
  @Test
  public void testAddShapesOMove() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(m3);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: o1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape o1 moves from (100.0,100.0) to (200.0,200.0) from t=5.0s to t=10.0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a rect with a color change action.
   */
  @Test
  public void testAddShapesRCol() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(c1);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: r2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape r2 changes color from (1.0,0.0,0.0) to (1.0,0.0,0.0) from t=5.0s to t=10"
            + ".0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse color.
   */
  @Test
  public void testAddShapesOCol() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(c2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: o1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape o1 changes color from (1.0,0.0,0.0) to (1.0,0.0,0.0) from t=5.0s to t=10"
            + ".0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a rect with a dimension change action.
   */
  @Test
  public void testAddShapesRDimChange() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(r2);
    buildModel.addAction(rs1);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: r2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape r2 scales from Width: 300.0 Height: 300.0 to Width: 200.0 Height: 200.0 from"
            + " t=5.0s to t=10.0s\n");
  }

  /**
   * Tests that our svg view correctly outputs a ellipse dimension change.
   */
  @Test
  public void testAddShapesODimChange() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(o1);
    buildModel.addAction(rs2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel), testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(), "Shapes:\nName: o1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape o1 scales from Width: 10.0 Height: 10.0 to Width: 60.0 Height: 60.0 from "
            + "t=5.0s to t=10.0s\n");
  }
}
