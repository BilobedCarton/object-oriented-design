package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;

public class VisualView extends AbstractView {
  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   * @param ticksPerSecond is the number of ticks executed per second.
   */
  VisualView(IAnimationModel model, double ticksPerSecond) {
  super(model, ticksPerSecond);
}

  @Override
  public void update() {
    // TODO draw something. based on the model.
  }
}
