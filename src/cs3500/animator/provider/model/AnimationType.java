package cs3500.animator.provider.model;

/**
 * Lists the types of animations that can be added to AnimatedShapes.
 */
public enum AnimationType {
  MOVE("moves"), CHANGE_COLOR("changes color"), SCALE("scales from"), NON_ANIMATION("");

  private final String description;

  /**
   * Constructs the enum values with a given description string used to create textual description.
   * @param desc the text description of the enum value
   * @throws IllegalArgumentException if the description is null
   */
  AnimationType(final String desc) throws IllegalArgumentException {
    if (desc == null)  {
      throw new IllegalArgumentException("desc must be non-null");
    }

    this.description = desc;
  }

  /**
   * Retrieves the text description of the enum value.
   * @return a string representing the enum value as text
   */
  public String getDescription() {
    return this.description;
  }
}
