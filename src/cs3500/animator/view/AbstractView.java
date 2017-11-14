package cs3500.animator.view;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.Shape;

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

  public void update() {
    // Empty method to be overridden by views requiring an update call to continue the animation.
  }

  @Override
  public void start() {
    // Empty method to be overridden by views requiring a start call to begin showing the animation.
  }

  @Override
  public void reset() {
    for (Shape s : this.getModel().getShapes()) {
      s.reset();
    }
  }
}
