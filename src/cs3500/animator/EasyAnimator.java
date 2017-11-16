package cs3500.animator;

import java.io.FileNotFoundException;

import javax.swing.*;

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
   * Opens a JOptionPane error window with the provided error message.
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
   * @param args is the string input we expect from the user.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String inputFile = "";
    String viewType = "";
    String outputFile = "System.out";
    double speed = 1;
    String s2 = "";

    for(int i = 0; i < args.length; i++) {
      String s = args[i];
      if(i +1 >= args.length) {
        throwErrorMessage("Invalid input, cannot end on - command.");
        return;
      }
      if(s.charAt(0) == '-') {
        switch(s) {
          case "-iv":
            i+=1;
            s2 = args[i];
            if(!s2.equals("text")
                    && !s2.equals("visual")
                    && !s2.equals("svg")
                    && !s2.equals("interactive")) {
              throwErrorMessage("Invalid input, invalid view.");
              return;
            }else {
              viewType = s2;
            }
            break;
          case "-if":
            i+=1;
            s2 = args[i];
            if(s2.length() <= 4 && !s2.substring(s2.length() - 4).equals(".txt") &&
                    !s2.substring(s2.length() - 4).equals(".svg") ) {
              throwErrorMessage("Invalid input, input must be .txt or .svg");
              return;
            }else {
              inputFile = s2;
            }
            break;
          case "-o":
            i+=1;
            s2 = args[i];
            if(s2.length() >= 4 && (s2.substring(s2.length() - 4).equals(".txt") ||
                    s2.substring(s2.length() - 4).equals(".svg"))) {
              outputFile = s2;
            }else {
              throwErrorMessage("Invalid output, must output to .svg for svg or .txt for text "
                      + "defaulting to System.out. If System.out intended, omit -o from command");
            }
            break;
          case "-speed":
            i+=1;
            s2 = args[i];
            //make sure everything in the input is a number.
            for (int c = 0; c < s2.length(); c++) {
              if (!Character.isDigit(s2.charAt(c))) {
                throwErrorMessage("Invalid output, input must be an int");
                return;              }
            }

            speed = (double)Integer.parseInt(s2);
            break;
          default:
            throwErrorMessage("Invalid commandline input.");
            return;
        }
      }
    }

    AnimationFileReader animReader = new <IAnimationModel>AnimationFileReader();
    String useFile = System.getProperty("user.dir") + "/resources/" + inputFile;

    IAnimationModel model = animReader.readFile(useFile, new SimpleAnimation.Builder());
    IView view = ViewFactory.build(viewType, outputFile, speed, new ReadOnlySimpleAnimation(model));
    IAnimationController controller = view.isInteractive()
            ? new InteractiveAnimationController(model, (InteractiveView) view, speed)
            : new AnimationController(model, view, speed);

    controller.go();
  }
}
