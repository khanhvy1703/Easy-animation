package cs3500.animator.provider.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model.Builder;
import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.ProviderAnimationModel;
import cs3500.animator.provider.view.InteractiveView;
import cs3500.animator.util.AnimationReader;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * class that represent play back animation controller which implements key listener and
 * IAnimationController interface.
 */
public class PlayBackAnimationController implements KeyListener, IAnimationController {

  private InteractiveView hybrid;
  private PrintStream output = System.out;
  private AnimationModel model;
  private IModel mainModel;
  private final JFrame warningFrame = new JFrame();
  private int frameSecond;
  private int tick = 0;

  /**
   * PlayBackAnimationController Constructor.
   *
   * @param interactiveView interactiveView
   */
  public PlayBackAnimationController(InteractiveView interactiveView) {
    this.hybrid = interactiveView;
    warningFrame.setSize(new Dimension(200, 200));
  }

  /**
   * PlayBackAnimationController that takes view, model, and frame second.
   *
   * @param interactiveView interactive view
   * @param model           AnimationModel
   * @param frameSecond     frameSecond
   */
  public PlayBackAnimationController(InteractiveView interactiveView, AnimationModel model,
      int frameSecond) {
    this.hybrid = interactiveView;
    this.model = model;
    this.frameSecond = frameSecond;
    warningFrame.setSize(new Dimension(200, 200));
  }

  @Override
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 83:
        hybrid.start();
        break;
      case 80:
        hybrid.pause();
        break;
      case 82:
        hybrid.restart();
        break;
      case 76:
        hybrid.enableLoop();
        break;
      case 68:
        hybrid.disableLoop();
        break;
      case 38:
        hybrid.increaseSpeed();
        break;
      case 40:
        hybrid.decreaseSpeed();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 83:
        hybrid.start();
        break;
      case 80:
        hybrid.pause();
        break;
      case 82:
        hybrid.restart();
        break;
      case 76:
        hybrid.enableLoop();
        break;
      case 68:
        hybrid.disableLoop();
        break;
      case 38:
        hybrid.increaseSpeed();
        break;
      case 40:
        hybrid.decreaseSpeed();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 83:
        hybrid.start();
        break;
      case 80:
        hybrid.pause();
        break;
      case 82:
        hybrid.restart();
        break;
      case 76:
        hybrid.enableLoop();
        break;
      case 68:
        hybrid.disableLoop();
        break;
      case 38:
        hybrid.increaseSpeed();
        break;
      case 40:
        hybrid.decreaseSpeed();
        break;
      default:
        break;
    }
  }

  @Override
  public void playAnimation(double speed, String viewType, String inputFile, String outputFile) {
    assert mainModel != null;

    if (inputFile != null) {
      try {
        InputStream is = new FileInputStream(inputFile);
        mainModel = AnimationReader.parseFile(new InputStreamReader(is), new Builder());
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(warningFrame, "FileNotFound", "Waring",
            JOptionPane.WARNING_MESSAGE);
      }
    }

    this.model = new ProviderAnimationModel(mainModel);

    if (outputFile != null) {
      try {
        File out = new File(outputFile);
        if (!out.exists()) {
          out.createNewFile();
        }
        this.output = new PrintStream(out);
      } catch (IOException e) {
        this.output = System.out;
      }
    }

    hybrid.setUpView(model, new StringBuilder(), frameSecond);

    try {
      hybrid.render();
    } catch (IOException ie) {
      ie.printStackTrace(this.output);
    }
  }
}
