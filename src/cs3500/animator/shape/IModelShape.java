package cs3500.animator.shape;

import cs3500.animator.motion.IMotion;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import java.util.List;

/**
 * Interface that represent  for IModelShapes.
 */
public interface IModelShape {

  /**
   * Getter method to get the type of the shape.
   *
   * @return type of the shape.
   */
  String getType();


  /**
   * Getter method to get the name of the shape.
   *
   * @return name of the shape.
   */
  String getName();


  /**
   * Getter method to get the position of the shape.
   *
   * @return position of the shape.
   */
  Position getPosition();

  /**
   * Getter method to get the dimension of the shape.
   *
   * @return dimension of the shape.
   */
  Dimension getDimension();

  /**
   * Getter method to get the color of the shape.
   *
   * @return color of the shape.
   */
  Color getColor();

  /**
   * Getter method to get the given tick of the shape.
   *
   * @return given tick of the shape.
   */
  int getGivenTick();

  /**
   * Getter method to get the motions of the shape.
   *
   * @return motions of the shape.
   */
  List<IMotion> getMotions();

  /**
   * Getter method to get the list of numeric information of the shape.
   *
   * @return list of numeric information of the shape.
   */
  int[] getIntegers();

  /**
   * Getter method to get the visibility of the shape.
   *
   * @return visibility of the shape (t/f).
   */
  boolean getVisible();

  /**
   * check if the shape in the outline mode.
   *
   * @return true if it is outlined, otherwise false.
   */
  boolean isOutlined();

  /**
   * setter outline mode of the shape.
   *
   * @param outline is the condition of the outline mode.
   */
  void toOutlined(boolean outline);

  /**
   * setter position of AModelShape.
   *
   * @param p the given position
   */
  void setPosition(Position p);

  /**
   * setter dimension of AModelShape.
   *
   * @param d the given dimension
   */
  void setDimension(Dimension d);

  /**
   * setter color of AModelShape.
   *
   * @param c the given color
   */
  void setColor(Color c);

  /**
   * Setter method to set the visibility of the shape.
   *
   * @param isVisible changed visibility.
   */
  void setVisible(boolean isVisible);

  /**
   * Method that add motion to the list of the motion. After that, make a the change to the frame
   * shape.
   *
   * @param motion additional motion that the shape is taking.
   */
  void addMotion(IMotion motion);

  /**
   * setter method for the shape which takes position, dimension, color and tick and change the
   * information of the shape.
   *
   * @param position  new position
   * @param dimension new dimension
   * @param color     new color
   * @param tick      new tick
   */

  void setAModelShape(Position position, Dimension dimension, Color color, int tick);

  /**
   * Method that apply by each tick.
   *
   * @param tick current tick
   */
  void apply(int tick);

  /**
   * Method that apply the motion to the shape by each tick.
   *
   * @param motion motion to take
   * @param tick   current tick
   */
  void applyMotion(IMotion motion, int tick);

  /**
   * Method that apply the frame shape by each tick. get the frame shape and its' position,
   * dimension, color by the tick accordingly.
   *
   * @param tick current tick
   */
  void applyFrameShape(int tick);

  /**
   * gets the last tick of the shape.
   *
   * @return the last tick
   */
  int getLastTick();

  /**
   * setter motion of the model shape.
   *
   * @param motions the given motions.
   */
  void setMotions(List<IMotion> motions);
}
