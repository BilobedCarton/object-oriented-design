package cs3500.animator.view.graphics;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import cs3500.animator.model.shapes.Shape;

/**
 * Represents a basic graphics frame for use by an Animation view.
 */
public class BasicAnimationGraphicsFrame extends JFrame {
  AnimationPanel animationPanel;

  public BasicAnimationGraphicsFrame(int width, int height) {
    super();
    this.setTitle("Animation");

    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setMinimumSize(new Dimension(300, 300));

    this.setLayout(new BorderLayout());

    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(width, height));

    this.add(animationPanel, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Makes the frame visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Update the animationPanel's shape data for the next update.
   *
   * @param shapes is the list of shapes whose data we are using.
   */
  public void updateShapeData(List<Shape> shapes) {
    this.animationPanel.updateShapes(shapes);
  }

  /**
   * Refresh the image using the current state of data held by the AnimationPanel.
   */
  public void refresh() {
    this.repaint();
  }
}
