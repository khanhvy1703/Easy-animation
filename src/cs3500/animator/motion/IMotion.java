package cs3500.animator.motion;

/**
 * The Interface Class of the Motions. It contains all possible motions that the shape can have.
 */
public interface IMotion {

  /**
   * Getter method of the start time.
   *
   * @return the start time
   */
  int getStartTime();

  /**
   * getter method of end time.
   *
   * @return the end time
   */
  int getEndTime();

  /**
   * returns the duration.
   *
   * @return the duration
   */
  int getDuration();

  /**
   * getter method of the name of the motion.
   *
   * @return the name
   */
  String getName();

  /**
   * get the end state of the motion.
   * @return the end state as a string.
   */
  String getEndState();
}
