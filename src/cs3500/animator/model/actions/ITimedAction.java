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
   * Runs the final step of this action.
   * e.g. executes the exact final changes to the target,
   * putting it in the exact desired final state.
   */
  void executeFinal();

  /**
   * Converts this action into a string.
   * @param ticksPerSecond is the number of ticks that occur per second.
   * @return the String representing this action.
   */
  String toString(double ticksPerSecond);

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
}
