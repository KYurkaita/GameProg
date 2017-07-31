import java.awt.*;
import javax.swing.*;
import java.awt.event.* ;
import java.awt.Font;
import java.io.File;

public class GameProg extends JFrame implements ActionListener {
    JPanel change;

    public GameProg(){
        /*title panel*/
        JPanel title = new JPanel();

        Title tit = new Title();
        JButton btn = new JButton("start");
        btn.addActionListener(this);
        btn.setActionCommand("main");
        btn.setBounds(30,70,100,20);

        title.setLayout(null);
        title.add(btn);
        title.add(tit);

        /*main panel*/
        MainPanel mainp = new MainPanel();

        /*change card*/
        change = new JPanel();
        change.setLayout(new CardLayout());
        change.add(title,"title");
        change.add(mainp,"main");

        /*content */
        Container contentPane = getContentPane();
        setTitle("íπèbÉMÉK");
        setLayout(new BorderLayout(0,0));
        contentPane.add(change);


        pack();

    }

    public static void main(String[] arg){
        GameProg frame = new GameProg();

        /*initialize*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        /*windows location center*/
        frame.setLocationRelativeTo(null);
        /*can see*/
        // frame.setSize(640  , 480);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        CardLayout card = (CardLayout)(change.getLayout());
        if (e.getActionCommand() == "main"){
            card.show(change,"main");
        }

    }

}
