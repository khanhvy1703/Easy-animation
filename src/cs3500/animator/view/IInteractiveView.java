package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * The new view interface works as an interactive view. It takes Action Listener which allows users
 * to interact with the animation by starting, stopping, pausing, rewinding and making changes of
 * the speed.
 */
public interface IInteractiveView extends IView {

  /**
   * method to add the listeners into the action listener.
   *
   * @param listener is the given action listener
   */
  void addListener(ActionListener listener);

  /**
   * Get the string inside of the input value.
   * @return the input value
   */
  String getTextValue();

  /**
   * Clear the text input field.
   */
  void clearTextValue();
}
