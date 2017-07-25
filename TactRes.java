import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class TactRes extends JPanel implements MouseListener , MouseMotionListener{
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

    private Equip eq[] = new Equip[MAX_EQ_NUM];
    private MENU AddBt = new MENU();


    private int point;

    private int wh_point = -1;


    public TactRes(){
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

        AddBt.set("IMG/ICON/add.png");

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
        AddLevel();
    }

    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + eq[0].getLv();

        MBak.draw(g);
        MSubBak.draw(g);
        g.drawString(str, 0, 10);

        /* draw eq item */
        g.drawString( "所持装備" , 25 , 25 );
        g.drawString( "LV"     , 95 , 25 );
        g.drawString( "ATK(%)"  , 130 , 25 );
        g.drawString( "RNG"  , 185 , 25 );

        for(int i = 0 ; i <  MAX_EQ_NUM;i++){
            g.drawString( "・" + eq[i].getName() , 25 , 45 + i * 40 );
            g.drawString( ":Lv." + eq[i].getLv() , 95 , 45 + i * 40 );
            g.drawString( ":" + eq[i].getAtk(), 130 , 45 + i * 40 );
            g.drawString( ":" + eq[i].getRng() , 185 , 45 + i * 40 );
            AddBt.put( 240 , 25 + i * 40 );
            AddBt.draw(g);
            g.drawString( "消費ポイント：" , 285 , 45 + i * 40 );
        }

        DrawEqRef(g);


    }

    private void AddLevel(){
        int wh = wh_menu();
        if( wh != -1 )
            if( eq[wh].getLv() < 5 ) eq[wh].LevelAdd();
    }

    private int wh_menu(){
        for(int i = 0 ; i < MAX_EQ_NUM ; i++ ){
            if( 240 < x && x < 280 &&
                25 + 40 * i < y  && y < 65 + 40 * i )
                return i;
        }

        return -1;
    }

    private void DrawEqRef( Graphics g ){
        for(int i = 0 ; i < MAX_EQ_NUM ; i++ ){
            if( 25 < mx && mx < 410 &&
                25 + 40 * i  < my && my <  65 + 40 * i )
                eq[i].drawRef(g);
        }
    }

    public Equip[] LoadEquip(){
        return this.eq;
    }

    public void SaveEquip(Equip[] e){
        this.eq = e;
    }

    public void SavePoint(int p){
        this.point = p;
    }

    public int LoadPoint(){
        return this.point;
    }


}
