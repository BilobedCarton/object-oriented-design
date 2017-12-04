package cs3500.animator.adapters;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.view.SvgView;
import cs3500.animator.view.AbstractView;


/**
 * The adapter from their svg view to ours.
 */
public class AdapterSVGView extends AbstractView {

  //the view we will use to adapt
  private SvgView theirView;
  private Appendable out;
  private int tickSpeed;


  /**
   * Constructor for adapter to svg view.
   * @param model the model to translate.
   * @param theirs the svgview of theirs to translate.
   * @param out the output location.
   */
  public AdapterSVGView(IReadOnlyAnimationModel model, SvgView theirs, Appendable out) {
    super(model);
    this.theirView = theirs;
    this.out = out;
  }

  @Override
  public IReadOnlyAnimationModel getModel() {
    return model;
  }

  @Override
  public void update(int currTick) {
    tickSpeed = currTick;
  }

  @Override
  public void start() {
    this.export(false);
  }

  @Override
  public String export(boolean loop) throws NotImplementedException {
    theirView = new SvgView(loop);
    String retString = theirView.viewAsSvg(new AdapterReadOnlyToIViewModel(model), tickSpeed);

    try {
      out.append(retString);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    if (out instanceof FileWriter) {

      FileWriter outN = (FileWriter) out;
      try {
        outN.close();
      } catch (IOException error) {
        throw new IllegalStateException("Error closing file.");
      }
    }
    return retString;
  }
}
