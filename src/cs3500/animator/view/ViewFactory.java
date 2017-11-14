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
  public static IView build(String viewType,
                     String outputFile,
                     double speed,
                     ReadOnlySimpleAnimation model) {
    IView view;

    switch (viewType) {
      case "text":
        if (outputFile != "System.out") {
          if (!outputFile.substring(outputFile.length() - 4).equals(".txt")) {
            EasyAnimator.throwErrorMessage("Invalid input, output must be .txt for type text.");
            return null;
          }
          FileWriter writer;
          try {
            writer = EasyAnimator.genFileWriter(outputFile);
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
          view = new TextView(model, writer, speed);
          view.start();
          try {
            writer.close();
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Error closing file.");
            return null;
          }
        } else {
          view = new TextView(model, System.out, speed);
          view.start();
        }
        break;
      case "visual":
        view = new VisualView(model, speed, 700, 700);
        view.start();
        break;
      case "svg":
        if (outputFile != "System.out") {
          System.out.println(outputFile);
          if (!outputFile.substring(outputFile.length() - 4).equals(".svg")) {
            EasyAnimator.throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          FileWriter writer;
          try {
            writer = EasyAnimator.genFileWriter(outputFile);
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
          view = new SVGView(model, writer, speed);
          view.start();
          try {
            writer.close();
          } catch (IOException error) {
            EasyAnimator.throwErrorMessage("Error closing file.");
            return null;
          }
        } else {
          view = new SVGView(model, System.out, speed);
          view.start();
        }
        break;
      default:
        EasyAnimator.throwErrorMessage("Not supported view type");
        return null;
    }

    return view;
  }
}
