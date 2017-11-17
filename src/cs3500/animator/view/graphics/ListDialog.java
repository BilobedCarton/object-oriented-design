package cs3500.animator.view.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.control.listeners.ISelectionListener;
import cs3500.animator.model.IReadOnlyAnimationModel;

/**
 * Represents a dialog with a multiple selection list.
 */
public class ListDialog extends JDialog {
  IReadOnlyAnimationModel model;
  private JList jList;
  private JButton visibleButton;
  private JButton invisibleButton;
  private JButton exportButton;

  /**
   * Creates a new {@code ListDialog} object.
   *
   * @param frame    is the frame this dialog is dependent on.
   * @param model    is the model whose data is used by this dialog.
   * @param listener is the listener used by this dialog on selection.
   */
  public ListDialog(JFrame frame, IReadOnlyAnimationModel model, ISelectionListener listener) {
    super(frame);
    this.model = model;

    this.setLayout(new BorderLayout());

    JPanel buttonPanel;
    JButton closeButton;

    jList = new JList(this.getShapeStates());
    jList.setVisibleRowCount(5);
    jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    listener.setJList(jList);
    jList.addListSelectionListener(listener);

    this.add(new JScrollPane(jList));
    this.setMinimumSize(new Dimension(300, 300));
    this.setLocationRelativeTo(frame);
    this.setTitle("Select Shapes to Ignore");

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    closeButton = new JButton("Close");
    closeButton.addActionListener((ActionEvent e) -> {
      this.dispose();
    });
    buttonPanel.add(closeButton);

    visibleButton = new JButton("Set as visible");
    buttonPanel.add(visibleButton);

    invisibleButton = new JButton("Set as invisible");
    buttonPanel.add(invisibleButton);

    exportButton = new JButton("Export as SVG");
    buttonPanel.add(exportButton);

    this.pack();
  }

  /**
   * Open the modal for view to the user.
   */
  public void doModal() {
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    setModal(true);
    setVisible(true);
  }

  /**
   * Link the controller's functionality to this dialog box's buttons.
   *
   * @param controller is the controller being linked to this dialog box.
   */
  public void setUpButtons(InteractiveAnimationController controller) {
    visibleButton.addActionListener((ActionEvent e) -> {
      controller.markSelectedShapesVisibility(true);
      jList.setListData(this.getShapeStates());
      controller.getView().update(controller.getCurrTick());
    });
    invisibleButton.addActionListener((ActionEvent e) -> {
      controller.markSelectedShapesVisibility(false);
      jList.setListData(this.getShapeStates());
      controller.getView().update(controller.getCurrTick());
    });
    exportButton.addActionListener((ActionEvent e) -> {
      controller.getView().export(controller.getLooping());
    });
  }

  /**
   * Get the string version of the states of the shape.
   *
   * @return the String array of shape states.
   */
  private String[] getShapeStates() {
    String[] shapeStates = new String[model.getShapes().size()];
    for (int i = 0; i < shapeStates.length; i++) {
      shapeStates[i] = model.getShapes().get(i).getName()
              + (model.getShapes().get(i).isVisible() ? ": Visible" : ": Invisible");
    }
    return shapeStates;
  }
}
