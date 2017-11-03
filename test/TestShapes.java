import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;

import static org.junit.Assert.assertEquals;

public class TestShapes {

  // Test the various actions a shape can directly undertake.
  @Test
  public void testResize() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    assertEquals(rect.getSizeX(), 0, 0);
    assertEquals(rect.getSizeY(), 0, 0);
    rect.resize(10, 10);
    assertEquals(rect.getSizeX(), 10, 0);
    assertEquals(rect.getSizeY(), 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeResize() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.OVAL);
    oval.resize(-4, 5);
  }

  @Test
  public void testRelocate() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.OVAL);
    assertEquals(oval.getPosX(), 0, 0);
    assertEquals(oval.getPosY(), 0, 0);
    oval.relocate(20, 20);
    assertEquals(oval.getPosX(), 20, 0);
    assertEquals(oval.getPosY(), 20, 0);
  }

  @Test
  public void testRecolor() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    assertEquals(rect.getColor(), new Color(0, 0, 0));
    rect.recolor(new Color(21, 147, 179));
    assertEquals(rect.getColor(), new Color(21, 147, 179));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRecolor() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    assertEquals(rect.getColor(), new Color(0, 0, 0));
    rect.recolor(null);
  }

  @Test
  public void testSetLifespan() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.OVAL);
    assertEquals(oval.getAppearTick(), 0);
    assertEquals(oval.getDisappearTick(), 0);
    oval.setLifeSpan(10, 20);
    assertEquals(oval.getAppearTick(), 10);
    assertEquals(oval.getDisappearTick(), 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSetLifespan() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    oval.setLifeSpan(-4, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLifespan() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.OVAL);
    oval.setLifeSpan(10, 5);
  }

  @Test
  public void testOvalToString() {
    Shape oval = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.OVAL);
    assertEquals(oval.toString(1), "Name: Test\n" + "Type: oval\n"
            + "Lower-left corner: (0.0,0.0), Width: 0.0 Height: 0.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0.0s\n" + "Disappears at t=0.0s\n");
  }

  @Test
  public void testRectToString() {
    Shape rect = ShapeBuilder.initialize()
            .setName("Test")
            .setColor(new Color(0, 0, 0))
            .build(ShapeBuilder.ShapeType.RECTANGLE);
    assertEquals(rect.toString(1), "Name: Test\n" + "Type: rectangle\n"
            + "Lower-left corner: (0.0,0.0), Width: 0.0 Height: 0.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0.0s\n" + "Disappears at t=0.0s\n");
  }
}
