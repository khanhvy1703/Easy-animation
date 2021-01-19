package cs3500.animator.provider.model;

import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import java.awt.Color;
import java.util.List;

/**
 * Provider Instruction Impl class that implements Instruction. The class takes IMotion and
 * IModelShape that makes it work with the provider Instruction. It acts as a wrapper and adopt
 * IMotion for the provider code.
 */
public class ProviderInstruction implements Instruction {

  private IMotion motion;
  private IModelShape shape;

  /**
   * Constructor of the ProviderInstruction class that takes IMotion and IModelShape.
   *
   * @param motion IMotion
   * @param shape  IModelShape
   */
  public ProviderInstruction(IMotion motion, IModelShape shape) {
    this.motion = motion;
    this.shape = shape;
  }

  @Override
  public String getName() {
    return shape.getName();
  }

  @Override
  public int getStartTick() {
    return motion.getStartTime();
  }

  @Override
  public int getEndTick() {
    return motion.getEndTime();
  }

  @Override
  public Posn getPositionAt(int currentTick) {
    return null;
  }

  @Override
  public int getXValAt(int currentTick) {
    return 0;
  }

  @Override
  public int getYValAt(int currentTick) {
    return 0;
  }

  @Override
  public Color getColorAt(int currentTick) {
    return null;
  }

  @Override
  public void isFollowedBy(Instruction other) {
    // no use.
  }

  @Override
  public String toString(double tickPerSec) {
    int start = (int) (this.getStartTick() / tickPerSec);
    int end = (int) (this.getEndTick() / tickPerSec);

    StringBuilder view = new StringBuilder();

    int[] startShape = shape.getIntegers();

    shape.setAModelShape(new Position(startShape[0], startShape[1]),
        new Dimension(startShape[2], startShape[3]),
        new cs3500.animator.tools.Color(startShape[4], startShape[5], startShape[6]),
        shape.getGivenTick());

    int x = shape.getPosition().getX();
    int y = shape.getPosition().getY();
    int w = shape.getDimension().getWidth();
    int h = shape.getDimension().getHeight();
    int r = shape.getColor().getRed();
    int g = shape.getColor().getGreen();
    int b = shape.getColor().getBlue();
    String name = motion.getName();

    // append the initial data.
    view.append(String.format("%s %d %d %d %d %d %d %d %d", shape.getName(),
        start, x, y, w, h, r, g, b));

    // check motion cases.
    switch (name) {
      case "Motion Move":
        MotionMove move = (MotionMove) motion;
        int tX = move.getToPosition().getX();
        int tY = move.getToPosition().getY();
        view.append(String
            .format("   %d %d %d %d %d %d %d %d\n", end, tX, tY, w, h, r, g, b));
        for (int i = start; i < motion.getEndTime(); i++) {
          shape.apply(i);
        }
        Position nP = new Position(tX, tY);
        shape
            .setAModelShape(nP, new Dimension(w, h), new cs3500.animator.tools.Color(r, g, b), end);
        break;
      case "Motion Dimension":
        MotionDimension dimension = (MotionDimension) motion;
        int tW = dimension.getToDimension().getWidth();
        int tH = dimension.getToDimension().getHeight();
        view.append(String
            .format("   %d %d %d %d %d %d %d %d\n", end, x, y, tW, tH, r, g, b));
        for (int i = start; i < end; i++) {
          shape.apply(i);
        }
        Dimension nD = new Dimension(tW, tH);
        shape.setAModelShape(new Position(x, y), nD, new cs3500.animator.tools.Color(r, g, b), end);
        break;
      case "Motion Color":
        MotionColor color = (MotionColor) motion;
        int tR = color.getToColor().getRed();
        int tG = color.getToColor().getGreen();
        int tB = color.getToColor().getBlue();
        view.append(String
            .format("   %d %d %d %d %d %d %d %d\n", end, x, y, w, h, tR, tG, tB));
        for (int i = start; i < end; i++) {
          shape.apply(i);
        }
        cs3500.animator.tools.Color nC = new cs3500.animator.tools.Color(tR, tG, tB);
        shape.setAModelShape(new Position(x, y), new Dimension(w, h), nC, end);
        break;
      default:
        break;
    }
    return view.toString();
  }

  @Override
  public List<String> getChanges() {
    return null; // no use
  }

  @Override
  public String getChangeInfo(String att, String shapeType) {
    return null; // no use
  }
}
