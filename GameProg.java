import java.awt.*;
import javax.swing.*;




public class GameProg extends JFrame {
  public GameProg(){
    setTitle("HELLO WORLD");

    MainPanel panel = new MainPanel();
    Container conP = getContentPane();
    conP.add(panel);

    pack();
  }

  public static void main(String[] arg){
    GameProg frame = new GameProg();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

}
