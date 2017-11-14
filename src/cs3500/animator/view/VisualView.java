package cs3500.animator.view;


import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.control.listeners.UpdateListener;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.graphics.AnimationGraphicsFrame;

/**
 * Represents a visual view of an animation. This actually renders a moving image.
 */
public class VisualView extends AbstractView {
  protected AnimationGraphicsFrame frame;

  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   */
  public VisualView(
          ReadOnlySimpleAnimation model,
          int windowWidth,
          int windowHeight) {
  super(model);
  this.frame = new AnimationGraphicsFrame(windowWidth, windowHeight);
}

  @Override
  public void update(int currTick) {
    frame.updateShapeData(this.getModel().getVisibleShapes(currTick));
    frame.refresh();
  }

  @Override
  public void start() {
    super.start();
    frame.makeVisible();
  }
}
