package cs3500.animator.adapters;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.model.IViewModel;
import cs3500.animator.provider.view.InteractiveView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;


/**
 * The adapter from interactive view to an iview.
 */
public class AdapterInteractiveView implements IView {
  private InteractiveView interactive;
  private IReadOnlyAnimationModel model;
  private Appendable out;


  /**
   * The constructor for our adapter.
   *
   * @param interactive the interactive view to adapt.
   * @param model       the model whose information we ned.
   * @param out         where to export.
   */
  public AdapterInteractiveView(
          InteractiveView interactive, IReadOnlyAnimationModel model, Appendable out) {
    this.interactive = interactive;
    this.model = model;
    this.out = out;

  }


  @Override
  public IReadOnlyAnimationModel getModel() {
    return model;
  }

  @Override
  public void update(int currTick) {
    interactive.setSpeed(currTick);
  }

  @Override
  public void start() {
    interactive.restart();
  }

  @Override
  public boolean isInteractive() {
    return true;
  }

  @Override
  public void setUpInteractivity(IInteractiveAnimationController controller)
          throws NotImplementedException {
    IViewModel viewM = new AdapterReadOnlyToIViewModel(model);
    interactive.viewAsSwing(viewM, interactive.getSpeed());
  }

  @Override
  public String export(boolean loop) throws NotImplementedException {
    String s = (new SVGView(this.getModel(), out, interactive.getSpeed())).export(loop);
    return s;
  }
}
