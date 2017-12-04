package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shape.IShape;

/**
 * JPanel extension used by VisualView to display the GUI to the user.
 */
class VisualViewJPanel extends JPanel {
  private List<IShape> shapes;

  /**
   * Constructs the panel.
   */
  VisualViewJPanel() {
    this.shapes = new ArrayList<IShape>();
  }

  /**
   * Draws all of the shapes held in shapes to the screen using 2D graphics.
   * @param graphics the graphics object
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g = (Graphics2D) graphics;

    for (IShape s : this.shapes) {
      //System.out.println(s.toString());
      g.setPaint(s.getColor());
      g.draw(s.getImage());
      g.fill(s.getImage());
    }
  }

  /**
   * Set the list of shapes that need to be drawn.
   * @param shapes the list of shapes to be drawn
   */
  void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }
}
