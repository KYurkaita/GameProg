import java.awt.*;
import javax.swing.*;
import java.awt.event.* ;


public class GameProg extends JFrame implements ActionListener{
    JPanel change = new JPanel();
    JPanel title = new JPanel();
    public static int flag = 0;

    public GameProg(){
        Container contentPane = getContentPane();

        /*titleパネル*/
        create_title(title);

        MainPanel main = new MainPanel();
        change.setLayout(new CardLayout());
        change.add(title,"title");
        change.add(main,"main");
        setTitle("test");

        setLayout(new BorderLayout(100,20));
        contentPane.add(change);

        pack();
    }

    private void create_title(JPanel tit){
      Title title = new Title();
      title.setBounds(50,0,640,480);

      JButton btn = new JButton("start");
      btn.addActionListener(this);
      btn.setActionCommand("main");
      btn.setBounds(30,70,100,20);

      tit.setComponentZOrder(btn,0);
      // tit.setComponentZOrder(title,1);
      tit.setLayout(null);
      tit.add(btn);
      tit.add(title);
    }

    public static void main(String[] arg){
        GameProg frame = new GameProg();

        /*initialize*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        /*windows location center*/
        frame.setLocationRelativeTo(null);
        /*can see*/
        frame.setVisible(true);

        while(true){
          if( flag != 0 ) frame.repaint();
        }
    }

    public void setflag(int i){
      flag = i;
    }

    public void actionPerformed(ActionEvent e){
        CardLayout card = (CardLayout)(change.getLayout());
        // String cmd = e.getActionCommand();
        // cl.show(change,cmd);
        if (e.getActionCommand() == "main"){
          card.show(change,"main");
        }

    }

}
