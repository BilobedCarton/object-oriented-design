package cs3500.animator.view;
import java.io.IOException;

import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.actions.AnimationAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Represents a text based view for an animation program.
 */
public class TextView extends AbstractView {
  private Appendable out;

  /**
   * Creates a new {@code TextView} object.
   * @param model is the model.
   * @param out is the output location for this TextView, where we output the text to.
   */
  public TextView(ReadOnlySimpleAnimation model, Appendable out, double speed) {
    super(model, speed);
    this.out = out;
  }

  //in this case we output the shapes and such as a string.
  @Override
  public void update() {
    String str = "Shapes:\n";
    for (Shape shape : getModel().getShapes()) {
      str += shape.toString(this.speed) + "\n";
    }
    for (AnimationAction action : getModel().getActions()) {
      str += action.toString(this.speed) + "\n";
    }

    try {
      out.append(str);
    } catch (IOException e) {
      throw new IllegalStateException("error with writing file");
    }
  }
}
