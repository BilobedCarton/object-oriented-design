package cs3500.animator.control;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller for an animation using an InteractiveView and an IAnimationModel
 */
public class InteractiveAnimationController extends AnimationController {
  private boolean loopAnimation = false;

  public InteractiveAnimationController(
          IAnimationModel model, InteractiveView view, double ticksPerSecond) {
    super(model, view, ticksPerSecond);
  }

  @Override
  public void go() {
    this.view.start();
    ((InteractiveView) this.view).setButtonActions(this);
  }

  public void startAnimation() {
    this.timer.start();
  }

  public void pauseAnimation() {
    this.timer.stop();
    this.resetTimer();
  }

  public void changeLoopAnimation() {
    this.loopAnimation = !this.loopAnimation;
  }
}
