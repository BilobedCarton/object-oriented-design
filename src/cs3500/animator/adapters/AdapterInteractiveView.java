package cs3500.animator.adapters;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.model.IViewModel;
import cs3500.animator.provider.view.IAnimationView;
import cs3500.animator.provider.view.InteractiveView;
import cs3500.animator.provider.view.SvgView;
import cs3500.animator.view.IView;

public class AdapterInteractiveView implements IView {
  private InteractiveView interactive;
  private IReadOnlyAnimationModel model;
  private Appendable out;

  public AdapterInteractiveView(
          InteractiveView interactive, IReadOnlyAnimationModel model, Appendable out) {
    this.interactive = interactive;
    this.model = model;
    this.out = out;
  }

  private void checkModel(IViewModel model, int ticksPerSec)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }

    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("ticks per second has to be > 0");
    }
  }


  @Override
  public IReadOnlyAnimationModel getModel() {
    return model;
  }

  @Override
  public void update(int currTick) {

  }

  @Override
  public void start() {

  }

  @Override
  public boolean isInteractive() {
    return true;
  }

  @Override
  public void setUpInteractivity(IInteractiveAnimationController controller) throws NotImplementedException {
    interactive.addListeners();
  }

  @Override
  public String export(boolean loop) throws NotImplementedException {
    IAnimationView svgView = new SvgView(loop);

    fOut.write(svgView.viewAsSvg(this.model, this.ticksPerSec));

  }
}
