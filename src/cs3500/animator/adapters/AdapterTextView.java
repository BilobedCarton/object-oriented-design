package cs3500.animator.adapters;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.IReadOnlyAnimationModel;
import cs3500.animator.provider.view.TextualView;
import cs3500.animator.view.AbstractView;


/**
 * The adapter to our textView.
 */
public class AdapterTextView extends AbstractView {

  //the view we will use to adapt
  private TextualView theirView;
  private Appendable out;
  private int tickSpeed;

  /**
   * A constructor for our adapter text view.
   * @param theView our textView to be adapted.
   */
  public AdapterTextView(IReadOnlyAnimationModel model,TextualView theView, Appendable out) {
    super(model);
    this.theirView = theView;
    this.out = out;
  }


  //in this case we output the shapes and such as a string.
  @Override
  public void start() {
    this.export(false);
  }

  @Override
  public String export(boolean loop) {

    String retString = theirView.viewAsString(new AdapterReadOnlyToIViewModel(model), tickSpeed);

    try {
      out.append(retString);
    } catch (IOException e) {
      throw new IllegalStateException("error with writing file");
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
