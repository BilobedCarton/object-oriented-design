package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.provider.model.IShape;

/**
 * JFrame extension that is used by VisualView to display the animation to the user.
 */
class VisualViewJFrame extends JFrame {
  private VisualViewJPanel panel;

  /**
   * Constructs a JFrame with given width and height.
   * @param width preferred width
   * @param height preferred height
   */
  VisualViewJFrame(int width, int height) {
    super("Animator");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.panel = new VisualViewJPanel();
    this.panel.setPreferredSize(new Dimension(width, height));
    JScrollPane scroll = new JScrollPane(this.panel);
    this.add(scroll, BorderLayout.CENTER);

    this.setLocation(50, 50);
    this.pack();
    this.setVisible(true);
  }

  /**
   * Gives the GUI an updated list of shapes to draw to the screen. Updates the list, and
   * schedules a repaint of the screen.
   * @param shapes list of shapes to draw
   */
  void update(List<IShape> shapes) {
    this.panel.setShapes(shapes);
    this.panel.repaint();
  }


}
