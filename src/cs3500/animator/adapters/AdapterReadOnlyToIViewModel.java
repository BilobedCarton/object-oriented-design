package cs3500.animator.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.model.actions.ITimedAction;
import cs3500.animator.model.shapes.IAnimationPiece;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.ShapeBuilder;
import cs3500.animator.provider.model.IViewModel;
import cs3500.animator.provider.model.animation.IAnimation;
import cs3500.animator.provider.model.shape.IShape;


/**
 * An adapter to convert our readonly models into IViewModels.
 */
public class AdapterReadOnlyToIViewModel implements IViewModel {
  private IReadOnlyAnimationModel model;


  /**
   * An adapter for the IReadonlyAnimation's constructor.
   * @param model the model whose information we need.
   */
  AdapterReadOnlyToIViewModel(IReadOnlyAnimationModel model) {
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
    for (IAnimationPiece s : model.getShapes()) {
      // Create a identical copy to modify.
      Shape sCopy = ShapeBuilder.copy((Shape) s);

      // Create a list of actions that correspond to our new shape
      List<ITimedAction> actionsOnS = new ArrayList<ITimedAction>();

      for (ITimedAction a : model.getActions()) {
        if (a.getShape().getName().equals(s.getName())) {
          actionsOnS.add(a);
          a.setShape(sCopy);
          a.updateOriginalValues();
        }
      }

      for (int i = 0; i <= ticks; i++) {
        for (ITimedAction a : actionsOnS) {
          if (i == a.getStartTick()) {
            a.updateOriginalValues();
          } else if (i > a.getStartTick() && i < a.getEndTick()) {
            a.execute();
          } else if (i == a.getEndTick()) {
            a.executeFinal();
          }
        }
      }

      // Set actions back to original shape.
      for (ITimedAction a : actionsOnS) {
        a.setShape(s);
      }

      shapesAtT.add(new AdapterIAnimationPiece(sCopy));
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
