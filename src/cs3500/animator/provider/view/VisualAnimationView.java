package cs3500.animator.provider.view;

import cs3500.animator.provider.model.Canvas;
import cs3500.animator.provider.model.ImmutableAnimationModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * A class that represents a visual view of animation models. However, it is a read-only
 * view. The animation ends when the last instruction is executed.
 */
public class VisualAnimationView extends JFrame implements AnimationView {

  protected ImmutableAnimationModel model;
  protected int tick;
  protected double tickPerSec;
  protected Timer timer;

  /**
   * Create a default visual view for an animation.
   */
  public VisualAnimationView() {
    this.model = null;
    this.tick = 0;
    this.tickPerSec = 1.0;
    this.timer = null;
  }

  /**
   * Construct a new visual animation viewer based on the given inputs.
   * @param model the corresponding model
   * @param tickPerSec tick-per-second of the animation
   */
  public VisualAnimationView(ImmutableAnimationModel model, double tickPerSec) {
    super("Visual Animation View");
    this.model = Objects.requireNonNull(model);
    if (tickPerSec <= 0) {
      throw new IllegalArgumentException("Tick-per-second has to be positive.");
    }
    this.tick = 0;
    this.tickPerSec = tickPerSec;
    this.timer = null;
  }

  @Override
  public void setUpView(ImmutableAnimationModel model, Appendable ap, double tickPerSec) {
    this.model = Objects.requireNonNull(model);
    if (tickPerSec <= 0) {
      throw new IllegalArgumentException("Tick-per-second has to be positive.");
    }
    this.tick = 0;
    this.tickPerSec = tickPerSec;
    this.timer = null;
  }

  @Override
  public void render() throws IOException {
    AnimationPanel panel = this.createPanel();
    add(new JScrollPane(panel));
    setVisible(true);

    this.timer = new Timer((int)(1000 / this.tickPerSec), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.addAllShapes(model.getShapesAt(tick));
        panel.repaint();
        tick += 1;
      }
    });

    this.timer.start();
  }

  /**
   * Set up the GUI features for this view.
   * @return a JPanel that draws the current shapes in the animation
   */
  protected AnimationPanel createPanel() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(new Dimension(800, 800));

    Canvas canvas = model.getCanvas();
    AnimationPanel panel = new AnimationPanel(canvas);
    panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));

    return panel;
  }
}
