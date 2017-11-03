package cs3500.animator.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.graphics.AnimationGraphicsFrame;

/**
 * Represents a visual view of an animation. This actually renders a moving image.
 */
public class VisualView extends AbstractView {
  AnimationGraphicsFrame frame;
  Timer timer;
  int currTick;

  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   */
  public VisualView(
          ReadOnlySimpleAnimation model,
          double speed,
          int windowWidth,
          int windowHeight) {
  super(model, speed);
  this.frame = new AnimationGraphicsFrame(windowWidth, windowHeight);
  this.timer = new Timer((int) (1000 / speed), new updateListener(this));
  this.currTick = 0;
}

  @Override
  public void update() {
    ArrayList<Shape> shapesToDraw = new ArrayList<Shape>();
    for (Shape s : this.getModel().getShapes()) {
      shapesToDraw.add(this.getModel().getShapeStateAt(this.currTick, s));
    }
    this.currTick++;
    frame.refresh();
  }

  public void start() {
    this.timer.start();
    frame.makeVisible();
  }

  public void reset() {
    this.timer.start();
    this.currTick = 0;
  }

  class updateListener implements ActionListener {
    VisualView view;

    updateListener(VisualView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      view.update();
    }
  }
}
