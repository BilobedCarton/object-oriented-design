package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimation;

public class VisualView extends AbstractView {
  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   */
  VisualView(ReadOnlyAnimation model) {
  super(model);
}

  @Override
  public void update() {
    // TODO draw something. based on the model.
  }
}
