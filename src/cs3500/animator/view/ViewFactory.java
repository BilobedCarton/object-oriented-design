package cs3500.animator.view;

import cs3500.animator.adapters.AdapterInteractiveView;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.view.InteractiveView;

/**
 * Represents a factory to create new views.
 */
public class ViewFactory {
  /**
   * Creates and returns a new view based on the given viewType. Returns null if a view could not be
   * created.
   *
   * @param viewType   is the type of view we want: "text", "visual", "svg".
   * @param outputFile is the name of the potential output file for either an svg or text view. This
   *                   can be null if an output is not needed.
   * @param speed      is the speed of the animation in ticksPerSecond.
   * @param model      is the model this new view will be using to get data from.
   * @return an IView corresponding to the given data.
   * @throws IllegalArgumentException if the given viewType is invalid.
   */
  public static IView build(String viewType,
                            Appendable outputFile,
                            double speed,
                            IReadOnlyAnimationModel model) throws IllegalArgumentException{
    IView view;

    switch (viewType) {
      case "text":
        if (outputFile != System.out) {
          view = new TextView(model, outputFile, speed);
        } else {
          view = new TextView(model, System.out, speed);
        }
        break;
      case "visual":
        view = new VisualView(model, 700, 700);
        break;
      case "svg":
        if (outputFile != System.out) {
          view = new SVGView(model, outputFile, speed);
        } else {
          view = new SVGView(model, System.out, speed);
        }
        break;
      case "interactive":
        if (outputFile == null) {
          view = new cs3500.animator.view.InteractiveView(model, null, 0, 700, 700);
        } else if (outputFile != System.out) {
          view = new cs3500.animator.view.InteractiveView(model, outputFile, speed, 700, 700);
        } else {
          view = new cs3500.animator.view.InteractiveView(model, System.out, speed, 700, 700);
        }
        break;
      case "provider":
        if (outputFile == null) {
          view = new AdapterInteractiveView(new InteractiveView(), model);
        } else if (outputFile != System.out) {

        } else {

        }
        break;
      default:
        throw new IllegalArgumentException("Not supported view type");
    }
    return view;
  }

}
