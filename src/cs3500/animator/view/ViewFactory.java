package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.EasyAnimator;
import cs3500.animator.model.ReadOnlySimpleAnimation;

/**
 * Represents a factory to create new views.
 */
public class ViewFactory {

  /**
   * Creates and returns a new view based on the given viewType.
   * Returns null if a view could not be created.
   * @param viewType is the type of view we want: "text", "visual", "svg".
   * @param outputFile is the name of the potential output file for either an svg or text view.
   *                   This can be null if an output is not needed.
   * @param speed is the speed of the animation in ticksPerSecond.
   * @param model is the model this new view will be using to get data from.
   */
  public IView build(String viewType,
                     String outputFile,
                     double speed,
                     ReadOnlySimpleAnimation model) {
    IView view;

    switch (viewType) {
      case "text":
        if (outputFile == null) {
          throw new IllegalArgumentException("ViewFactory(String, String, double, "
                  + "ReadOnlySimpleAnimation) -- outputFile name cannot be null for a text view.");
        }
        if (outputFile != "System.out") {
          if (outputFile.substring(outputFile.length() - 4) != ".txt") {
            EasyAnimator.throwErrorMessage("Invalid input, output must be .txt for type text.");
          }
          FileWriter writer;
          try {
            writer = new FileWriter(outputFile);
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Invalid input, issue creating output file");
            return null;
          }
          view = new TextView(model, writer, speed);
          view.update();
          try {
            writer.close();
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Error closing file.");
            return null;
          }
        } else {
          view = new TextView(model, System.out, speed);
          view.update();
        }
        break;
      case "visual":
        view = new VisualView(model, speed, 700, 500);
        break;
      case "svg":
        if (outputFile == null) {
          throw new IllegalArgumentException("ViewFactory(String, String, double, "
                  + "ReadOnlySimpleAnimation) -- outputFile name cannot be null for a svg view.");
        }
        if (outputFile != "System.out") {
          if (outputFile.substring(outputFile.length() - 4) != ".svg") {
            EasyAnimator.throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          FileWriter writer;
          try {
            writer = new FileWriter(outputFile);
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Invalid input, issue creating output file");
            return null;
          }
          view = new SVGView(model, writer, speed);
          view.update();
          try {
            writer.close();
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Error closing file.");
            return null;
          }
        } else {
          view = new SVGView(model, System.out, speed);
          view.update();
        }
        break;
      default:
        EasyAnimator.throwErrorMessage("Not supported view type");
        return null;
    }
    return view;
  }
}
