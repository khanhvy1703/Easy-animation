package cs3500.animator.provider.view;

/**
 * An interface for all views that incorporate interactive features like starting, stopping,
 * looping, and changing the speed of an animation.
 */
public interface InteractiveAnimationView {

  /**
   * Begins the animation.
   */
  void start();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Resumes the animation.
   */
  void resume();

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Allows the animation to loop once it has reached the end.
   */
  void enableLoop();

  /**
   * Does not allow the animation to loop.
   */
  void disableLoop();

  /**
   * Increases the rate of the animation by a ratio of 1.5.
   */
  void increaseSpeed();

  /**
   * Decreases the rate of the animation by a ration of 1.5.
   */
  void decreaseSpeed();
}
