import java.awt.*;
import javax.swing.*;
import java.awt.event.* ;


public class GameProg extends JFrame implements ActionListener , Runnable {
    JPanel change;

    private Thread gameLoop;
    MainPanel mainp;

    private Unit btmem[] = new Unit[6];
    private int btnum = 5;

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
        mainp = new MainPanel();

        /*change card*/
        change = new JPanel();
        change.setLayout(new CardLayout());
        change.add(title,"title");
        change.add(mainp,"main");


        /*btmem inisialize*/
        for( int i = 0; i < 6 ; i++){
            btmem[i] = new Unit();
        }

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
        CardLayout card = (CardLayout)(change.getLayout());
        if (e.getActionCommand() == "main"){
            card.show(change,"main");
        }

    }

    public void run(){
        while(true){

            repaint();
            /* change main panel main to war */
            if( mainp.GetMenuFlag() ){

                mainp.SetMenuFlag(false);

                this.btnum = mainp.LoadBtUnitNum();
                for(int i = 0 ; i < this.btnum; i++){
                    this.btmem[i] = mainp.LoadBtUnit(i);
                }

                // System.out.println( "" + this.btnum );
                mainp.SaveBtUnitToWar( btmem , btnum );

                mainp.ChangeShow("war");
            }

            if( mainp.GetWarFlag() ){

                mainp.SetMenuFlag(false);
                mainp.SetWarFlag(false);
                mainp.ChangeShow("menu");
            }

            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
