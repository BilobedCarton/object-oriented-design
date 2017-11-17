package cs3500.animator.view;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents an abstract implementation of a view for an animation program.
 */
public abstract class AbstractView implements IView {
  private ReadOnlySimpleAnimation model;

  /**
   * Creates a new {@code AbstractView} object.
   *
   * @param model is the model related to this view.
   */
  AbstractView(ReadOnlySimpleAnimation model) {
    this.model = model;
  }

  @Override
  public ReadOnlySimpleAnimation getModel() {
    return model;
  }

  @Override
  public void update(int currTick) {
    // Empty method to be overridden by views requiring an update call to continue the animation.
  }

  @Override
  public void start() {
    // Empty method to be overridden by views requiring a start call to begin showing the animation.
  }

  @Override
  public boolean isInteractive() {
    return false;
  }

  @Override
  public void setUpInteractivity(InteractiveAnimationController controller) {
    throw new NotImplementedException();
  }

  @Override
  public void export(boolean loop) {
    throw new NotImplementedException();
  }
}
