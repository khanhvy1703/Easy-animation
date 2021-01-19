package cs3500.animator.view;

import cs3500.animator.shape.IModelShape;
import java.util.ArrayList;

/**
 * Interface IView of the view. It has 2 methods to add more shapes and repaint the view.
 */
public interface IView {

  /**
   * Repaints the view.
   */
  void refresh();

  /**
   * Setter methods of the View.
   *
   * @param s given list of shapes
   */
  void addShape(ArrayList<IModelShape> s);


  /**
   * Represents the views that display the animation by returns the description of the animation or
   * output of svg file as a string, which is depended on the input. throws
   * UnsupportedOperationException if it is a visual view.
   *
   * @return view as text or svg file
   * @throws UnsupportedOperationException if it is called in visual view.
   */
  String getTextualView();
}