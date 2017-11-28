package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.IViewModel;
import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.IAnimation;

/**
 * A hybrid view capable of taking interactive input from the user, and provides the option to
 * export the currently shown animation to an SVG file.
 */
public class InteractiveView extends AAnimationView implements Interactive {
  private Timer timer;
  private IViewModel model;
  private InteractiveViewJFrame frame;
  private int currentTick;
  private int ticksPerSec;
  private boolean isRunning;
  private boolean loop;

  /**
   * Set default values of this InteractiveView. Set ticksPerSec to 1 to prevent unwanted errors.
   */
  public InteractiveView() {
    this.currentTick = 0;
    this.isRunning = false;
    this.timer = new Timer();
    this.model = null;
    this.ticksPerSec = 1;
    this.loop = false;
  }

  /**
   * Initialize more values of this InteractiveView.
   * @param model           given model to use for initialization
   * @param ticksPerSec     desired initial tickspersecond
   */
  private void init(IViewModel model, int ticksPerSec) {
    this.ticksPerSec = ticksPerSec;
    this.model = model;
    checkModel(model, ticksPerSec);
  }

  @Override
  public String getStateAsSvg() {
    IAnimationView svgView = new SvgView(this.loop);
    return svgView.viewAsSvg(this.model, this.ticksPerSec);
  }

  @Override
  public void setViewModel(IViewModel model) {
    this.model = model;
  }

  @Override
  public void viewAsSwing(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.init(model, ticksPerSec);
    this.frame = new InteractiveViewJFrame(model.getWidthRequired(), model.getHeightRequired(),
            ticksPerSec, this.getShapeNames());

  }

  @Override
  public void togglePlayback() {
    this.isRunning = !this.isRunning;
    if (this.isRunning) {
      this.timer.schedule(new NextTick(), 1000 / this.ticksPerSec);
    }
  }

  @Override
  public void restart() {
    this.currentTick = 0;
    if (!this.isRunning) {
      this.togglePlayback();
    }
  }

  @Override
  public void toggleLooping() {
    this.loop = !this.loop;
  }

  @Override
  public void setSpeed(int ticksPerSec) {
    this.ticksPerSec = ticksPerSec;
  }

  @Override
  public int getSpeed() {
    return this.ticksPerSec;
  }

  @Override
  public void addListeners(ActionListener ar, ChangeListener cr, ListSelectionListener lr) {
    this.frame.addListeners(ar, cr, lr);
  }

  @Override
  public void showShapeSelection() {
    this.frame.showShapeSelection();
  }

  @Override
  public List<String> getSelectedShapes() {
    return this.frame.getSelectedShapes();
  }

  protected List<String> getShapeNames() {
    List<IAnimation> animations = model.getAnimations();
    List<String> retList = new ArrayList<String>();
    for (IAnimation a : animations) {
      if (a.getType() == AnimationType.NON_ANIMATION) {
        retList.add(a.getShapeName());
      }
    }
    return retList;
  }

  private void nextTick() {
    if (currentTick + 1 >= model.getEndTick()) {
      if (this.loop) {
        currentTick = 0;
      } else {
        return;
      }
    }

    frame.update(model.getShapesAtT(currentTick));
    currentTick += 1;
  }

  @Override
  public void makeVisible() {
    this.frame.setVisible(true);
  }

  @Override
  public int getCurrentTick() {
    return this.currentTick;
  }

  class NextTick extends TimerTask {
    @Override
    public void run() {
      InteractiveView.this.nextTick();
      if (InteractiveView.this.isRunning) {
        InteractiveView.this.timer.schedule(new NextTick(),
                1000 / InteractiveView.this.ticksPerSec);
      }
    }
  }
}
