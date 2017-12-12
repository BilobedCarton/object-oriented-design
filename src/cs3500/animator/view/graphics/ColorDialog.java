package cs3500.animator.view.graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;

import javax.swing.event.ChangeEvent;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;

public class ColorDialog extends JDialog {
  IReadOnlyAnimationModel model;
  private JButton subColor;
  private JButton exportButton;
  private JSpinner rSpinner;
  private JSpinner gSpinner;
  private JSpinner bSpinner;


  /**
   * Creates a new {@code ListDialog} object.
   *
   * @param frame     is the frame this dialog is dependent on.
   * @param model     is the model whose data is used by this dialog.
   */
  public ColorDialog(JFrame frame, IReadOnlyAnimationModel model) {
    super(frame);
    this.model = model;

    this.setLayout(new BorderLayout());

    JPanel buttonPanel;
    JButton closeButton;
    JPanel controlPanel;
    String[] labels = {"R Component", "G Component", "B Component"};
    double orig = 1.0;
    double bot = 0.0;
    double step = 0.1;
    SpinnerNumberModel spinnerModelr = new SpinnerNumberModel(orig, bot, orig, step);
    SpinnerNumberModel spinnerModelg = new SpinnerNumberModel(orig, bot, orig, step);
    SpinnerNumberModel spinnerModelb = new SpinnerNumberModel(orig, bot, orig, step);

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    JLabel rLabel = new JLabel("R Component");
    controlPanel.add(rLabel);
    rSpinner = new JSpinner(spinnerModelr);
    Component mySpinnerEditor = rSpinner.getEditor();
    JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
    jftf.setColumns(2);
    rLabel.setLabelFor(rSpinner);
    controlPanel.add(rSpinner);

    JLabel gLabel = new JLabel("G Component");
    controlPanel.add(gLabel);
    gSpinner = new JSpinner(spinnerModelg);
    mySpinnerEditor = gSpinner.getEditor();
    jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
    jftf.setColumns(2);
    gLabel.setLabelFor(gSpinner);
    controlPanel.add(gSpinner);

    JLabel bLabel = new JLabel("B Component");
    controlPanel.add(bLabel);
    bSpinner = new JSpinner(spinnerModelb);
    mySpinnerEditor = bSpinner.getEditor();
    jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
    jftf.setColumns(2);
    bLabel.setLabelFor(bSpinner);
    controlPanel.add(bSpinner);

    this.add(controlPanel, BorderLayout.CENTER);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    closeButton = new JButton("Close");
    closeButton.addActionListener((ActionEvent e) -> {
      this.dispose();
    });
    buttonPanel.add(closeButton);

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
    System.out.println("her");
    setVisible(true);
  }

  /**
   * Link the controller's functionality to this dialog box's buttons.
   *
   * @param controller is the controller being linked to this dialog box.
   */
  public void setUpButtons(InteractiveAnimationController controller) {
    subColor.addActionListener((ActionEvent e) -> {
      controller.applyBGColor();

      controller.getView().update(controller.getCurrTick());
    });
    exportButton.addActionListener((ActionEvent e) -> {
      controller.getView().export(controller.getLooping());
    });
    rSpinner.addChangeListener((ChangeEvent e) -> {
      JSpinner source = (JSpinner) e.getSource();
      double currentVal = (double) source.getValue();
      controller.setSelectedColor(currentVal, InteractiveAnimationController.rgbType.R);
    });
    gSpinner.addChangeListener((ChangeEvent e) -> {
      JSpinner source = (JSpinner) e.getSource();
      double currentVal = (double) source.getValue();
      controller.setSelectedColor(currentVal, InteractiveAnimationController.rgbType.G);
    });
    bSpinner.addChangeListener((ChangeEvent e) -> {
      JSpinner source = (JSpinner) e.getSource();
      double currentVal = (double) source.getValue();
      controller.setSelectedColor(currentVal, InteractiveAnimationController.rgbType.B);
    });
  }


}
