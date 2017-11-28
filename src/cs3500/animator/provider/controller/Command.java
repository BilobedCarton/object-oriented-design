package cs3500.animator.provider.controller;

/**
 * Enum to represent different commands that can be given to the Animation.
 * Commands were created into an ENUM to make sharing functionality accross classes easier and
 * more flexible and resilient.
 */
public enum Command {
  TOGGLE_PLAYBACK("togglePlayback"), TOGGLE_LOOPBACK("toggleLoopback"), RESTART("restart"),
  SELECT_SHAPES("selectShapes"), GET_SVG("getSvg");

  private final String CMD_CODE;

  /**
   * Creates a command with the given code.
   * @param code        desired command code
   */
  Command(final String code) {
    if (code == null) {
      throw new IllegalArgumentException("cmd code must be non-null");
    }

    this.CMD_CODE = code;
  }

  /**
   * Produces the code of this command.
   * @return    the code of this command
   */
  public String getCode() {
    return this.CMD_CODE;
  }
}
