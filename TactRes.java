import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Color;

public class TactRes extends PANEL{

    private MENU AddBt = new MENU();
    private MENU MoveRabbit = new MENU();

    private int[] req_p = new int[ EQ_MAX ];
    private int req_maxadd = 50;

    private int wh_point = -1;

    private boolean moveflag = true;
    private AlphaComposite aniAlph;
    private AlphaComposite def;
    private float d;

    public TactRes(){
        /* panelsize */
        super();
        setBounds( MENU_X , MENU_Y , MENU_WIDTH , MENU_HEIGHT );
        AddBt.set("IMG/ICON/add.png");

        MoveRabbit.set("IMG/EFF/move.png");
        MoveRabbit.put( 460 , 100 );

        for(int i = 0 ; i < EQ_MAX ; i++){
            req_p[i] = 20;
        }

        aniAlph = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);

        def = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        StartMove();
        d = 0.0f;

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
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" ;

        MBak.draw(g);
        MSubBak.draw(g);
        g.drawString(str, 0, 10);

        g.drawString( "ユニットの最大コスト上限:  現在値" + unit_cost , 25 , 30 );
        if( unit_cost < 1000 ) g.drawString( "→ " + (unit_cost + 50) , 244 ,30 );
        if( point < req_maxadd ) g.setColor(Color.red);
        g.drawString("消費P:" + req_maxadd , 330 , 30 );
        g.setColor(Color.black);

        AddBt.put( 290 , 10 );
        AddBt.draw(g);

        /* draw eq item */
        g.drawString( "所持装備"  , 25  , 65 );
        g.drawString( "LV"       , 95  , 65 );
        g.drawString( "ATK(%)"   , 130 , 65 );
        g.drawString( "RNG"      , 185 , 65 );

        for(int i = 0 ; i <  EQ_MAX ; i++){
            g.drawString( "・" + eq[i].getName() , 25 , 85 + i * 40 );
            g.drawString( ":Lv." + eq[i].getLv() , 95 , 85 + i * 40 );
            g.drawString( ":" + eq[i].getAtk(), 130 , 85 + i * 40 );
            g.drawString( ":" + eq[i].getRng() , 185 , 85 + i * 40 );
            AddBt.put( 240 , 65 + i * 40 );
            AddBt.draw(g);
            if( point < req_p[i] ) g.setColor(Color.red);
            g.drawString( "消費P：" + req_p[i] , 285 , 85 + i * 40 );
            g.setColor(Color.black);
        }

        DrawEqRef(g);

        g.drawString( "ポイントを消費して"  , 455 , 30);
        g.drawString( "武器や上限を上げられます."  , 455 , 50);
        g.drawString( "所持ポイント:" + point , 450 + 5 , 75 );

        g.drawString( "費用:"  , 455 , 95);
        g.drawString( "コスト上限:　100ずつ上昇"  , 455 , 115);
        g.drawString( "武器:　20 × Lv (P)"  , 455 , 135);


        Graphics2D g2 = (Graphics2D)g;
        d = animate % 100 * 0.01f;
        if( animate == 100 ){
            moveflag = !moveflag;
            animate = 0;
        }

        if( moveflag == true )
        aniAlph = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, d );
        else
        aniAlph = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - d );

        g2.setComposite(aniAlph);
        MoveRabbit.put( 460 , 210 );
        MoveRabbit.draw( g2 , 130 , 100 );

        if( moveflag == true )
        aniAlph = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - d );
        else
        aniAlph = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  d );

        g2.setComposite(aniAlph);
        MoveRabbit.put( 590 , 210 );
        MoveRabbit.draw( g2 , -130 , 100 );
        g2.setComposite(def);



    }

    private void AddLevel(){
        int wh = wh_menu();
        if( wh >= 0 ){
            if( eq[wh].getLv() < 5 && point >= req_p[ wh ] ){
                eq[wh].LevelAdd();
                point -=  req_p[wh];
                req_p[wh] *= 2;
            }
        }
        else if( wh == -2 ){
            if( point >= req_maxadd ){
                unit_cost += 50;
                point -= req_maxadd;
                req_maxadd += 100;
            }
        }

    }

    private int wh_menu(){
        for(int i = 0 ; i < EQ_MAX ; i++ ){
            if( 240 < x && x < 280 &&
                65 + 40 * i < y  && y < 105 + 40 * i )
                return i;
        }

        if( 280 < x && x < 320 &&
             10 < y && y < 50 ){
            return -2;
        }

        return -1;
    }

    private void DrawEqRef( Graphics g ){
        for(int i = 0 ; i < EQ_MAX ; i++ ){
            if( 25 < mx && mx < 410 &&
                65 + 40 * i  < my && my <  105 + 40 * i )
                eq[i].drawRef(g);
        }
    }



}
