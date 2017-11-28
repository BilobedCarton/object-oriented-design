package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.provider.controller.Command;
import cs3500.animator.provider.model.IShape;

/**
 * JPanel extension used by VisualView to display the GUI to the user.
 */
class InteractiveViewJPanel extends JPanel {
  private final int MAX_TPS = Integer.MAX_VALUE;
  private List<IShape> shapes;
  private List<String> names;
  private JButton togglePlayback;
  private JButton toggleLoopback;
  private JButton restart;
  private JButton selectShapes;
  private JButton printSvg;
  private JSpinner speed;
  private JList shapeList;
  private JScrollPane listScroll;

  /**
   * Constructs the panel.
   */
  InteractiveViewJPanel(int initialTicksPerSec, List<String> shapeNames) {
    this.shapes = new ArrayList<IShape>();
    this.names = shapeNames;
    this.setupComponents(initialTicksPerSec);
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

  /**
   * Produce the currently selected shapes.
   * @return      a list of the names of the currently selected shapes.
   */
  List<String> getSelectedShapes() {
    return this.shapeList.getSelectedValuesList();
  }

  /**
   * Set up the components of this JPanel.
   * @param initialTicksPerSec      the ticksPerSecond to set the components up with.
   */
  private void setupComponents(int initialTicksPerSec) {
    this.togglePlayback = new JButton("play/pause");
    this.toggleLoopback = new JButton("toggle loopback");
    this.restart = new JButton("restart");
    this.selectShapes = new JButton("select shapes");
    this.printSvg = new JButton("make svg");

    this.shapeList = new JList(this.names.toArray());
    this.shapeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    this.shapeList.setLayoutOrientation(JList.VERTICAL_WRAP);
    this.listScroll = new JScrollPane(this.shapeList);
    this.listScroll.setPreferredSize(new Dimension(500,500));

    SpinnerModel spinnerModel = new SpinnerNumberModel(initialTicksPerSec, 1, this.MAX_TPS, 1);
    this.speed = new JSpinner(spinnerModel);

    this.togglePlayback.setActionCommand(Command.TOGGLE_PLAYBACK.getCode());
    this.toggleLoopback.setActionCommand(Command.TOGGLE_LOOPBACK.getCode());
    this.restart.setActionCommand(Command.RESTART.getCode());
    this.selectShapes.setActionCommand(Command.SELECT_SHAPES.getCode());
    this.printSvg.setActionCommand(Command.GET_SVG.getCode());
  }

  /**
   * Add the provided listeners to the appropriate components.
   * @param ar      provided ActionListener
   * @param cr      provided ChangeListener
   * @param lr      provided ListSelectionListener
   */
  public void addListeners(ActionListener ar, ChangeListener cr, ListSelectionListener lr) {
    this.togglePlayback.addActionListener(ar);
    this.toggleLoopback.addActionListener(ar);
    this.restart.addActionListener(ar);
    this.selectShapes.addActionListener(ar);
    this.printSvg.addActionListener(ar);
    this.speed.addChangeListener(cr);
    this.shapeList.addListSelectionListener(lr);

    this.addComponentsToPanel();
  }

  /**
   * Add the components to the JPanel (this).
   */
  private void addComponentsToPanel() {
    this.add(this.toggleLoopback);
    this.add(this.togglePlayback);
    this.add(this.selectShapes);
    this.add(this.restart);
    this.add(this.printSvg);
    this.add(this.speed);
  }

  /**
   * Show the interface that allows the user to manually select shapes.
   */
  public void showShapeSelection() {
    JFrame popup = new JFrame();
    popup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    popup.setSize(new Dimension(500,500));
    popup.setLayout(new BorderLayout());

    popup.add(this.listScroll, BorderLayout.CENTER);
    popup.setLocation(50, 50);
    popup.pack();
    popup.setVisible(true);
  }
}
