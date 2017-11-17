package cs3500.animator.control;

import javax.swing.Timer;

import cs3500.animator.control.listeners.UpdateListener;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.IView;

/**
 * Represents a controller for an animation using an IAnimationModel and an IView.
 */
public class AnimationController implements IAnimationController {
  protected IAnimationModel model;
  protected IView view;
  protected double ticksPerSecond;
  protected int currTick = 1;
  protected Timer timer;

  /**
   * Creates a new {@code AnimationController} object.
   *
   * @param model          is the associated IAnimationModel for this controller.
   * @param view           is the associated IView for this controller.
   * @param ticksPerSecond is the number of ticks executed per second by this controller.
   */
  public AnimationController(IAnimationModel model, IView view, double ticksPerSecond) {
    this.model = model;
    this.view = view;
    this.ticksPerSecond = ticksPerSecond;
    resetTimer();
    view.update(currTick);
  }

  @Override
  public IReadOnlyAnimationModel getModel() {
    return new ReadOnlySimpleAnimation(model);
  }

  @Override
  public IView getView() {
    return view;
  }

  @Override
  public double getSpeed() {
    return ticksPerSecond;
  }

  @Override
  public int getCurrTick() {
    return currTick;
  }

  @Override
  public void go() {
    view.start();
    timer.start();
  }

  @Override
  public void runUpdate() {
    model.runCycle(currTick);
    view.update(currTick);
    currTick++;
  }

  @Override
  public void changeSpeed(double ticksPerSecond) {
    boolean continueAnimation = this.timer.isRunning();
    this.timer.stop();
    this.ticksPerSecond = ticksPerSecond;
    resetTimer();
    if (continueAnimation) {
      this.timer.start();
    }
  }

  @Override
  public void reset(boolean originalVisibility) {
    this.timer.stop();
    for (Shape s : this.getModel().getShapes()) {
      s.reset(originalVisibility);
    }
    resetTimer();
    this.currTick = 1;
    view.update(currTick);
  }

  /**
   * Creates a new timer for use by the controller.
   *
   * @return the new Timer.
   */
  public void resetTimer() {
    this.timer = new Timer((int) (1000 / this.ticksPerSecond), new UpdateListener(this));
  }

  @Override
  public Timer getTimer(){
    return this.timer;
  }
}
