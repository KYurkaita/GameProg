import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;


public class MainPanel extends JPanel implements Runnable {
    /*tyu kai nin class*/
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private MenuPanel menu;
    private War war;

    private Thread gameLoop;
    private CardLayout chmain;

    private Unit btmem[] = new Unit[6];
    private int btnum = 5;

    public MainPanel(){
        /* panelsize */
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        /*menu*/
        menu = new MenuPanel();

        /*war*/
        war  = new War();

        /*card create */
        setLayout(new CardLayout());
        add(menu,"menu");
        add(war,"war");

        chmain = (CardLayout)getLayout();

        /*btmem inisialize*/
        for( int i = 0; i < 6 ; i++){
            btmem[i] = new Unit();
        }

        gameLoop = new Thread(this);
        gameLoop.start();

    }

    public void ChangeShow(String s){
        chmain.show( this , s );
    }


    public void run(){
        while(true){
            repaint();

            /* change main panel main to war */
            if( menu.GetFlag() ){
                menu.SetFlag(false);
                this.btnum = menu.LoadBtNumber();
                for(int i = 0 ; i < this.btnum; i++){
                    this.btmem[i] = menu.LoadBtMember(i);
                }

                war.SaveUnit( btmem , btnum );
                ChangeShow("war");
            }

            if( war.GetFlag() ){
                war.SetFlag(false);
                ChangeShow("menu");
            }

            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

}
