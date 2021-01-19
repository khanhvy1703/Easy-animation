package cs3500.animator.provider.controller;

/**
 * The interface for all controllers that set up and run an animation.
 */
public interface IAnimationController {

  /**
   * Begins the animation given the speed of the animation, type of view being used, the input and
   * output files.
   *
   * @param speed      the tempo or ticks per second of the animation
   * @param viewType   the type of view
   * @param inputFile  pathname for the input file
   * @param outputFile pathname for the output file
   */
  void playAnimation(double speed, String viewType, String inputFile, String outputFile);

}
