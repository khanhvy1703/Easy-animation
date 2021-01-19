import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.bubblesort.BubbleSort;
import java.util.ArrayList;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import org.junit.Test;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * Tests for BubbleSort Algorithm.
 */
public class BubbleSortTest {

  IModel model = new Model(0, 0, 800, 800);
  BubbleSort bs = new BubbleSort(model);

  AModelShape block1 = new AModelShape(new Position(420, 20),
      new Dimension(50, 100),
      new Color(255, 127, 0), "block1") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };
  AModelShape block2 = new AModelShape(new Position(220, 20),
      new Dimension(50, 600),
      new Color(148, 0, 211), "block2") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };
  AModelShape block3 = new AModelShape(new Position(20, 20),
      new Dimension(50, 50),
      new Color(255, 0, 0), "block3") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };
  AModelShape block4 = new AModelShape(new Position(320, 20),
      new Dimension(50, 220),
      new Color(0, 255, 0), "block4") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };
  AModelShape block5 = new AModelShape(new Position(520, 20),
      new Dimension(50, 500),
      new Color(75, 0, 130), "block5") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };
  AModelShape block6 = new AModelShape(new Position(120, 20),
      new Dimension(50, 350),
      new Color(2, 7, 93), "block6") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };

  AModelShape block7 = new AModelShape(new Position(620, 20),
      new Dimension(50, 150),
      new Color(255, 255, 0), "block7") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };

  AModelShape block8 = new AModelShape(new Position(720, 20),
      new Dimension(50, 300),
      new Color(0, 0, 255), "block8") {
    @Override
    public String getType() {
      return "Rectangle";
    }
  };

  @Test
  public void addAModelShape() {
    ArrayList<AModelShape> shapelist = new ArrayList<>();
    bs.addAModelShape(block1);
    shapelist.add(block1);
    assertEquals(bs.getArrayShapes(), shapelist);
    bs.addAModelShape(block2);
    shapelist.add(block2);
    assertEquals(bs.getArrayShapes(), shapelist);
    bs.addAModelShape(block3);
    shapelist.add(block3);
    assertEquals(bs.getArrayShapes(), shapelist);
    bs.addAModelShape(block4);
    shapelist.add(block1);
    assertNotEquals(bs.getArrayShapes(), shapelist);
    bs.addAModelShape(block5);
    shapelist.add(block1);
    assertNotEquals(bs.getArrayShapes(), shapelist);
  }

  @Test
  public void bubbleSort() {
    bs.addAModelShape(block3);
    bs.addAModelShape(block6);
    bs.addAModelShape(block2);
    bs.addAModelShape(block4);
    bs.addAModelShape(block1);
    bs.addAModelShape(block5);
    bs.addAModelShape(block7);
    bs.addAModelShape(block8);
    String bubbleSortAnimation = bs.bubbleSort();
    assertEquals("canvas 0 0 800 800\n"
            + "shape block3 rectangle\n"
            + "shape block6 rectangle\n"
            + "shape block2 rectangle\n"
            + "shape block4 rectangle\n"
            + "shape block1 rectangle\n"
            + "shape block5 rectangle\n"
            + "shape block7 rectangle\n"
            + "shape block8 rectangle\n"
            + "motion block3 1 20 20 50 50 255 0 0 1 20 20 50 50 255 0 0\n"
            + "motion block6 1 120 20 50 350 2 7 93 1 120 20 50 350 2 7 93\n"
            + "motion block2 1 220 20 50 600 148 0 211 1 220 20 50 600 148 0 211\n"
            + "motion block4 1 320 20 50 220 0 255 0 1 320 20 50 220 0 255 0\n"
            + "motion block1 1 420 20 50 100 255 127 0 1 420 20 50 100 255 127 0\n"
            + "motion block5 1 520 20 50 500 75 0 130 1 520 20 50 500 75 0 130\n"
            + "motion block7 1 620 20 50 150 255 255 0 1 620 20 50 150 255 255 0\n"
            + "motion block8 1 720 20 50 300 0 0 255 1 720 20 50 300 0 0 255\n"
            + "\n"
            + "motion block4 3 320 20 50 220 0 255 0 13 220 20 50 220 0 255 0\n"
            + "motion block2 3 220 20 50 600 148 0 211 13 320 20 50 600 148 0 211\n"
            + "motion block1 14 420 20 50 100 255 127 0 24 320 20 50 100 255 127 0\n"
            + "motion block2 14 320 20 50 600 148 0 211 24 420 20 50 600 148 0 211\n"
            + "motion block5 25 520 20 50 500 75 0 130 35 420 20 50 500 75 0 130\n"
            + "motion block2 25 420 20 50 600 148 0 211 35 520 20 50 600 148 0 211\n"
            + "motion block7 36 620 20 50 150 255 255 0 46 520 20 50 150 255 255 0\n"
            + "motion block2 36 520 20 50 600 148 0 211 46 620 20 50 600 148 0 211\n"
            + "motion block8 47 720 20 50 300 0 0 255 57 620 20 50 300 0 0 255\n"
            + "motion block2 47 620 20 50 600 148 0 211 57 720 20 50 600 148 0 211\n"
            + "motion block4 58 220 20 50 220 0 255 0 68 120 20 50 220 0 255 0\n"
            + "motion block6 58 120 20 50 350 2 7 93 68 220 20 50 350 2 7 93\n"
            + "motion block1 69 320 20 50 100 255 127 0 79 220 20 50 100 255 127 0\n"
            + "motion block6 69 220 20 50 350 2 7 93 79 320 20 50 350 2 7 93\n"
            + "motion block7 80 520 20 50 150 255 255 0 90 420 20 50 150 255 255 0\n"
            + "motion block5 80 420 20 50 500 75 0 130 90 520 20 50 500 75 0 130\n"
            + "motion block8 91 620 20 50 300 0 0 255 101 520 20 50 300 0 0 255\n"
            + "motion block5 91 520 20 50 500 75 0 130 101 620 20 50 500 75 0 130\n"
            + "motion block1 102 220 20 50 100 255 127 0 112 120 20 50 100 255 127 0\n"
            + "motion block4 102 120 20 50 220 0 255 0 112 220 20 50 220 0 255 0\n"
            + "motion block7 113 420 20 50 150 255 255 0 123 320 20 50 150 255 255 0\n"
            + "motion block6 113 320 20 50 350 2 7 93 123 420 20 50 350 2 7 93\n"
            + "motion block8 124 520 20 50 300 0 0 255 134 420 20 50 300 0 0 255\n"
            + "motion block6 124 420 20 50 350 2 7 93 134 520 20 50 350 2 7 93\n"
            + "motion block7 135 320 20 50 150 255 255 0 145 220 20 50 150 255 255 0\n"
            + "motion block4 135 220 20 50 220 0 255 0 145 320 20 50 220 0 255 0\n",
        bubbleSortAnimation);
  }
}