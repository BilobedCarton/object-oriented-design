package cs3500.animator.control;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller for an animation using an InteractiveView and an IAnimationModel.
 */
public class InteractiveAnimationController extends AnimationController
        implements IInteractiveAnimationController {
  private boolean loopAnimation = false;
  private List<String> selectedShapes = new ArrayList<>();

  public InteractiveAnimationController(
          IAnimationModel model, InteractiveView view, double ticksPerSecond) {
    super(model, view, ticksPerSecond);
  }

  @Override
  public void goStart() {
    this.view.start();
    ((InteractiveView) this.view).setUpInteractivity(this);
  }

  @Override
  public void runUpdate() {
    super.runUpdate();

    if (this.loopAnimation) {
      int finalTick = 0;
      for (IAnimationPiece s : this.model.getShapes()) {
        if (s.getDisappearTick() > finalTick) {
          finalTick = s.getDisappearTick();
        }
      }

      if (finalTick <= currTick) {
        this.reset(false);
        this.startAnimation();
      }
    }
  }

  @Override
  public void startAnimation() {
    this.timer.start();
  }

  @Override
  public void pauseAnimation() {
    this.timer.stop();
    this.resetTimer();
  }

  @Override
  public boolean getLooping() {
    return this.loopAnimation;
  }

  @Override
  public void toggleLooping() {
    this.loopAnimation = !this.loopAnimation;
  }

  @Override
  public void setSelectedShapes(String[] shapeStates) {
    this.selectedShapes.clear();
    for (String shapeState : shapeStates) {
      this.selectedShapes.add(shapeState);
    }
  }

  @Override
  public void markSelectedShapesVisibility(boolean visible) {
    for (String shapeState : selectedShapes) {
      for (IAnimationPiece s : this.getModel().getShapes()) {
        if (shapeState.contains(s.getName())) {
          s.setVisibility(visible);
        }
      }
    }
  }
}
