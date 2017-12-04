package cs3500.animator.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import cs3500.animator.model.IViewModel;
import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.IAnimation;

/**
 * View class that is able to render an animation as an SVG file that can by played back.
 */
public class SvgView extends AAnimationView {
  private int ticksPerSecond;
  private boolean loopBack;
  // first bool represents first move has been reset
  // second bool represents first scale has been reset
  // third bool represents first color has been reset
  // fourth bool represents first visibility has been reset
  TreeMap<String, ArrayList<Boolean>> resetChecks;

  private final String RESET_CONST = "15ms";

  public SvgView(boolean loopBack) {
    this.loopBack = loopBack;
    this.resetChecks = new TreeMap<String, ArrayList<Boolean>>();
  }

  /**
   * Outputs this model as an SVG, as a String.
   * @param model               given model to get data from
   * @param ticksPerSec         desired ticks per second to run animation in
   * @return                    this model in svg format as a string
   * @throws UnsupportedOperationException    if this operation isn't supported for this view
   */
  @Override
  public String viewAsSvg(IViewModel model, int ticksPerSec)
      throws UnsupportedOperationException {
    ArrayList<String> shapes = new ArrayList<String>();
    this.checkModel(model, ticksPerSec);
    this.ticksPerSecond = ticksPerSec;

    List<IAnimation> animations = model.getAnimations();
    StringBuilder svgOutput = new StringBuilder();

    // < shapeName, listOfShapesAnimations >
    TreeMap<String, List<String>> animationsOfShapes = new TreeMap<String, List<String>>();

    // < shapeName, shapesSvgWrapper >
    TreeMap<String, List<String>> wrappersOfShapes = new TreeMap<String, List<String>>();

    // initial svg setup
    svgOutput.append(this.defaultSvgInfo(model.getWidthRequired(), model.getHeightRequired()));
    svgOutput.append(this.loopBackSetup(model, ticksPerSec));

    // count how many unique animation types there are, besides non_animation
    HashSet<AnimationType> animationTypes = new HashSet<AnimationType>();
    for (IAnimation a : animations) {
      if (!a.getType().equals(AnimationType.NON_ANIMATION)) {
        if (!animationTypes.contains(a.getType())) {
          animationTypes.add(a.getType());
        }
      }
    }

    // ~~~~~~~~~~~~~~~~~~~~ START to fill SVG with shape and animation info ~~~~~~~~~~~~~~~~~~~~
    // add shape names to our maps
    for (IAnimation a : animations) {
      if (a.getType().equals(AnimationType.NON_ANIMATION)) {
        shapes.add(a.getShapeName());
        wrappersOfShapes.put(a.getShapeName(), this.getShapeWrapper(a));
        animationsOfShapes.put(a.getShapeName(), new ArrayList<String>());
        ArrayList<Boolean> falses = new ArrayList<Boolean>();

        // add a boolean for each animation (because we need to reset only the first
        // of each type of animation)
        // + 1 to reset visibility
        for (int i = 0; i < animationTypes.size() + 1; i++) {
          falses.add(Boolean.FALSE);
        }
        this.resetChecks.put(a.getShapeName(), falses);
      }
    }

    // add animation info to our maps
    for (IAnimation a : animations) {
      String xName = "";
      String yName = "";
      AnimationType type = a.getType();
      if (type.equals(AnimationType.MOVE)) {
        if (a.getInitialShape().getType().equals("ellipse")) {
          xName = "cx";
          yName = "cy";
        } else if (a.getInitialShape().getType().equals("rect")) {
          xName = "x";
          yName = "y";
        }

        animationsOfShapes.get(a.getShapeName()).addAll(this.xyAnimationToSvg(a, xName, yName));
      } else if (type.equals(AnimationType.SCALE)) {
        if (a.getInitialShape().getType().equals("ellipse")) {
          xName = "rx";
          yName = "ry";
        } else if (a.getInitialShape().getType().equals("rect")) {
          xName = "width";
          yName = "height";
        }

        animationsOfShapes.get(a.getShapeName()).addAll(this.xyAnimationToSvg(a, xName, yName));
      } else if (type.equals(AnimationType.CHANGE_COLOR)) {
        animationsOfShapes.get(a.getShapeName()).addAll(this.colorAnimationToSvg(a));
      }
    }

    for (String shapeName : shapes) {
      svgOutput.append(wrappersOfShapes.get(shapeName).get(0)).append("\n");
      svgOutput.append(wrappersOfShapes.get(shapeName).get(1)).append("\n");
      svgOutput.append(wrappersOfShapes.get(shapeName).get(3)).append("\n");
      for (String animationInfo : animationsOfShapes.get(shapeName)) {
        svgOutput.append(animationInfo).append("\n");
      }
      svgOutput.append(wrappersOfShapes.get(shapeName).get(2)).append("\n\n");
    }
    // ~~~~~~~~~~~~~~~~~~~~ END filling SVG with shape and animation info ~~~~~~~~~~~~~~~~~~~~

    svgOutput.append("</svg>");

    return svgOutput.toString();
  }

  private List<String> xyAnimationToSvg(IAnimation a, String xName, String yName) {
    String svgShapeName = a.getShapeName();

    if (a.getType().equals(AnimationType.MOVE)) {
      int xStart =
          (int) a.shapeAtTime(a.getInitialShape(), a.getStartTime()).getAnchor().getXCoord();
      int xEnd =
          (int) a.shapeAtTime(a.getInitialShape(), a.getEndTime()).getAnchor().getXCoord();
      int yStart =
          (int) a.shapeAtTime(a.getInitialShape(), a.getStartTime()).getAnchor().getYCoord();
      int yEnd =
          (int) a.shapeAtTime(a.getInitialShape(), a.getEndTime()).getAnchor().getYCoord();

      return this.xyAnimationToSvgHelper(this.defaultAnimateInfo(a), svgShapeName,
          xName, yName, xStart, xEnd, yStart, yEnd);
    } else if (a.getType().equals(AnimationType.SCALE)) {
      int xStart = (int) a.shapeAtTime(a.getInitialShape(), a.getStartTime()).getXSize();
      int xEnd = (int) a.shapeAtTime(a.getInitialShape(), a.getEndTime()).getXSize();

      int yStart = (int) a.shapeAtTime(a.getInitialShape(), a.getStartTime()).getYSize();
      int yEnd = (int) a.shapeAtTime(a.getInitialShape(), a.getEndTime()).getYSize();

      return this.xyAnimationToSvgHelper(this.defaultAnimateInfo(a), svgShapeName,
          xName, yName, xStart, xEnd, yStart, yEnd);
    }

    return null;
  }

  private List<String> xyAnimationToSvgHelper(String defAnimInfo, String svgShapeName,
                                              String svgXName, String svgYName,
                                              int xStart, int xEnd, int yStart, int yEnd) {
    List<String> toReturn = new ArrayList<String>();
    StringBuilder xAnimation = new StringBuilder();
    StringBuilder yAnimation = new StringBuilder();

    String fromToX = this.wrapVarInQuotes("from", xStart)
        + this.wrapVarInQuotes("to", xEnd);
    String fromToY = this.wrapVarInQuotes("from", yStart)
        + this.wrapVarInQuotes("to", yEnd);
    String fillFreeze = this.wrapVarInQuotes("fill", "freeze");

    xAnimation.append(defAnimInfo);

    yAnimation.append(xAnimation.toString());

    xAnimation.append(this.wrapVarInQuotes("attributeName", svgXName));
    xAnimation.append(fromToX);
    xAnimation.append(fillFreeze);

    yAnimation.append(this.wrapVarInQuotes("attributeName", svgYName));
    yAnimation.append(fromToY);
    yAnimation.append(fillFreeze);

    toReturn.add(xAnimation.append("/>").toString());
    toReturn.add(yAnimation.append("/>").toString());

    if (this.loopBack && !this.resetChecks.get(svgShapeName).get(0)) {
      StringBuilder xAnimationReset = new StringBuilder();
      StringBuilder yAnimationReset = new StringBuilder();

      xAnimationReset.append(this.resetAnimation("animate", svgXName, xStart));
      yAnimationReset.append(this.resetAnimation("animate", svgYName, yStart));

      toReturn.add(xAnimationReset.toString());
      toReturn.add(yAnimationReset.toString());

      ArrayList<Boolean> resetArr = this.resetChecks.get(svgShapeName);
      resetArr.set(0, Boolean.TRUE);
      this.resetChecks.put(svgShapeName, resetArr);
    }

    return toReturn;
  }

  /**
   * Produces the default SVG information.
   * @return        the default SVG information
   */
  private String defaultSvgInfo(int width, int height) {
    StringBuilder toReturn = new StringBuilder();

    toReturn.append("<svg " + this.wrapVarInQuotes("width", width));
    toReturn.append(this.wrapVarInQuotes("height", height));
    toReturn.append(this.wrapVarInQuotes("version", 1.1));
    toReturn.append(this.wrapVarInQuotes("xmlns", "http://www.w3.org/2000/svg"));
    toReturn.deleteCharAt(toReturn.length() - 1); // remove the extra space at the end of xmlns
    toReturn.append(">\n");

    return toReturn.toString();
  }

  /**
   * Produces the SVG representation of a color animation.
   * @param a   the given color animation
   * @return      the SVG representation of a color animation
   */
  private List<String> colorAnimationToSvg(IAnimation a) {
    List<String> toReturn = new ArrayList<String>();
    StringBuilder colorAnimation = new StringBuilder();

    colorAnimation.append(this.defaultAnimateInfo(a));

    Color startColor = a.shapeAtTime(a.getInitialShape(), a.getStartTime()).getColor();
    String startShapeColor = "rgb("
        + startColor.getRed() + "," + startColor.getGreen() + "," + startColor.getBlue() + ")";
    Color endColor = a.shapeAtTime(a.getInitialShape(), a.getEndTime()).getColor();
    String endShapeColor = "rgb("
        + endColor.getRed() + "," + endColor.getGreen() + "," + endColor.getBlue() + ")";

    colorAnimation.append(this.wrapVarInQuotes("attributeName", "fill"));
    colorAnimation.append(this.wrapVarInQuotes("from",
        startShapeColor));
    colorAnimation.append(this.wrapVarInQuotes("to",
        endShapeColor));
    colorAnimation.append(this.wrapVarInQuotes("fill", "freeze"));

    toReturn.add(colorAnimation.append("/>").toString());

    if (this.loopBack &&
        !this.resetChecks.get(a.getShapeName()).get(2)) {
      StringBuilder colorReset = new StringBuilder();

      colorReset.append(this.resetAnimation("set", "fill", startShapeColor));

      toReturn.add(colorReset.toString());

      ArrayList<Boolean> resetArr = this.resetChecks.get(a.getShapeName());
      resetArr.set(2, Boolean.TRUE);
      this.resetChecks.put(a.getShapeName(), resetArr);
    }

    return toReturn;
  }

  private String resetAnimation(String animSet, String attributeName, Object value) {
    StringBuilder toRet = new StringBuilder();

    toRet.append("<" + animSet + " " + this.wrapVarInQuotes("attributeType", "xml"));
    toRet.append(this.wrapVarInQuotes("begin", "base.end"));
    toRet.append(this.wrapVarInQuotes("dur", this.RESET_CONST));
    toRet.append(this.wrapVarInQuotes("attributeName", attributeName));
    toRet.append(this.wrapVarInQuotes("to", value.toString()));
    toRet.append(this.wrapVarInQuotes("fill", "freeze"));
    toRet.append("/>");

    return toRet.toString();
  }

  /**
   * Produces the SVG representation of a variable and its value.
   * @param name      name of the variable
   * @param val       value of the variable
   * @return    the SVG representation of a variable and its value
   */
  private String wrapVarInQuotes(String name, Object val) {
    return name + "=\"" + val.toString() + "\" ";
  }

  /**
   * Produces the meta information of a shape in SVG format.
   * @param a       the animation to grab shape information from
   * @return        the meta information of a shape in SVG format
   */
  private List<String> getShapeWrapper(IAnimation a) {
    List<String> toReturn = new ArrayList<String>();
    StringBuilder thisShapeAsSvg = new StringBuilder();

    String shapeName = a.getShapeName();
    String shapeType = a.getInitialShape().getType();
    int shapeXPos = (int) a.getInitialShape().getAnchor().getXCoord();
    int shapeYPos = (int) a.getInitialShape().getAnchor().getYCoord();
    int shapeXSize = (int) a.getInitialShape().getXSize();
    int shapeYSize = (int) a.getInitialShape().getYSize();
    Color color = a.getInitialShape().getColor();
    String shapeColor = "rgb("
        + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    String shapeStartVisibility = "";
    if (a.getStartTime() == 0) {
      shapeStartVisibility = "visible";
    } else {
      shapeStartVisibility = "hidden";
    }

    thisShapeAsSvg.append("<" + shapeType + " ");
    thisShapeAsSvg.append(this.wrapVarInQuotes("id", shapeName));

    if (shapeType.equals("ellipse")) {
      thisShapeAsSvg.append(this.wrapVarInQuotes("cx", shapeXPos));
      thisShapeAsSvg.append(this.wrapVarInQuotes("cy", shapeYPos));
      thisShapeAsSvg.append(this.wrapVarInQuotes("rx", shapeXSize));
      thisShapeAsSvg.append(this.wrapVarInQuotes("ry", shapeYSize));
    } else if (shapeType.equals("rect")) {
      thisShapeAsSvg.append(this.wrapVarInQuotes("x", shapeXPos));
      thisShapeAsSvg.append(this.wrapVarInQuotes("y", shapeYPos));
      thisShapeAsSvg.append(this.wrapVarInQuotes("width", shapeXSize));
      thisShapeAsSvg.append(this.wrapVarInQuotes("height", shapeYSize));
    }

    thisShapeAsSvg.append(this.wrapVarInQuotes("fill", shapeColor));
    thisShapeAsSvg.append(
        this.wrapVarInQuotes("visibility", shapeStartVisibility));
    thisShapeAsSvg.append(">");

    StringBuilder thisShapeVisibility = new StringBuilder();

    thisShapeVisibility.append("<set ");
    thisShapeVisibility.append(this.wrapVarInQuotes("attributeType", "xml"));
    thisShapeVisibility.append(
            this.wrapVarInQuotes("begin",
                this.formatBegin((int)((double)a.getStartTime() / ticksPerSecond * 1000) + "ms")));
    thisShapeVisibility.append(
            this.wrapVarInQuotes("dur",
                (int)((double)(a.getEndTime() - a.getStartTime()) / ticksPerSecond * 1000) + "ms"));

    thisShapeVisibility.append(this.wrapVarInQuotes("to", "visible"));
    thisShapeVisibility.append(this.wrapVarInQuotes("fill", "freeze"));
    thisShapeVisibility.append(this.wrapVarInQuotes("attributeName", "visibility"));

    thisShapeVisibility.append("/>");

    toReturn.add(thisShapeAsSvg.toString());
    toReturn.add(thisShapeVisibility.toString());
    toReturn.add("</" + shapeType + ">");

    StringBuilder resetVisibility = new StringBuilder();
    resetVisibility.append(this.resetAnimation("set", "visibility", "hidden"));
    toReturn.add(resetVisibility.toString());

    return toReturn;
  }

  /**
   * Produces the default SVG animation info.
   * @param a     the animation to get information from
   * @return      the default SVG animation info
   */
  private String defaultAnimateInfo(IAnimation a) {
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("<animate ");
    toReturn.append(this.wrapVarInQuotes("attributeType", "xml"));
    toReturn.append(
        this.wrapVarInQuotes("begin",
            this.formatBegin((int)((double)a.getStartTime() / ticksPerSecond * 1000) + "ms")));
    toReturn.append(
        this.wrapVarInQuotes("dur",
            (int)((double)(a.getEndTime() - a.getStartTime()) / ticksPerSecond * 1000) + "ms"));

    return toReturn.toString();
  }

  /**
   * Produces the loopback setup SVG information.
   * @param model           the given model to grab information from
   * @param ticksPerSec     the desired ticks per second to run the SVG
   * @return        the loopback setup SVG information
   */
  private String loopBackSetup(IViewModel model, int ticksPerSec) {
    StringBuilder toReturn = new StringBuilder();

    toReturn.append("<rect>");
    toReturn.append("<animate " + this.wrapVarInQuotes("id", "base"));
    toReturn.append(this.wrapVarInQuotes("begin", "0;base.end"));
    toReturn.append(this.wrapVarInQuotes("dur", (int)(model.getEndTick()
            / (double)ticksPerSec * 1000) + "ms"));
    toReturn.append(this.wrapVarInQuotes("attributeName", "visibility"));
    toReturn.append(this.wrapVarInQuotes("from", "hide"));
    toReturn.append(this.wrapVarInQuotes("to", "hide"));
    toReturn.deleteCharAt(toReturn.length() - 1); // remove the extra space at the end of hide
    toReturn.append("/>");
    toReturn.append("</rect>\n\n");

    return toReturn.toString();
  }

  private String formatBegin(Object arg) {
    if (this.loopBack) {
      return "base.begin+" + arg;
    } else {
      return arg.toString();
    }
  }
}
