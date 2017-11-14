package cs3500.animator.control;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller for an animation using an InteractiveView and an IAnimationModel
 */
public class InteractiveAnimationController extends AnimationController {

  public InteractiveAnimationController(
          IAnimationModel model, InteractiveView view, double ticksPerSecond) {
    super(model, view, ticksPerSecond);
  }
}
