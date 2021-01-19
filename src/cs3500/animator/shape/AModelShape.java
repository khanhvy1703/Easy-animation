package cs3500.animator.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Represent abstract class for shapes. All the shape contains position, dimension, color, its name,
 * given tick, visibility, motions, shapes, and list of integer representing info.
 */
public abstract class AModelShape implements IModelShape {

  protected Position position;
  protected Dimension dimension;
  protected Color color;
  protected String name;
  protected int givenTick;
  protected boolean isVisible;
  protected boolean isOutlined;
  protected List<IMotion> motions;
  protected List<cs3500.animator.shape.FrameShape> frameShapes;
  protected int[] integers; // list of integer taking from position, dimension and color.

  /**
   * Constructor that has only name of the shape and the others are set with default values 0, which
   * will be change later on by the other methods.
   *
   * @param name name of the shape.
   */
  public AModelShape(String name) {
    this.position = new Position(0, 0);
    this.dimension = new Dimension(0, 0);
    this.color = new Color(0, 0, 0);
    this.name = name;
    this.givenTick = 0;
    this.motions = new ArrayList<>();
    this.frameShapes = new ArrayList<>();
    this.isVisible = false;
    this.isOutlined = false;
  }

  /**
   * Constructors that has position, dimension, color and the name of the shape.
   *
   * @param position  position of the shape.
   * @param dimension dimension of the shape.
   * @param color     color of the shape.
   * @param name      name of the shape.
   */
  public AModelShape(Position position, Dimension dimension, Color color, String name) {
    this.position = position;
    this.dimension = dimension;
    this.color = color;
    this.name = name;
    this.givenTick = 0;
    this.motions = new ArrayList<>();
    this.frameShapes = new ArrayList<>();
    this.integers = new int[]{position.getX(), position.getY(), dimension.getWidth(),
        dimension.getHeight(), color.getRed(), color.getGreen(), color.getBlue()};
    this.isVisible = false;
    this.isOutlined = false;
  }

  /**
   * Getter method to get the type of the shape.
   *
   * @return type of the shape.
   */
  public abstract String getType();

  /**
   * Getter method to get the name of the shape.
   *
   * @return name of the shape.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter method to get the position of the shape.
   *
   * @return position of the shape.
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * Getter method to get the dimension of the shape.
   *
   * @return dimension of the shape.
   */
  public Dimension getDimension() {
    return dimension;
  }

  /**
   * Getter method to get the color of the shape.
   *
   * @return color of the shape.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Getter method to get the given tick of the shape.
   *
   * @return given tick of the shape.
   */
  public int getGivenTick() {
    return givenTick;
  }

  /**
   * Getter method to get the motions of the shape.
   *
   * @return motions of the shape.
   */
  public List<IMotion> getMotions() {
    return this.motions;
  }

  /**
   * Getter method to get the list of numeric information of the shape.
   *
   * @return list of numeric information of the shape.
   */
  public int[] getIntegers() {
    return integers;
  }

  /**
   * swaps the positions of the current shape with the given shape.
   *
   * @param aShape the given shape.
   */
  public void swipeOperation(AModelShape aShape, int tick) {
    Position p1 = aShape.position;
    Position p2 = this.position;
    this.position = p1;
    this.givenTick = tick;
    aShape.setAModelShape(p2, aShape.getDimension(), aShape.getColor(), tick);
  }

  /**
   * setter position of AModelShape.
   *
   * @param p the given position
   */
  public void setPosition(Position p) {
    this.position = p;
  }

  /**
   * setter position of AModelShape.
   *
   * @param d the given dimension
   */
  public void setDimension(Dimension d) {
    this.dimension = d;
  }

  /**
   * setter position of AModelShape.
   *
   * @param c the given color
   */
  public void setColor(Color c) {
    this.color = c;
  }

  /**
   * Getter method to get the visibility of the shape.
   *
   * @return visibility of the shape (t/f).
   */
  public boolean getVisible() {
    return this.isVisible;
  }

  /**
   * Setter method to set the visibility of the shape.
   *
   * @param isVisible changed visibility.
   */
  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }


  /**
   * Getter method to get true if shape is outlined.
   *
   * @return boolean
   */
  public boolean isOutlined() {
    return this.isOutlined;
  }

  /**
   * Setter method for the online of the shape.
   *
   * @param outline is the condition of the outline mode.
   */
  public void toOutlined(boolean outline) {
    this.isOutlined = outline;
  }

  /**
   * Method that add motion to the list of the motion. After that, make a the change to the frame
   * shape.
   *
   * @param motion additional motion that the shape is taking.
   */
  public void addMotion(IMotion motion) {
    if (!checkOverLap(motion)) {
      this.motions.add(motion);
      this.addFrameShape();
    } else {
      throw new IllegalArgumentException("2 motions cant happen at the same time.");
    }
    System.out.println(this.motions.size());
  }

  /**
   * checks if the given motion is overlapped.
   *
   * @param motion the given motion
   * @return true if overlap.
   */
  private boolean checkOverLap(IMotion motion) {
    if (motions.size() == 0) {
      return false;
    } else {
      boolean result = true;
      for (IMotion m : motions) {
        int start = motion.getStartTime();
        int end = motion.getEndTime();
        int s = m.getStartTime();
        int e = m.getEndTime();
        result = (start < s) || (start == s && end == e && m.getName().equals(motion.getName()))
            || (((s < start) && (start < e)) || ((s < end) && (end < e))
            || ((s < start) && (end < e)));
      }
      return result;
    }
  }

  /**
   * setter method for the shape which takes position, dimension, color and tick and change the
   * information of the shape.
   *
   * @param position  new position
   * @param dimension new dimension
   * @param color     new color
   * @param tick      new tick
   */

  public void setAModelShape(Position position, Dimension dimension, Color color, int tick) {
    this.position = position;
    this.dimension = dimension;
    this.color = color;
    this.givenTick = tick;
    this.integers = new int[]{position.getX(), position.getY(), dimension.getWidth(),
        dimension.getHeight(), color.getRed(), color.getGreen(), color.getBlue()};
  }

  /**
   * Method that take the motion and look at the start part of the motion and add to the frame.
   *
   * @param motion the motion that the shape takes
   */
  private void addFrameStart(IMotion motion) {
    FrameShape startFrame = new FrameShape(motion.getStartTime());
    startFrame.setCopyShapeUlti(position, dimension, color);
    frameShapes.add(startFrame);
  }

  /**
   * Method that take the motion and look at the start part of the motion and add to the frame.
   *
   * @param motion the motion that the shape takes.
   */
  private void addFrameEnd(IMotion motion) {
    FrameShape endFrame = new FrameShape(motion.getEndTime());

    for (int i = motion.getStartTime(); i <= motion.getEndTime(); i++) {
      this.apply(i);
    }
    endFrame.setCopyShapeUlti(position, dimension, color);
    frameShapes.add(endFrame);
  }

  /**
   * Method that check the motion of the shape and add in to the frame.
   */
  private void addFrameShape() {
    IMotion motion = motions.get(motions.size() - 1);
    addFrameStart(motion);
    addFrameEnd(motion);
  }

  /**
   * Method that apply by each tick.
   *
   * @param tick current tick
   */
  public void apply(int tick) {
    if (tick == givenTick) {
      this.position = new Position(integers[0], integers[1]);
      this.dimension = new Dimension(integers[2], integers[3]);
      this.color = new Color(integers[4], integers[5], integers[6]);
    }
    for (IMotion motion : motions) {
      if (tick >= motion.getStartTime() && tick < motion.getEndTime()) {
        this.applyMotion(motion, tick);
      }
    }
  }

  /**
   * Method that apply the motion to the shape by each tick.
   *
   * @param motion motion to take
   * @param tick   current tick
   */
  public void applyMotion(IMotion motion, int tick) {
    int start = motion.getStartTime();
    int duration = motion.getDuration();
    String name = motion.getName();

    switch (name) {
      case "Motion Move":
        MotionMove move = (MotionMove) motion;
        if (start == tick && move.getFromPosition().equals(new Position(0, 0))) {
          move.setFromPosition(this.position);
        }

        int fX = move.getFromPosition().getX();
        int fY = move.getFromPosition().getY();
        int tX = move.getToPosition().getX();
        int tY = move.getToPosition().getY();

        int x = (fX + ((tX - fX) * (tick + 1 - start)) / duration);
        int y = (fY + ((tY - fY) * (tick + 1 - start)) / duration);

        this.position = new Position(x, y);
        break;

      case "Motion Dimension":
        MotionDimension dimension = (MotionDimension) motion;
        if (start == tick && dimension.getFromDimension().equals(new Dimension(0, 0))) {
          dimension.setFromDimension(this.dimension);
        }
        int fW = dimension.getFromDimension().getWidth();
        int fH = dimension.getFromDimension().getHeight();
        int tW = dimension.getToDimension().getWidth();
        int tH = dimension.getToDimension().getHeight();

        int w = (fW + ((tW - fW) * (tick + 1 - start)) / duration);
        int h = (fH + ((tH - fH) * (tick + 1 - start)) / duration);

        this.dimension = new Dimension(w, h);
        break;

      case "Motion Color":
        MotionColor color = (MotionColor) motion;
        if (start == tick && color.getFromColor().equals(new Color(0, 0, 0))) {
          color.setFromColor(this.color);
        }

        int fR = color.getFromColor().getRed();
        int fG = color.getFromColor().getGreen();
        int fB = color.getFromColor().getBlue();
        int tR = color.getToColor().getRed();
        int tG = color.getToColor().getGreen();
        int tB = color.getToColor().getBlue();

        int r = (fR + ((tR - fR) * (tick + 1 - start)) / duration);
        int g = (fG + ((tG - fG) * (tick + 1 - start)) / duration);
        int b = (fB + ((tB - fB) * (tick + 1 - start)) / duration);

        this.color = new Color(r, g, b);
        break;
      default:
        break;
    }
  }

  /**
   * Method that apply the frame shape by each tick. get the frame shape and its' position,
   * dimension, color by the tick accordingly.
   *
   * @param tick current tick
   */
  public void applyFrameShape(int tick) { // apply key frame
    List<FrameShape> f = this.frameShapes;

    for (int i = 0; i < f.size(); i++) {
      FrameShape currentFrame = f.get(i);
      FrameShape nextFrame;

      try {
        nextFrame = f.get(i + 1);
      } catch (Exception e) {
        nextFrame = f.get(i);
      }

      if (tick == givenTick) {
        this.position = new Position(integers[0], integers[1]);
        this.dimension = new Dimension(integers[2], integers[3]);
        this.color = new Color(integers[4], integers[5], integers[6]);
      }

      if (tick >= currentFrame.getFrameTick() && nextFrame.getFrameTick() > tick) {

        int curTime = currentFrame.getFrameTick();
        int neTime = nextFrame.getFrameTick();
        int gap = neTime - curTime;

        // get current data.
        Position currentPosition = currentFrame.getP();
        int cX = currentPosition.getX();
        int cY = currentPosition.getY();
        Dimension currentDimension = currentFrame.getD();
        int cW = currentDimension.getWidth();
        int cH = currentDimension.getHeight();
        Color currentColor = nextFrame.getC();
        int cR = currentColor.getRed();
        int cG = currentColor.getGreen();
        int cB = currentColor.getBlue();

        // get next data.
        Position nextPosition = nextFrame.getP();
        int nX = nextPosition.getX();
        int nY = nextPosition.getY();
        Dimension nextDimension = nextFrame.getD();
        int nW = nextDimension.getWidth();
        int nH = nextDimension.getHeight();
        Color nextColor = nextFrame.getC();
        int nR = nextColor.getRed();
        int nG = nextColor.getGreen();
        int nB = nextColor.getBlue();

        int x = (cX + ((nX - cX) * (tick - curTime)) / gap);
        int y = (cY + ((nY - cY) * (tick - curTime)) / gap);
        int w = (cW + ((nW - cW) * (tick - curTime)) / gap);
        int h = (cH + ((nH - cH) * (tick - curTime)) / gap);
        int r = (cR + ((nR - cR) * (tick - curTime)) / gap);
        int g = (cG + ((nG - cG) * (tick - curTime)) / gap);
        int b = (cB + ((nB - cB) * (tick - curTime)) / gap);

        this.position = new Position(x, y);
        this.dimension = new Dimension(w, h);
        this.color = new Color(r, g, b);
      }
    }
  }

  /**
   * gets the last tick of the shape.
   *
   * @return the last tick
   */
  public int getLastTick() {
    List<Integer> li = new ArrayList<>();

    for (IMotion m : motions) {
      li.add(m.getEndTime());
    }
    return Collections.max(li);
  }

  @Override
  public void setMotions(List<IMotion> motions) {
    this.motions = motions;
  }
}
