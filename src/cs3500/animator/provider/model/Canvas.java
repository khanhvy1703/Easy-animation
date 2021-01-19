package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * The class represents the canvas our animation will work on.
 */
public class Canvas {

  private final int leftMost;
  private final int topMost;
  private final int width;
  private final int height;

  /**
   * Create a new canvas based on the given attributes of a canvas.
   *
   * @param leftMost the left-most of this canvas
   * @param topMost  the top-most of this canvas
   * @param width    the width of this canvas
   * @param height   the height of this canvas
   */
  public Canvas(int leftMost, int topMost, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("The width and height of a canvas must be positive.");
    }
    this.leftMost = leftMost;
    this.topMost = topMost;
    this.width = width;
    this.height = height;
  }

  /**
   * Create a new canvas based on the given canvas.
   *
   * @param c the exact canvas we want to build
   */
  public Canvas(Canvas c) {
    this.leftMost = c.leftMost;
    this.topMost = c.topMost;
    this.width = c.width;
    this.height = c.height;
  }

  @Override
  public String toString() {
    return this.leftMost + " " + this.topMost + " " + this.width + " " + this.height;
  }

  @Override
  public int hashCode() {
    return this.leftMost + this.topMost;
  }

  @Override
  public boolean equals(Object o) {
    Objects.requireNonNull(o);

    if (o instanceof Canvas) {
      Canvas other = (Canvas) o;
      return this.leftMost == other.leftMost && this.topMost == other.topMost
          && this.width == other.width && this.height == other.height;
    } else {
      return false;
    }
  }

  /**
   * Get the location of the left border of this canvas.
   *
   * @return the x-value of the left border of the canvas
   */
  public int getLeftMost() {
    return this.leftMost;
  }

  /**
   * Get the location of the top border of this canvas.
   *
   * @return the y-value of the top border of the canvas
   */
  public int getTopMost() {
    return this.topMost;
  }

  /**
   * Get the width of this canvas.
   *
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of this canvas.
   *
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }
}
