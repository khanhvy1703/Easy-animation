package mocks;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.Timer;

/**
 * Class that Mocking IController, The class is used for the testing.
 */
public class MockController implements IController, ActionListener {

  //private final Readable in;
  private final Appendable out;
  private IView view;
  private IModel model;
  private int frameSecond;
  private Timer timer;
  private IInteractiveView newView;
  private int tick = 0;
  private boolean isLoop;
  private MockView mockV;

  /**
   * Constructor of the Mock Controller.
   *
   * @param out         appendable
   * @param model       model
   * @param view        view
   * @param frameSecond frameSecond
   */
  MockController(Appendable out, IModel model, IView view, int frameSecond) {
    this.out = out;
    this.model = Objects.requireNonNull(model, "null model");
    try {
      this.newView = (InteractiveView) view;
      newView.addListener(this);
    } catch (Exception e) {
      this.view = view;
    }
    this.view = view;
    view.addShape(model.getShapes());
    this.frameSecond = frameSecond;
    timer = new Timer(1000 / frameSecond, this);
    this.isLoop = false;
    this.mockV = new MockView(out);
  }

  @Override
  public void goAction() {
    if (newView != null) {
      timer.setActionCommand("start");
    } else {
      timer.setActionCommand("non-interactive view");
      timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "start":
          timer.start();
          model.isVisible(tick);
          model.doActionPerform(tick);
          view.addShape(model.getShapes());
          view.refresh();
          tick = tick + 1;
          if (isLoop && tick > model.getLastTick()) {
            tick = 0;
            timer.start();
            model.doActionPerform(tick);
          }
          mockV.render();
          out.append("start\n");
          out.append("current delay time is is: ").append(String.valueOf(1000 / frameSecond));
          out.append("\ncurrent loop is: ").append(String.valueOf(isLoop)).append("\n");
          break;
        case "pause":
          timer.stop();
          mockV.render();
          out.append("pause\n");
          out.append("current tick is: ").append(String.valueOf(tick));
          out.append("\ncurrent delay time is is: ").append(String.valueOf(1000 / frameSecond));
          out.append("\ncurrent loop is: \n").append(String.valueOf(isLoop));
          break;
        case "resume":
          timer.restart();
          mockV.render();
          out.append("resume\n");
          out.append("\ncurrent delay time is is: ").append(String.valueOf(1000 / frameSecond));
          out.append("\ncurrent loop is: ").append(String.valueOf(isLoop)).append("\n");
          break;
        case "restart":
          mockV.render();
          out.append("resume\n");
          tick = 0;
          timer.start();
          model.doActionPerform(tick);
          break;
        case "speed up":
          int newDelay = (1000 / frameSecond) / 5;
          timer.setDelay(newDelay);
          mockV.render();
          out.append("speed up. The new delay time is : ").append(
              String.valueOf(newDelay)).append("\n");
          break;
        case "loop":
          out.append("loop. The new loop is : ").append(
              String.valueOf(isLoop)).append("\n");
          isLoop = !isLoop;
          mockV.render();
          out.append("loop. The new loop is : ").append(
              String.valueOf(isLoop)).append("\n");
          break;
        case "speed down":
          int downDelay = 5 * (1000 / frameSecond);
          timer.setDelay(5 * (1000 / frameSecond));
          mockV.render();
          out.append("speed down. The new delay time is : ").append(
              String.valueOf(downDelay)).append("\n");
          break;
        case "non-interactive view":
          mockV.render();
          out.append("Well this is the non interactive view.\n");
          break;
        default:
          throw new IllegalArgumentException("Cant find valid input command.");
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
