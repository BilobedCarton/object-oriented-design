package cs3500.animator.adapters;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import cs3500.animator.control.IInteractiveAnimationController;
import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.model.IViewModel;
import cs3500.animator.provider.view.IAnimationView;
import cs3500.animator.view.IView;

/**
 * Adapts the provider version of InteractiveViewO for use by the native animation software.
 */
public class AdapterIAnimationView implements IView {
  private IAnimationView view;
  private IViewModel viewModel;

  /**
   * Creates a new {@code AdapterIAnimationView} object.
   * @param view is the provider view we are adapting.
   */
  public AdapterIAnimationView(IAnimationView view) {
    this.view = view;
  }

  @Override
  public IReadOnlyAnimationModel getModel() {
    return null;
  }

  @Override
  public void update(int currTick) {

  }

  @Override
  public void start() {

  }

  @Override
  public boolean isInteractive() {
    return false;
  }

  @Override
  public void setUpInteractivity(IInteractiveAnimationController controller) throws NotImplementedException {

  }

  @Override
  public void export(boolean loop) throws NotImplementedException {

  }
}
