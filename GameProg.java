import java.awt.*;
import javax.swing.*;
import java.awt.event.* ;


public class GameProg extends JFrame implements ActionListener , Runnable {
    JPanel change;

    private Thread gameLoop;

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
        MainPanel main = new MainPanel();

        /*change card*/
        change = new JPanel();
        change.setLayout(new CardLayout());
        change.add(title,"title");
        change.add(main,"main");


        /*content */
        Container contentPane = getContentPane();
        setTitle("test");
        setLayout(new BorderLayout(0,0));
        contentPane.add(change);

        pack();

        gameLoop = new Thread(this);
        gameLoop.start();

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
        // String cmd = e.getActionCommand();
        // cl.show(change,cmd);
        CardLayout card = (CardLayout)(change.getLayout());
        if (e.getActionCommand() == "main"){
            card.show(change,"main");
        }

    }

    public void run(){
        while(true){
            repaint();
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
