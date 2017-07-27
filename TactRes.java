import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class TactRes extends PANEL{
    private MENU AddBt = new MENU();

    private int[] req_p = new int[ EQ_MAX ];

    private int wh_point = -1;

    public TactRes(){
        /* panelsize */
        super();
        setBounds( MENU_X , MENU_Y , MENU_WIDTH , MENU_HEIGHT );
        AddBt.set("IMG/ICON/add.png");

    }


    /*MouseEvent*/
    @Override
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
        AddLevel();
    }

    @Override
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

        for(int i = 0 ; i <  EQ_MAX ; i++){
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
        for(int i = 0 ; i < EQ_MAX ; i++ ){
            if( 240 < x && x < 280 &&
                25 + 40 * i < y  && y < 65 + 40 * i )
                return i;
        }

        return -1;
    }

    private void DrawEqRef( Graphics g ){
        for(int i = 0 ; i < EQ_MAX ; i++ ){
            if( 25 < mx && mx < 410 &&
                25 + 40 * i  < my && my <  65 + 40 * i )
                eq[i].drawRef(g);
        }
    }



}
