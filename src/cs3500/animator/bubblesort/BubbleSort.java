package cs3500.animator.bubblesort;

import java.util.ArrayList;
import cs3500.animator.model.IModel;
import cs3500.animator.shape.AModelShape;

/**
 * This class implements the sorting strategy using the simple bubble sort of algorithm, which
 * compares the dimension of the shapes and then sorts them from the smallest to the largest one.
 */
public class BubbleSort {

  private final IModel m;
  private final ArrayList<AModelShape> as;
  private AModelShape[] arr;

  /**
   * Constructor of the class has 1 parameter, which is the model.
   *
   * @param m is the model
   */
  public BubbleSort(IModel m) {
    this.m = m;
    this.as = new ArrayList<>();
  }

  /**
   * puts all the elements of as into arr.
   */
  private void setArr() {
    int n = as.size();
    arr = new AModelShape[n];
    for (int i = 0; i < as.size(); i++) {
      arr[i] = as.get(i);
    }
  }

  /**
   * Adds the given shape into the list, throw IllegalArgument if the shape is already in the list.
   *
   * @param shape the given shape
   */
  public void addAModelShape(AModelShape shape) {
    for (AModelShape s : as) {
      if (shape.getName().equals(s.getName())) {
        throw new IllegalArgumentException("Same shape");
      }
    }
    as.add(shape);
  }

  /**
   * Get initial data of all the shapes as a string, which includes information of canvas, shapes
   * and their initial position.
   *
   * @return initial data as a string.
   */
  private String getGameTxt() {
    setArr();
    StringBuilder str = new StringBuilder();
    str.append(String.format("canvas %d %d %d %d\n", m.getXCanvas(), m.getYCanvas(), m.getWCanvas(),
        m.getHCanvas()));

    // show shapes and their name.
    for (AModelShape s : as) {
      str.append(String.format("shape %s %s\n", s.getName(), s.getType().toLowerCase()));
    }

    // shows initial position, dimension and color of all shapes
    for (AModelShape s : as) {
      str.append(String.format("motion %s 1 %d %d %d %d %d %d %d 1 %d %d %d %d %d %d %d\n",
          s.getName(), s.getPosition().getX(), s.getPosition().getY(),
          s.getDimension().getWidth(), s.getDimension().getHeight(),
          s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue(),
          s.getPosition().getX(), s.getPosition().getY(),
          s.getDimension().getWidth(), s.getDimension().getHeight(),
          s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue()));
    }
    return str.toString();
  }

  /**
   * Prints out the first motion of the given shapes at the current tick, which position is
   * swapped.
   *
   * @param tick   is the current tick
   * @param shape  the 1st shape
   * @param shape2 the 2nd shape
   * @return the first motion as a string
   */
  private String printFirstMotion(int tick, AModelShape shape, AModelShape shape2) {
    StringBuilder firstMotion = new StringBuilder();
    firstMotion.append(String.format("motion %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d\n",
        shape.getName(), tick, shape2.getPosition().getX(), shape2.getPosition().getY(),
        shape.getDimension().getWidth(), shape.getDimension().getHeight(),
        shape.getColor().getRed(), shape.getColor().getGreen(),
        shape.getColor().getBlue(), shape.getGivenTick(), shape.getPosition().getX(),
        shape.getPosition().getY(), shape.getDimension().getWidth(),
        shape.getDimension().getHeight(), shape.getColor().getRed(), shape.getColor().getGreen(),
        shape.getColor().getBlue()));
    return firstMotion.toString();
  }

  /**
   * Prints out the second motion of the given shapes at the current tick, which is after the
   * shape's position swapped, and the shape currently is in the new position.
   *
   * @param tick   is the current tick
   * @param shape  the 1st shape
   * @param shape2 the 2nd shape
   * @return the second motion as a string
   */
  private String printSecondMotion(int tick, AModelShape shape, AModelShape shape2) {
    StringBuilder secondMotion = new StringBuilder();
    secondMotion.append(String.format("motion %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d\n",
        shape2.getName(), tick, shape.getPosition().getX(), shape.getPosition().getY(),
        shape2.getDimension().getWidth(), shape2.getDimension().getHeight(),
        shape2.getColor().getRed(), shape2.getColor().getGreen(),
        shape2.getColor().getBlue(), shape2.getGivenTick(), shape2.getPosition().getX(),
        shape2.getPosition().getY(), shape2.getDimension().getWidth(),
        shape2.getDimension().getHeight(), shape2.getColor().getRed(), shape2.getColor().getGreen(),
        shape2.getColor().getBlue()));
    return secondMotion.toString();
  }

  /**
   * Return the whole state of the sorting including the initial data and motions.
   *
   * @return the whole state as a string
   */
  public String bubbleSort() {
    this.setArr();
    int n = arr.length;
    int tick = 3;
    String gameTxt = this.getGameTxt();
    String gameStage = "";

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if ((arr[j].getDimension().getHeight() * arr[j].getDimension().getWidth()) > (
            arr[j + 1].getDimension().getHeight() * arr[j + 1].getDimension().getWidth())) {
          AModelShape temp = arr[j];
          AModelShape tempNext = arr[j + 1];
          arr[j].swipeOperation(arr[j + 1], tick + 10);
          arr[j] = tempNext;
          arr[j + 1] = temp;
          gameStage = gameStage + printFirstMotion(tick, arr[j], arr[j + 1])
              + printSecondMotion(tick, arr[j], arr[j + 1]);
          tick = arr[j].getGivenTick() + 1;
        }
      }
    }
    return gameTxt + "\n" + gameStage;
  }

  /**
   * Getter Methods for the Array shapes in the bubble sort class.
   *
   * @return Current Arraylist of shapes in the bubble class.
   */
  public ArrayList<AModelShape> getArrayShapes() {
    return as;
  }
}
