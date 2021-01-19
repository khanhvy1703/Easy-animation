import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import org.junit.Before;
import org.junit.Test;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.shape.Ellipse;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Test for the model.
 */
public class ModelTest {

  IModel initialModel;
  IModel model;

  @Before
  public void setUp() {
    initialModel = new Model();
    model = new Model(100, 100, 200, 250);
  }

  @Test
  public void getXCanvasTest() {
    assertEquals(initialModel.getXCanvas(), 0);
    assertEquals(model.getXCanvas(), 100);
  }

  @Test
  public void getYCanvasTest() {
    assertEquals(initialModel.getYCanvas(), 0);
    assertEquals(model.getYCanvas(), 100);
  }

  @Test
  public void getWCanvasTest() {
    assertEquals(initialModel.getWCanvas(), 0);
    assertEquals(model.getWCanvas(), 200);
  }

  @Test
  public void getHCanvasTest() {
    assertEquals(initialModel.getHCanvas(), 0);
    assertEquals(model.getHCanvas(), 250);
  }

  @Test
  public void setCanvasTest() {
    initialModel.setCanvas(30, 30, 200, 200);
    model.setCanvas(50, 50, 500, 500);
    assertEquals(initialModel.getXCanvas(), 30);
    assertEquals(model.getXCanvas(), 50);
    assertEquals(initialModel.getYCanvas(), 30);
    assertEquals(model.getYCanvas(), 50);
    assertEquals(initialModel.getWCanvas(), 200);
    assertEquals(model.getWCanvas(), 500);
    assertEquals(initialModel.getHCanvas(), 200);
    assertEquals(model.getHCanvas(), 500);
  }

  @Test
  public void setAModelShapeTest() {
    model.addShape(new Ellipse("E2"));
    initialModel.addShape(new Rectangle("R6"));
    assertEquals(model.getShapes().get(0).getName(), "E2");
    assertEquals(initialModel.getShapes().get(0).getName(), "R6");
    model.setAModelShape("E2", 1, 2, 3, 4, 5, 6, 7, 8);
    initialModel.setAModelShape("R6", 8, 7, 6, 5, 4, 3, 2, 1);
    assertEquals(model.getShapes().get(0).getPosition(), new Position(1, 2));
    assertEquals(initialModel.getShapes().get(0).getColor(), new Color(4, 3, 2));
  }

  @Test
  public void isVisibleTest() {
    model.addShape(new Ellipse("E2"));
    initialModel.addShape(new Rectangle("R6"));
    model.setAModelShape("E2", 1, 2, 3, 4, 5, 6, 7, 8);
    initialModel.setAModelShape("R6", 8, 7, 6, 5, 4, 3, 2, 10);
    model.isVisible(8);
    assertEquals(model.getShapes().get(0).getVisible(), true);
    initialModel.isVisible(3);
    assertEquals(initialModel.getShapes().get(0).getVisible(), false);
  }

  @Test
  public void addGetShapeTest() {
    model.addShape(new Ellipse("E2"));
    initialModel.addShape(new Rectangle("R6"));
    assertEquals(model.getShapes().get(0).getName(), "E2");
    assertEquals(initialModel.getShapes().get(0).getName(), "R6");
  }

  @Test
  public void doActionPerformTest() {
    model.addShape(new Ellipse("E2"));
    initialModel.addShape(new Rectangle("R6"));
    model.setAModelShape("E2", 1, 2, 3, 4, 5, 6, 7, 8);
    initialModel.setAModelShape("R6", 8, 7, 6, 5, 4, 3, 2, 10);
    model.doActionPerform(3);
    initialModel.doActionPerform(11);
    assertEquals(model.getShapes().get(0).getPosition(), new Position(1, 2));
  }

  @Test
  public void getShapeTest() {
    model.addShape(new Ellipse("E2"));
    initialModel.addShape(new Rectangle("R6"));
    model.setAModelShape("E2", 1, 2, 3, 4, 5, 6, 7, 8);
    initialModel.setAModelShape("R6", 8, 7, 6, 5, 4, 3, 2, 1);
    assertEquals(model.getShape("E2").getPosition(), new Position(1, 2));
    assertEquals(initialModel.getShape("R6").getColor(), new Color(4, 3, 2));
  }

  @Test
  public void getLastTickTest() {
    AModelShape e = new Ellipse("E");
    AModelShape r = new Rectangle("R");
    IMotion move = new MotionMove(1, 4, new Position(40, 60));
    IMotion size = new MotionDimension(5, 9, new Dimension(456, 35));
    IMotion color = new MotionColor(5, 30, new Color(0, 0, 0));
    e.addMotion(move);
    e.addMotion(size);
    r.addMotion(move);
    r.addMotion(color);
    initialModel.addShape(e);
    initialModel.addShape(r);
    assertEquals(30, initialModel.getLastTick());
  }
}