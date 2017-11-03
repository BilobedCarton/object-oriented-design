package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimation;

/**
 * Represents a visual view of an animation. This actually renders a moving image.
 */
public class VisualView extends AbstractView {
  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   */
  public VisualView(ReadOnlyAnimation model, double speed) {
  super(model, speed);
}

  @Override
  public void update() {
    // TODO draw something. based on the model.
  }
}
