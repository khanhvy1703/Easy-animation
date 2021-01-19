package cs3500.animator.tools;

import java.util.Objects;

/**
 * Class that represent Dimension of the model built with width and height of shape. This object
 * will be used to represent the shape's dimension later in the animation and its view.
 */
public class Dimension {

  private final int width;
  private final int height;

  /**
   * Constructor of the Dimension.
   *
   * @param width  width of the Shape.
   * @param height height of the Shape.
   * @throws IllegalArgumentException throw exception when width or height are negative number.
   */
  public Dimension(int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("must be non-negative.");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Dimension)) {
      return false;
    }

    Dimension that = (Dimension) o;
    return this.width == that.width && this.height == that.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height);
  }

  /**
   * method to get width of the shape.
   *
   * @return width of the shape.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * method to get height of the shape.
   *
   * @return height of the shape.
   */
  public int getHeight() {
    return this.height;
  }

}
