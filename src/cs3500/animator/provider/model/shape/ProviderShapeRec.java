package cs3500.animator.provider.model.shape;

import cs3500.animator.shape.IModelShape;

/**
 * One of the subclass that extends to the ProviderShape class. This class is built to define the
 * type of the shape, Rectangle.
 */
public class ProviderShapeRec extends ProviderShape {

  private final String type;

  /**
   * The constructor of the ProviderShapeOval. It takes the same param as ProviderShape but defines
   * its own type, rectangle.
   *
   * @param shape IModelShape
   */
  public ProviderShapeRec(IModelShape shape) {
    super(shape);
    this.type = "Rectangle";
  }

  @Override
  public String toString() {
    return this.type.toLowerCase();
  }
}
