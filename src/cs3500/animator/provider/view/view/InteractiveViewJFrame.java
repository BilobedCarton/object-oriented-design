package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.shape.IShape;

/**
 * JFrame extension that is used by VisualView to display the animation to the user.
 */
class InteractiveViewJFrame extends JFrame {
  private InteractiveViewJPanel panel;

  /**
   * Constructs a JFrame with given width and height.
   * @param width preferred width
   * @param height preferred height
   */
  InteractiveViewJFrame(int width, int height, int initialTicksPerSec, List<String> shapeNames) {
    super("Animator");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    this.panel = new InteractiveViewJPanel(initialTicksPerSec, shapeNames);
    this.panel.setPreferredSize(new Dimension(width, height));
    JScrollPane scroll = new JScrollPane(this.panel);
    this.add(scroll, BorderLayout.CENTER);

    this.setLocation(50, 50);
    this.pack();
    //this.setVisible(true);
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

  /**
   * Pass the provided listeners to this JFrame's JPanel.
   * @param ar    provided ActionListener
   * @param cr    provided ChangeListener
   * @param lr    provided ListSelectionListener
   */
  void addListeners(ActionListener ar, ChangeListener cr, ListSelectionListener lr) {
    this.panel.addListeners(ar, cr, lr);
  }

  /**
   * Produce the currently selected shapes.
   * @return      the names of the currently selected shapes
   */
  List<String> getSelectedShapes() {
    return this.panel.getSelectedShapes();
  }

  /**
   * Show the shape selection utility.
   */
  void showShapeSelection() {
    this.panel.showShapeSelection();
  }
}
