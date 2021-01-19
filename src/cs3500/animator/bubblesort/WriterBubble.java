package cs3500.animator.bubblesort;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.shape.AModelShape;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;

/**
 * The write application that take the bubble sort and its state, which is a string as an input, run
 * through them and create a txt file as an output.
 */
public class WriterBubble {

  /**
   * Takes the argument and run the application.
   *
   * @param args argument to run the application
   */
  public static void main(String[] args) {
    IModel model = new Model(0, 0, 800, 800);
    cs3500.animator.bubblesort.BubbleSort bs = new BubbleSort(model);

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

    bs.addAModelShape(block3);
    bs.addAModelShape(block6);
    bs.addAModelShape(block2);
    bs.addAModelShape(block4);
    bs.addAModelShape(block1);
    bs.addAModelShape(block5);
    bs.addAModelShape(block7);
    bs.addAModelShape(block8);

    Writer output = null;
    try {
      output = new FileWriter("bubbleSortAlgo.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    String input = bs.bubbleSort();
    PrintWriter pw = new PrintWriter(output);
    pw.println(input);
    pw.close();
  }
}
