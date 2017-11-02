package cs3500.animator;

import javax.swing.*;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

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
   * Takes the inputs and makes a model load them in.
   * @param inputFile the inputFile to be read.
   * @param viewType the type of view to launch.
   * @param outputFile the output position of the view.
   * @param speed the ticks per second.
   */
  public static void kickOffView(String inputFile, String viewType, String outputFile, int speed) {
    switch(viewType) {
      IView launchView;
      case "text":
        launchView = new TextView(inputFile, outputFile, speed);
        break;
      case "visual":
        launchView = new VisualView(inputFile, outputFile, speed);
        break;
      case "svg":
        launchView = new SVGView(inputFile, outputFile, speed);
        break;
      default:
        throwErrorMessage("Not supported view type");
        return;
    }
  }

  /**
   * The main method which will be where the program starts.
   * @param args is the string input we expect from the user.
   */
  public static void main(String[] args) {
    String inputFile = "";
    String viewType = "";
    String outputFile = "System.out";
    int speed = 1;
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
            if(s2 != "text" && s2 != "visual" && s2 != "svg") {
              throwErrorMessage("Invalid input, invalid view.");
              return;
            }else {
              viewType = s2;
            }
            break;
          case "-if":
            i+=1;
            s2 = args[i];
            if(s2.length() <= 4 || s2.substring(s2.length() - 4) != ".txt") {
              throwErrorMessage("Invalid input, input must be .txt");
              return;
            }else {
              inputFile = s2;
            }
            break;
          case "-o":
            i+=1;
            s2 = args[i];
            if(s2.length() <= 4 || s2.substring(s2.length() - 4) != ".txt") {
              throwErrorMessage("Invalid output, input must be .txt");
              return;
            }else {
              outputFile = s2;
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

            speed = Integer.parseInt(s2);
            break;
          default:
            throwErrorMessage("Invalid commandline input.");
            return;
        }
      }
    }


  }
}
