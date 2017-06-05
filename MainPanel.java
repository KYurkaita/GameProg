import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class MainPanel extends JPanel {
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;
  private String str;

  public MainPanel(){
    setPreferredSize(new Dimension(WIDTH,HEIGHT));

    str = "Hello World";
  }

  public void paintComponent(Graphics g){
     super.paintComponent(g);
     g.drawString(str, 20, 50);
  }

}
