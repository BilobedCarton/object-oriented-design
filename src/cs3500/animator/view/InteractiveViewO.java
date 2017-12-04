package cs3500.animator.view;

import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.view.graphics.InteractiveAnimationGraphicsFrame;

/**
 * Represents an interactive visual view for an animation. It can also export the animation as an
 * SVG file.
 */
public class InteractiveViewO extends AbstractView {
  private Appendable out;
  private double speed;
  protected InteractiveAnimationGraphicsFrame frame;

  /**
   * Creates a new {@code InteractiveViewO} object.
   *
   * @param model        is the model linked to this view.
   * @param out          is the location where the data can be exported as SVG.
   * @param speed        is the speed in ticksPerSecond of this animation.
   * @param windowWidth  is the width of the window to be displayed.
   * @param windowHeight is the height of the window to be displayed.
   */
  public InteractiveViewO(
          IReadOnlyAnimationModel model,
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
  public void setUpInteractivity(IInteractiveAnimationController controller) {
    this.frame.setButtonActions(controller);
    this.frame.linkSpeedSlider(controller);
    this.frame.buildListDialog(controller);
  }

  @Override
  public String export(boolean loop) {
    return (new SVGView(this.getModel(), out, speed)).export(loop);
  }
}
