package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.graphics.AnimationGraphicsFrame;

public class InteractiveView extends AbstractView {
  private Appendable out;
  private int frameSizeX;
  private int frameSizeY;
  private double speed;
  protected AnimationGraphicsFrame frame;

  public InteractiveView(
          ReadOnlySimpleAnimation model,
          Appendable out,
          double speed,
          int windowWidth,
          int windowHeight) {
    super(model);
    this.frameSizeX = windowWidth;
    this.frameSizeY = windowHeight;
    this.out = out;
    this.speed = speed;
    this.frame = new AnimationGraphicsFrame(windowWidth, windowHeight);
  }

  @Override
  public void update(int currTick) {
    frame.updateShapeData(this.getModel().getVisibleShapes(currTick));
    frame.refresh();
  }

  @Override
  public void start() {
    super.start();
    this.toSVG();
    frame.makeVisible();
  }

  /**
   * Convert this view to svg and export it to the out appendable
   */
  private void toSVG() {
    // In the case where we don't want this to actually output svg, we just want to render visually.
    if (out == null) {
      return;
    }

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
