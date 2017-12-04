package cs3500.animator.controller;

public interface IAnimationController {

  /**
   * Process a given command. Return value is like in C, C++. -1 if error, >= 0 if otherwise.
   * @param cmd   given command to execute
   * @return -1 if error, >= otherwise
   */
  int processCommand(String cmd);

  void start(int initialTicksPerSec);
}
