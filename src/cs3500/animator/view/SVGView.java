package cs3500.animator.view;

import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents an SVG based view for an animation. Takes model data and outputs and SVG version.
 */
public class SVGView extends AbstractView {
  // TODO add some output property?

  public SVGView(ReadOnlySimpleAnimation model) {
    super(model);
  }

  @Override
  public void update() {

  }
}
