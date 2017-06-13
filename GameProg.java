import java.awt.*;
import javax.swing.*;
import java.awt.event.* ;




public class GameProg extends JFrame implements ActionListener{
    JPanel change = new JPanel();

    public GameProg(){
        Title title = new Title();
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();

        change.setLayout(new CardLayout());
        change.add(title,"view1");
        change.add(panel,"view2");

        JButton bt1 = new JButton("change");
        bt1.addActionListener(this);
        bt1.setActionCommand("view1");

        JButton bt2 = new JButton("change");
        bt2.addActionListener(this);
        bt2.setActionCommand("view2");
        JPanel bt = new JPanel();
        bt.add(bt1);
        bt.add(bt2);

        setTitle("test");

        setLayout(new BorderLayout(100,20));
        contentPane.add(change,BorderLayout.CENTER);
        contentPane.add(bt,BorderLayout.PAGE_END);

        // contentPane.add(new JButton("1 - North"), BorderLayout.NORTH);
        // contentPane.add(new JButton("2-East"),BorderLayout.EAST);
        // contentPane.add(panel,BorderLayout.CENTER);
        // contentPane.add(title,BorderLayout.SOUTH);

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
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        CardLayout cl = (CardLayout)(change.getLayout());
        String cmd = e.getActionCommand();
        cl.show(change,cmd);

    }

}
