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

    int place;

    int hp;
    int atk;
    int def;
    int spd;

    int nh;
    int na;
    int nd;
    int ns;

    int eqnum;
    Equip eq[] = new Equip[4];
    int pre[] = new int[4];


    Unit(){
        set(50,50,50,50);
        set(new Equip(),0,0);
        set("IMG/CHARA/ch_frog.png");
        this.place = -1;

        for (int i = 0 ; i < 4 ; i++ ){
            eq[i] = new Equip();
        }

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

        this.nh = h;
        this.na = a;
        this.nd = d;
        this.ns = s;
    }

    void set( Equip e , int n,int pre ){
        this.eq[n] = e;
        this.pre[n] = pre;
        this.eqnum = HvEqNum();
    }

    void set(String s){
        ImageIcon icon = new ImageIcon(getClass().getResource(s));
        this.img = icon.getImage();
    }

    void put( int n ){
        this.place = n;
    }

    void draw(Graphics g, int x, int y ){
        g.drawImage( this.img , x + 80 , y , -80 , 100 , null );
    }

    void sdraw(Graphics g, int x, int y ){
        g.drawImage( this.img , x + 2 , y + 2 , 80 , 60 , null );
    }

    void wdraw(Graphics g , int n){
        int cx,cy;
        switch(n){
            case 0: cx = 186; cy = 100; break;
            case 1: cx = 186; cy = 185; break;
            case 2: cx = 186; cy = 270; break;
            case 3: cx =  89; cy = 100; break;
            case 4: cx =  89; cy = 185; break;
            case 5: cx =  89; cy = 270; break;
            default:
                cx = 0; cy = 0; break;
        }
        draw( g , cx , cy );
    }

    void edraw(Graphics g , int n){
        int cx,cy;
        switch(n){
            case 0: cx = 370; cy = 100; break;
            case 1: cx = 370; cy = 185; break;
            case 2: cx = 370; cy = 270; break;
            case 3: cx = 470; cy = 100; break;
            case 4: cx = 470; cy = 185; break;
            case 5: cx = 470; cy = 270; break;
            default:
                cx = 0; cy = 0; break;
        }
        draw( g , cx , cy );
    }

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
        g.drawString( "===================", 450 , 230 );
        g.drawString( "E:ATK:DEF:RANGE", 450 , 250 );

        for(int i = 0; i < eqnum; i++ ){
            g.drawString( "E" + eq[i].getName() + ":" + eq[i].getAtk() + ":" +eq[i].getDef() + ":" + eq[i].getRng(), 450 +10, 270 + 20 * i );
        }

    }

    private int HvEqNum(){
        int i = 0;
        while( i < 4 ){
            if( this.pre[i] == 0 ) return i;
            i++;
        }

        return 4;
    }

}
