package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimation;

/**
 * Represents an abstract implementation of a view for an animation program.
 */
public abstract class AbstractView implements IView {
  private ReadOnlyAnimation model;

  /**
   * Creates a new {@code AbstractView} object.
   * @param model is the model related to this view.
   */
  AbstractView(ReadOnlyAnimation model) {
    this.model = model;
  }

  @Override
  public ReadOnlyAnimation getModel() {
    return model;
  }

  public abstract void update();
}
