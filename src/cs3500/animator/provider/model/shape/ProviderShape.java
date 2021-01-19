package cs3500.animator.provider.model.shape;

import cs3500.animator.provider.model.Canvas;
import cs3500.animator.provider.model.Posn;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Provider shape that takes IModelShape that makes it work with the provider shape. It acts as a
 * wrapper and adopt IModelShapes for the provider code.
 */
public class ProviderShape implements IShape {

  private IModelShape shape;

  /**
   * Provider shape constructor that takes IModel shape as param.
   *
   * @param shape IModelShape
   */
  public ProviderShape(IModelShape shape) {
    this.shape = shape;
  }

  @Override
  public int size() {
    return shape.getDimension().getWidth() * shape.getDimension().getHeight();
  }

  @Override
  public Posn getPosition() {
    Posn newPo = new Posn(shape.getPosition().getX(), shape.getPosition().getY());
    return newPo;
  }

  @Override
  public Color getColor() {
    Color neCor = new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
        shape.getColor().getBlue());
    return neCor;
  }

  @Override
  public int getXVal() {
    return shape.getDimension().getWidth();
  }

  @Override
  public int getYVal() {
    return shape.getDimension().getHeight();
  }

  @Override
  public void setColor(Color newColor) {
    cs3500.animator.tools.Color transCol = new cs3500.animator.tools.Color(newColor.getRed(),
        newColor.getGreen(), newColor.getBlue());
    shape.setColor(transCol);
  }

  @Override
  public void changeSize(int xVal, int yVal) throws IllegalArgumentException {
    Dimension transD = new Dimension(xVal, yVal);
    shape.setDimension(transD);
  }

  @Override
  public void move(Posn newPos) {
    Position transPos = new Position(newPos.getX(), newPos.getY());
    shape.setPosition(transPos);
  }

  @Override
  public void draw(Graphics g, Canvas canvas) {
    Graphics2D g2d = (Graphics2D) g;

    if (shape.getType().equalsIgnoreCase("rectangle")) {
      g2d.setColor(new Color(shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue()));
      g2d.fillRect(shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getDimension().getWidth(), shape.getDimension().getHeight());
    }
    if (shape.getType().equalsIgnoreCase("ellipse")) {
      g2d.setColor(new Color(shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue()));
      g2d.fillOval(shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getDimension().getWidth(), shape.getDimension().getHeight());
    }
  }

  @Override
  public boolean isVisible() {
    return shape.getVisible();
  }
}
