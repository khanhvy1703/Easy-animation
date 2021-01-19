package cs3500.animator.view;

import cs3500.animator.shape.IModelShape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * The class that extends the JPanel class. It draws the current list of shapes in the model into
 * the canvas.
 */
public class DrawPanel extends JPanel {

  List<IModelShape> currentShape;
  BufferedImage image;

  /**
   * ConBstructor of the DrawPanel. It sets the background and the preferredS size of the panel.
   */
  public DrawPanel() {
    super();
    setBackground(Color.black);
    setSize(new Dimension(1250, 3000));
    currentShape = new ArrayList<>();
  }

  /**
   * setter method of the draw panel.
   *
   * @param shapes the given list of shapes.
   */
  public void addShape(ArrayList<IModelShape> shapes) {
    this.currentShape = shapes;
  }


  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D);
    g.drawImage(image, 0, 0, this);
    for (IModelShape shape : currentShape) {
      if (shape.getType().equals("Rectangle") && shape.getVisible()
          && !shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.fillRect(shape.getPosition().getX(), shape.getPosition().getY(),
            shape.getDimension().getWidth(), shape.getDimension().getHeight());
      } else if (shape.getType().equals("Ellipse") && shape.getVisible()
          && !shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.fillOval(shape.getPosition().getX(), shape.getPosition().getY(),
            shape.getDimension().getWidth(), shape.getDimension().getHeight());
      } else if (shape.getType().equals("Plus") && shape.getVisible()
          && !shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.fillRect(shape.getPosition().getX(),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4),
            shape.getDimension().getWidth(), shape.getDimension().getHeight() / 2);
        g2D.fillRect(shape.getPosition().getX() + (shape.getDimension().getWidth() / 4),
            shape.getPosition().getY(),
            shape.getDimension().getWidth() / 2, shape.getDimension().getHeight());
      } else if (shape.getType().equals("Rectangle") && shape.getVisible() && shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.drawRect(shape.getPosition().getX(), shape.getPosition().getY(),
            shape.getDimension().getWidth(), shape.getDimension().getHeight());
      } else if (shape.getType().equals("Ellipse") && shape.getVisible() && shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.drawOval(shape.getPosition().getX(), shape.getPosition().getY(),
            shape.getDimension().getWidth(), shape.getDimension().getHeight());
      } else if (shape.getType().equals("Plus") && shape.getVisible() && shape.isOutlined()) {
        g2D.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        g2D.drawLine(shape.getPosition().getX() + (shape.getDimension().getWidth() / 4),
            shape.getPosition().getY(),
            shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY());
        g2D.drawLine(shape.getPosition().getX() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getY(),
            shape.getPosition().getX() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4));
        g2D.drawLine(shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY(),
            shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4));
        g2D.drawLine(shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getX() + shape.getDimension().getWidth(),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4));
        g2D.drawLine(shape.getPosition().getX(),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getX() + (shape.getDimension().getWidth() / 4),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4));
        g2D.drawLine(shape.getPosition().getX(),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getX(),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)));
        g2D.drawLine(shape.getPosition().getX() + shape.getDimension().getWidth(),
            shape.getPosition().getY() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getX() + shape.getDimension().getWidth(),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)));
        g2D.drawLine(shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getX() + shape.getDimension().getWidth(),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)));
        g2D.drawLine(shape.getPosition().getX() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getX() + (shape.getDimension().getHeight() / 4),
            shape.getPosition().getY() + shape.getDimension().getHeight());
        g2D.drawLine(shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + shape.getDimension().getHeight());
        g2D.drawLine(shape.getPosition().getX(),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getX() + (shape.getDimension().getWidth() / 4),
            shape.getPosition().getY() + (3 * (shape.getDimension().getWidth() / 4)));
        g2D.drawLine(shape.getPosition().getX() + (shape.getDimension().getWidth() / 4),
            shape.getPosition().getY() + shape.getDimension().getHeight(),
            shape.getPosition().getX() + (3 * (shape.getDimension().getWidth() / 4)),
            shape.getPosition().getY() + shape.getDimension().getHeight());
      }
    }
  }
}
