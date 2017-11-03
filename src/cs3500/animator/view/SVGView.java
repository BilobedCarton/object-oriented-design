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

  public SVGView(ReadOnlySimpleAnimation model, Appendable out, double speed) {
    super(model,speed);
    this.out = out;
  }

  @Override
  public void update() {
    String retString ="<svg width=\""+frameSizeX+"\" height=\""+frameSizeY+"\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">";
    for(Shape shape : getModel().getShapes()) {
      retString += shape.toSVG(this.speed);
      for (AnimationAction action : getModel().getActions()) {
        if(action.getShape() == shape){
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
