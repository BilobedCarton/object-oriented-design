package cs3500.animator.provider.view;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.provider.model.IViewModel;
import cs3500.animator.provider.model.animation.AnimationType;
import cs3500.animator.provider.model.animation.IAnimation;

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
   * Constructs an interactive view, and initializes variables to defaults.
   */
  public InteractiveView() {
    this.currentTick = 0;
    this.isRunning = false;
    this.timer = new Timer();
    this.model = null;
    this.ticksPerSec = 1;
    this.loop = false;
  }

  private void init(IViewModel model, int ticksPerSec) {
    this.ticksPerSec = ticksPerSec;
    this.model = model;
    checkModel(model, ticksPerSec);
  }

  @Override
  public void printCurrentAsSvg() {
    try {
      PrintWriter fOut = new PrintWriter(Files.newOutputStream(Paths.get(
              JOptionPane.showInputDialog("Enter SVG File Output:"))));
      IAnimationView svgView = new SvgView(this.loop);
      fOut.write(svgView.viewAsSvg(this.model, this.ticksPerSec));
      fOut.close();
      Frame successFrame = new Frame();
      JOptionPane.showMessageDialog(successFrame, "File write successful!", "SVG File Written",
              JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
      Frame errorFrame = new Frame();
      JOptionPane.showMessageDialog(errorFrame, "Error writing to file!", "SVG Write Error",
              JOptionPane.ERROR_MESSAGE);
    }
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

  List<String> getShapeNames() {
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

  public void makeVisible() {
    this.frame.setVisible(true);
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
