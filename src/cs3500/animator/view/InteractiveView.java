package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A new view class the implements the new view interface and also extends the JFrameView class, so
 * that it can use the DrawPanel as its component. In addition, it adds interactive button, so users
 * can interact with the animation.
 */
public class InteractiveView extends JFrameView implements IInteractiveView {

  private final JButton start;
  private final JButton pause;
  private final JButton resume;
  private final JButton restart;
  private final JButton loop;
  private final JButton speedUp;
  private final JButton speedDown;

  //Extra Credit
  private final JTextField textLabel;
  private final JButton addShape;
  private final JButton move;
  private final JButton changeDimension;
  private final JButton changeColor;
  private final JButton outlineMode;
  private final JButton exportSVG;
  private final JButton discrete;

  /**
   * Constructor of new view takes no parameters, it adds buttons into the panel and assigns action
   * commands.
   */
  public InteractiveView() {
    super();
    JPanel buttons = new JPanel();
    JPanel editButtons = new JPanel();
    buttons.setLayout(new FlowLayout(30, 50, 10));
    editButtons.setLayout(new FlowLayout(30, 10, 10));

    this.start = new JButton("Start");
    this.resume = new JButton("Resume");
    this.restart = new JButton("Restart");
    this.loop = new JButton("Loop");
    this.speedUp = new JButton("Speed Up");
    this.speedDown = new JButton("Speed Down");
    this.pause = new JButton("Pause");

    //Extra Credit
    this.textLabel = new JTextField();
    this.addShape = new JButton("Add Shape");
    this.move = new JButton("Move");
    this.changeDimension = new JButton("Change Dimension");
    this.changeColor = new JButton("Change Color");
    this.outlineMode = new JButton("Outline Mode");
    this.exportSVG = new JButton("Export SVG");
    this.discrete = new JButton("Discrete");

    // sets action command.
    start.setActionCommand("start");
    resume.setActionCommand("resume");
    restart.setActionCommand("restart");
    loop.setActionCommand("loop");
    speedUp.setActionCommand("speed up");
    speedDown.setActionCommand("speed down");
    pause.setActionCommand("pause");

    //Extra Credit
    addShape.setActionCommand("addShape");
    move.setActionCommand("move");
    changeDimension.setActionCommand("changeD");
    changeColor.setActionCommand("changeC");
    outlineMode.setActionCommand("outline");
    exportSVG.setActionCommand("exportSVG");
    discrete.setActionCommand("discrete");

    // add command buttons into button panel.
    buttons.add(start);
    buttons.add(resume);
    buttons.add(restart);
    buttons.add(loop);
    buttons.add(speedUp);
    buttons.add(speedDown);
    buttons.add(pause);

    //Extra Credit
    editButtons.add(textLabel);
    editButtons.add(addShape);
    editButtons.add(move);
    editButtons.add(changeDimension);
    editButtons.add(changeColor);
    editButtons.add(outlineMode);
    editButtons.add(exportSVG);
    editButtons.add(discrete);

    // set the size of the buttons.
    start.setPreferredSize(new Dimension(120, 30));
    resume.setPreferredSize(new Dimension(120, 30));
    restart.setPreferredSize(new Dimension(120, 30));
    loop.setPreferredSize(new Dimension(120, 30));
    speedUp.setPreferredSize(new Dimension(120, 30));
    speedDown.setPreferredSize(new Dimension(120, 30));
    pause.setPreferredSize(new Dimension(120, 30));
    // Extra Credit
    textLabel.setPreferredSize(new Dimension(300, 40));
    addShape.setPreferredSize(new Dimension(120, 30));
    move.setPreferredSize(new Dimension(120, 30));
    changeDimension.setPreferredSize(new Dimension(120, 30));
    changeColor.setPreferredSize(new Dimension(120, 30));
    outlineMode.setPreferredSize(new Dimension(120, 30));
    exportSVG.setPreferredSize(new Dimension(120, 30));
    discrete.setPreferredSize(new Dimension(120, 30));

    this.add(buttons, BorderLayout.SOUTH);
    this.add(editButtons, BorderLayout.NORTH);
    this.setVisible(true);
  }

  @Override
  public void addListener(ActionListener listener) {
    Objects.requireNonNull(listener);
    start.addActionListener(listener);
    resume.addActionListener(listener);
    restart.addActionListener(listener);
    loop.addActionListener(listener);
    speedUp.addActionListener(listener);
    speedDown.addActionListener(listener);
    pause.addActionListener(listener);
    // Extra Credit
    addShape.addActionListener(listener);
    move.addActionListener(listener);
    changeDimension.addActionListener(listener);
    changeColor.addActionListener(listener);
    outlineMode.addActionListener(listener);
    exportSVG.addActionListener(listener);
    discrete.addActionListener(listener);
  }

  //Extra Credit
  @Override
  public String getTextValue() {
    return textLabel.getText();
  }

  @Override
  public void clearTextValue() {
    textLabel.setText("");
  }
}
