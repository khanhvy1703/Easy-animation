package mocks;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.JFrameView;
import java.awt.event.ActionEvent;
import org.junit.Test;

/**
 * Tests for controller.
 */
public class ControllerTest {

  IModel model = new Model(0, 0, 80, 80);
  IInteractiveView inter = new InteractiveView();
  IView oldView = new JFrameView();
  StringBuilder app = new StringBuilder();
  MockController con = new MockController(app, model, inter, 15);

  @Test
  public void testStartAndPause() {
    ActionEvent start = new ActionEvent("animation", 1, "start");
    ActionEvent pause =
        new ActionEvent("animation", 1, "pause");
    ActionEvent resume = new ActionEvent("animation", 1, "resume");
    ActionEvent restart =
        new ActionEvent("animation", 1, "restart");

    con.actionPerformed(start);
    con.actionPerformed(pause);
    con.actionPerformed(resume);
    con.actionPerformed(restart);

    con.goAction();
    assertEquals("You pressed: \n"
        + "start\n"
        + "current delay time is is: 66\n"
        + "current loop is: false\n"
        + "You pressed: \n"
        + "pause\n"
        + "current tick is: 1\n"
        + "current delay time is is: 66\n"
        + "current loop is: \n"
        + "falseYou pressed: \n"
        + "resume\n"
        + "\n"
        + "current delay time is is: 66\n"
        + "current loop is: false\n"
        + "You pressed: \n"
        + "resume\n", app.toString());
  }

  @Test
  public void testSpeedAndLoop() {
    ActionEvent start = new ActionEvent("animation", 1, "start");
    ActionEvent loop =
        new ActionEvent("animation", 1, "loop");
    ActionEvent speedUp =
        new ActionEvent("animation", 1, "speed up");

    con.actionPerformed(start);
    con.actionPerformed(speedUp);
    con.actionPerformed(loop);

    con.goAction();
    assertEquals("You pressed: \n"
        + "start\n"
        + "current delay time is is: 66\n"
        + "current loop is: false\n"
        + "You pressed: \n"
        + "speed up. The new delay time is : 13\n"
        + "loop. The new loop is : false\n"
        + "You pressed: \n"
        + "loop. The new loop is : true\n", app.toString());
  }

  @Test
  public void testNonInter() {
    MockController con = new MockController(app, model, oldView, 15);
    ActionEvent nonInter =
        new ActionEvent("animation", 1, "non-interactive view");
    con.actionPerformed(nonInter);
    con.goAction();
    assertEquals("You pressed: \n"
        + "Well this is the non interactive view.\n", app.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    MockController con = new MockController(app, model, oldView, 15);
    ActionEvent nonInter =
        new ActionEvent("animation", 1, "hahahahahah");
    con.actionPerformed(nonInter);
    con.goAction();
  }
}
