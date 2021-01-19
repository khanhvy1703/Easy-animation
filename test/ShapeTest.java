import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import org.junit.Before;
import org.junit.Test;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.shape.Ellipse;
import cs3500.animator.shape.FrameShape;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;


/**
 * Class that represent tests for Shape classes. Shape classes are AModelShape, Ellipse, Rectangle
 * and FrameShape.
 */
public class ShapeTest {

  Position position1;
  Dimension dimension1;
  Color color1;
  Position position2;
  Dimension dimension2;
  Color color2;
  AModelShape initialEllipse;
  AModelShape initialRectangle;
  AModelShape ellipse;
  AModelShape rectangle;
  FrameShape frameShape;
  IMotion motionPosition;
  IMotion motionDimension;
  IMotion motionColor;
  IMotion malMotionPosition;
  IMotion malMotionDimension;
  IMotion malMotionColor;
  int[] integers1;
  int[] integers2;


  /**
   * Setup for initial conditions for the test examples.
   */
  @Before
  public void setUp() {
    position1 = new Position(40, 70);
    dimension1 = new Dimension(100, 150);
    color1 = new Color(30, 60, 90);
    position2 = new Position(50, 80);
    dimension2 = new Dimension(120, 150);
    color2 = new Color(60, 80, 100);
    initialEllipse = new Ellipse("E1");
    initialRectangle = new Rectangle("R1");
    frameShape = new FrameShape(10);
    integers1 = new int[]{40, 70, 100, 150, 30, 60, 90};
    integers2 = new int[]{50, 80, 120, 150, 60, 80, 100};

    ellipse = new AModelShape(position1, dimension1, color1, "E2") {
      @Override
      public String getType() {
        return "Ellipse";
      }
    };
    rectangle = new AModelShape(position2, dimension2, color2, "R2") {
      @Override
      public String getType() {
        return "Rectangle";
      }
    };
    motionPosition = new MotionMove(1, 3, new Position(10, 20));
    motionDimension = new MotionDimension(4, 8, new Dimension(40, 50));
    motionColor = new MotionColor(10, 14, new Color(3, 7, 9));
  }

  /**
   * A Test case: check if the getter functions for shape are working properly.
   */
  @Test
  public void getterMethodTest() {
    // Getter test for getType.
    assertEquals(initialEllipse.getType(), "Ellipse");
    assertEquals(initialRectangle.getType(), "Rectangle");
    assertEquals(ellipse.getType(), "Ellipse");
    assertEquals(rectangle.getType(), "Rectangle");
    // Getter test for getName.
    assertEquals(initialEllipse.getName(), "E1");
    assertEquals(initialRectangle.getName(), "R1");
    assertEquals(ellipse.getName(), "E2");
    assertEquals(rectangle.getName(), "R2");
    // Getter test for getPosition.
    assertEquals(initialEllipse.getPosition(), new Position(0, 0));
    assertEquals(initialRectangle.getPosition(), new Position(0, 0));
    assertEquals(ellipse.getPosition(), position1);
    assertEquals(rectangle.getPosition(), position2);
    // Getter test for getDimension.
    assertEquals(initialEllipse.getDimension(), new Dimension(0, 0));
    assertEquals(initialRectangle.getDimension(), new Dimension(0, 0));
    assertEquals(ellipse.getDimension(), dimension1);
    assertEquals(rectangle.getDimension(), dimension2);
    // Getter test for getColor.
    assertEquals(initialEllipse.getColor(), new Color(0, 0, 0));
    assertEquals(initialRectangle.getColor(), new Color(0, 0, 0));
    assertEquals(ellipse.getColor(), color1);
    assertEquals(rectangle.getColor(), color2);
    // Getter test for getGivenTick.
    assertEquals(initialEllipse.getGivenTick(), 0);
    assertEquals(initialRectangle.getGivenTick(), 0);
    assertEquals(ellipse.getGivenTick(), 0);
    assertEquals(rectangle.getGivenTick(), 0);
    // Getter test for getMotions.
    assertEquals(initialEllipse.getMotions(), new ArrayList<>());
    assertEquals(initialRectangle.getMotions(), new ArrayList<>());
    assertEquals(ellipse.getMotions(), new ArrayList<>());
    assertEquals(rectangle.getMotions(), new ArrayList<>());
    // Getter test for getIntegers.
    assertEquals(initialEllipse.getIntegers(), null);
    assertEquals(initialRectangle.getIntegers(), null);
    assertEquals(ellipse.getIntegers()[1], 70);
    // Getter test for getVisible.
    assertEquals(initialEllipse.getVisible(), false);
    assertEquals(initialRectangle.getVisible(), false);
    assertEquals(ellipse.getVisible(), false);
    assertEquals(rectangle.getVisible(), false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void exceptionTestAddMotion() {
    malMotionPosition = new MotionMove(1, 3, new Position(10, 20));
    malMotionDimension = new MotionDimension(3, 8, new Dimension(40, 50));
    malMotionColor = new MotionColor(1, 9, new Color(2, 3, 4));
    initialEllipse.addMotion(malMotionDimension);
    initialEllipse.addMotion(malMotionPosition);
    initialEllipse.addMotion(malMotionColor);
  }


  /**
   * A Test case: check if the addMotion functions for shape are working properly.
   */
  @Test
  public void testAddMotion() {
    initialEllipse.addMotion(motionPosition);
    assertEquals(initialEllipse.getMotions().get(0).getStartTime(), 1);
    initialRectangle.addMotion(motionColor);
    assertEquals(initialRectangle.getMotions().get(0).getStartTime(), 10);
    ellipse.addMotion(motionDimension);
    assertEquals(ellipse.getMotions().get(0).getStartTime(), 4);
  }

  /**
   * A Test case: check if the setModelShape functions for shape are working properly.
   */
  @Test
  public void testSetModelShape() {
    initialEllipse.setAModelShape(position1, dimension1, color1, 5);
    assertEquals(initialEllipse.getPosition(), position1);
    assertEquals(initialEllipse.getDimension(), dimension1);
    assertEquals(initialEllipse.getColor(), color1);
    assertEquals(initialEllipse.getGivenTick(), 5);
    assertEquals(initialEllipse.getIntegers()[3], 150);
  }

  @Test
  public void applyTest() {
    initialEllipse.addMotion(motionPosition);
    initialEllipse.addMotion(motionColor);
    initialEllipse.apply(3);
    assertEquals(initialEllipse.getPosition(), new Position(10, 20));
    assertEquals(initialEllipse.getColor(), new Color(3, 7, 9));
  }


  @Test
  public void applyMotionTest() {
    initialEllipse.addMotion(motionPosition);
    initialEllipse.addMotion(motionColor);
    initialEllipse.applyMotion(motionPosition, 3);
    assertEquals(initialEllipse.getPosition(), new Position(15, 30));
    assertEquals(initialEllipse.getColor(), new Color(3, 7, 9));
  }


  @Test
  public void applyFrameShapeTest() {
    initialEllipse.addMotion(motionPosition);
    initialEllipse.addMotion(motionColor);
    initialEllipse.applyFrameShape(3);
    assertEquals(initialEllipse.getPosition(), new Position(10, 20));
    assertEquals(initialEllipse.getColor(), new Color(0, 0, 0));
  }

  /**
   * A Test case: check if the functions related to frameShape are working properly. The methods are
   * getP, getD, getC, getFrameTick, setCopyShapeUlti.
   */
  @Test
  public void frameShapeTest() {
    frameShape.setCopyShapeUlti(position1, dimension1, color1);
    assertEquals(frameShape.getP(), position1);
    assertEquals(frameShape.getD(), dimension1);
    assertEquals(frameShape.getC(), color1);
    assertEquals(frameShape.getFrameTick(), 10);
  }

  @Test
  public void getLastTickTest() {
    AModelShape e = new Ellipse("E");
    AModelShape r = new Rectangle("R");
    IMotion move = new MotionMove(1,4,new Position(40, 60));
    IMotion size = new MotionDimension(5,9, new Dimension(456,35));
    IMotion color = new MotionColor(5,30, new Color(0,0,0));
    e.addMotion(move);
    e.addMotion(size);
    r.addMotion(move);
    r.addMotion(color);
    assertEquals(30,r.getLastTick());
    assertEquals(9, e.getLastTick());
  }
}
