package cs3500.animator.provider.model.shape;

import cs3500.animator.shape.IModelShape;

/**
 * One of the subclass that extends to the ProviderShape class. This class is built to define the
 * type of the shape, Ellipse.
 */
public class ProviderShapeOval extends ProviderShape {

  private final String type;

  /**
   * The constructor of the ProviderShapeOval. It takes the same param as ProviderShape but defines
   * its own type, ellipse.
   *
   * @param shape IModelShape
   */
  public ProviderShapeOval(IModelShape shape) {
    super(shape);
    this.type = "Ellipse";
  }

  @Override
  public String toString() {
    return this.type.toLowerCase();
  }
}
