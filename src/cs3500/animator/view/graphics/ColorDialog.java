package cs3500.animator.view.graphics;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.control.listeners.ISelectionListener;
import cs3500.animator.model.IReadOnlyAnimationModel;

public class ColorDialog extends JDialog {
  IReadOnlyAnimationModel model;
  private JList jList;
  private JTextArea rInput;
  private JTextArea gInput;
  private JTextArea bInput;
  private JButton subColor;
  private JButton exportButton;

  /**
   * Creates a new {@code ListDialog} object.
   *
   * @param frame    is the frame this dialog is dependent on.
   * @param model    is the model whose data is used by this dialog.
   * @param listener is the listener used by this dialog on selection.
   */
  public ColorDialog(JFrame frame, IReadOnlyAnimationModel model, ISelectionListener listener) {
    super(frame);
    this.model = model;

    this.setLayout(new BorderLayout());

    JPanel buttonPanel;
    JButton closeButton;

    String[] colList = {"Black", "Red","Blue", "Green", "Yellow", "White","Orange"};
    jList = new JList(colList);
    jList.setVisibleRowCount(4);
    jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listener.setJList(jList);
    jList.addListSelectionListener(listener);

    this.add(new JScrollPane(jList));
    this.setMinimumSize(new Dimension(300, 300));
    this.setLocationRelativeTo(frame);
    this.setTitle("Select Color for BackGround");

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    closeButton = new JButton("Close");
    closeButton.addActionListener((ActionEvent e) -> {
      this.dispose();
    });
    buttonPanel.add(closeButton);


    rInput = new JTextArea("rComponent");
    gInput = new JTextArea("gComponent");
    bInput = new JTextArea("bComponent");

    subColor = new JButton("Set background");
    buttonPanel.add(subColor);

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
    subColor.addActionListener((ActionEvent e) -> {
      if(isValidNum(rInput.getText()) && isValidNum(gInput.getText()) &&
              isValidNum(bInput.getText())) {
        controller.setBackGroundColor(new Color(Integer.parseInt(rInput.getText()),
                Integer.parseInt(gInput.getText()), Integer.parseInt(bInput.getText())));
      } else {
        controller.applyBGColor();
      }
      controller.getView().update(controller.getCurrTick());
    });
    exportButton.addActionListener((ActionEvent e) -> {
      controller.getView().export(controller.getLooping());
    });
  }


  /**
   * Tests if the provided string has an rgb equivalent.
   * @param check the string to check.
   * @return true if valid int
   */
  public boolean isValidNum(String check) {
    if(check.isEmpty()) return false;
    for(int i = 0; i < check.length(); i++) {
      if(i == 0 && check.charAt(i) == '-') {
        return false;
      } else if(Character.digit(check.charAt(i),10) < 0) {
        return false;
      }
    }
    if(Integer.parseInt(check) > 255) return false;
    return true;
  }
}
