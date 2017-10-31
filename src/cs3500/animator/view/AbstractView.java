package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;

/**
 * Represents an abstract implementation of a view.
 */
public abstract class AbstractView implements IView {
  private IAnimationModel model;
  private double ticksPerSecond;

  /**
   * Creates a new {@code AbstractView} object.
   * @param model is the model related to this view.
   * @param ticksPerSecond is the ticks executed per second on the view.
   */
  AbstractView(IAnimationModel model, double ticksPerSecond) {if (ticksPerSecond <= 0) {
    throw new IllegalArgumentException("AbstractView(IAnimationModel, double) -- "
            + "ticksPerSecond is less than or equal to zero.");
    }
    this.model = model;
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public IAnimationModel getModel() {
    return model;
  }

  @Override
  public double getTicksPerSecond() {
    return ticksPerSecond;
  }

  @Override
  public void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
    throw new IllegalArgumentException("AbstractView.setTicksPerSecond(double) -- "
            + "ticksPerSecond is less than or equal to zero.");
    }
    this.ticksPerSecond = ticksPerSecond;
  }

  public abstract void update();
}
