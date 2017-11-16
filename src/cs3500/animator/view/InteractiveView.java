package cs3500.animator.view;

import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.view.graphics.InteractiveAnimationGraphicsFrame;

public class InteractiveView extends AbstractView {
  private Appendable out;
  private double speed;
  protected InteractiveAnimationGraphicsFrame frame;

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
    this.frame.linkSpeedSlider(controller, controller.getSpeed());
    this.frame.buildListDialog(controller);
  }

  @Override
  public void export() {
    (new SVGView(this.getModel(), out, speed)).export();
  }
}
