package cs3500.animator.model;

import cs3500.animator.shape.IModelShape;
import cs3500.animator.tools.Tempo;
import java.util.ArrayList;

/**
 * Interface Model that contains all the method that is used in the model.
 */
public interface IModel {

  /**
   * Getter method to get the position X of the canvas.
   *
   * @return the position X of the canvas
   */
  int getXCanvas();

  /**
   * Getter method to get the position Y of the canvas.
   *
   * @return the position Y of the canvas
   */
  int getYCanvas();

  /**
   * Getter method to get the size W of the canvas.
   *
   * @return the size W of the canvas
   */
  int getWCanvas();

  /**
   * Getter method to get the size H of the canvas.
   *
   * @return the size H of the canvas
   */
  int getHCanvas();

  /**
   * Setter Method to modify the information of the canvas.
   *
   * @param xCanvas new position x of the canvas.
   * @param yCanvas new position y of the canvas.
   * @param wCanvas new size w of the canvas.
   * @param hCanvas new size h of the canvas.
   */
  void setCanvas(int xCanvas, int yCanvas, int wCanvas, int hCanvas);

  /**
   * Setter method to modify the shape in the model. The method takes shape name and change with the
   * given information (x, y, w, h, r, g, b, t).
   *
   * @param name the name of the shape that need to be changed
   * @param x    new position x
   * @param y    new position y
   * @param w    new dimension w
   * @param h    new dimension h
   * @param r    new color r
   * @param g    new color g
   * @param b    new color b
   * @param t    new tick t
   */
  void setAModelShape(String name, int x, int y, int w, int h, int r, int g, int b, int t);

  /**
   * gets the list of tempos.
   *
   * @return the list of tempo.
   */
  ArrayList<Tempo> getTempos();

  /**
   * add the given tempo into the list of tempos.
   *
   * @param t the given tempo.
   */
  void addTempos(Tempo t);

  /**
   * Method that change the visibility based on the current tick.
   *
   * @param tick current tick
   */
  void isVisible(int tick);

  /**
   * Method that return the list of the shapes in the model.
   *
   * @return list of the shapes in the model
   */
  ArrayList<IModelShape> getShapes();

  /**
   * Method that takes the shape and put in the shape list in the model.
   *
   * @param shape the shape that needs to be in the shape list of the model.
   */
  void addShape(IModelShape shape);

  /**
   * Method that apply the action correspondingly to the current tick.
   *
   * @param tick current tick
   */
  void doActionPerform(int tick);

  /**
   * get the last tick.
   *
   * @return the last tick.
   */
  int getLastTick();

  /**
   * The method that takes the name of the shape and return the shape in the model.
   *
   * @param name the name of the shape need to be found
   * @return the shape matches with the name input
   */
  IModelShape getShape(String name);
}
