package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents an SVG based view for an animation. Takes model data and outputs and SVG version.
 */
public class SVGView extends AbstractView {
  private Appendable out;
  private double speed;
  private int frameSizeX;
  private int frameSizeY;

  public SVGView(ReadOnlySimpleAnimation model, Appendable out, double speed) {
    super(model);
    this.frameSizeX = 700;
    this.frameSizeY = 500;
    this.out = out;
    this.speed = speed;
  }

  @Override
  public void start() {
    for(Shape shape : getModel().getShapes()) {
      if(shape.getSizeX() > frameSizeX){
        frameSizeX = (int)shape.getSizeX();
      }
      if(shape.getSizeY() > frameSizeY){
        frameSizeY = (int)shape.getSizeY();
      }
    }

    String retString ="<svg width=\""+frameSizeX+"\" height=\""+frameSizeY+"\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n";
    for(Shape shape : getModel().getShapes()) {
      retString += shape.toSVG(this.speed);
      for (AnimationAction action : getModel().getActions()) {
        if(action.getShape().getName() == shape.getName()){
          retString += action.toSVG(this.speed);
        }
      }
      retString += shape.svgEnd();
    }
    retString += "</svg>";

    try {
      out.append(retString);
    } catch (IOException e) {
      throw new IllegalStateException("error with writing file");
    }
  }
}
