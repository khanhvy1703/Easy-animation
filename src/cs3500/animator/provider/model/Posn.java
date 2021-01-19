package cs3500.animator.provider.model;


/**
 * A class that represents positions, where coordinates are integers.
 */
public class Posn {

  private final int x;
  private final int y;

  /**
   * Construct a position with the given x and y coordinates.
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("The given object is null.");
    }

    if (o instanceof Posn) {
      Posn other = (Posn) o;
      return this.x == other.x && this.y == other.y;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return x + y;
  }

  @Override
  public String toString() {
    return x + " " + y;
  }

  /**
   * Get the x-value of this posn.
   * @return x-value
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get the y-value of this posn.
   * @return y-value
   */
  public int getY() {
    return this.y;
  }
}
