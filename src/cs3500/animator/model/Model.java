package cs3500.animator.model;

import cs3500.animator.shape.IModelShape;
import cs3500.animator.shape.Plus;
import cs3500.animator.tools.Tempo;
import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.shape.Ellipse;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Class the represent Model which implements from IModel. This class will be used to build the
 * canvas for the animation and store information of its shapes.
 */
public class Model implements IModel {

  private int xCanvas;
  private int yCanvas;
  private int wCanvas;
  private int hCanvas;
  private final ArrayList<IModelShape> shapes;
  private ArrayList<Tempo> temps;

  /**
   * A default constructor that has no parameters, and coordinates are set initially 0.
   */
  public Model() {
    this.xCanvas = 0;
    this.yCanvas = 0;
    this.wCanvas = 0;
    this.hCanvas = 0;
    this.shapes = new ArrayList<>();
    this.temps = new ArrayList<>();
  }

  /**
   * A constructor that has 4 parameters, leftmost x coordinate, topmost y coordinate, the width of
   * bounding box and the height of bounding box.
   *
   * @param xCanvas The leftmost x value
   * @param yCanvas The topmost y value
   * @param wCanvas The width of the bounding box
   * @param hCanvas The height of the bounding box
   */
  public Model(int xCanvas, int yCanvas, int wCanvas, int hCanvas) {
    this.xCanvas = xCanvas;
    this.yCanvas = yCanvas;
    this.wCanvas = wCanvas;
    this.hCanvas = hCanvas;
    this.shapes = new ArrayList<>();
    this.temps = new ArrayList<>();
  }

  @Override
  public int getXCanvas() {
    return this.xCanvas;
  }

  @Override
  public int getYCanvas() {
    return this.yCanvas;
  }

  @Override
  public int getWCanvas() {
    return this.wCanvas;
  }

  @Override
  public int getHCanvas() {
    return this.hCanvas;
  }

  @Override
  public void setCanvas(int xCanvas, int yCanvas, int wCanvas, int hCanvas) {
    this.xCanvas = xCanvas;
    this.yCanvas = yCanvas;
    this.wCanvas = wCanvas;
    this.hCanvas = hCanvas;
  }

  @Override
  public void setAModelShape(String name, int x, int y, int w, int h, int r, int g, int b, int t) {
    for (IModelShape shape : shapes) {
      if (shape.getName().equals(name)) {
        shape.setAModelShape(new Position(x, y), new Dimension(w, h), new Color(r, g, b), t);
      }
    }
  }

  @Override
  public ArrayList<Tempo> getTempos() {
    return this.temps;
  }

  @Override
  public void addTempos(Tempo t) {
    temps.add(t);
  }

  @Override
  public ArrayList<IModelShape> getShapes() {
    return this.shapes;
  }

  @Override
  public void addShape(IModelShape s) {
    shapes.add(s);
  }

  @Override
  public IModelShape getShape(String name) {
    IModelShape result = null;
    for (IModelShape shape : shapes) {
      if (shape.getName().equals(name)) {
        result = shape;
      }
    }
    return result;
  }

  @Override
  public void doActionPerform(int tick) {
    for (IModelShape shape : shapes) {
      shape.applyFrameShape(tick);
    }
  }

  @Override
  public int getLastTick() {
    List<Integer> li = new ArrayList<>();
    for (IModelShape shape : shapes) {
      li.add(shape.getLastTick());
    }
    return Collections.max(li);
  }


  @Override
  public void isVisible(int tick) {
    for (IModelShape shape : shapes) {
      shape.setVisible(tick >= shape.getGivenTick());
    }
  }

  /**
   * Class that represent builder which will be used to read the file and return correspondingly.
   */
  public static final class Builder implements AnimationBuilder<IModel> {

    private final Model model;

    /**
     * Constructor of the builder.
     */
    public Builder() {
      this.model = new Model();
    }

    @Override
    public IModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width,
        int height) {
      model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      if (type.equals("rectangle")) {
        model.addShape(new Rectangle(name));
      }
      if (type.equals("ellipse")) {
        model.addShape(new Ellipse(name));
      }
      if (type.equals("plus")) {
        model.addShape(new Plus(name));
      }
      return this;
    }

    @Override
    public AnimationBuilder<IModel> setTempo(int start, int end, int speed) {
      Tempo tempo = new Tempo(start, end, speed);
      model.addTempos(tempo);
      return null;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

      // initiate all position, dimension and color.
      Position p1 = new Position(x1, y1);
      Position p2 = new Position(x2, y2);
      Dimension d1 = new Dimension(w1, h1);
      Dimension d2 = new Dimension(w2, h2);
      Color c1 = new Color(r1, g1, b1);
      Color c2 = new Color(r2, g2, b2);

      if ((model.getShape(name).getPosition().getX() == 0)
          && (model.getShape(name).getPosition().getY() == 0)
          && (model.getShape(name).getDimension().getWidth() == 0)
          && (model.getShape(name).getDimension().getHeight() == 0)
          && (model.getShape(name).getColor().getRed() == 0)
          && (model.getShape(name).getColor().getGreen() == 0)
          && (model.getShape(name).getColor().getBlue() == 0)
          && (model.getShape(name).getGivenTick() == 0)) {
        model.setAModelShape(name, x1, y1, w1, h1, r1, g1, b1, t1);

      }

      IModelShape shape = model.getShape(name);

      if (!p1.equals(p2)) {
        shape.addMotion(new MotionMove(t1, t2, p2));
      }
      if (!c1.equals(c2)) {
        shape.addMotion(new MotionColor(t1, t2, c2));
      }
      if (!d1.equals(d2)) {
        shape.addMotion(new MotionDimension(t1, t2, d2));
      }
      if (p1.equals(p2) && (d1.equals(d2)) && (c1.equals(c2))) {
        shape.addMotion(new MotionDimension(t1, t2, d2));
      }

      return this;
    }


  }
}