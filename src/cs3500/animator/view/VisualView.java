package cs3500.animator.view;


import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.view.graphics.BasicAnimationGraphicsFrame;

/**
 * Represents a visual view of an animation. This actually renders a moving image.
 */
public class VisualView extends AbstractView {
  protected BasicAnimationGraphicsFrame frame;

  /**
   * Creates a new {@code TextView} object.
   *
   * @param model is the model.
   */
  public VisualView(
          ReadOnlySimpleAnimation model,
          int windowWidth,
          int windowHeight) {
    super(model);
    this.frame = new BasicAnimationGraphicsFrame(windowWidth, windowHeight);
  }

  @Override
  public void update(int currTick) {
    frame.updateShapeData(this.getModel().getVisibleShapes(currTick));
    frame.refresh();
  }

  @Override
  public void start() {
    super.start();
    frame.makeVisible();
  }
}
