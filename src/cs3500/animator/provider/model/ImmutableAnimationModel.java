package cs3500.animator.provider.model;

import cs3500.animator.provider.model.shape.IShape;
import java.awt.Color;
import java.util.List;

/**
 * An interface for all read-only models that represent an animation.
 */
public interface ImmutableAnimationModel {

  /**
   * Get a copy of list of shapes in this model.
   * @return a list of names
   */
  List<String> getNames();

  /**
   * Get a copy of list of shapes in this model at the given tick.
   * @param currentTick the tick the animation is currently at
   * @return a list of shapes
   */
  List<IShape> getShapesAt(int currentTick);

  /**
   * Get a copy of the list of instructions in this model.
   * @param name the name of the shape
   * @return a list of instructions
   * @throws IllegalArgumentException if the given name is not in hashmap
   */
  List<Instruction> getInstructionsFor(String name) throws IllegalArgumentException;

  /**
   * Get the shape of the given name.
   * @param name the name of the shape
   * @return the corresponding shape
   * @throws IllegalArgumentException if the given name is not in the hashmap
   */
  IShape getShapeOf(String name) throws IllegalArgumentException;

  /**
   * Get the position of the shape with the given name.
   * @param name the name of the shape we are checking on
   * @return the position of the shape that has the name
   * @throws IllegalArgumentException if the given name is not in hashmap
   */
  Posn getPositionOf(String name) throws IllegalArgumentException;

  /**
   * Get the color of the shape with the given name.
   * @param name the name of the shape we are checking on
   * @return the color of the shape that has the name
   * @throws IllegalArgumentException if the given name is not in hashmap
   */
  Color getColorOf(String name) throws IllegalArgumentException;

  /**
   * Get the size of the shape with the given name.
   * @param name the name of the shape we are checking on
   * @return the size of the shape that has the name
   * @throws IllegalArgumentException if the given name is not in hashmap
   */
  int getSizeOf(String name) throws IllegalArgumentException;

  /**
   * Get the canvas of this animation model.
   * @return a copy of canvas
   */
  Canvas getCanvas();

}
