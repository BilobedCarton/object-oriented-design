package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;

/**
 * Represents a text based view for an animation program.
 */
public class TextView extends AbstractView {
  private Appendable out;

  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   * @param ticksPerSecond is the number of ticks executed per second.
   * @param out is the output location for this TextView, where we output the text to.
   */
  TextView(IAnimationModel model, double ticksPerSecond, Appendable out) {
    super(model, ticksPerSecond);
    this.out = out;
  }

  @Override
  public void update() {
    // TODO append something to the out based on the model.
  }
}
