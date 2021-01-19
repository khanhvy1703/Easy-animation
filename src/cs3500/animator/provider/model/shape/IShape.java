package cs3500.animator.provider.model.shape;

import cs3500.animator.provider.model.Canvas;
import cs3500.animator.provider.model.Posn;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The interface that represents all kinds of 2D shapes.
 */
public interface IShape {

  /**
   * Get the size of this 2D shape. the size of the shape must be a positive number.
   * @return the size of this shape
   */
  int size();

  /**
   * Get the position of this shape, which is represented by Posn class.
   * @return the position of this shape
   */
  Posn getPosition();

  /**
   * Get the color of this shape.
   * @return the color of this shape.
   */
  Color getColor();

  /**
   * Get the horizontal length of this shape.
   * @return the horizontal length of this shape.
   */
  int getXVal();

  /**
   * Get the vertical length of this shape.
   * @return the vertical length of this shape.
   */
  int getYVal();

  /**
   * Set the color of this shape to the given color.
   * @param newColor the new color of this shape
   */
  void setColor(Color newColor);

  /**
   * Change the size by changing to the given attributes.
   * @param xVal the length value along x-axis
   * @param yVal the length value along y-axis
   * @throws IllegalArgumentException if one of the given attributes does not satisfy
   *         this shape's requirement
   */
  void changeSize(int xVal, int yVal) throws IllegalArgumentException;

  /**
   * Move this shape to the given position.
   * @param newPos the position user wants to this shape to
   */
  void move(Posn newPos);

  /**
   * Draw this shape.
   * @param g drawing tool
   * @param canvas what the shapes are drawn on
   */
  void draw(Graphics g, Canvas canvas);

  /**
   * Check if this shape is visible or not.
   * @return the visibility of this shape
   */
  boolean isVisible();

}
