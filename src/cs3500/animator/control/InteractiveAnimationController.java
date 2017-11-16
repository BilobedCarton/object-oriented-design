package cs3500.animator.control;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller for an animation using an InteractiveView and an IAnimationModel
 */
public class InteractiveAnimationController extends AnimationController {
  private boolean loopAnimation = false;
  private List<String> selectedShapes = new ArrayList<>();

  public InteractiveAnimationController(
          IAnimationModel model, InteractiveView view, double ticksPerSecond) {
    super(model, view, ticksPerSecond);
  }

  @Override
  public void go() {
    this.view.start();
    ((InteractiveView) this.view).setButtonActions(this);
    ((InteractiveView) this.view).linkSpeedSlider(this);
    ((InteractiveView) this.view).buildListDialog(this);
  }

  @Override
  public void runUpdate() {
    super.runUpdate();

    if (this.loopAnimation == true) {
      int finalTick = 0;
      for (Shape s : this.model.getShapes()) {
        if (s.getDisappearTick() > finalTick) {
          finalTick = s.getDisappearTick();
        }
      }

      if (finalTick <= currTick) {
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

  public void setSelectedShapes(String[] shapeStates) {
    this.selectedShapes.clear();
    for (String shapeState : shapeStates) {
      this.selectedShapes.add(shapeState);
    }
  }

  public void markSelectedShapesVisibility(boolean visible) {
    for (String shapeState : selectedShapes) {
      for (Shape s : this.getModel().getShapes()) {
        if (shapeState.contains(s.getName())) {
          s.setVisibility(visible);
        }
      }
    }
  }
}
