package cs3500.animator.view.graphics;

import javax.swing.*;

/**
 * Represents a button that toggles functionality.
 */
public class ToggleButton extends JButton {
  private boolean active = false;
  private final String textWhenActive;
  private final String textWhenInactive;

  /**
   * Creates a new {@code ToggleButton} object.
   * @param textWhenActive is the text displayed when the functionality is active.
   * @param textWhenInactive is the text displayed when the functionality is inactive.
   */
  public ToggleButton(String textWhenActive, String textWhenInactive) {
    super(textWhenInactive);
    this.textWhenActive = textWhenActive;
    this.textWhenInactive = textWhenInactive;
  }

  /**
   * Toggle this button's text.
   */
  public void toggle() {
    this.active = !this.active;
    this.setText(this.active ? this.textWhenActive : this.textWhenInactive);
  }
}
