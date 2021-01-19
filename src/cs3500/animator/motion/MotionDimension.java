package cs3500.animator.motion;

import cs3500.animator.tools.Dimension;

/**
 * Motion Dimension class that extends the AMotion. It contains the name, from Dimension and to
 * Dimension to execute the motion when called.
 */
public class MotionDimension extends AMotion {

  private Dimension fromDimension;
  private Dimension toDimension;
  private String name;

  /**
   * Constructor that has 3 parameters, which are the startTime, endTime and the toDimension.
   *
   * @param startTime   the startTime
   * @param endTime     the end Time
   * @param toDimension the dimension.
   */
  public MotionDimension(int startTime, int endTime, Dimension toDimension) {
    super(startTime, endTime);
    this.fromDimension = new Dimension(0, 0);
    this.toDimension = toDimension;
    this.name = "Motion Dimension";
  }

  /**
   * getter method of the from dimension.
   *
   * @return the from color.
   */
  public Dimension getFromDimension() {
    return fromDimension;
  }

  /**
   * getter method of the from dimension.
   *
   * @return the from color.
   */
  public Dimension getToDimension() {
    return toDimension;
  }

  /**
   * setter method of the from dimension.
   */
  public void setFromDimension(Dimension fromDimension) {
    this.fromDimension = fromDimension;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getEndState() {
    StringBuilder st = new StringBuilder();
    int tW = this.getToDimension().getWidth();
    int tH = this.getToDimension().getHeight();
    st.append(String
        .format("%d %d ",tW, tH));

    return st.toString();
  }
}
