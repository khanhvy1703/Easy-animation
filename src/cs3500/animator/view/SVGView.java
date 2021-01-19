package cs3500.animator.view;

import cs3500.animator.model.IModel;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * The SVG class that takes model to return the xml-based format that can describe images and
 * animations.
 */
public class SVGView implements IView {

  private IModel model;
  private int frameSecond;

  /**
   * Constructor of the SVG class that has one parameter, the model to take the list of shapes and
   * list of their animation to return the xml format.
   *
   * @param model       model
   * @param frameSecond is the given speed.
   */
  public SVGView(IModel model, int frameSecond) {
    this.model = model;
    this.frameSecond = frameSecond;
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Cant refresh in svg view");
  }

  @Override
  public void addShape(ArrayList<IModelShape> s) {
    throw new UnsupportedOperationException("cant add more shape");
  }

  @Override
  public String getTextualView() {
    StringBuilder svg = new StringBuilder();
    ArrayList<IModelShape> shapes = model.getShapes();

    svg.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n", model.getWCanvas(), model.getHCanvas()));

    for (IModelShape s : shapes) {
      int[] ints = s.getIntegers();
      s.setAModelShape(new Position(ints[0], ints[1]),
          new Dimension(ints[2], ints[3]), new Color(ints[4], ints[5], ints[6]), s.getGivenTick());

      String type = s.getType();
      switch (type) {
        case "Rectangle":
          svg.append(String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" "
                  + "width=\"%d\" height=\"%d\" "
                  + "fill=\"%s\" visibility=\"visible\">\n", s.getName(), s.getPosition().getX(),
              s.getPosition().getY(), s.getDimension().getWidth(), s.getDimension().getHeight(),
              this.createRGB(s.getColor())));
          svg.append(rectangleHelper(s));
          svg.append("</rect>\n");
          break;
        case "Ellipse":
          svg.append(
              String.format("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" "
                      + "fill=\"%s\" visibility=\"visible\">\n", s.getName(),
                  s.getPosition().getX() + s.getDimension().getWidth() / 2,
                  s.getPosition().getY() + s.getDimension().getHeight() / 2,
                  s.getDimension().getWidth() / 2, s.getDimension().getHeight() / 2,
                  this.createRGB(s.getColor())));
          svg.append(ellipseHelper(s, s.getDimension().getWidth(), s.getDimension().getHeight()));
          svg.append("</ellipse>\n");
          break;
        case "Plus":
          IModelShape copyShape = new AModelShape(s.getPosition(), s.getDimension(), s.getColor(),
              s.getName()) {
            @Override
            public String getType() {
              return s.getType();
            }
          };
          copyShape.setMotions(s.getMotions());

          svg.append(horizontalPlus(s));
          svg.append(verticalPlus(copyShape));
          break;
        default:
          break;
      }
    }
    svg.append("</svg>");
    return svg.toString();
  }

  /**
   * return the horizontal bar of the plus sign.
   *
   * @param s the given plus shape.
   * @return the xml of horizontal bar.
   */
  private String horizontalPlus(IModelShape s) {
    StringBuilder hor = new StringBuilder();
    hor.append(String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" "
            + "width=\"%d\" height=\"%d\" "
            + "fill=\"%s\" visibility=\"visible\">\n", s.getName(),
        s.getPosition().getX() + s.getDimension().getWidth() / 4,
        s.getPosition().getY(), s.getDimension().getWidth() / 2, s.getDimension().getHeight(),
        this.createRGB(s.getColor())));

    hor.append(horHelper(s));
    hor.append("</rect>\n");
    return hor.toString();
  }

  /**
   * Describes the motions, as a xml-based format, of the given horizontal bar of the plus shape.
   *
   * @param shape the given shape
   * @return the xml format for motions of horizontal bar.
   */
  private String horHelper(IModelShape shape) {
    StringBuilder hori = new StringBuilder();
    List<IMotion> motions = shape.getMotions();

    for (IMotion m : motions) {
      int start = (1000 * m.getStartTime()) / frameSecond;
      int duration = (1000 * m.getDuration()) / frameSecond;
      String name = m.getName();
      switch (name) {
        case "Motion Move":
          MotionMove move = (MotionMove) m;
          shape.applyMotion(m, m.getStartTime());
          if (move.getToPosition().getX() - move.getFromPosition().getX()
              != shape.getDimension().getWidth() / 4) {
            hori.append(this.animate(start, duration));
            hori.append(
                String.format("attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getX() + shape.getDimension().getWidth() / 4,
                    move.getToPosition().getX() + shape.getDimension().getWidth() / 4));
          }
          if (move.getToPosition().getY() != move.getFromPosition().getY()) {
            hori.append(this.animate(start, duration));
            hori.append(
                String.format("attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getY(), move.getToPosition().getY()));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;

        case "Motion Dimension":
          MotionDimension dimension = (MotionDimension) m;
          shape.applyMotion(m, m.getStartTime());

          if ((dimension.getFromDimension().getWidth() / 2 != dimension.getToDimension()
              .getWidth()) || (dimension.getFromDimension().getWidth() != dimension.getToDimension()
              .getWidth() / 2)) {
            hori.append(this.animate(start, duration));
            hori.append(
                String.format("attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getWidth() / 2,
                    dimension.getToDimension().getWidth() / 2));
            hori.append(this.animate(start, duration));
            hori.append(
                String.format("attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getX() + shape.getDimension().getWidth() / 4,
                    shape.getPosition().getX() + dimension.getToDimension().getWidth() / 4));
          }

          if (dimension.getFromDimension().getHeight() != dimension.getToDimension().getHeight()) {
            hori.append(this.animate(start, duration));
            hori.append(
                String.format("attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getHeight(), dimension.getToDimension().getHeight()));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Color":
        default:
          break;
      }
    }
    return hori.toString();
  }

  /**
   * returns the vertical bar of the plus sign.
   *
   * @param s the given plus shape.
   * @return the xml of horizontal bar.
   */
  private String verticalPlus(IModelShape s) {
    StringBuilder ver = new StringBuilder();
    ver.append(String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" "
            + "width=\"%d\" height=\"%d\" "
            + "fill=\"%s\" visibility=\"visible\">\n", s.getName(),
        s.getPosition().getX(), s.getPosition().getY() + s.getDimension().getWidth() / 4,
        s.getDimension().getWidth(), s.getDimension().getHeight() / 2,
        this.createRGB(s.getColor())));
    System.out.println(s.getDimension().getWidth());
    ver.append(vertHelper(s));
    ver.append("</rect>\n");
    return ver.toString();
  }

  /**
   * Describes the motions, as a xml-based format, of the given vertical bar of the plus shape.
   *
   * @param shape the given shape
   * @return the xml format for motions of vertical bar.
   */
  private String vertHelper(IModelShape shape) {
    StringBuilder vert = new StringBuilder();
    List<IMotion> motions = shape.getMotions();
    for (IMotion m : motions) {
      int start = (1000 * m.getStartTime()) / frameSecond;
      int duration = (1000 * m.getDuration()) / frameSecond;
      int x;
      String name = m.getName();

      switch (name) {
        case "Motion Move":
          MotionMove move = (MotionMove) m;
          shape.applyMotion(m, m.getStartTime());
          if (move.getToPosition().getX() != move.getFromPosition().getX()) {
            vert.append(this.animate(start, duration));
            vert.append(
                String.format("attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getX(),
                    move.getToPosition().getX()));

          }
          if (move.getToPosition().getY() - move.getFromPosition().getY()
              != shape.getDimension().getWidth() / 4) {
            vert.append(this.animate(start, duration));
            vert.append(
                String.format("attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getY() + shape.getDimension().getWidth() / 4,
                    move.getToPosition().getY() + shape.getDimension().getWidth() / 4));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;

        case "Motion Dimension":
          MotionDimension dimension = (MotionDimension) m;
          shape.applyMotion(m, m.getStartTime());
          if (dimension.getFromDimension().getWidth() != dimension.getToDimension()
              .getWidth()) {
            vert.append(this.animate(start, duration));
            vert.append(
                String.format("attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getWidth(), dimension.getToDimension().getWidth()));
          }
          if ((dimension.getFromDimension().getHeight() / 2 != dimension.getToDimension()
              .getHeight()) || (dimension.getFromDimension().getHeight()
              != dimension.getToDimension().getHeight() / 2)) {
            vert.append(this.animate(start, duration));
            vert.append(
                String.format("attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getHeight() / 2,
                    dimension.getToDimension().getHeight() / 2));
            vert.append(this.animate(start, duration));
            vert.append(
                String.format("attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getY() + shape.getDimension().getWidth() / 4,
                    shape.getPosition().getY() + dimension.getToDimension().getWidth() / 4));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Color":
        default:
          break;
      }
    }
    return vert.toString();
  }

  /**
   * create a rgb for svg.
   *
   * @param color the given color
   * @return the xml format for color.
   */
  private String createRGB(Color color) {
    return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
  }

  /**
   * Describes the motions, as a xml-based format, of the given rectangle by using the given speed.
   *
   * @param shape the given rectangle
   * @return the xml-based format of the motions of the given rectangle
   */
  private String rectangleHelper(IModelShape shape) {
    StringBuilder rec = new StringBuilder();
    List<IMotion> motions = shape.getMotions();
    for (IMotion m : motions) {
      int start = (1000 * m.getStartTime()) / frameSecond;
      int duration = (1000 * m.getDuration()) / frameSecond;
      String name = m.getName();
      switch (name) {
        case "Motion Move":
          MotionMove move = (MotionMove) m;
          shape.applyMotion(m, m.getStartTime());
          if (move.getToPosition().getX() != move.getFromPosition().getX()) {
            rec.append(this.animate(start, duration));
            rec.append(
                String.format("attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getX(), move.getToPosition().getX()));
          }
          if (move.getToPosition().getY() != move.getFromPosition().getY()) {
            rec.append(this.animate(start, duration));
            rec.append(
                String.format("attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getY(), move.getToPosition().getY()));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Dimension":
          MotionDimension dimension = (MotionDimension) m;
          shape.applyMotion(m, m.getStartTime());
          if (dimension.getFromDimension().getWidth() != dimension.getToDimension().getWidth()) {
            rec.append(this.animate(start, duration));
            rec.append(
                String.format("attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getWidth(), dimension.getToDimension().getWidth()));
          }
          if (dimension.getFromDimension().getHeight() != dimension.getToDimension().getHeight()) {
            rec.append(this.animate(start, duration));
            rec.append(
                String.format("attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getHeight(), dimension.getToDimension().getHeight()));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Color":
          MotionColor color = (MotionColor) m;
          shape.applyMotion(m, m.getStartTime());
          int fR = color.getFromColor().getRed();
          int fG = color.getFromColor().getGreen();
          int fB = color.getFromColor().getBlue();
          int tR = color.getToColor().getRed();
          int tG = color.getToColor().getGreen();
          int tB = color.getToColor().getBlue();
          if (fR != tR || fG != tG || fB != tB) {
            rec.append(this.animate(start, duration));
            rec.append(
                String.format("attributeName=\"fill\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    createRGB(shape.getColor()), createRGB(color.getToColor())));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        default:
          break;
      }
    }
    return rec.toString();
  }

  /**
   * create animate format for rectangle svg.
   *
   * @param start    the start time
   * @param duration the duration
   * @return the animate format.
   */
  private String animate(int start, int duration) {
    return String.format("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" ",
        start, duration);
  }

  /**
   * Describes the motions, as a xml-based format, of the given ellipse by using the given speed.
   *
   * @param shape the given ellipse
   * @param w     the given width
   * @param h     the given height
   * @return the xml-based format of the motions of the given ellipse
   */
  private String ellipseHelper(IModelShape shape, int w, int h) {
    StringBuilder ell = new StringBuilder();
    List<IMotion> motions = shape.getMotions();
    for (IMotion m : motions) {
      int start = (1000 * m.getStartTime()) / frameSecond;
      int duration = (1000 * m.getDuration()) / frameSecond;
      String name = m.getName();
      switch (name) {
        case "Motion Move":
          MotionMove move = (MotionMove) m;
          shape.applyMotion(m, m.getStartTime());
          if (move.getToPosition().getX() + w / 2 != shape.getPosition().getX() + w / 2) {
            ell.append(this.animate(start, duration));
            ell.append(
                String.format("attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getX() + w / 2, move.getToPosition().getX() + w / 2));
          }
          if (move.getToPosition().getY() + h / 2 != shape.getPosition().getY() + h / 2) {
            ell.append(this.animate(start, duration));
            ell.append(
                String.format("attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getPosition().getY() + h / 2, move.getToPosition().getY() + h / 2));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Dimension":
          MotionDimension dimension = (MotionDimension) m;
          shape.applyMotion(m, m.getStartTime());
          if (dimension.getFromDimension().getWidth() / 2
              != dimension.getToDimension().getWidth() / 2) {
            ell.append(this.animate(start, duration));
            ell.append(
                String.format("attributeName=\"rx\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getWidth() / 2,
                    dimension.getToDimension().getWidth() / 2));
          }
          if (dimension.getFromDimension().getHeight() / 2
              != dimension.getToDimension().getHeight() / 2) {
            ell.append(this.animate(start, duration));
            ell.append(
                String.format("attributeName=\"ry\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
                    shape.getDimension().getHeight() / 2,
                    dimension.getToDimension().getHeight() / 2));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        case "Motion Color":
          MotionColor color = (MotionColor) m;
          shape.applyMotion(m, m.getStartTime());
          int fR = color.getFromColor().getRed();
          int fG = color.getFromColor().getGreen();
          int fB = color.getFromColor().getBlue();
          int tR = color.getToColor().getRed();
          int tG = color.getToColor().getGreen();
          int tB = color.getToColor().getBlue();
          if (fR != tR || fG != tG || fB != tB) {
            ell.append(this.animate(start, duration));
            ell.append(
                String.format("attributeName=\"fill\" from=\"%s\" to=\"%s\" fill=\"freeze\"/>\n",
                    createRGB(shape.getColor()), createRGB(color.getToColor())));
          }
          for (int i = m.getStartTime() + 1; i < m.getEndTime(); i++) {
            shape.apply(i);
          }
          break;
        default:
          break;
      }
    }
    return ell.toString();
  }
}
