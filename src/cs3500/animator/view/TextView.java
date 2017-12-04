package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyAnimationModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a text based view for an animation program.
 */
public class TextView extends AbstractView {

  private Appendable out;
  private double speed;

  /**
   * Creates a new {@code TextView} object.
   *
   * @param model is the model.
   * @param out   is the output location for this TextView, where we output the text to.
   */
  public TextView(IReadOnlyAnimationModel model, Appendable out, double speed) {
    super(model);
    this.out = out;
    this.speed = speed;
  }

  //in this case we output the shapes and such as a string.
  @Override
  public void start() {
    this.export(false);
  }

  @Override
  public String export(boolean loop) {
    int[] shapeTimes = new int[getModel().getShapes().size()];
    for (int i = 0; i < getModel().getShapes().size(); i++) {
      shapeTimes[i] = getModel().getShapes().get(i).getAppearTick();
    }
    Arrays.sort(shapeTimes);
    String retString = "Shapes:\n";
    for (int i = 0; i < shapeTimes.length; i++) {
      for (int x = 0; x < getModel().getShapes().size(); x++) {
        if (shapeTimes[i] == getModel().getShapes().get(x).getAppearTick()) {
          retString += getModel().getShapes().get(x).toString(speed);
          break;
        }
      }
    }

    int[] actionTimes = new int[getModel().getActions().size()];
    for (int i = 0; i < getModel().getActions().size(); i++) {
      if (i == 0) {
        retString += "\n";
      }
      actionTimes[i] = getModel().getActions().get(i).getStartTick();
    }
    Arrays.sort(actionTimes);

    for (int i = 0; i < actionTimes.length; i++) {
      for (int x = 0; x < getModel().getActions().size(); x++) {
        if (actionTimes[i] == getModel().getActions().get(x).getStartTick()) {
          getModel().getActions().get(x).updateOriginalValues();
          retString += getModel().getActions().get(x).toString(speed);
          getModel().getActions().get(x).executeFinal();
          break;
        }
      }
    }

    try {
      out.append(retString);
    } catch (IOException e) {
      throw new IllegalStateException("error with writing file");
    }

    if (out instanceof FileWriter) {

      FileWriter outN = (FileWriter) out;
      try {
        outN.close();
      } catch (IOException error) {
        throw new IllegalStateException("Error closing file.");
      }
    }
    return retString;
  }
}
