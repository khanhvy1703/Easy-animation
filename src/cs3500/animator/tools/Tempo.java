package cs3500.animator.tools;

import java.util.Objects;

/**
 * Class that represent Tempo.
 */
public class Tempo {

  private int start;
  private int end;
  private int speed;

  /**
   * Constructor for the tempo class.
   *
   * @param start start time of the tempo
   * @param end   end time of the tempo
   * @param speed speed
   */
  public Tempo(int start, int end, int speed) {
    if (end - start < 1) {
      throw new IllegalArgumentException("start time and end time is not in the right form");
    }
    this.start = start;
    this.end = end;
    this.speed = speed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Tempo)) {
      return false;
    }

    Tempo that = (Tempo) o;
    return this.start == that.start && this.end == that.end && this.speed == that.speed;
  }

  public int hashCode() {
    return Objects.hash(this.start, this.end, this.speed);
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd() {
    return this.end;
  }

  public int getSpeed() {
    return this.speed;
  }
}
