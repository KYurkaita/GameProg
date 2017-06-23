import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel implements MouseListener , MouseMotionListener{
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private String str;
    private Image image;
    private int x = 100;
    private int y = 100;
    private int mx = 0;
    private int my = 0;

    MenuPanel menu;
    JPanel war;

    MenuItem imenu[] = new MenuItem[4];
    boolean changeFlag = false;
    private CardLayout chmain ;

    public MainPanel(){
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        /* panelsize */
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        /*menu*/
        menu = new MenuPanel();

        /*war*/
        war  = new JPanel();

        addMouseListener(this);
        addMouseMotionListener(this);

        /*card create */
        setLayout(new CardLayout());
        add(menu,"menu");
        add(war,"war");

        chmain = (CardLayout)getLayout();

    }
    /*MouseEvent*/
    public void mouseClicked (MouseEvent e){;}
    public void mouseEntered (MouseEvent e){;}
    public void mouseExited  (MouseEvent e){;}
    public void mouseReleased(MouseEvent e){;}
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved  (MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }

    public void ShowMain(String s){
        chmain.show( this , s );
    }

    public boolean gChangeMainFlag(){
        return menu.getFlag();
    }

    public void sChangeMainFlag(boolean f){
        menu.setFlag(f);
    }


}
