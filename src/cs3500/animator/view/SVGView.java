package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.IAnimationPiece;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an SVG based view for an animation. Takes model data and outputs and SVG version.
 */
public class SVGView extends AbstractView {
  private Appendable out;
  private double speed;
  private int frameSizeX;
  private int frameSizeY;

  /**
   * The constructor for our svg view.
   * @param model is the model the view uses .
   * @param out is where the output will be appended to.
   * @param speed is how many ticks per second to run at.
   */
  public SVGView(IReadOnlyAnimationModel model, Appendable out, double speed) {
    super(model);
    this.frameSizeX = 700;
    this.frameSizeY = 500;
    this.out = out;
    this.speed = speed;
  }

  @Override
  public void start() {
    this.export(false);
  }

  @Override
  public void update(int currTick) {
    this.speed = (double)currTick;
  }

  @Override
  public String export(boolean loop) {
    for (IAnimationPiece shape : getModel().getShapes()) {
      if (shape.getSizeX() > frameSizeX) {
        frameSizeX = (int) shape.getSizeX();
      }
      if (shape.getSizeY() > frameSizeY) {
        frameSizeY = (int) shape.getSizeY();
      }
    }

    String retString = "<svg width=\"" + frameSizeX + "\" height=\"" + frameSizeY + "\" "
            + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";

    //if we need to worry about looping.
    if (loop) {
      int maxLife = 1;
      for (IAnimationPiece shape : getModel().getShapes()) {
        if (shape.isVisible()) {
          if (shape.getDisappearTick() > maxLife) {
            maxLife = shape.getDisappearTick();
          }
        }
      }
      maxLife += 1;
      retString += "<rect>\n\t<animate id=\"base\" begin=\"0;base.end\" dur=\""
              + (maxLife / speed * 1000) + "ms\" attributeName=\"visibility\" from=\"hide\""
              + " to=\"hide\"/>\n</rect>\n";
    }
    for (IAnimationPiece shape : getModel().getShapes()) {
      if (shape.isVisible()) {
        retString += shape.toSVG(this.speed, loop);
        for (ITimedAction action : getModel().getActions()) {
          if (action.getShape().getName() == shape.getName()) {
            retString += action.toSVG(this.speed, loop);
          }
        }
        retString += shape.svgEnd();
      }
    }
    retString += "</svg>";

    try {
      out.append(retString);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
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
