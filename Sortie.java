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

    private static final int BUT_X = 450;
    private static final int BUT_Y = 270;
    private static final int BUT_W = 150;
    private static final int BUT_H = 80;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();
    private MENU But = new MENU();

    private boolean ch_flag = false;
    private int bt = 0 ;

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
        But.set("IMG/ITEM/change3.png");
        But.put(450,270);


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

        bt = SetWhM();
        switch(bt){
            case 7:
                ch_flag = true;
                break;
            default:
                break;
        }



    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ unum;

        /*Main Menu Panel*/
        MBak.draw(g);
        /*Sub Menu Panel*/
        MSubBak.draw(g);
        But.draw(g);

        g.drawString(str, 0, 10);

    }

    private int SetWhM(){
        // for (int i = 0 ; i < 25 ; i++ ){
        //     if ( Chr[ i ].x < mx && mx < ( Chr[ i ].x + 90)  &&
        //          Chr[ i ].y < my && my < ( Chr[ i ].y + 70)  &&
        //          i < unum )
        //         return i;
        // }

        if( ( BUT_X ) < mx && mx < (BUT_X + BUT_W) &&
            ( BUT_Y ) < my && my < (BUT_Y + BUT_H) ){
            return 7;
        }
        else {
            return -1;
        }
    }

    public Unit LoadUnit(Unit u[], int i){
        return this.un[i];
    }

    public int LoadUnitNum(){
        return this.unum;
    }

    public void SaveUnit(Unit u[], int n){
        for (int i = 0; i < n ; i++ ){
            this.un[i] = u[i];
        }
        this.unum = n;
    }

    public boolean GetSorFlag(){
        return this.ch_flag;
    }

    public void SetSorFlag( boolean f ){
        this.ch_flag = f;
    }

}
