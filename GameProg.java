import java.awt.*;
import javax.swing.*;




public class GameProg extends JFrame {
    Title title = new Title();

    public GameProg(){
        setTitle("tyoujuugiga");

        MainPanel panel = new MainPanel();
        Title title = new Title();
        Container conP = getContentPane();

        //conP.add(title);

        conP.add(panel);

        pack();
    }


    public static void main(String[] arg){
        GameProg frame = new GameProg();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
