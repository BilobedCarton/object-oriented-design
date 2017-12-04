package cs3500.animator.view;

import cs3500.animator.model.IViewModel;

/**
 * Defines the public facing functions every valid AnimationView implmentation must provide.
 * This class serves as the way the animation displays information to the user, including the
 * representation of the overall animation itself.
 */
public interface IAnimationView {
  /**
   * Displays the animation given by the model as a java swing GUI animation. The tempo is
   * determined by the number of ticks per second.
   * @param model the model to animate
   * @param ticksPerSec tempo in ticks per second
   * @throws UnsupportedOperationException if the view does not support this view type
   */
  void viewAsSwing(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException;

  /**
   * Displays the animation given by the model by converting the animation to a text representation
   * in string format.
   * @param model the model to animate
   * @param ticksPerSec tempo in ticks per second
   * @return a String containing the full text representation of the animation
   * @throws UnsupportedOperationException if the view does not support this view type
   */
  String viewAsString(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException;

  /**
   * Displays the animation given by the model by converting the animation to a text representation
   * in an Appendable format.
   * @param appendable the appendable to add the animation description to
   * @param model the model to animate
   * @param ticksPerSec the tempo in ticks per second
   * @throws UnsupportedOperationException if the view does not support this view type
   */
  void viewAsAppendable(Appendable appendable, IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException;

  /**
   * Display the animation given by the model by converting the animation to a text representation
   * in a StringBuilder format. This method is provided to allow consumers of this class to easily
   * continue appending to the text description.
   * @param model the model to animate
   * @param ticksPerSec tempo in ticks per second
   * @return the text description held in a string builder
   * @throws UnsupportedOperationException if the view does not support this view type
   */
  StringBuilder viewAsStringBuilder(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException;

  /**
   * Converts the model into SVG formatted text, which can be saved and viewed in browser.
   * @param model the model to animate
   * @param ticksPerSec tempo in ticks per second
   * @throws UnsupportedOperationException if the view does not support this view type
   */
  String viewAsSvg(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException;

  void viewAsInteractive() throws UnsupportedOperationException;
}
