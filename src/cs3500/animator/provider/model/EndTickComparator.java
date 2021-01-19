package cs3500.animator.provider.model;

import java.util.Comparator;
import java.util.Objects;

/**
 * A class solely designed to compare end-tick of instructions to get a
 * descending order of ticks.
 */
public class EndTickComparator implements Comparator<Instruction> {

  /**
   * Compare two instructions.
   * @param o1 the first instruction
   * @param o2 the second instruction
   * @return an integer representing their ordering
   */
  @Override
  public int compare(Instruction o1, Instruction o2) {
    Objects.requireNonNull(o1);
    Objects.requireNonNull(o2);

    return o2.getEndTick() - o1.getEndTick();
  }
}
