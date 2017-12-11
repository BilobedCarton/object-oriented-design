package cs3500.animator.control;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller for an animation using an InteractiveView and an IAnimationModel.
 */
public class InteractiveAnimationController extends AnimationController {
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
      int finalTick = this.getLastTick();

      if (finalTick <= currTick) {
        this.reset(false);
        this.startAnimation();
      }
    }
  }

  /**
   * Starts the timer and begins the animation updating.
   */
  public void startAnimation() {
    this.timer.start();
  }

  /**
   * Stops and resets the timer, pausing the animation.
   */
  public void pauseAnimation() {
    this.timer.stop();
    this.resetTimer();
  }

  /**
   * A getter for the looping property.
   *
   * @return the looping boolean.
   */
  public boolean getLooping() {
    return this.loopAnimation;
  }

  /**
   * Toggles looping behavior on this controller.
   */
  public void toggleLooping() {
    this.loopAnimation = !this.loopAnimation;
  }

  /**
   * Set the shapes currently selected for visibility changes.
   *
   * @param shapeStates is the array of selected shapes with their current visibility status.
   */
  public void setSelectedShapes(String[] shapeStates) {
    this.selectedShapes.clear();
    for (String shapeState : shapeStates) {
      this.selectedShapes.add(shapeState);
    }
  }

  /**
   * Marks the selected shapes with the given visibility.
   *
   * @param visible is the target visibility of the selected shapes.
   */
  public void markSelectedShapesVisibility(boolean visible) {
    for (String shapeState : selectedShapes) {
      for (Shape s : this.getModel().getShapes()) {
        if (shapeState.contains(s.getName())) {
          s.setVisibility(visible);
        }
      }
    }
  }

  /**
   * Sets this model's state to the given tick.
   * @param tick is the tick that this model's state is to be based upon.
   * @throws IllegalArgumentException if the given tick is negative.
   */
  public void setModelToStateAt(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Given tick is less than zero.");
    }
    if (this.currTick > tick) {
      this.reset(false);
    }

    while (this.currTick < tick) {
      this.runUpdate();
    }
  }
}
