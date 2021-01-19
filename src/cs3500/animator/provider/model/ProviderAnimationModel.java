package cs3500.animator.provider.model;

import cs3500.animator.model.IModel;
import cs3500.animator.motion.IMotion;
import cs3500.animator.provider.model.shape.IShape;
import cs3500.animator.provider.model.shape.ProviderShapeOval;
import cs3500.animator.provider.model.shape.ProviderShapeRec;
import cs3500.animator.shape.IModelShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Provider animation model that implements Animation Model. The class takes IModel that makes it
 * work with the provider model. It acts as a wrapper and adopt IModel for the provider code.
 */
public class ProviderAnimationModel implements AnimationModel {

  private IModel model;

  private List<IModelShape> shapes;

  /**
   * Constructor for the ProviderAnimationModel that takes param as an IModel.
   *
   * @param model IModel.
   */
  public ProviderAnimationModel(IModel model) {
    this.model = model;
    this.shapes = model.getShapes();
  }

  @Override
  public void startAnimation(HashMap<String, IShape> shapes, List<Instruction> instructions,
      Canvas canvas) throws IllegalArgumentException {
    // We do not use this method for the animation provider
  }

  @Override
  public void addShape(String name, IShape s) throws IllegalArgumentException {
    // We do not use this method for the animation provider
  }

  @Override
  public void deleteShape(String name) throws IllegalArgumentException {
    // We do not use this method for the animation provider
  }

  @Override
  public void addInstruction(Instruction inst) throws IllegalArgumentException {
    // We do not use this method for the animation provider
  }

  @Override
  public List<String> getNames() {
    List<String> strs = new ArrayList<>();
    for (IModelShape s : model.getShapes()) {
      strs.add(s.getName());
    }
    return strs;
  }


  @Override
  public List<IShape> getShapesAt(int currentTick) {
    List<IModelShape> copyShapes = new ArrayList<>();
    for (IModelShape shape : shapes) {
      shape.apply(currentTick);
      copyShapes.add(shape);
    }
    if (currentTick == 0) {
      return new ArrayList<>();
    }
    List<IShape> newIShape = new ArrayList<>();
    for (IModelShape s : copyShapes) {
      IShape shape = null;
      if (s.getType().equals("Rectangle")) {
        shape = new ProviderShapeRec(s);
      } else {
        shape = new ProviderShapeOval(s);
      }
      newIShape.add(shape);
    }
    return newIShape;
  }

  @Override
  public List<Instruction> getInstructionsFor(String name) throws IllegalArgumentException {
    List<IMotion> ints = new ArrayList<>();
    IModelShape a = null;
    for (IModelShape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        ints.addAll(shape.getMotions());
        a = shape;
      }
    }
    return transformToInstruction(ints, a);
  }

  /**
   * Method that transform the list of IMotion to the list of the Instruction.
   *
   * @param motions List of IMotion
   * @param shape   IModelShape
   * @return List of Instruction
   */
  private List<Instruction> transformToInstruction(List<IMotion> motions, IModelShape shape) {
    List<Instruction> intrs = new ArrayList<>();
    for (IMotion m : motions) {
      Instruction in = new ProviderInstruction(m, shape);
      intrs.add(in);
    }
    return intrs;
  }

  @Override
  public IShape getShapeOf(String name) throws IllegalArgumentException {
    for (IModelShape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        if (shape.getType().equals("Rectangle")) {
          return new ProviderShapeRec(shape);
        } else {
          return new ProviderShapeOval(shape);
        }
      }
    }
    throw new IllegalArgumentException("Cant find the shape");
  }

  @Override
  public Posn getPositionOf(String name) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Color getColorOf(String name) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getSizeOf(String name) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public Canvas getCanvas() {
    return new Canvas(model.getXCanvas(), model.getYCanvas(), model.getWCanvas(),
        model.getHCanvas());
  }
}
