package cs3500.animator.view;

import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents a visual view of an animation. This actually renders a moving image.
 */
public class VisualView extends AbstractView {
  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   */
  public VisualView(ReadOnlySimpleAnimation model, double speed) {
  super(model, speed);
}

  @Override
  public void update() {
    // TODO draw something. based on the model.
  }
}
