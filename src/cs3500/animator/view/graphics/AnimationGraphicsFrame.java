package cs3500.animator.view.graphics;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.shapes.Shape;

public class AnimationGraphicsFrame extends JFrame {
  private AnimationPanel animationPanel;

  public AnimationGraphicsFrame(int width, int height) {
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
