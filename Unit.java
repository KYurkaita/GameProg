import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;

enum SEED {
    FROG,
};



public class Unit {
    // private static final int ;
    Image img;

    int hp;
    int atk;
    int def;
    int spd;

    int num;
    Equip eq[] = new Equip[4];
    int pre[] = new int[4];

    Unit(){
        set(30,10,10,10);
        set(new Equip(0),0,0);
        set("IMG/CHARA/ch_frog.png");
        this.num = 1;
    }

    void set( int h , int a , int d , int s ){
        this.hp  = h;
        this.atk = a;
        this.def = d;
        this.spd = s;
    }

    void set( Equip e , int pre ,int n){
        this.eq[n] = e;
        this.pre[n] = pre;
    }

    void set(String s){
        ImageIcon icon = new ImageIcon(getClass().getResource(s));
        this.img = icon.getImage();
    }

    void drawSubMenu(Graphics g){
        g.drawString( "HP :" + hp  , 450 + 5 , 160 );
        g.drawString( "ATK:" + atk , 450 + 5 , 180 );
        g.drawString( "DEF:" + def , 450 + 5 , 200 );
        g.drawString( "SPD:" + spd , 450 + 5 , 220 );
        g.drawImage ( img , 455 , 5 , 140, 130, null );
    }

}
