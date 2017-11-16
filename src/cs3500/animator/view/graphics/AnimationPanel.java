package cs3500.animator.view.graphics;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a panel where a simple animation is drawn.
 * The region where the animation will be drawn.
 */
public class AnimationPanel extends JPanel {
  private List<Shape> shapesToDraw = new ArrayList<Shape>();

  /**
   * Update this panel's shape data with the given shape data.
   * @param shapes is the list of shapes we should draw.
   */
  public void updateShapes(List<Shape> shapes) {
    this.shapesToDraw = shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g;
    g2D.setBackground(Color.WHITE);

    AffineTransform originalTransform = g2D.getTransform();
    g2D.scale(1, 1);

    for (Shape s : shapesToDraw) {
      g2D.setColor(s.getColor());
      if (s instanceof Rectangle) {
        g2D.fill(new Rectangle2D.Double(s.getPosX(), s.getPosY(), s.getSizeX(), s.getSizeY()));
      }
      else if (s instanceof Oval) {
        g2D.fill(new Ellipse2D.Double(s.getPosX(), s.getPosY(), s.getSizeX(), s.getSizeY()));
      }
    }

    g2D.transform(originalTransform);
  }
}
