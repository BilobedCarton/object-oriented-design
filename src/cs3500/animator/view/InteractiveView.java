package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.graphics.AnimationGraphicsFrame;

public class InteractiveView extends AbstractView {
  private Appendable out;
  private int frameSizeX;
  private int frameSizeY;
  protected AnimationGraphicsFrame frame;
  protected Timer timer;
  protected int currTick;

  public InteractiveView(
          ReadOnlySimpleAnimation model,
          double speed,
          Appendable out,
          int windowWidth,
          int windowHeight) {
    super(model, speed);
    this.frameSizeX = 700;
    this.frameSizeY = 500;
    this.out = out;
    this.frame = new AnimationGraphicsFrame(windowWidth, windowHeight);
    this.timer = new Timer((int) (1000 / speed), new UpdateListener(this));
    this.currTick = 0;
  }

  @Override
  public void update() {
    ArrayList<Shape> shapesToDraw = new ArrayList<Shape>();
    this.getModel().runCycle(currTick);
    for (Shape s : this.getModel().getShapes()) {
      if (s.getAppearTick() <= currTick && s.getDisappearTick() > currTick && s.isVisible()) {
        shapesToDraw.add(s);
      }
    }
    frame.updateShapeData(shapesToDraw);
    frame.refresh();
    this.currTick++;
  }

  @Override
  public void start() {
    super.start();
    this.toSVG();
    this.timer.start();
    frame.makeVisible();
  }

  @Override
  public void reset() {
    super.reset();
    this.timer = new Timer((int) (1000 / speed), new UpdateListener(this));
    this.currTick = 0;
  }

  /**
   * Convert this view to svg and export it to the out appendable
   */
  private void toSVG() {
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
