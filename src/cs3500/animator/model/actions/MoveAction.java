package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Shape;

/**
 * This represents a movement action in an animation.
 */
public class MoveAction extends AnimationAction {
  private final double targetX;
  private final double targetY;
  private double originalX;
  private double originalY;

  /**
   * Creates a new {@code MoveAction} object.
   * @param timeStart is the time this action begins.
   * @param timeEnd is the time this action ends.
   * @param shape is the shape this action executes upon.
   * @param targetX is the target x coord the shape should be moved to.
   * @param targetY is the target y coord the shape should be moved to.
   */
  MoveAction(int timeStart, int timeEnd, Shape shape, double targetX,
             double targetY) {
    super(timeStart, timeEnd, shape);
    this.targetX = targetX;
    this.targetY = targetY;
    this.originalX = shape.getPosX();
    this.originalY = shape.getPosY();
  }

  @Override
  public void execute() {
    this.getShape().relocate(
            this.getShape().getPosX()
                    + ((this.targetX - this.originalX) / (this.getEndTick() - this.getStartTick())),
            this.getShape().getPosY()
                    + ((this.targetY - this.originalY)
                    / (this.getEndTick() - this.getStartTick())));
  }


  @Override
  public void executeFinal() {
    this.getShape().relocate(this.targetX, this.targetY);
  }

  @Override
  public String toString(double ticksPerSecond) {
    return "Shape " + this.getShape().getName() + " moves from (" + this.getShape().getPosX() + ","
            + this.getShape().getPosX() + ") to (" + this.targetX + "," + this.targetY + ") from t="
            + this.getStartTick() / ticksPerSecond + "s to t="
            + this.getEndTick() / ticksPerSecond + "s\n";
  }

  @Override
  public void updateOriginalValues() {
    this.originalX = this.getShape().getPosX();
    this.originalY = this.getShape().getPosY();
  }

  @Override
  public void setOriginalValues(Shape s) throws IllegalArgumentException {
    if (this.getShape().getName().equals(s.getName()) == false) {
      throw new IllegalArgumentException("ColorShiftAction.setOriginalValues(Shape) -- "
              + "This action does not execute upon the given shape.");
    }
    this.originalX = s.getPosX();
    this.originalY = s.getPosY();
  }

  /**
   * Gets the target x coordinate.
   * @return the double representing the target x coordinate.
   */
  public double getTargetX() {
    return targetX;
  }

  /**
   * Gets the target y coordinate.
   * @return the double representing the target y coordinate.
   */
  public double getTargetY() {
    return targetY;
  }

  @Override
  public String toSVG(double ticksPerSecond){
    updateOriginalValues();
    String xChar;
    String yChar;
    switch(getShape().getType()) {
      case "rect":
        xChar = "x";
        yChar = "y";
        break;
      case "oval":
        xChar = "cx";
        yChar = "cy";
        break;
      default:
        throw new IllegalArgumentException("invalid shape type");
    }
    String retString = "\t<animate attributeType=\"xml\" begin=\""
            + (getStartTick()/ticksPerSecond*1000) +"ms\" dur=\""
            + ((getEndTick()- getStartTick())/ticksPerSecond*1000)+"ms\" attributeName=\"" + xChar
            + "\" from=\"" + originalX + "\" to=\"" + getTargetX() +"\" fill=\"freeze\" />\n";
    retString += "\t<animate attributeType=\"xml\" begin=\"" + (getStartTick()/ticksPerSecond*1000)
            +"ms\" dur=\"" + ((getEndTick()- getStartTick())/ticksPerSecond*1000)+"ms\" "
            +"attributeName=\"" + yChar + "\" from=\"" + originalY + "\" to=\"" + getTargetY()
            +"\" fill=\"freeze\" />\n";
    executeFinal();
    return retString;
  }
}
