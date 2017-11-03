package cs3500.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.util.AnimationFileReader;
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
   * Generates a filewrite object for the given output.
   * @param output the name of the output file to be made.
   * @return the fileWriter set to write to this file.
   */
  public static FileWriter genFileWriter(String output) throws IOException {
    File file = new File(System.getProperty("user.dir") + "\\resources\\"+output);
    try {
      file.createNewFile();
    } catch (IOException e) {
      throwErrorMessage("Issue creating file.");
    }
    FileWriter writer;
    writer = new FileWriter(file);
    return writer;
  }

  /**
   * Takes the inputs and makes a model load them in.
   * @param inputFile the inputFile to be read.
   * @param viewType the type of view to launch.
   * @param outputFile the output position of the view.
   * @param speed the ticks per second.
   */
  public static void kickOffView(String inputFile, String viewType, String outputFile, double speed)
          throws FileNotFoundException{
    SimpleAnimation buildModel = new SimpleAnimation();
    AnimationFileReader animReader = new <IAnimationModel>AnimationFileReader();
    String useFile = System.getProperty("user.dir") + "/resources/"+inputFile;
    ReadOnlySimpleAnimation useModel = new ReadOnlySimpleAnimation(animReader.readFile(useFile,
            new SimpleAnimation.Builder()));
    IView launchView;

    switch(viewType) {
      case "text":
        if(outputFile != "System.out") {
          if(!outputFile.substring(outputFile.length() - 4).equals(".txt")) {
            throwErrorMessage("Invalid input, output must be .txt for type text.");
          }
          FileWriter writer;
          try {
            writer = genFileWriter(outputFile);
          }catch(IOException e) {
            throwErrorMessage("Error making file.");
            return;
          }
          launchView = new TextView(useModel,writer, speed);
          launchView.update();
          try {
            writer.close();
          } catch(IOException error){
            throwErrorMessage("Error closing file.");
            return;
          }
        }
        else {
          launchView = new TextView(useModel, System.out, speed);
          launchView.update();
        }
        break;
      case "visual":
        launchView = new VisualView(useModel, speed, 700, 700);
        ((VisualView) launchView).start();
        break;
      case "svg":
        if(outputFile != "System.out") {
          System.out.println(outputFile);
          if(!outputFile.substring(outputFile.length() - 4).equals(".svg")) {
            throwErrorMessage("Invalid input, output must be svg for type svg.");
          }
          FileWriter writer;
          try {
            writer = genFileWriter(outputFile);
          }catch(IOException e) {
            throwErrorMessage("Error making file.");
            return;
          }
          launchView = new SVGView(useModel,writer, speed);
          launchView.update();
          try {
            writer.close();
          } catch(IOException error){
            throwErrorMessage("Error closing file.");
            return;
          }
        }
        else {
          launchView = new SVGView(useModel, System.out, speed);
          launchView.update();
        }
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
            if(!s2.equals("text") && !s2.equals("visual") && !s2.equals("svg")) {
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

    kickOffView(inputFile, viewType, outputFile, speed);

  }
}
