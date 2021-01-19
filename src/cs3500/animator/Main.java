package cs3500.animator;


import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.motion.IMotion;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;

/**
 * Main application to test the visual view without the files.
 */
public class Main {

  /**
   * Runs the arguments.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    IModel model = new Model(0, 0, 800, 800);
    //InteractiveView iv = new InteractiveView();
    //IController c = new Controller(iv, model, 1);

    IModelShape el = new AModelShape(new Position(10, 10), new Dimension(30, 90),
        new Color(0, 59, 49), "R") {
      @Override
      public String getType() {
        return "Ellipse";
      }
    };

    IModelShape rec = new AModelShape(new Position(100, 100),
        new Dimension(30, 90),
        new Color(90, 80, 150), "R") {
      @Override
      public String getType() {
        return "Rectangle";
      }
    };

    IModelShape plus = new AModelShape(new Position(200, 200),
        new Dimension(50, 50),
        new Color(45, 100, 250), "P1") {
      @Override
      public String getType() {
        return "Plus";
      }
    };

    IMotion move = new MotionMove(1, 5, new Position(340, 56));
    IMotion move1 = new MotionMove(5, 10, new Position(100, 600));
    IMotion move2 = new MotionMove(10, 18, new Position(34, 56));

    IMotion color = new MotionColor(1, 10,
        new Color(250, 250, 250));
    IMotion color1 = new MotionColor(10, 15,
        new Color(90, 50, 100));
    IMotion dimension = new MotionDimension(15, 20,
        new Dimension(90, 50));
    IMotion color2 = new MotionColor(15, 20,
        new Color(250, 70, 150));

    el.addMotion(color);
    el.addMotion(color1);
    el.addMotion(dimension);
    el.addMotion(color2);

    rec.addMotion(move);
    rec.addMotion(move1);
    rec.addMotion(move2);

    model.addShape(el);
    model.addShape(rec);
    model.addShape(plus);

    //c.goAction();
    IView view = new TextualView(model, 15);
    IView svg = new SVGView(model, 15);
    System.out.println(svg.getTextualView());
  }
}