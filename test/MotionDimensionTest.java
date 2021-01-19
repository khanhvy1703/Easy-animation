
import static org.junit.Assert.assertEquals;

import cs3500.animator.motion.MotionDimension;
import org.junit.Test;
import cs3500.animator.tools.Dimension;

/**
 * Test for the Dimension Motion.
 */
public class MotionDimensionTest {

  MotionDimension dimension = new MotionDimension(3, 6,
      new Dimension(1, 3));

  @Test
  public void getterFromDimension() {
    assertEquals(new Dimension(0, 0), dimension.getFromDimension());
  }

  @Test
  public void getterToDimension() {
    assertEquals(new Dimension(1, 3), dimension.getToDimension());
  }

  @Test
  public void setterFromDimension() {
    dimension.setFromDimension(new Dimension(3, 3));
    assertEquals(new Dimension(3, 3), dimension.getFromDimension());
  }

  @Test
  public void getterName() {
    assertEquals("Motion Dimension", dimension.getName());
  }
}