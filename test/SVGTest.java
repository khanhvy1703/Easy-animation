
import static org.junit.Assert.assertEquals;

import cs3500.animator.view.IView;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.view.SVGView;
import org.junit.Test;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Tests for SVG class.
 */
public class SVGTest {

  IModel model = new Model();
  int frameSecond = 15;

  IView svg = new SVGView(model, frameSecond);

  // creates an ellipse, motions of the shape.
  AModelShape ellipse = new AModelShape(new Position(200, 200),
      new Dimension(100, 100), new Color(0, 1, 3), "E") {
    @Override
    public String getType() {
      return "Ellipse";
    }
  };

  // create a rectangle
  AModelShape rectangle = new AModelShape(new Position(500, 100),
      new Dimension(300, 200), new Color(200, 20, 40), "R") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };

  // creates motions
  IMotion move = new MotionMove(1, 5, new Position(20, 30));
  IMotion dimension = new MotionDimension(6, 10, new Dimension(50, 100));
  IMotion color = new MotionColor(15, 20, new Color(20, 20, 20));

  @Test
  public void svgRectangleMoveOnce() {
    rectangle.addMotion(move);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<rect id=\"R x=\"20\" y=\"30\" width=\"300\" height=\"200\"\n"
            + "        fill=\"[200,20,40]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" attributeName=\"x\" "
            + "from=\"380\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" attributeName=\"y\" "
            + "from=\"83\" to=\"30\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void svgRectangleSameMoveTwice() {
    rectangle.addMotion(move);
    rectangle.addMotion(move);
    model.addShape(rectangle);
  }

  @Test
  public void svgRectangleDimension() {
    rectangle.addMotion(dimension);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<rect id=\"R x=\"500\" y=\"100\" width=\"50\" height=\"100\"\n"
            + "        fill=\"[200,20,40]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"300\" to=\"50\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"height\" from=\"200\" to=\"100\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgRectangleColor() {
    rectangle.addMotion(color);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<rect id=\"R x=\"500\" y=\"100\" width=\"300\" height=\"200\"\n"
            + "        fill=\"[20,20,20]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(200,20,40)\" to=\"rgb(20,20,20)\" "
            + "fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgRectangleMoveDimensionColor() {
    rectangle.addMotion(move);
    rectangle.addMotion(dimension);
    rectangle.addMotion(color);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN"
            + "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<rect id=\"R x=\"20\" y=\"30\" width=\"50\" height=\"100\"\n"
            + "        fill=\"[20,20,20]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"x\" from=\"380\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"y\" from=\"83\" to=\"30\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\""
            + " attributeName=\"width\" from=\"300\" to=\"50\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"height\" from=\"200\" to=\"100\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(200,20,40)\" to=\"rgb(20,20,20)\" "
            + "fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseMove() {
    ellipse.addMotion(move);
    model.addShape(ellipse);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"20\" cy=\"30\" rx=\"50\" ry=\"50\"\n"
            + "        fill=\"[0,1,3]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\""
            + " attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseDimension() {
    ellipse.addMotion(dimension);
    model.addShape(ellipse);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"200\" cy=\"200\" rx=\"25\" ry=\"50\"\n"
            + "        fill=\"[0,1,3]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseColor() {
    ellipse.addMotion(color);
    model.addShape(ellipse);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"200\" cy=\"200\" rx=\"50\" ry=\"50\"\n"
            + "        fill=\"[20,20,20]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void svgEllipseMoveAfterColor() {
    ellipse.addMotion(color);
    ellipse.addMotion(move);
    model.addShape(ellipse);
  }

  @Test
  public void svgEllipseMoveDimensionColor() {
    ellipse.addMotion(move);
    ellipse.addMotion(dimension);
    ellipse.addMotion(color);
    model.addShape(ellipse);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN"
            + "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"20\" cy=\"30\" rx=\"25\" ry=\"50\"\n"
            + "        fill=\"[20,20,20]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseAndRectangleMove() {
    ellipse.addMotion(move);
    rectangle.addMotion(move);
    model.addShape(ellipse);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"20\" cy=\"30\" rx=\"50\" ry=\"50\"\n"
            + "        fill=\"[0,1,3]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\""
            + " attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\""
            + " attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect id=\"R x=\"20\" y=\"30\" width=\"300\" height=\"200\"\n"
            + "        fill=\"[200,20,40]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\""
            + " attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\""
            + " attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseAndRectangleDimension() {
    ellipse.addMotion(dimension);
    rectangle.addMotion(dimension);
    model.addShape(ellipse);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"200\" cy=\"200\" rx=\"25\" ry=\"50\"\n"
            + "        fill=\"[0,1,3]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect id=\"R x=\"500\" y=\"100\" width=\"50\" height=\"100\"\n"
            + "        fill=\"[200,20,40]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"100\" to=\"50\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseAndRectangleColor() {
    ellipse.addMotion(color);
    rectangle.addMotion(color);
    model.addShape(ellipse);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"200\" cy=\"200\" rx=\"50\" ry=\"50\"\n"
            + "        fill=\"[20,20,20]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect id=\"R x=\"500\" y=\"100\" width=\"300\" height=\"200\"\n"
            + "        fill=\"[20,20,20]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }

  @Test
  public void svgEllipseAndRectangleMoveDimenColor() {
    ellipse.addMotion(move);
    ellipse.addMotion(dimension);
    ellipse.addMotion(color);
    rectangle.addMotion(move);
    rectangle.addMotion(dimension);
    rectangle.addMotion(color);
    model.addShape(ellipse);
    model.addShape(rectangle);
    assertEquals(
        "<?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\""
            + "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
            + "<svg width=\"0cm\" height=\"0cm\" viewBox=\"0 0 0 0\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
            + "<ellipse id=\"Ecx=\"20\" cy=\"30\" rx=\"25\" ry=\"50\"\n"
            + "        fill=\"[20,20,20]\"  />\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect id=\"R x=\"20\" y=\"30\" width=\"50\" height=\"100\"\n"
            + "        fill=\"[20,20,20]\" visibility=\"visible\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"x\" from=\"155\" to=\"20\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"66ms\" dur=\"266ms\" "
            + "attributeName=\"y\" from=\"158\" to=\"30\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"266ms\""
            + " attributeName=\"width\" from=\"100\" to=\"50\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"333ms\""
            + " attributeName=\"fill\" from=\"rgb(0,1,3)\" to=\"rgb(20,20,20)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>", svg.getTextualView());
  }
}