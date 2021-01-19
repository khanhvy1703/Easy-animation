
import static org.junit.Assert.assertEquals;

import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import org.junit.Test;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Tests for AMotion.
 */
public class AMotionTest {

  IMotion move = new MotionMove(1,4,new Position(2,6));
  IMotion dimension = new MotionDimension(3,6,new Dimension(1,3));
  IMotion color = new MotionColor(3,6,new Color(1,3,5));

  @Test
  public void getterStart() {
    assertEquals(1, move.getStartTime());
    assertEquals(3, dimension.getStartTime());
    assertEquals(3, color.getStartTime());
  }

  @Test
  public void getterEnd() {
    assertEquals(4, move.getEndTime());
    assertEquals(6, dimension.getEndTime());
    assertEquals(6, color.getEndTime());
  }

  @Test
  public void duration() {
    assertEquals(3, move.getDuration());
    assertEquals(3, dimension.getDuration());
    assertEquals(3, color.getDuration());
  }

  @Test
  public void getterName() {
    assertEquals("Motion Move", move.getName());
    assertEquals("Motion Dimension", dimension.getName());
    assertEquals("Motion Color", color.getName());
  }

}