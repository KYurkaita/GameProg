import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class UnitOrg extends JPanel implements MouseListener , MouseMotionListener{
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

    private boolean createflag = false;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();

    private MENU Chr[] = new MENU[25];
    private MENU MSubIcom = new MENU();

    private Unit un[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int unum = 1 ;
    private int selnum = 0;

    public UnitOrg(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 20 , 170 , WIDTH , HEIGHT );

        MBak.set("IMG/ITEM/menu.png");
        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(SUBMENU_X,SUBMENU_Y);
        MSubIcom.set("IMG/ITEM/submenuicon.png");
        MSubIcom.put(SUBMENU_X,SUBMENU_Y);

        int xy;
        for(int y = 0 ; y < 5 ; y++ ){
            for(int x = 0 ; x < 5 ; x++ ){
                xy = y * 5 + x ;
                Chr[ xy ] = new MENU();
                Chr[ xy ].set("IMG/ITEM/ch_menu.png");
                Chr[ xy ].put( x * 90 , y * 70 );
            }
        }

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
        selnum = SetWhM();
        if ( selnum == -1 ){
            createflag = true;
        }
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
        selnum = SetWhM();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + selnum;

        /* MENU */
        MBak.draw(g);
        for (int i = 0 ; i < 25 ; i++){
            Chr[ i ].draw(g);
        }

        /* SUB MENU */
        MSubBak.draw(g);
        MSubIcom.draw(g);
        if( selnum >= 0 ) un[ selnum ].drawSubMenu(g);
        else {
            g.drawString( "新規ユニットを作成する." , 450 + 5 , 160 );
        }

        DrawCreateWindow(g);

        g.drawString(str, 0, 10);
    }

    public Unit LoadUnit(int i){
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

    private void DrawCreateWindow( Graphics g ){
        int i;

    }

    private int SetWhM(){
        for (int i = 0 ; i < 25 ; i++ ){
            if ( Chr[ i ].x < mx && mx < ( Chr[ i ].x + 90)  &&
                 Chr[ i ].y < my && my < ( Chr[ i ].y + 70)  &&
                 i < unum )
                return i;
        }

        if( (SUBMENU_X) < mx && mx < (WIDTH) &&
            (SUBMENU_Y) < my && my < (HEIGHT) ){
            return -2;
        }
        else{
            return -1;
        }
    }


}
