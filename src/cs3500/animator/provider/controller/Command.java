package cs3500.animator.controller;

public enum Command {
  TOGGLE_PLAYBACK("togglePlayback"), TOGGLE_LOOPBACK("toggleLoopback"), RESTART("restart"),
  SELECT_SHAPES("selectShapes"), GET_SVG("getSvg");

  private final String CMD_CODE;

  Command(final String code) {
    if (code == null) {
      throw new IllegalArgumentException("cmd code must be non-null");
    }

    this.CMD_CODE = code;
  }

  public String getCode() {
    return this.CMD_CODE;
  }
}
