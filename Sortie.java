import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Sortie extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int SUBMENU_X = 450;
    private static final int SUBMENU_Y = 0;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();

    private Unit un[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int unum = 1 ;
    private int selnum = 0;

    public Sortie(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 20 , 170 , WIDTH , HEIGHT );

        /*MENU Panel create*/
        MBak.set("IMG/ITEM/menu.png");
        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(SUBMENU_X,SUBMENU_Y);
        // MSubIcom.set("IMG/ITEM/submenuicon.png");
        // MSubIcom.put(SUBMENU_X,SUBMENU_Y);

        for( int i = 0 ; i < 25 ; i++ ){
            un[i] = new Unit();
        }

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
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        /*Main Menu Panel*/
        MBak.draw(g);
        /*Sub Menu Panel*/
        MSubBak.draw(g);

        g.drawString(str, 0, 10);

    }

    public void LoadUnit(Unit u[], int n){
        for( int i = 0 ; i< unum ; i++ ){
            u[i] = this.un[i];
        }
        n = this.unum;
    }

    public void SaveUnit(Unit u[], int n){
        for (int i = 0; i < n ; i++ ){
            this.un[i] = u[i];
        }
        this.unum = n;
    }

}
