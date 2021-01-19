package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.PlayBackAnimationController;
import cs3500.animator.provider.model.EndTickComparator;
import cs3500.animator.provider.model.Instruction;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * Represents a visual view of animation models with interactive features. Users can start, pause,
 * resume, restart, enable loop, disable loop, and change the speed of the animation.
 */
public class InteractiveView extends VisualAnimationView implements InteractiveAnimationView {

  private final JLabel startLabel;
  private final JLabel pauseLabel;
  private final JLabel restartLabel;
  private final JLabel loopLabel;
  private final JLabel noLoopLabel;
  private final JLabel incSpeedLabel;
  private final JLabel decSpeedLabel;
  private boolean looping;

  /**
   * Constructs a default view with interactive features.
   */
  public InteractiveView() {
    super();
    this.looping = false;
    this.startLabel = new JLabel("Start (s)");
    this.pauseLabel = new JLabel("Pause (p)");
    this.restartLabel = new JLabel("Restart (r)");
    this.loopLabel = new JLabel("Enable Looping (l)");
    this.noLoopLabel = new JLabel("Disable Looping (d)");
    this.incSpeedLabel = new JLabel("Increase Speed (↑)");
    this.decSpeedLabel = new JLabel("Decrease Speed (↓)");
  }

  @Override
  public void render() throws IOException {
    this.start();
  }

  @Override
  public void start() {
    System.out.println("start");
    if (this.timer != null) {
      this.resume();
      return;
    }

    JPanel panel = new JPanel(new GridBagLayout());
    AnimationPanel animationPanel = this.createPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.5;
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(this.startLabel, gbc);

    gbc.gridx = 1;
    panel.add(this.pauseLabel, gbc);

    gbc.gridx = 2;
    panel.add(this.restartLabel, gbc);

    gbc.gridx = 3;
    panel.add(this.loopLabel, gbc);

    gbc.gridx = 4;
    panel.add(this.noLoopLabel, gbc);

    gbc.gridx = 5;
    panel.add(this.incSpeedLabel, gbc);

    gbc.gridx = 6;
    panel.add(this.decSpeedLabel, gbc);

    gbc.weightx = 0.0;
    gbc.ipady = 700;
    gbc.gridwidth = 7;
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JScrollPane(animationPanel), gbc);

    add(panel);
    setVisible(true);
    addKeyListener(new PlayBackAnimationController(this));

    int maxEndTick = this.getMaxEndTick();
    this.timer = new Timer((int)(1000 / this.tickPerSec), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animationPanel.addAllShapes(model.getShapesAt(tick));
        animationPanel.repaint();
        tick += 1;
        System.out.println(tick);
        if (looping && tick > maxEndTick) {
          tick = 0;
        }
      }
    });

    this.timer.start();
    this.startLabel.setForeground(Color.GREEN);
    this.pauseLabel.setForeground(Color.BLACK);
    this.restartLabel.setForeground(Color.BLACK);
    this.loopLabel.setForeground(Color.BLACK);
    this.noLoopLabel.setForeground(Color.RED);
    this.incSpeedLabel.setForeground(Color.BLACK);
    this.decSpeedLabel.setForeground(Color.BLACK);
  }

  @Override
  public void pause() {
    System.out.println("pause");
    this.timer.stop();
    this.startLabel.setForeground(Color.BLACK);
    this.pauseLabel.setForeground(Color.RED);
    this.restartLabel.setForeground(Color.BLACK);
  }

  @Override
  public void resume() {
    System.out.println("resume");
    this.timer.start();
    this.startLabel.setForeground(Color.GREEN);
    this.pauseLabel.setForeground(Color.BLACK);
    this.restartLabel.setForeground(Color.BLACK);
  }

  @Override
  public void restart() {
    System.out.println("restart");
    this.tick = 0;
    this.timer.restart();
    this.startLabel.setForeground(Color.BLACK);
    this.pauseLabel.setForeground(Color.BLACK);
    this.restartLabel.setForeground(Color.BLUE);
  }

  @Override
  public void enableLoop() {
    System.out.println("en loop");
    this.looping = true;
    this.loopLabel.setForeground(Color.GREEN);
    this.noLoopLabel.setForeground(Color.BLACK);
  }

  @Override
  public void disableLoop() {
    System.out.println("dis loop");
    this.looping = false;
    this.loopLabel.setForeground(Color.BLACK);
    this.noLoopLabel.setForeground(Color.RED);
  }

  @Override
  public void increaseSpeed() {
    System.out.println("up");
    this.tickPerSec *= 1.5;
    this.timer.setDelay((int) (1000 / this.tickPerSec));
    this.incSpeedLabel.setForeground(Color.GREEN);
    this.decSpeedLabel.setForeground(Color.BLACK);
  }

  @Override
  public void decreaseSpeed() {
    System.out.println("down");
    this.tickPerSec /= 1.5;
    this.timer.setDelay((int) (1000 / this.tickPerSec));
    this.incSpeedLabel.setForeground(Color.BLACK);
    this.decSpeedLabel.setForeground(Color.RED);
  }

  /**
   * Get the final tick from the list of motions
   * given to the animation model in this view.
   * @return the end tick of the very last motion
   */
  private int getMaxEndTick() {
    int maxEndTick = 0;
    for (String name : this.model.getNames()) {
      List<Instruction> instructions = new ArrayList<>(this.model.getInstructionsFor(name));
      instructions.sort(new EndTickComparator());
      int currEndTick = instructions.get(0).getEndTick();

      if (currEndTick > maxEndTick) {
        maxEndTick = currEndTick;
      }
    }

    return maxEndTick;
  }
}
