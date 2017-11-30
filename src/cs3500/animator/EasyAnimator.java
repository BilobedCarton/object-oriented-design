package cs3500.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.control.AnimationController;
import cs3500.animator.control.IAnimationController;
import cs3500.animator.control.InteractiveAnimationController;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.ViewFactory;

/**
 * The class housing our main method. Handles user input for starting the animator.
 */
public final class EasyAnimator {


  /**
   * Generates a filewrite object for the given output.
   *
   * @param output the name of the output file to be made.
   * @return the fileWriter set to write to this file.
   */
  private static FileWriter genFileWriter(String output) throws IOException {
    File file = new File(System.getProperty("user.dir") + "/" + output);
    try {
      file.createNewFile();
    } catch (IOException e) {
      EasyAnimator.throwErrorMessage("Issue creating file.");
    }
    FileWriter writer;
    writer = new FileWriter(file);
    return writer;
  }


  /**
   * Generates a filewriter for our output.
   * @param viewType the input type.
   * @param outputFile the output location.
   * @return a filewriter to hold the output.
   */
  public static FileWriter getWriter(String viewType,
                                     String outputFile) {
    FileWriter writer;
    switch (viewType) {
      case "text":
        if (!outputFile.equals("System.out")) {
          if (!outputFile.substring(outputFile.length() - 4).equals(".txt")) {
            throwErrorMessage("Invalid input, output must be .txt for type text.");
            return null;
          }
          try {
            writer = genFileWriter(outputFile);
            return writer;
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
        }
        break;
      case "svg":
        if (!outputFile.equals("System.out")) {
          if (!outputFile.substring(outputFile.length() - 4).equals(".svg")) {
            EasyAnimator.throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          try {
            writer = genFileWriter(outputFile);
            return writer;
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
        }
        break;
      case "interactive":
        if (!outputFile.equals("System.out")) {
          if (!outputFile.substring(outputFile.length() - 4).equals(".svg")) {
            EasyAnimator.throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          try {
            writer = genFileWriter(outputFile);
            return writer;
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
        }
        break;
      case "provider":
        if (!outputFile.equals("System.out")) {
          if (!outputFile.substring(outputFile.length() - 4).equals(".svg")) {
            EasyAnimator.throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          try {
            writer = genFileWriter(outputFile);
            return writer;
          } catch (IOException e) {
            EasyAnimator.throwErrorMessage("Error making file.");
            return null;
          }
        }
        break;
      default:
        EasyAnimator.throwErrorMessage("Not supported view type");
        return null;
    }
    throwErrorMessage("Not supported view type");
    return null;
  }

  /**
   * Opens a JOptionPane error window with the provided error message.
   *
   * @param mes the message to be presented in the error.
   */
  public static void throwErrorMessage(String mes) {
    JFrame frame = new JFrame();
    frame.setSize(200, 300);
    frame.setLocation(200, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JOptionPane.showMessageDialog(frame, mes, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * The main method which will be where the program starts.
   *
   * @param args is the string input we expect from the user.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String inputFile = "";
    String viewType = "";
    String outputFile = "System.out";
    double speed = 1;
    String s2 = "";
    boolean writeF = false;

    for (int i = 0; i < args.length; i++) {
      String s = args[i];
      if (i + 1 >= args.length) {
        throwErrorMessage("Invalid input, cannot end on - command.");
        return;
      }
      if (s.charAt(0) == '-') {
        switch (s) {
          case "-iv":
            i += 1;
            s2 = args[i];
            if (!s2.equals("text")
                    && !s2.equals("visual")
                    && !s2.equals("svg")
                    && !s2.equals("interactive")
                    && !s2.equals("provider")) {
              throwErrorMessage("Invalid input, invalid view.");
              return;
            } else {
              viewType = s2;
            }
            break;
          case "-if":
            i += 1;
            s2 = args[i];
            if (s2.length() <= 4 && !s2.substring(s2.length() - 4).equals(".txt") &&
                    !s2.substring(s2.length() - 4).equals(".svg")) {
              throwErrorMessage("Invalid input, input must be .txt or .svg");
              return;
            } else {
              inputFile = s2;
            }
            break;
          case "-o":
            i += 1;
            s2 = args[i];
            if (s2.length() >= 4 && (s2.substring(s2.length() - 4).equals(".txt") ||
                    s2.substring(s2.length() - 4).equals(".svg"))) {
              outputFile = s2;
              writeF = true;
            } else {
              throwErrorMessage("Invalid output, must output to .svg for svg or .txt for text "
                      + "defaulting to System.out. If System.out intended, omit -o from command");
            }
            break;
          case "-speed":
            i += 1;
            s2 = args[i];
            //make sure everything in the input is a number.
            for (int c = 0; c < s2.length(); c++) {
              if (!Character.isDigit(s2.charAt(c))) {
                throwErrorMessage("Invalid output, input must be an int");
                return;
              }
            }

            speed = (double) Integer.parseInt(s2);
            break;
          default:
            throwErrorMessage("Invalid commandline input.");
            return;
        }
      }
    }

    AnimationFileReader animReader = new AnimationFileReader();
    String useFile = System.getProperty("user.dir") + "/" + inputFile;

    IAnimationModel model = animReader.readFile(useFile, new SimpleAnimation.Builder());
    IView view;
    if (writeF) {
      view = ViewFactory.build(viewType, getWriter(viewType, outputFile), speed,
              new ReadOnlySimpleAnimation(model));
    } else {
      view = ViewFactory.build(viewType, System.out, speed, new ReadOnlySimpleAnimation(model));
    }
    IAnimationController controller = view.isInteractive()
            ? new InteractiveAnimationController(model, (InteractiveView) view, speed)
            : new AnimationController(model, view, speed);

    controller.goStart();
  }
}
