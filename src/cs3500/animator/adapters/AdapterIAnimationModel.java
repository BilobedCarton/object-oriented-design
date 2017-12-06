package cs3500.animator.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimationModelOrig;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.provider.model.animation.IAnimation;
import cs3500.animator.provider.model.shape.IShape;
import cs3500.animator.provider.model.IViewModel;

/**
 * Represents an Adapter for an IAnimationModelOrig to turn it into IViewModel.
 */
public class AdapterIAnimationModel implements IViewModel {
  private IAnimationModelOrig model;

  /**
   * Creates a new {@code AdapterIAnimationModel} object.
   * @param model is the IAnimationModelOrig being adapted by this adapter.
   */
  public AdapterIAnimationModel(IAnimationModelOrig model) {
    this.model = model;
  }

  @Override
  public List<IAnimation> getAnimations() {
    List<ITimedAction> timedActions = model.getActions();
    List<IAnimation> animations = new ArrayList<>();
    for (ITimedAction action : timedActions) {
      animations.add(new AdapterITimedAction(action));
    }
    return animations;
  }

  @Override
  public List<IShape> getShapesAtT(int ticks) throws IllegalArgumentException {
    List<IShape> shapesAtT = new ArrayList<>();
    for (IAnimationPiece shape : model.getShapes()) {
      shapesAtT.add(new AdapterIAnimationPiece(model.getShapeStateAt(ticks, shape)));
    }
    return shapesAtT;
  }

  @Override
  public int getEndTick() {
    int lastDisappearTick = 0;
    for (IAnimationPiece shape : model.getShapes()) {
      if (shape.getDisappearTick() > lastDisappearTick) {
        lastDisappearTick = shape.getDisappearTick();
      }
    }
    return lastDisappearTick;
  }

  @Override
  public int getWidthRequired() {
    double farthestRightPoint = 0;
    for (IAnimationPiece shape : model.getShapes()) {
      if (shape.getPosX() + shape.getSizeX() > farthestRightPoint) {
        farthestRightPoint = shape.getPosX() + shape.getSizeX();
      }
    }
    return (int) farthestRightPoint;
  }

  @Override
  public int getHeightRequired() {
    double farthestDownPoint = 0;
    for (IAnimationPiece shape : model.getShapes()) {
      if (shape.getPosY() + shape.getSizeY() > farthestDownPoint) {
        farthestDownPoint = shape.getPosY() + shape.getSizeY();
      }
    }
    return (int) farthestDownPoint;
  }
}
