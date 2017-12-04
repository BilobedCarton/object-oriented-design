package cs3500.animator.view;

import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents an abstract implementation of a view for an animation program.
 */
public abstract class AbstractView implements IView {
  protected IReadOnlyAnimationModel model;

  /**
   * Creates a new {@code AbstractView} object.
   *
   * @param model is the model related to this view.
   */
  public AbstractView(IReadOnlyAnimationModel model) {
    this.model = model;
  }

  @Override
  public IReadOnlyAnimationModel getModel() {
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
  public void setUpInteractivity(IInteractiveAnimationController controller) {
    throw new NotImplementedException();
  }

  @Override
  public String export(boolean loop) {
    throw new NotImplementedException();
  }
}
