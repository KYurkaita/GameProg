import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class ConfSit extends PANEL{
    public ConfSit(){
        /* panelsize */
        super();
        setBounds( MENU_X , MENU_Y , MENU_WIDTH , MENU_HEIGHT );

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        MBak.draw(g);
        MSubBak.draw(g);
        g.drawString(str, 0, 10);

        g.drawString("所持ユニット数:" + unum , 20 , 35 );
        g.drawString("所持ポイント:"+point, 20 , 55 );
        g.drawString("最大ユニットコスト:"+unit_cost, 20 , 75 );

        g.drawString( "所持装備" , 250 , 25 );
        g.drawString( "LV"     , 320 , 25 );
        g.drawString( "ATK"  , 355 , 25 );
        g.drawString( "RNG"  , 405 , 25 );

        for(int i = 0 ; i <  EQ_MAX; i++){
            g.drawString( "・" + eq[i].getName() , 250 , 45 + i * 20 );
            g.drawString( ":Lv." + eq[i].getLv() , 320 , 45 + i * 20 );
            g.drawString( ":" + eq[i].getAtk(), 355 , 45 + i * 20 );
            g.drawString( ":" + eq[i].getRng() , 405 , 45 + i * 20 );
        }

    }


}
