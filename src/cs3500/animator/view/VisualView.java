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
  protected AnimationGraphicsFrame frame;
  protected Timer timer;
  protected int currTick;

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
  this.timer = new Timer((int) (1000 / speed), new UpdateListener(this));
  this.currTick = 0;
}

  @Override
  public void update() {
    ArrayList<Shape> shapesToDraw = new ArrayList<Shape>();
    this.getModel().runCycle(currTick);
    for (Shape s : this.getModel().getShapes()) {
      if (s.getAppearTick() <= currTick && s.getDisappearTick() > currTick) {
        shapesToDraw.add(s);
      }
    }
    frame.updateShapeData(shapesToDraw);
    frame.refresh();
    this.currTick++;
  }

  /**
   * Starts the timer and makes the frame visible.
   */
  public void start() {
    this.timer.start();
    frame.makeVisible();
  }

  /**
   * Resets the timer to the beginning and sets the current tick back to zero. Then runs start().
   */
  public void reset() {
    this.timer = new Timer((int) (1000 / speed), new UpdateListener(this));
    this.currTick = 0;
    for (Shape s : this.getModel().getShapes()) {
      s.reset();
    }
    this.start();
  }

  /**
   * Represents a listener that runs update on every action.
   */
  class UpdateListener implements ActionListener {
    VisualView view;

    /**
     * Creates a new {@code UpdateListener} object.
     * @param view is the view that will be updated when an action is performed.
     */
    UpdateListener(VisualView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      view.update();
    }
  }
}
