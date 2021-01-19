package cs3500.animator.view;

import cs3500.animator.shape.IModelShape;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * The JFrameView class implements the IView, overrides its methods, and extends the JFrame to show
 * the view. Also it has the panel where to draw the shape there.
 */
public class JFrameView extends JFrame implements IView {

  protected DrawPanel drawPanel;

  /**
   * Constructor of the view, which set the exit button, the scroll bar and the size of the panel,
   * where we draw the shape there. The scroll panel will appear ig the preferred size is bigger
   * than the size set.
   */
  public JFrameView() {
    super();
    this.setTitle("Homework 6");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(new Dimension(1250, 1000));
    setLocation(400, 30);
    this.drawPanel = new DrawPanel();
    JScrollPane scrollPane = new JScrollPane(drawPanel);
    add(scrollPane);
    setResizable(true);
    setVisible(true);
  }

  @Override
  public void refresh() {
    drawPanel.repaint();
  }

  @Override
  public void addShape(ArrayList<IModelShape> s) {
    drawPanel.addShape(s);
  }


  @Override
  public String getTextualView() {
    throw new UnsupportedOperationException("Visual View Cannot Get Output");
  }

}
