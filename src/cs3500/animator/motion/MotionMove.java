package cs3500.animator.motion;

import cs3500.animator.tools.Position;

/**
 * Motion Move class that extends the AMotion. It contains the name, from Position and to Position
 * to execute the motion when called.
 */
public class MotionMove extends AMotion {

  private Position fromPosition;
  private final Position toPosition;
  private final String name;

  /**
   * Constructor that has 3 parameters, which are the startTime, endTime and the toPositio .
   *
   * @param startTime  the startTime
   * @param endTime    the end Time
   * @param toPosition the position.
   */
  public MotionMove(int startTime, int endTime, Position toPosition) {
    super(startTime, endTime);
    this.fromPosition = new Position(0, 0);
    this.toPosition = toPosition;
    this.name = "Motion Move";
  }

  /**
   * getter method of the from position.
   *
   * @return the from color.
   */
  public Position getFromPosition() {
    return fromPosition;
  }

  /**
   * getter method of the from position.
   *
   * @return the from color.
   */
  public Position getToPosition() {
    return toPosition;
  }

  /**
   * setter method of the from position.
   */
  public void setFromPosition(Position position) {
    this.fromPosition = position;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getEndState() {
    StringBuilder st = new StringBuilder();
    int tX = this.getToPosition().getX();
    int tY = this.getToPosition().getY();
    st.append(String.format("%d %d ",tX, tY));

    return st.toString();
  }
}
