package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.provider.controller.IAnimationController;
import cs3500.animator.provider.controller.PlayBackAnimationController;
import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.ProviderAnimationModel;
import cs3500.animator.provider.view.AnimationView;
import cs3500.animator.provider.view.TextualView;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.JFrameView;
import cs3500.animator.view.SVGView;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Formatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import cs3500.animator.model.Model;
import cs3500.animator.model.Model.Builder;

/**
 * Excellence Application that takes the argument as an input and run by the type of the argument.
 * These are the possible type input : -in, -out, -view, -speed.
 */
public class  Excellence {

  /**
   * Takes the argument and run the application.
   *
   * @param args argument to run the application
   * @throws IOException IOException
   */
  public static void main(String[] args) throws IOException {

    IModel model = new Model();
    IView view;
    AnimationView text;
    String output = "";
    int index = 0;
    int frameSecond = 1;
    boolean out = false;

    JFrame warningFrame = new JFrame();
    warningFrame.setSize(new Dimension(200, 200));

    // -in
    while (index < args.length) {
      if (args[index].equals("-in")) {
        try {
          InputStream is = new FileInputStream(args[index + 1]);
          model = AnimationReader.parseFile(new InputStreamReader(is), new Builder());
          break;
        } catch (FileNotFoundException e) {
          JOptionPane.showMessageDialog(warningFrame, "FileNotFound", "Waring",
              JOptionPane.WARNING_MESSAGE);
        }
      }
      index = index + 2;
    }

    index = 0;
    while (index < args.length) {
      if (args[index].equals("-speed")) {
        try {
          frameSecond = Integer.parseInt(args[index + 1]);
          break;
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(warningFrame, "Not a good format of the speed", "Waring",
              JOptionPane.WARNING_MESSAGE);

        }
      }
      index = index + 2;
    }
    index = 0;

    while (index < args.length) {
      if (args[index].equals("-view")) {
        String type = args[index + 1];
        switch (type) {
          case "text":
            assert model != null;
            view = new cs3500.animator.view.TextualView(model, frameSecond);
            output = view.getTextualView();
            break;
          case "visual":
            view = new JFrameView();
            Controller controller = new Controller(view, model, frameSecond);
            controller.goAction();
            break;
          case "svg":
            assert model != null;
            view = new SVGView(model, frameSecond);
            output = view.getTextualView();
            break;
          case "interactive":
            view = new InteractiveView();
            Controller newController = new Controller(view, model, frameSecond);
            newController.goAction();
            break;
          case "provider":
            assert model != null;
            cs3500.animator.provider.view.InteractiveView hybrid =
                new cs3500.animator.provider.view.InteractiveView();

            AnimationModel newMod = new ProviderAnimationModel(model);

            IAnimationController control = new PlayBackAnimationController(hybrid,
                newMod, 1);
            control.playAnimation(1000, "interactive",
                "buildings.txt", null);
            break;
          case "text-provider":
            assert model != null;
            AnimationModel newModel = new ProviderAnimationModel(model);
            text = new TextualView(newModel, new StringBuilder(), 1.0);
            output = text.toString();
            break;
          default:
            JOptionPane.showMessageDialog(warningFrame, "invalid type", "Waring",
                JOptionPane.WARNING_MESSAGE);
            break;
        }
        break;
      }
      index = index + 2;
    }
    // reset the index again.
    index = 0;
    // -out
    while (index < args.length) {
      if (args[index].equals("-out")) {
        out = true;
        String type = args[index + 1];
        if (type.equals("out")) {
          System.out.println(output);
        } else {
          try {
            new Formatter(type);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(warningFrame, "Cannot add a file", "Waring",
                JOptionPane.WARNING_MESSAGE);
          }
          BufferedWriter bufferedWriter;
          try {
            bufferedWriter = new BufferedWriter(new FileWriter(type, true));
          } catch (Exception e) {
            throw new IllegalArgumentException("No File name");
          }
          bufferedWriter.write(output);
          bufferedWriter.close();
        }
      }
      index += 2;
    }
    if (!out) {
      System.out.println();
    }
    System.out.println("output here: \n" + output);
  }
}

