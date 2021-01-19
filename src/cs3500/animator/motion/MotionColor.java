package cs3500.animator.motion;

import cs3500.animator.tools.Color;

/**
 * Motion Color class that extends the AMotion. It contains the name, from Color and to Color to
 * execute the motion when called.
 */
public class MotionColor extends AMotion {

  private Color fromColor;
  private Color toColor;
  private String name;

  /**
   * Constructor that has 3 parameters, which are the startTime, endTime and the toColor.
   *
   * @param startTime the startTime
   * @param endTime   the end Time
   * @param toColor   the color.
   */
  public MotionColor(int startTime, int endTime, Color toColor) {
    super(startTime, endTime);
    this.fromColor = new Color(0, 0, 0);
    this.toColor = toColor;
    this.name = "Motion Color";
  }

  /**
   * getter method of the from color.
   *
   * @return the from color.
   */
  public Color getFromColor() {
    return fromColor;
  }

  /**
   * getter method of the to color.
   *
   * @return the from color.
   */
  public Color getToColor() {
    return toColor;
  }

  /**
   * setter method of the from color.
   */
  public void setFromColor(Color fromColor) {
    this.fromColor = fromColor;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getEndState() {
    StringBuilder st = new StringBuilder();

    int tR = this.getToColor().getRed();
    int tG = this.getToColor().getGreen();
    int tB = this.getToColor().getBlue();
    st.append(String.format("%d %d %d\n", tR, tG, tB));

    return st.toString();
  }
}
