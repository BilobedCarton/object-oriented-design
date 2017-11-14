package cs3500.animator.view;

import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents an abstract implementation of a view for an animation program.
 */
public abstract class AbstractView implements IView {
  private ReadOnlySimpleAnimation model;
  protected double speed;

  /**
   * Creates a new {@code AbstractView} object.
   * @param model is the model related to this view.
   */
  AbstractView(ReadOnlySimpleAnimation model, double speed) {
    this.model = model;
    this.speed = speed;
  }

  @Override
  public ReadOnlySimpleAnimation getModel() {
    return model;
  }

  public abstract void update();
}
