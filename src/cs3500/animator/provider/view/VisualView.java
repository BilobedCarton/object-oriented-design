package cs3500.animator.view;

import cs3500.animator.model.IViewModel;

/**
 * Visual view that can display a given animation as a java swing GUI application.
 */
public class VisualView extends AAnimationView {
  @Override
  public void viewAsSwing(IViewModel model, int ticksPerSec)
      throws UnsupportedOperationException {
    VisualViewJFrame frame = new VisualViewJFrame(
        model.getWidthRequired(), model.getHeightRequired());

    for (int tick = 0; tick < model.getEndTick(); tick++) {
      frame.update(model.getShapesAtT(tick));

      try {
        Thread.sleep(1000 / ticksPerSec);
      } catch (InterruptedException e) {
        System.exit(0); // blow up
      }
    }
  }
}
