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
      if (s.getAppearTick() <= currTick && s.getDisappearTick() > currTick && s.isVisible()) {
        shapesToDraw.add(s);
      }
    }
    frame.updateShapeData(shapesToDraw);
    frame.refresh();
    this.currTick++;
  }

  @Override
  public void start() {
    super.start();
    this.timer.start();
    frame.makeVisible();
  }

  @Override
  public void reset() {
    super.reset();
    this.timer = new Timer((int) (1000 / speed), new UpdateListener(this));
    this.currTick = 0;
  }
}
