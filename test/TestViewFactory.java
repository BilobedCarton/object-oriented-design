import org.junit.Test;

import cs3500.animator.EasyAnimator;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.ReadOnlySimpleAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.ViewFactory;
import cs3500.animator.view.VisualView;

import static org.junit.Assert.assertEquals;

public class TestViewFactory {
  IAnimationModel model = new SimpleAnimation();

  @Test
  public void testNewTextViewToOut() {
    IView view = ViewFactory.build("text", EasyAnimator.getWriter("text",
            "System.out"), 20, new ReadOnlySimpleAnimation(model));
    assertEquals(view.getClass(), TextView.class);
  }

  @Test
  public void testNewTextViewToFile() {
    IView view = ViewFactory.build("text", EasyAnimator.getWriter("text",
            "test.txt"), 25, new ReadOnlySimpleAnimation(model));
    assertEquals(view.getClass(), TextView.class);
  }

  @Test
  public void testNewVisualView() {
    IView view = ViewFactory.build("visual", null, 30, new ReadOnlySimpleAnimation(model));
    assertEquals(view.getClass(), VisualView.class);
  }

  @Test
  public void testNewSVGViewToConsole() {
    IView view = ViewFactory.build("svg", System.out, 40, new ReadOnlySimpleAnimation(model));
    assertEquals(view.getClass(), SVGView.class);
  }

  @Test
  public void testNewSVGViewToFile() {
    IView view = ViewFactory.build("svg", EasyAnimator.getWriter("svg",
            "System.svg"), 40, new ReadOnlySimpleAnimation(model));
    assertEquals(view.getClass(), SVGView.class);
  }
}
