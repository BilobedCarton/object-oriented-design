package cs3500.animator.adapters;

import cs3500.animator.provider.model.shape.ICoordinate;

public class AdapterCoord implements ICoordinate {
  private double x;
  private double y;

  public AdapterCoord(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public double getXCoord() {
    return x;
  }

  @Override
  public double getYCoord() {
    return y;
  }

  @Override
  public ICoordinate add(ICoordinate other) {
    return this.add(other.getXCoord(), other.getYCoord());
  }

  @Override
  public ICoordinate add(double x, double y) {
    this.x += x;
    this.y += y;
    return this;
  }
}
