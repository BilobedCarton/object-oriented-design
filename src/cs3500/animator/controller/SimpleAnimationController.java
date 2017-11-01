package cs3500.animator.controller;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.IView;

/**
 * Represents a simple controller for an animation.
 */
public class SimpleAnimationController implements IController {
  private IAnimationModel model;
  private IView view;
  private double ticksPerSecond;

  /**
   * Creates a new {@code SimpleAnimationController} object.
   * @param model is the model used by this controller.
   * @param view is the view used by this controller.
   * @param ticksPerSecond is the number of ticks executed per second by this controller.
   * @throws IllegalArgumentException if the this controller, the model,
   *                                  and the view's ticksPerSecond differ.
   */
  SimpleAnimationController(IAnimationModel model, IView view, double ticksPerSecond)
          throws IllegalArgumentException {
    if (model.getTicksPerSecond() != ticksPerSecond) {
      throw new IllegalArgumentException("SimpleAnimationController(IAnimationModel, IView, double)"
              + " -- ticksPerSecond do not match. Make sure the model and controller use the same "
              + "value for ticksPerSecond.");
    }
    this.model = model;
    this.view = view;
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public void run() {
    // TODO actually work in time logic.
    double currTime = 0;
    while (model.animationIncomplete(currTime)) {
      this.update();
      currTime++;
    }
  }

  @Override
  public void update() {
    // TODO update model and view based on the time since last update (using ticksPerSecond).
    // TODO should look something like this:
    // this.model.runCycle(someTime);
    // this.view.update();
  }

  @Override
  public IView getView() {
    return view;
  }

  @Override
  public IAnimationModel getModel() {
    return model;
  }
}
