package cs3500.animator.control;

import javax.swing.Timer;

import cs3500.animator.control.listeners.UpdateListener;
import cs3500.animator.model.IAnimationModelOrig;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.view.IView;

/**
 * Represents a controller for an animation using an IAnimationModelOrig and an IView.
 */
public class AnimationController implements IAnimationController {
  protected IAnimationModelOrig model;
  protected IView view;
  protected double ticksPerSecond;
  protected int currTick = 1;
  protected Timer timer;

  /**
   * Creates a new {@code AnimationController} object.
   *
   * @param model          is the associated IAnimationModelOrig for this controller.
   * @param view           is the associated IView for this controller.
   * @param ticksPerSecond is the number of ticks executed per second by this controller.
   */
  public AnimationController(IAnimationModelOrig model, IView view, double ticksPerSecond) {
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
  public void goStart() {
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
    this.view.update((int)ticksPerSecond);
    this.ticksPerSecond = ticksPerSecond;
    resetTimer();
    if (continueAnimation) {
      this.timer.start();
    }
  }

  @Override
  public void reset(boolean originalVisibility) {
    this.timer.stop();
    for (IAnimationPiece s : this.getModel().getShapes()) {
      s.reset(originalVisibility);
    }
    resetTimer();
    this.currTick = 1;
    view.update(currTick);
  }

  @Override
  public void resetTimer() {
    this.timer = new Timer((int) (1000 / this.ticksPerSecond), new UpdateListener(this));
  }

  @Override
  public Timer getTimer() {
    return this.timer;
  }
}
