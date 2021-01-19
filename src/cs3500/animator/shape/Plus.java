package cs3500.animator.shape;

import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Class that represent Plus shape. This shape will be ued to represent the type of shape later in
 * the animation and its view.
 */
public class Plus extends AModelShape {

  /**
   * constructor of the Plus class. Position, dimension, color will be initially set by 0.
   *
   * @param name the name will inherited.
   */
  public Plus(String name) {
    super(name);
    this.position = new Position(0, 0);
    this.dimension = new Dimension(0, 0);
    this.color = new Color(0, 0, 0);
  }

  @Override
  public String getType() {
    return "Plus";
  }

  @Override
  public void setAModelShape(Position position, Dimension dimension, Color color, int tick) {
    if (dimension.getWidth() != dimension.getHeight()) {
      throw new IllegalArgumentException("The value of the width and the height needs to be shape");
    }
    super.setAModelShape(position, dimension, color, tick);
  }
}
