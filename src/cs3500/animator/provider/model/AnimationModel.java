package cs3500.animator.provider.model;

import cs3500.animator.provider.model.shape.IShape;
import java.util.HashMap;
import java.util.List;

/**
 * An interface for all models of an animation that modifies the properties of shapes like their
 * positions, colors, width, and height.
 */
public interface AnimationModel extends ImmutableAnimationModel {

  /**
   * Start the animation with the given hashmap of names and shapes.
   *
   * @param shapes the hashmap where keys are names of shapes, and shapes themselves
   * @throws IllegalArgumentException is shapes or instructions are null
   */
  void startAnimation(HashMap<String, IShape> shapes, List<Instruction> instructions, Canvas canvas)
      throws IllegalArgumentException;

  /**
   * Add a single shape and its name.
   *
   * @param name the name of the shape
   * @param s    the shape to be added
   * @throws IllegalArgumentException if the given name is already in this model
   */
  void addShape(String name, IShape s) throws IllegalArgumentException;

  /**
   * Remove a single shape.
   *
   * @param name the name of the shape
   * @throws IllegalArgumentException if the given name is not in this model
   */
  void deleteShape(String name) throws IllegalArgumentException;

  /**
   * Add a single instruction into this model.
   *
   * @param inst the given instruction
   * @throws IllegalArgumentException if the instruction does not match with any shape in the model
   */
  void addInstruction(Instruction inst) throws IllegalArgumentException;

}
