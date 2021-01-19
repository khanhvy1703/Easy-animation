package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ImmutableAnimationModel;
import cs3500.animator.provider.model.Instruction;
import java.io.IOException;
import java.util.Objects;

/**
 * The class that renders an animation as text for all kinds of animation models. It is read-only.
 */
public class TextualView implements AnimationView {

  private ImmutableAnimationModel model;
  private Appendable ap;
  private double tickPerSec;

  /**
   * Construct a default textual view.
   */
  public TextualView() {
    this.model = null;
    this.ap = null;
    this.tickPerSec = 1.0;
  }

  /**
   * Construct a textual shape viewer.
   * @param model the model we are rendering on
   * @param ap where we appending our textual view
   */
  public TextualView(ImmutableAnimationModel model, Appendable ap) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(ap);

    this.model = model;
    this.ap = ap;
    this.tickPerSec = 5.0;
  }

  /**
   * Construct a textual shaper viewer based on the given inputs.
   * @param model the corresponding model
   * @param ap the tool to append our output
   * @param tickPerSec tick-per-second of the animation
   */
  public TextualView(ImmutableAnimationModel model, Appendable ap, double tickPerSec) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(ap);

    this.model = model;
    this.ap = ap;
    if (tickPerSec <= 0) {
      throw new IllegalArgumentException("Tick-per-second has to be positive.");
    }
    this.tickPerSec = tickPerSec;
  }

  /**
   * Get the textual representation of the model of this view.
   * @return the textual view this the model we are working on
   */
  @Override
  public String toString() {
    String str = "canvas " + this.model.getCanvas().toString() + "\n";

    for (String name : this.model.getNames()) {
      str += "shape " + name + " " + this.model.getShapeOf(name).toString() + "\n";
      for (Instruction inst : this.model.getInstructionsFor(name)) {
        str += "motion " + inst.toString(tickPerSec) + "\n";
      }
    }

    return str;
  }

  @Override
  public void setUpView(ImmutableAnimationModel model, Appendable ap, double tickPerSec) {
    this.model = Objects.requireNonNull(model);
    this.ap = Objects.requireNonNull(ap);
    this.tickPerSec = tickPerSec;
  }

  @Override
  public void render() throws IOException {
    this.ap.append(this.toString());
  }

}
