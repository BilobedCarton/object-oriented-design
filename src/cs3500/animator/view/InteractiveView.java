package cs3500.animator.view;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.view.graphics.InteractiveAnimationGraphicsFrame;

/**
 * Represents an interactive visual view for an animation. It can also export the animation as an
 * SVG file.
 */
public class InteractiveView extends AbstractView {
  private Appendable out;
  private double speed;
  protected InteractiveAnimationGraphicsFrame frame;

  /**
   * Creates a new {@code InteractiveView} object.
   *
   * @param model        is the model linked to this view.
   * @param out          is the location where the data can be exported as SVG.
   * @param speed        is the speed in ticksPerSecond of this animation.
   * @param windowWidth  is the width of the window to be displayed.
   * @param windowHeight is the height of the window to be displayed.
   */
  public InteractiveView(
          ReadOnlySimpleAnimation model,
          Appendable out,
          double speed,
          int windowWidth,
          int windowHeight) {
    super(model);
    this.out = out;
    this.speed = speed;
    this.frame = new InteractiveAnimationGraphicsFrame(windowWidth, windowHeight);
  }


  /**
   * A getter for our frame.
   * @return the frame.
   */
  public InteractiveAnimationGraphicsFrame getFrame() {
    return this.frame;
  }

  @Override
  public void update(int currTick) {
    frame.updateShapeData(this.getModel().getVisibleShapes(currTick));
    frame.refresh();
  }

  @Override
  public void start() {
    super.start();
    this.frame.makeVisible();
    this.frame.refresh();
  }

  @Override
  public boolean isInteractive() {
    return true;
  }

  @Override
  public void setUpInteractivity(InteractiveAnimationController controller) {
    this.frame.setButtonActions(controller);
    this.frame.linkSpeedSlider(controller);
    this.frame.buildListDialog(controller);
    this.frame.buildColorDialog(controller);
    this.frame.buildScrubberSlider(controller);
  }

  @Override
  public void export(boolean loop) {
    (new SVGView(this.getModel(), out, speed)).export(loop);
  }
}
