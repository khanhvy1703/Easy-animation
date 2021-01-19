package cs3500.animator.tools;

import java.util.Objects;

/**
 * Class that represent Position of the model built with x coordinate and y coordinate of shape.
 * This object will used to represent the shape's position later in the animation and its view.
 */
public class Position {

  private final int x;
  private final int y;

  /**
   * Constructor of the Position.
   *
   * @param x x coordinate of the Shape.
   * @param y y coordinate of the Shape.
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Position)) {
      return false;
    }

    Position that = (Position) o;
    return this.x == that.x && this.y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }


  /**
   * method to get x coordinate of the shape.
   *
   * @return x coordinate of the shape.
   */
  public int getX() {
    return this.x;
  }

  /**
   * method to get y coordinate of the shape.
   *
   * @return y coordinate of the shape
   */
  public int getY() {
    return this.y;
  }
}
