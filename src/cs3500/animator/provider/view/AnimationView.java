package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ImmutableAnimationModel;
import java.io.IOException;

/**
 * The interface that renders views of all kinds of animation models.
 */
public interface AnimationView {

  /**
   * Sets up this view with the given model, appendable, and tempo.
   * @param model a model for animations
   * @param ap an appendable for getting the output of this view
   * @param tickPerSec the tempo or the rate at which the animation runs
   */
  void setUpView(ImmutableAnimationModel model, Appendable ap, double tickPerSec);

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

}
