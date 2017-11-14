import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.actions.AnimationActionBuilder;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;


public class TestTextView {
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
   * Tests that our text view correctly outputs a rect when added.
   */
  @Test
  public void testAddShapesR() {
    SimpleAnimation buildModel = new SimpleAnimation();
    StringBuilder testBuilder = new StringBuilder();
    buildModel.addShape(R2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: R2\nType: rectangle\n"
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
    buildModel.addShape(O1);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: O1\nType: oval\nLower-left corner:"
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
    buildModel.addShape(R2);
    buildModel.addAction(M1);
    buildModel.addAction(M2);
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: R2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape R2 moves from (0.0,0.0) to (200.0,200.0) from t=5.0s to t=10.0s\n"
            + "Shape R2 moves from (200.0,200.0) to (100.0,200.0) from t=10.0s to t=12.0s\n");
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
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: O1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape O1 moves from (100.0,100.0) to (200.0,200.0) from t=5.0s to t=10.0s\n");
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
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: R2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape R2 changes color from (1.0,0.0,0.0) to (1.0,0.0,0.0) from t=5.0s to t=10"
            + ".0s\n");
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
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: O1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape O1 changes color from (1.0,0.0,0.0) to (1.0,0.0,0.0) from t=5.0s to t=10"
            + ".0s\n");
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
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: R2\nType: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 300.0 Height: 300.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape R2 scales from Width: 300.0 Height: 300.0 to Width: 200.0 Height: 200.0 from"
            + " t=5.0s to t=10.0s\n");
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
    IView testView = new TextView(new ReadOnlySimpleAnimation(buildModel),testBuilder, 1);
    testView.start();
    assertEquals(testBuilder.toString(),"Shapes:\nName: O1\nType: oval\n"
            + "Lower-left corner: (100.0,100.0), Width: 10.0 Height: 10.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\nDisappears at t=12.0s\n\n"
            + "Shape O1 scales from Width: 10.0 Height: 10.0 to Width: 60.0 Height: 60.0 from "
            + "t=5.0s to t=10.0s\n");
  }
}
