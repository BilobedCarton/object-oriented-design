package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimation;

/**
 * Represents an abstract implementation of a view for an animation program.
 */
public abstract class AbstractView implements IView {
  private ReadOnlyAnimation model;
  protected int frameSizeX;
  protected int frameSizeY;
  protected double speed;

  /**
   * Creates a new {@code AbstractView} object.
   * @param model is the model related to this view.
   */
  AbstractView(ReadOnlyAnimation model, double speed) {
    this.frameSizeX = 700;
    this.frameSizeY= 500;
    this.model = model;
    this.speed = speed;
  }

  @Override
  public ReadOnlyAnimation getModel() {
    return model;
  }

  public abstract void update();
}
