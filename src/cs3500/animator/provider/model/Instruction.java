package cs3500.animator.provider.model;

import java.awt.Color;
import java.util.List;

/**
 * An interface for the instructions of motions given to the model.
 */
public interface Instruction {
  /**
   * Get name of the shape we are working on.
   * @return a string represents name
   */
  String getName();

  /**
   * Get starting tick of this instruction.
   * @return starting tick
   */
  int getStartTick();

  /**
   * Get starting tick of this instruction.
   * @return starting tick
   */
  int getEndTick();

  /**
   * Get the position that the shape will be in at the given tick.
   * @param currentTick current tick we are at
   * @return the predicted position
   */
  Posn getPositionAt(int currentTick);

  /**
   * Get the length along x-axis at the given tick.
   * @param currentTick current tick we are at
   * @return length along x-axis
   */
  int getXValAt(int currentTick);

  /**
   * Get the length along y-axis at the given tick.
   * @param currentTick current tick we are at
   * @return length along y-axis
   */
  int getYValAt(int currentTick);

  /**
   * Get the color at the given tick.
   * @param currentTick current tick we are at
   * @return color at this tick
   */
  Color getColorAt(int currentTick);

  /**
   * Checks that the last state of this instruction matches
   * the first state of the other instruction.
   * @param other the next instruction
   */
  void isFollowedBy(Instruction other);

  /**
   * A custom toString method that takes how many ticks in one second.
   * @param tickPerSec the tempo
   * @return the concatenated string of the properties of this instruction
   *         with the given tempo
   */
  String toString(double tickPerSec);

  /**
   * Get the labels for the attributes that change from the first state to the last state
   * in this instruction.
   * A label is a short string that describes a property.
   * @return a list of labels
   */
  List<String> getChanges();

  /**
   * Given the label for an attribute in this instruction, get the changing values of the attribute
   * from the first state to the last state as a concatenated string.
   * @param att the label for an attribute
   * @return a concatenated string of the first and last values of an attribute
   */
  String getChangeInfo(String att, String shapeType);
}
