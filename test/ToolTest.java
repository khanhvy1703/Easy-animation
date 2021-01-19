import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Class that represent tests for tool classes. Tool classes are Position, Dimension, and Color.
 */
public class ToolTest {

  Position p1;
  Position p2;
  Position p3;
  Dimension d1;
  Dimension d2;
  Dimension d3;
  Color red;
  Color green;
  Color blue;

  /**
   * Setup for initial conditions for the test examples (position, dimension, color).
   */
  @Before
  public void setUp() {
    p1 = new Position(3, 4);
    p2 = new Position(10, 15);
    p3 = new Position(100, 200);
    d1 = new Dimension(10, 20);
    d2 = new Dimension(40, 40);
    d3 = new Dimension(100, 10);
    red = new Color(100, 0, 0);
    green = new Color(0, 100, 0);
    blue = new Color(0, 0, 100);
  }

  /**
   * A Test case: When constructor for tool is not well constructed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor() {
    Dimension malDimension = new Dimension(-5, -10);
    Color malColor1 = new Color(-5, -10, -15);
    Color malColor2 = new Color(300, 400, 500);
  }

  /**
   * A Test case: check if the getter functions for Position are working properly.
   */
  @Test
  public void getterPositionTest() {
    assertEquals(p1.getX(), 3);
    assertEquals(p1.getY(), 4);
    assertEquals(p2.getX(), 10);
    assertEquals(p2.getY(), 15);
    assertEquals(p3.getX(), 100);
    assertEquals(p3.getY(), 200);
  }

  /**
   * A Test case: check if the getter functions for Dimension are working properly.
   */
  @Test
  public void getterDimensionTest() {
    assertEquals(d1.getWidth(), 10);
    assertEquals(d1.getHeight(), 20);
    assertEquals(d2.getWidth(), 40);
    assertEquals(d2.getHeight(), 40);
    assertEquals(d3.getWidth(), 100);
    assertEquals(d3.getHeight(), 10);
  }

  /**
   * A Test case: check if the getter functions for Color are working properly.
   */
  @Test
  public void getterColorTest() {
    assertEquals(red.getRed(), 100);
    assertEquals(red.getGreen(), 0);
    assertEquals(red.getBlue(), 0);
    assertEquals(green.getRed(), 0);
    assertEquals(green.getGreen(), 100);
    assertEquals(green.getBlue(), 0);
    assertEquals(blue.getRed(), 0);
    assertEquals(blue.getGreen(), 0);
    assertEquals(blue.getBlue(), 100);
  }

  /**
   * A Test case: check if the equals function for each tools are working properly.
   */
  @Test
  public void equalsTest() {
    assertTrue(p1.equals(new Position(3, 4)));
    assertFalse(p2.equals(new Dimension(1, 1)));
    assertTrue(p3.equals(new Position(100, 200)));
    assertTrue(d1.equals(new Dimension(10, 20)));
    assertFalse(d2.equals(new Position(50, 50)));
    assertTrue(d3.equals(new Dimension(100, 10)));
    assertTrue(red.equals(new Color(100, 0, 0)));
    assertFalse(green.equals(new Color(0, 150, 0)));
    assertTrue(blue.equals(new Color(0, 0, 100)));
  }

  /**
   * A Test case: check if the hashcode function for Position are working properly.
   */
  @Test
  public void hashcodeTest() {
    assertEquals(p1.hashCode(), 1058);
    assertEquals(p2.hashCode(), 1286);
    assertEquals(p3.hashCode(), 4261);
    assertEquals(d1.hashCode(), 1291);
    assertEquals(d2.hashCode(), 2241);
    assertEquals(d3.hashCode(), 4071);
    assertEquals(red.hashCode(), 125891);
    assertEquals(green.hashCode(), 32891);
    assertEquals(blue.hashCode(), 29891);
  }
}
