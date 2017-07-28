import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.util.Random;
import java.lang.Math;
import java.awt.Graphics2D;
import java.awt.Color;

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
    Image small_menu;

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
    Equip eq[] = new Equip[3];
    int pre[] = new int[3];


    Unit(){
        set(50,50,50,50);
        set(new Equip(),0,0);
        set("IMG/CHARA/ch_frog.png");
        this.place = -1;

        for (int i = 0 ; i < 3 ; i++ ){
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
        icon = new ImageIcon(getClass().getResource("IMG/ITEM/smallmenu.png"));
        this.small_menu = icon.getImage();

    }

    public void set( int h , int a , int d , int s ){
        this.hp  = h;
        this.atk = a;
        this.def = d;
        this.spd = s;

        this.nh = h;
        this.na = a;
        this.nd = d;
        this.ns = s;
    }

    public void set( Equip e , int n , int pre ){
        if( n >= 3 ) return ;
        this.eq[n] = e;
        this.pre[n] = pre;
        this.eqnum = HvEqNum();
    }

    public void set(String s){
        ImageIcon icon = new ImageIcon(getClass().getResource(s));
        this.img = icon.getImage();
    }

    public void put( int n ){
        this.place = n;
    }

    public void draw(Graphics g, int x, int y ){
        g.drawImage( this.img , x + 80 , y , -80 , 100 , null );
    }

    public void sdraw(Graphics g, int x, int y ){
        g.drawImage( this.img , x + 2 , y + 2 , 80 , 60 , null );
    }

    public void wdraw(Graphics g , int n){
        int cx,cy;
        switch(n){
            case 0: cx = 188; cy = 100; break;
            case 1: cx = 188; cy = 185; break;
            case 2: cx = 188; cy = 270; break;
            case 3: cx =  90; cy = 100; break;
            case 4: cx =  90; cy = 185; break;
            case 5: cx =  90; cy = 270; break;
            default:
                cx = 0; cy = 0; break;
        }
        draw( g , cx , cy );

        drawSmallMenu( g , cx , cy , true );
    }

    public void edraw(Graphics g , int n){
        int cx,cy;
        switch(n){
            case 0: cx = 369; cy = 100; break;
            case 1: cx = 369; cy = 185; break;
            case 2: cx = 369; cy = 270; break;
            case 3: cx = 466; cy = 100; break;
            case 4: cx = 466; cy = 185; break;
            case 5: cx = 466; cy = 270; break;
            default:
                cx = 0; cy = 0; break;
        }
        g.drawImage( this.img , cx  , cy , 80 , 100 , null );

        drawSmallMenu( g , cx , cy , false );
    }

    public void drawSmallMenu( Graphics g , int x , int y , boolean f){
        int wh = 0;
        if( f == true ) wh = 60;

        Graphics2D g2 = (Graphics2D)g;
        AlphaComposite half = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.8f);
        AlphaComposite all = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(half);
        g2.drawImage( this.small_menu , x + wh - 12 , y + 48, null );
        g2.setComposite(all);

        g.drawImage ( hp_icon  , x + wh - 10 , y + 50 , 10, 10, null );
        g.drawImage ( atk_icon , x + wh - 10 , y + 60 , 10, 10, null );
        g.drawImage ( def_icon , x + wh - 10 , y + 70 , 10, 10, null );
        g.drawImage ( spd_icon , x + wh - 10 , y + 80 , 10, 10, null );

        g.drawString( "" + nh  , x + wh , y + 60 );
        g.drawString( "" + atk , x + wh , y + 70 );
        g.drawString( "" + def , x + wh , y + 80 );
        g.drawString( "" + spd , x + wh , y + 90 );
    }

    public void drawSubMenu(Graphics g){
        g.drawString( "HP :" + hp  , 450 + 20 , 160 );
        g.drawString( "ATK:" + atk , 450 + 20 , 180 );
        g.drawString( "DEF:" + def , 450 + 20 , 200 );
        g.drawString( "SPD:" + spd , 450 + 20 , 220 );
        g.drawImage ( img , 455 , 5 , 140, 130, null );
        g.drawImage ( hp_icon  , 454 , 150 , 12, 12, null );
        g.drawImage ( atk_icon , 454 , 170 , 12, 12, null );
        g.drawImage ( def_icon , 454 , 190 , 12, 12, null );
        g.drawImage ( spd_icon , 454 , 210 , 12, 12, null );
        g.drawString( "=====================", 450 , 230 );
        g.drawString( "E : LV : ATK : RANGE", 455 , 240 );
        if( this.place != -1 ) g.drawString( "" + this.place, 540 , 160 );

        for(int i = 0; i < eqnum; i++ ){
            g.drawString( "E" + eq[i].getName() +  ":Lv." + eq[i].getLv()+":" + eq[i].getAtk() + ":" + eq[i].getRng()  , 450+10, 252 + 12 * i );
        }

    }

    public int SelectEqip(){
        Random rnd = new Random();
        int ran = rnd.nextInt(100);

        for (int i = 0; i < this.eqnum ; i++ ){
            if( ( 100 / this.eqnum ) * (i) <= ran &&
                  ran < ( 100 / this.eqnum ) * ( i + 1 ) )
                return i;
        }
        return 0;
    }

    private int HvEqNum(){
        int i = 0;
        while( i < 3 ){
            if( this.pre[i] == 0 ) return i;
            i++;
        }

        return 3;
    }

}
