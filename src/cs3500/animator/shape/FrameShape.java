package cs3500.animator.shape;

import java.util.Objects;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * A copy class of AModelShape that just takes Position, Dimension, Color and tick.
 */
public class FrameShape implements Comparable {

  private Position p;
  private Dimension d;
  private Color c;
  private final int tick;

  /**
   * Constructor of the Frame shape.
   *
   * @param tick tick
   */
  public FrameShape(int tick) {
    this.p = new Position(0, 0);
    this.d = new Dimension(0, 0);
    this.c = new Color(0, 0, 0);
    this.tick = tick;
  }

  /**
   * setter method for the frame shape which takes position, dimension, and color and change the
   * information of the frame shape.
   *
   * @param p position
   * @param d dimension
   * @param c color
   */
  public void setCopyShapeUlti(Position p, Dimension d, Color c) {
    this.p = p;
    this.d = d;
    this.c = c;
  }

  /**
   * Getter method to get the Color of the frame shape.
   *
   * @return color
   */
  public Color getC() {
    return c;
  }

  /**
   * Getter method to get the dimension of the frame shape.
   *
   * @return dimension
   */
  public Dimension getD() {
    return d;
  }

  /**
   * Getter method to get the position of the frame shape.
   *
   * @return position
   */
  public Position getP() {
    return p;
  }

  /**
   * Getter method to get the position of the frame shape.
   *
   * @return tick
   */
  public int getFrameTick() {
    return tick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof FrameShape)) {
      return false;
    }

    FrameShape that = (FrameShape) o;
    return this.tick == that.tick;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tick, this.p, this.d, this.c);
  }

  @Override
  public int compareTo(Object o) {
    FrameShape fs = (FrameShape) o;
    return this.tick - fs.tick;
  }
}
