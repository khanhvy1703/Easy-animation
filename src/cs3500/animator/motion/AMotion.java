package cs3500.animator.motion;

/**
 * Abstract class of the Motion, it implements the interface IMotion and takes its methods. Also it
 * contains the startTime and endTime of each motion.
 */
public abstract class AMotion implements IMotion {

  protected final int startTime;
  protected final int endTime;

  /**
   * Constructor of Abstract class that contains 2 parameters, startTime and endTime. It throws
   * IllegalArgumentException of startTime > endTime or negative numbers.
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @throws IllegalArgumentException if the startTime greater than the endTime or negative
   *                                  numbers.
   */
  public AMotion(int startTime, int endTime) {
    if ((startTime < 0) || (endTime < 0) || (startTime > endTime)) {
      throw new IllegalArgumentException("invalid time.");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public int getDuration() {

    if (startTime == endTime) {
      return 1;
    } else {
      return this.endTime - this.startTime;
    }
  }

  @Override
  public abstract String getName();

  @Override
  public abstract String getEndState();
}
