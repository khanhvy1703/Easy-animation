package cs3500.animator.controller;

import cs3500.animator.motion.MotionColor;
import cs3500.animator.motion.MotionDimension;
import cs3500.animator.motion.MotionMove;
import cs3500.animator.shape.Ellipse;
import cs3500.animator.shape.IModelShape;
import cs3500.animator.shape.Plus;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.tools.Color;
import cs3500.animator.tools.Dimension;
import cs3500.animator.tools.Position;
import cs3500.animator.tools.Tempo;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.SVGView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import java.util.Scanner;
import javax.swing.Timer;

/**
 * Class that implements interface IController, It also takes the Action Listener and implements its
 * method to work with Timer.
 */
public class Controller implements IController, ActionListener {

  private IView view;
  private final IModel model;
  private final int frameSecond;
  private Timer timer;
  private IInteractiveView newView;
  private int tick = 0;
  private boolean isLoop;
  private boolean isOutline;
  private boolean isDiscrete;
  private int increaseTime;

  /**
   * Constructor for the Controller that has 3 parameters, a model, view, and frameSecond.
   *
   * @param view        the view of the animation
   * @param model       the model of the animation
   * @param frameSecond the speed of the animation
   */
  public Controller(IView view, IModel model, int frameSecond) {
    this.model = Objects.requireNonNull(model, "null model");
    try {
      this.newView = (InteractiveView) view;
      newView.addListener(this);
    } catch (Exception e) {
      this.view = view;
    }
    this.view = view;
    view.addShape(model.getShapes());
    this.frameSecond = frameSecond;
    timer = new Timer(1000 / frameSecond, this);
    this.isLoop = false;
    this.isOutline = false;
    this.isDiscrete = false;
    this.increaseTime = 1;
  }

  @Override
  public void goAction() {
    if (newView != null) {
      timer.setActionCommand("start");
    } else {
      timer.setActionCommand("non-interactive view");
      timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "start":
        System.out.println("start");
        System.out.println("tick: " + tick);
        timer.start();
        model.isVisible(tick);
        model.doActionPerform(tick);
        view.addShape(model.getShapes());
        view.refresh();
        tick = tick + increaseTime;
        tempoChecker();
        if (isLoop && tick > model.getLastTick()) {
          tick = 0;
          timer.start();
          model.doActionPerform(tick);
        }
        break;
      case "resume":
        System.out.println("resume");
        timer.restart();
        break;
      case "restart":
        System.out.println("restart");
        tick = 0;
        timer.start();
        model.doActionPerform(tick);
        break;
      case "speed up":
        if (!isDiscrete) {
          System.out.println("speed up");
          timer.setDelay((1000 / frameSecond) / 5);
        }
        break;
      case "loop":
        System.out.println("loop");
        isLoop = !isLoop;
        break;
      case "speed down":
        if (isDiscrete) {
          System.out.println("speed down");
          timer.setDelay(5 * (1000 / frameSecond));
        }
        break;
      case "pause":
        System.out.println("pause");
        timer.stop();
        break;
      case "addShape":
        System.out.println("Add Shape");
        String input = newView.getTextValue();
        System.out.println(input + " " + tick);
        newShapeAdder(input, tick);
        newView.clearTextValue();
        break;
      case "move":
        System.out.println("Move");
        String moveInput = newView.getTextValue();
        newMotionAdder("move", moveInput);
        newView.clearTextValue();
        break;
      case "changeD":
        System.out.println("Change Dimension");
        String dimInput = newView.getTextValue();
        newMotionAdder("changeD", dimInput);
        newView.clearTextValue();
        break;
      case "changeC":
        System.out.println("change Color");
        String colorInput = newView.getTextValue();
        newMotionAdder("changeC", colorInput);
        newView.clearTextValue();
        break;
      case "outline":
        System.out.println("outline Mode : " + !isOutline);
        isOutline = !isOutline;
        outlineToggle();
        break;
      case "exportSVG":
        System.out.println("It has exported");
        exportSVG();
        break;
      case "discrete":
        String discreteInput = newView.getTextValue();
        discreteMode(discreteInput);
        newView.clearTextValue();
        break;
      case "non-interactive view":
        System.out.println("tick: " + tick);
        model.isVisible(tick); // check if the shape is visible in this time.
        model.doActionPerform(tick); // do action
        view.addShape(model.getShapes());
        view.refresh(); // repaint
        tick = tick + 1;
        break;
      default:
        throw new IllegalArgumentException("Cant find command.");
    }
  }

  /**
   * Method that helps to create new shape in the animation.
   *
   * @param text text input from the text form.
   * @param tick current tick in the animation.
   */
  private void newShapeAdder(String text, int tick) {
    ArrayList<String> argus = new ArrayList<>();
    Scanner scan = new Scanner((text));
    while (scan.hasNext()) {
      argus.add(scan.next());
    }
    String type = argus.get(0);
    String name = argus.get(1);

    IModelShape shape;
    if (type.equalsIgnoreCase("Rectangle")) {
      shape = new Rectangle(name);
    } else if (type.equalsIgnoreCase("Plus")) {
      shape = new Plus(name);
    } else {
      shape = new Ellipse(name);
    }
    int posX;
    int posY;
    int width;
    int height;
    int red;
    int green;
    int blue;
    try {
      posX = Integer.parseInt(argus.get(2));
      posY = Integer.parseInt(argus.get(3));
      width = Integer.parseInt(argus.get(4));
      height = Integer.parseInt(argus.get(5));
      red = Integer.parseInt(argus.get(6));
      green = Integer.parseInt(argus.get(7));
      blue = Integer.parseInt(argus.get(8));
    } catch (Exception e) {
      throw new IllegalArgumentException("It is in the wrong format");
    }
    Position newShapePos = new Position(posX, posY);
    Dimension newShapeDim = new Dimension(width, height);
    Color newShapeClr = new Color(red, green, blue);
    shape.setAModelShape(newShapePos, newShapeDim, newShapeClr, tick);
    model.addShape(shape);
  }

  /**
   * Method that help to implement new motion in certain shape.
   */
  private void newMotionAdder(String command, String text) {
    ArrayList<String> argus = new ArrayList<>();
    Scanner scan = new Scanner((text));
    while (scan.hasNext()) {
      argus.add(scan.next());
    }
    String name = argus.get(0);

    ArrayList<IModelShape> shapes = model.getShapes();
    ArrayList<String> names = new ArrayList<>();
    for (IModelShape s : shapes) {
      names.add(s.getName());
    }
    int index;
    IModelShape shape;
    try {
      index = names.indexOf(name);
      shape = shapes.get(index);
    } catch (Exception e) {
      throw new IllegalArgumentException("There are no such name in the animation");
    }

    int start;
    int end;
    int first;
    int second;
    try {
      start = Integer.parseInt(argus.get(1));
      end = Integer.parseInt(argus.get(2));
      first = Integer.parseInt(argus.get(3));
      second = Integer.parseInt(argus.get(4));
    } catch (Exception e) {
      throw new IllegalArgumentException("Wrong format create motion");
    }
    Position newPos = new Position(first, second);
    Dimension newDim = new Dimension(first, second);

    switch (command) {
      case "move":
        shape.addMotion(new MotionMove(tick, tick + 1, shape.getPosition()));
        shape.addMotion(new MotionMove(start, end, newPos));
        break;
      case "changeD":
        shape.addMotion(new MotionDimension(tick, tick + 1, shape.getDimension()));
        shape.addMotion(new MotionDimension(start, end, newDim));
        break;
      case "changeC":
        int third;
        try {
          third = Integer.parseInt(argus.get(5));
        } catch (Exception e) {
          throw new IllegalArgumentException("Wrong format create motion");
        }
        Color newClr = new Color(first, second, third);
        shape.addMotion(new MotionColor(tick, tick + 1, shape.getColor()));
        shape.addMotion(new MotionColor(start, end, newClr));
        break;
      default:
        break;
    }
  }

  /**
   * Method that help animation to deal with the tempos that defined in the input file.
   */
  private void tempoChecker() {
    System.out.println("tempo checked");
    boolean tempoed = false;
    ArrayList<Tempo> tempos = model.getTempos();
    for (Tempo t : tempos) {
      if (tick >= t.getStart() && tick <= t.getEnd()) {
        if (t.getSpeed() > 0) {
          System.out.println("fast tempo: current tempo is " + t.getSpeed());
          timer.setDelay((1000 / frameSecond) / t.getSpeed());
          tempoed = true;
        }
        if (t.getSpeed() < 0) {
          System.out.println("slow tempo:  current tempo is " + t.getSpeed());
          timer.setDelay((t.getSpeed() * -1) * (1000 / frameSecond));
          tempoed = true;
        }
      } else {
        if (!tempoed) {
          System.out.println("normal speed");
          timer.setDelay(1000 / frameSecond);
        }
      }
    }
    tempoed = false;
  }

  /**
   * Method that help building outline animation.
   */
  private void outlineToggle() {
    ArrayList<IModelShape> shapes = model.getShapes();
    ArrayList<String> names = new ArrayList<>();
    for (IModelShape shape : shapes) {
      names.add(shape.getName());
    }
    if (!isOutline) {
      for (String name : names) {
        model.getShape(name).toOutlined(false);
      }
    } else {
      for (String name : names) {
        model.getShape(name).toOutlined(true);
      }
    }
  }

  /**
   * Method that build discrete mode in the animation.
   *
   * @param text textual value from the textual box.
   */
  private void discreteMode(String text) {

    ArrayList<String> argus = new ArrayList<>();
    Scanner scan = new Scanner((text));
    while (scan.hasNext()) {
      argus.add(scan.next());
    }
    if (argus.size() == 0) {
      isDiscrete = false;
      System.out.println("Discrete Mode is off");
      increaseTime = 1;
    } else {
      isDiscrete = true;
      System.out.println("Discrete Mode is On");
      int discreteNum = Integer.parseInt(argus.get(0));
      System.out.println(discreteNum);
      increaseTime = discreteNum;
    }
  }


  /**
   * Method that help to create SVG based on the output animation.
   */
  private void exportSVG() {
    IView svg = new SVGView(model, frameSecond);
    String svgText = svg.getTextualView();
    System.out.println(svgText);
    Writer output = null;
    try {
      output = new FileWriter("SVGnewShape.svg");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert output != null;
    PrintWriter pw = new PrintWriter(output);
    pw.println(svgText);
    pw.close();
  }
}

