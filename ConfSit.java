import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class ConfSit extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int MAX_EQ_NUM = 5;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    public static boolean changeFlag = false;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();

    private Unit un[] = new Unit[25];
    private int unum = 1;

    private Equip eq[] = new Equip[MAX_EQ_NUM];
    private int point;

    public ConfSit(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 20 , 170 , WIDTH , HEIGHT );

        MBak.set("IMG/ITEM/menu.png");
        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(450,0);

        for( int i = 0 ; i < MAX_EQ_NUM ; i++ ){
            eq[i] = new Equip(i);
        }
        point = 0;


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

        MBak.draw(g);
        MSubBak.draw(g);
        g.drawString(str, 0, 10);

        g.drawString("所持ユニット数:" + unum , 20 , 35 );
        g.drawString("所持ポイント:"+point, 20 , 55 );

        g.drawString( "所持装備" , 250 , 25 );
        g.drawString( "LV"     , 320 , 25 );
        g.drawString( "ATK"  , 355 , 25 );
        g.drawString( "RNG"  , 405 , 25 );

        for(int i = 0 ; i <  MAX_EQ_NUM;i++){
            g.drawString( "・" + eq[i].getName() , 250 , 45 + i * 20 );
            g.drawString( ":Lv." + eq[i].getLv() , 320 , 45 + i * 20 );
            g.drawString( ":" + eq[i].getAtk(), 355 , 45 + i * 20 );
            g.drawString( ":" + eq[i].getRng() , 405 , 45 + i * 20 );
        }

    }
    public void SaveEquip(Equip[] e){
        this.eq = e;
    }

    public void SaveUnit(Unit u[], int n){
        this.un = u;
        this.unum = n;
    }

    public void SavePoint(int p){
        this.point = p;
    }

}
