package cs3500.animator.shape;


import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Class that represent Rectangle shape. This shape will be ued to represent the type of shape later
 * in the animation and its view.
 */
public class Rectangle extends AModelShape {
  /**
   * constructor of the Rectangle. Position, dimension, color will be initially set by 0.
   * @param name the name will inherited.
   */
  public Rectangle(String name) {
    super(name);
    this.position = new Position(0, 0);
    this.dimension = new Dimension(0, 0);
    this.color = new Color(0, 0, 0);
  }

  @Override
  public String getType() {
    return  "Rectangle";
  }
}
