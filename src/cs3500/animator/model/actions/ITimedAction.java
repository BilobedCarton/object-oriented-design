package cs3500.animator.model.actions;

/**
 * Represents an action that takes place over time.
 * Could be for an animation, could be for game, etc.
 */
public interface ITimedAction {

  /**
   * Runs a step of this action.
   * e.g. All that should be done in one tick of the overarching program.
   * So an action that takes place over ten ticks will execute ten times.
   */
  void execute();

  /**
   * Converts this action into a string.
   * @return the String representing this action.
   */
  String toString();

  /**
   * Gets the int representing the starting tick of this action.
   * @return the int that is the starting tick.
   */
  int getStartTick();

  /**
   * Gets the int representing the ending tick of this action.
   * @return the int that is the ending tick.
   */
  int getEndTick();

  /**
   * Gets the int representing the number of ticks that happen per second.
   * @return the double representing the ticks per second.
   */
  double getTicksPerSecond();

  /**
   * Sets the ticks per second of this action to the given value.
   * @param ticksPerSecond is the double representing the new ticks per second.
   * @throws IllegalArgumentException if ticksPerSecond <= 0.
   */
  void setTicksPerSecond(double ticksPerSecond) throws IllegalArgumentException;
}
