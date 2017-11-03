package cs3500.animator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a panel where a simple animation is drawn.
 * The region where the animation will be drawn.
 */
public class AnimationPanel extends JPanel {
  private List<Shape> shapesToDraw = new ArrayList<Shape>();
  private int currTick = 0;

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
    g2D.translate(0, this.getPreferredSize().getHeight());
    g2D.scale(1, -1);

    for (Shape s : shapesToDraw) {
      if (s instanceof Rectangle) {
        g2D.drawRect((int) s.getPosX(), (int) s.getPosY(), (int) s.getSizeX(), (int) s.getSizeY());
      }
      else if (s instanceof Oval) {
        g2D.drawOval((int) s.getPosX(), (int) s.getPosY(), (int) s.getSizeX(), (int) s.getSizeY());
      }
    }

    g2D.transform(originalTransform);
  }
}
