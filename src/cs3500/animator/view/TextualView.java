package cs3500.animator.view;

import cs3500.animator.motion.IMotion;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import cs3500.animator.model.IModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Returns the text description of the animation.
 */
public class TextualView implements IView {

  private final IModel model;
  private final int frameSecond;

  /**
   * Constructor of the textual view, which take non-null model.
   *
   * @param model is the given model.
   * @param frameSecond the speed.
   */
  public TextualView(IModel model, int frameSecond) {
    this.model = Objects.requireNonNull(model);
    this.frameSecond = frameSecond;
  }

  @Override
  public String getTextualView() {
    StringBuilder str = new StringBuilder();
    str.append(String.format("canvas %d %d %d %d\n", model.getXCanvas(), model.getYCanvas(),
        model.getWCanvas(), model.getWCanvas()));

    for (IModelShape s : model.getShapes()) {
      str.append(String.format("shape %s %s\n", s.getName(), s.getType()));
      str.append(this.shapeTextualView(s));
    }
    return str.toString();
  }

  /**
   * The helper method to build the textual view of the shape.
   *
   * @param shape given shape
   * @return String form of the animation.
   */
  private String shapeTextualView(IModelShape shape) {
    StringBuilder view = new StringBuilder();

    int[] startShape = shape.getIntegers();

    shape.setAModelShape(new Position(startShape[0], startShape[1]),
        new Dimension(startShape[2], startShape[3]),
        new Color(startShape[4], startShape[5], startShape[6]),
        shape.getGivenTick());

    List<IMotion> motions = shape.getMotions();
    for (IMotion motion : motions) {
      // initial data
      int start = (int) (motion.getStartTime() / frameSecond);
      int end = (int) (motion.getEndTime() / frameSecond);
      String name = motion.getName();
      int x = shape.getPosition().getX();
      int y = shape.getPosition().getY();
      int w = shape.getDimension().getWidth();
      int h = shape.getDimension().getHeight();
      int r = shape.getColor().getRed();
      int g = shape.getColor().getGreen();
      int b = shape.getColor().getBlue();

      // append the initial data.
      view.append(String.format("motion %s %d %d %d %d %d %d %d %d", shape.getName(),
          start, x, y, w, h, r, g, b));

      // check motion cases.
      switch (name) {
        case "Motion Move":
          view.append(String
              .format("    %d %s %d %d %d %d %d\n", end, motion.getEndState(), w, h,
                  r, g, b));
          for (int i = start; i < end; i++) {
            shape.apply(i);
          }
          break;
        case "Motion Dimension":
          view.append(String
              .format("   %d %d %d %s %d %d %d\n", end, x, y, motion.getEndState(),
                  r, g, b));
          for (int i = start; i < end; i++) {
            shape.apply(i);
          }
          break;
        case "Motion Color":
          view.append(String
              .format("   %d %d %d %d %d %s", end, x, y, w, h,
                  motion.getEndState()));
          for (int i = start; i < end; i++) {
            shape.apply(i);
          }
          break;
        default:
          break;
      }
    }
    return view.toString();
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Textual View can not do paint's jobs.");
  }

  @Override
  public void addShape(ArrayList<IModelShape> s) {
    throw new UnsupportedOperationException("Textual View is not allowed to add more shapes.");
  }


}
