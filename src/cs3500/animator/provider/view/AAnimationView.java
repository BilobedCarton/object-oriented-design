package cs3500.animator.provider.view;

import cs3500.animator.provider.model.IViewModel;

/**
 * Contains abstractions for the IAnimationView classes. Every view by default throws an exception
 * unless it specifically overrides one of these methods.
 */
public class AAnimationView implements IAnimationView {
  @Override
  public void viewAsSwing(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);
    throw new UnsupportedOperationException("Swing not available.");
  }

  @Override
  public String viewAsString(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);
    throw new UnsupportedOperationException("String not available.");
  }

  @Override
  public void viewAsAppendable(Appendable appendable, IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);
    throw new UnsupportedOperationException("Avg not available.");
  }

  @Override
  public String viewAsSvg(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);
    throw new UnsupportedOperationException("Avg not available.");
  }

  @Override
  public void viewAsInteractive() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Interactive not available.");
  }

  @Override
  public StringBuilder viewAsStringBuilder(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);
    throw new UnsupportedOperationException("StringBuilder not available.");
  }

  protected void checkModel(IViewModel model, int ticksPerSec)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }

    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("ticks per second has to be > 0");
    }
  }
}
