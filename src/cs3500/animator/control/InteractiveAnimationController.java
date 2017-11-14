package cs3500.animator.control;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shapes.Shape;
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

  @Override
  public void runUpdate() {
    super.runUpdate();

    if (this.loopAnimation = true) {
      int finalTick = 0;
      for (Shape s : this.model.getShapes()) {
        if (s.getDisappearTick() > finalTick) {
          finalTick = s.getDisappearTick();
        }
      }

      if (finalTick < currTick) {
        this.reset();
        this.startAnimation();
      }
    }
  }

  public void startAnimation() {
    this.timer.start();
  }

  public void pauseAnimation() {
    this.timer.stop();
    this.resetTimer();
  }

  public void toggleLooping() {
    this.loopAnimation = !this.loopAnimation;
  }
}
