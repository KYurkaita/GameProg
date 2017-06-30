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

    Image hp_icon;
    Image atk_icon;
    Image def_icon;
    Image spd_icon;

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

        ImageIcon icon = new ImageIcon(getClass().getResource("IMG/ICON/hp.png"));
        this.hp_icon = icon.getImage();
        icon = new ImageIcon(getClass().getResource("IMG/ICON/attack.png"));
        this.atk_icon = icon.getImage();
        icon = new ImageIcon(getClass().getResource("IMG/ICON/sield.png"));
        this.def_icon = icon.getImage();
        icon = new ImageIcon(getClass().getResource("IMG/ICON/speed.png"));
        this.spd_icon = icon.getImage();

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

    void copy(Unit u){
        set(u.hp,u.atk,u.def,u.spd);
        /* ----add equip----- */
        this.img = u.img;

    };

    void drawSubMenu(Graphics g){
        g.drawString( "HP :" + hp  , 450 + 20 , 160 );
        g.drawString( "ATK:" + atk , 450 + 20 , 180 );
        g.drawString( "DEF:" + def , 450 + 20 , 200 );
        g.drawString( "SPD:" + spd , 450 + 20 , 220 );
        g.drawImage ( img , 455 , 5 , 140, 130, null );
        g.drawImage ( hp_icon  , 454 , 150 , 12, 12, null );
        g.drawImage ( atk_icon , 454 , 170 , 12, 12, null );
        g.drawImage ( def_icon , 454 , 190 , 12, 12, null );
        g.drawImage ( spd_icon , 454 , 210 , 12, 12, null );
    }

}
