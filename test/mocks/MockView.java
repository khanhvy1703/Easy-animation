package mocks;

import java.io.IOException;

/**
 * Class that Mocking IView, The class is used for the testing.
 */
public class MockView {

  private Appendable ap;

  /**
   * Constructor of Mock View.
   *
   * @param ap appendable
   */
  MockView(Appendable ap) {
    this.ap = ap;
  }

  /**
   * Method that render the event from the controller.
   *
   * @throws IOException Exception when output is invalid.
   */
  public void render() throws IOException {
    this.ap.append("You pressed: \n");
  }
}
