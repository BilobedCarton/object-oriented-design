package cs3500.animator.provider.view;

import java.io.IOException;
import java.util.List;

import cs3500.animator.provider.model.AnimationType;
import cs3500.animator.provider.model.IAnimation;
import cs3500.animator.provider.model.IViewModel;

/**
 * A View that can render an animation in human-readable text format. Shapes and animations are
 * displayed in order in which they appear/the animation begins.
 */
public class TextualView extends AAnimationView {
  @Override
  public StringBuilder viewAsStringBuilder(IViewModel model, int ticksPerSec)
          throws UnsupportedOperationException {
    this.checkModel(model, ticksPerSec);

    List<IAnimation> animations = model.getAnimations();

    StringBuilder sb = new StringBuilder();
    sb.append("Shapes:\n");

    for (IAnimation animation : animations) {
      // this works because each AnimatedShape has exactly one NonAnimation that spans the entire
      // duration of its existence
      if (animation.getType() == AnimationType.NON_ANIMATION) {
        sb.append("Name: ").append(animation.getShapeName()).append("\n");
        sb.append(animation.getInitialShape().toString()).append("\n");
        sb.append("Appears at t=").append((double)animation.getStartTime() / ticksPerSec)
                .append("s\n");
        sb.append("Disappears at t=").append((double)animation.getEndTime() / ticksPerSec)
                .append("s\n");
        sb.append("\n");
      }
    }

    if (sb.length() > 0)  {
      for (IAnimation animation : animations) {
        if (animation.getType() != AnimationType.NON_ANIMATION) {
          sb.append("Shape ").append(animation.getShapeName()).append(" ")
              .append(animation.getType().getDescription()).append(" ")
              .append(animation.transitionInfo()).append(" ")
              .append("from t=").append((double)animation.getStartTime() / ticksPerSec)
              .append("s to t=").append((double)animation.getEndTime() / ticksPerSec)
              .append("s\n");
        }
      }
      sb.deleteCharAt(sb.length() - 1);
    }

    return sb;
  }

  @Override
  public String viewAsString(IViewModel model, int ticksPerSec) {
    return viewAsStringBuilder(model, ticksPerSec).toString();
  }

  @Override
  public void viewAsAppendable(Appendable appendable, IViewModel model, int ticksPerSec) {
    if (appendable == null) {
      throw new IllegalArgumentException("cannot append to null appendable");
    }
    try {
      appendable.append(viewAsStringBuilder(model, ticksPerSec).toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("could not output to given appendable!");
    }
  }
}
