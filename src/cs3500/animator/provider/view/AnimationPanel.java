package cs3500.animator.provider.view;

import cs3500.animator.provider.model.Canvas;
import cs3500.animator.provider.model.shape.IShape;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;

/**
 * The class represents the panel where we paint our shapes, a helper class of VisualAnimationView.
 */
public class AnimationPanel extends JPanel {

  private List<IShape> shapes;
  private final Canvas canvas;

  /**
   * Create a panel based on the list of shapes given.
   * @param shapes a list of shapes
   * @param canvas the field on which the given shapes should be drawn
   */
  public AnimationPanel(List<IShape> shapes, Canvas canvas) {
    Objects.requireNonNull(shapes);
    this.shapes = new ArrayList<>(shapes);
    this.canvas = Objects.requireNonNull(canvas);
  }

  /**
   * Create a default panel for animations.
   * @param canvas the field on which the given shapes should be drawn
   */
  public AnimationPanel(Canvas canvas) {
    this.shapes = new ArrayList<>();
    this.canvas = Objects.requireNonNull(canvas);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (IShape s : shapes) {
      s.draw(g, canvas);
    }
  }

  /**
   * Set the given list of shapes to the list of shapes in this AnimationPanel.
   * @param shapes a list of shapes
   */
  void addAllShapes(List<IShape> shapes) {
    Objects.requireNonNull(shapes);
    this.shapes = new ArrayList<>(shapes);
    System.out.println(shapes);
  }

}
