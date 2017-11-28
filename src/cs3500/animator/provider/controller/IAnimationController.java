package cs3500.animator.provider.controller;

/**
 * All concrete implementations of AnimationControllers should implement this interface.
 * This is because, at the bare minimum, any given concrete implementation of an
 * AnimationController should have a processCommand() method (to process commands) and a
 * start() method, to actually start the animation.
 */
public interface IAnimationController {

  /**
   * Process a given command. Return value is like in C, C++. -1 if error, >= 0 if otherwise.
   * @param cmd   given command to execute
   * @return -1 if error, >= otherwise
   */
  int processCommand(String cmd);

  /**
   * Starts the whole animation. Initial ticksPerSecond required.
   * @param initialTicksPerSec      initial ticks per second of the animation
   */
  void start(int initialTicksPerSec);
}
