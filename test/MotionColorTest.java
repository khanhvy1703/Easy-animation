import static org.junit.Assert.assertEquals;

import cs3500.animator.motion.MotionColor;
import org.junit.Test;
import cs3500.animator.tools.Color;

/**
 * Tests for MotionColor.
 */
public class MotionColorTest {

  MotionColor color = new MotionColor(3, 6, new Color(1, 3, 5));

  @Test
  public void getterFromColor() {
    assertEquals(new Color(0, 0, 0), color.getFromColor());
  }

  @Test
  public void getterToColor() {
    assertEquals(new Color(1, 3, 5), color.getToColor());
  }

  @Test
  public void setterFromColor() {
    color.setFromColor(new Color(200, 200, 20));
    assertEquals(new Color(200, 200, 20), color.getFromColor());
  }

  @Test
  public void getterName() {
    assertEquals("Motion Color", color.getName());
  }
}