package cs3500.animator.view;
import cs3500.animator.EasyAnimator;
import cs3500.animator.model.ReadOnlySimpleAnimation;

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
   */
  public static IView build(String viewType,
                            Appendable outputFile,
                            double speed,
                            ReadOnlySimpleAnimation model) {
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
          view = new InteractiveView(model, null, 0, 700, 700);
          break;
        } else if (outputFile != System.out) {
          view = new InteractiveView(model, outputFile, speed, 700, 700);
          break;
        } else {
          view = new InteractiveView(model, System.out, speed, 700, 700);
          break;
        }
      default:
        EasyAnimator.throwErrorMessage("Not supported view type");
        return null;
    }
    return view;
  }

}
