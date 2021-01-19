package cs3500.animator.tools;

import java.util.Objects;

/**
 * Class that represent color of the model built with standard RGB system. This object will used to
 * represent the shape's color later in the animation and its view.
 */
public class Color {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for a color object with r g b value. Each value has to be in the ranges (0 - 255).
   *
   * @param red   red value in rgb.
   * @param green green value in rgb.
   * @param blue  blue value in rgb.
   */
  public Color(int red, int green, int blue) {
    if (((red < 0 || red > 255)
        || (green < 0 || green > 255)
        || (blue < 0 || blue > 255))) {
      throw new IllegalArgumentException("It is invalid Color.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Color)) {
      return false;
    }

    Color that = (Color) o;
    return this.red == that.red && this.green == that.green && this.blue == that.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }

  /**
   * Getter method for red value of the color.
   *
   * @return red value of color.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Getter method for green value of the color.
   *
   * @return green value of color.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Getter method for blue value of the color.
   *
   * @return blue value of color.
   */
  public int getBlue() {
    return this.blue;
  }
}
