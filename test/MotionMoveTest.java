

import static org.junit.Assert.assertEquals;

import cs3500.animator.motion.MotionMove;
import org.junit.Test;
import cs3500.animator.tools.Position;

/**
 * Tests for Motion Move.
 */
public class MotionMoveTest {
  MotionMove move = new MotionMove(1,4,new Position(2,6));

  @Test
  public void getterFromColor() {
    assertEquals(new Position(0,0), move.getFromPosition());
  }

  @Test
  public void getterToColor() {
    assertEquals(new Position(2,6), move.getToPosition());
  }

  @Test
  public void setterFromColor() {
    move.setFromPosition(new Position(34,556));
    assertEquals(new Position(34,556), move.getFromPosition());
  }

  @Test
  public void getterName() {
    assertEquals("Motion Move", move.getName());
  }

}