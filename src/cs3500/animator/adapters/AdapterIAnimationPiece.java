package cs3500.animator.adapters;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.provider.model.shape.ICoordinate;
import cs3500.animator.provider.model.shape.IShape;

public class AdapterIAnimationPiece implements IShape {
  private IAnimationPiece animationPiece;

  public AdapterIAnimationPiece(IAnimationPiece animationPiece) {
    this.animationPiece = animationPiece;
  }

  public IAnimationPiece getAnimationPiece() {
    return animationPiece;
  }

  @Override
  public IShape changeColor(Color color) throws IllegalArgumentException {
    animationPiece.recolor(color);
    return this;
  }

  @Override
  public IShape move(ICoordinate anchor) throws IllegalArgumentException {
    animationPiece.relocate(anchor.getXCoord(), anchor.getYCoord());
    return this;
  }

  @Override
  public IShape scale(double xScale, double yScale) throws IllegalArgumentException {
    animationPiece.resize(animationPiece.getSizeX() * xScale, animationPiece.getSizeY()
            * yScale);
    return this;
  }

  @Override
  public String getScaleDescription(double startXScale, double startYScale, double endXScale,
                                    double endYScale) {
    return null;
  }

  @Override
  public Shape getImage() {
    switch (animationPiece.getShapeType()) {
      case RECTANGLE:
        return new Rectangle.Double(animationPiece.getPosX(), animationPiece.getPosY(),
                animationPiece.getSizeX(), animationPiece.getSizeY());
      case OVAL:
        return new Ellipse2D.Double(animationPiece.getPosX(), animationPiece.getPosY(),
                animationPiece.getSizeX(), animationPiece.getSizeY());
      default:
        throw new IllegalArgumentException("Invalid shape type.");
    }
  }

  @Override
  public Color getColor() {
    return animationPiece.getColor();
  }

  @Override
  public String getType() {
    switch (animationPiece.getShapeType()) {
      case RECTANGLE:
        return "Rectangle";
      case OVAL:
        return "Oval";
      default:
        throw new IllegalArgumentException("Invalid shape type.");
    }
  }

  @Override
  public ICoordinate getAnchor() {
    return new AdapterCoord(animationPiece.getPosX(), animationPiece.getPosY());
  }

  @Override
  public double getXSize() {
    return animationPiece.getSizeX();
  }

  @Override
  public double getYSize() {
    return animationPiece.getSizeY();
  }
}
