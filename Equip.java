import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;


public class Equip{
    private int no;
    private String name;

    private static final int SWORD = 0;
    private static final int RANCE = 1;
    private static final int ARROW = 2;
    private static final int RMISSILE = 3;
    private static final int AMISSILE = 4;

    private int atk;
    private int rng;
    private int level;

    private MENU RangMap[] = new MENU[5];

    Equip(){
        set("NONE",0,0,0);
        this.no = -1;
    }

    Equip(int num){
        switch(num){
            case SWORD:
            set( "sword" , 130, 3 , 1 );
            break;
            case RANCE:
            set( "rance" , 120, 2 , 1 );
            break;
            case ARROW:
            set( "arrow" , 150 ,1 , 1 );
            break;
            case RMISSILE:
            set( "R-missile" , 60 , 5 , 1 );
            break;
            case AMISSILE:
            set( "A-missile" , 70 , 6 , 1 );
            break;
            default:
            set("NONE",0,0,0);
        }
        this.no = num;

        for( int i = 0 ; i < 5 ; i++ ){
            RangMap[i] = new MENU();
            RangMap[i].set("IMG/ITEM/range" + i + ".png");
            RangMap[i].put(315,245);
        }
    }

    void set(String s,int a,int r, int lv){
        this.name = new String(s);
        this.atk = a;
        this.rng = r;
        this.level = lv;
    }

    int getAtk(){ return atk; }
    int getLv(){ return level; }
    int getRng(){ return rng; }
    String getName(){ return name; }

    public void LevelAdd(){
        if( this.level > 5 ) return;

        this.level += 1;
        this.atk += 10;
    }

    private void refs( Graphics g ){
        String s;
        switch( this.no ){
            case SWORD:
            RangMap[3].draw(g);
            g.drawString( "敵1列に威力" + getAtk() + "%のダメージ." , 25 , 290 );
            g.drawString( "列で攻撃でき、威力も高い。" , 25 , 310 );
            g.drawString( "だが、前列に一体でもユニットが残っていると後列には攻撃できない。" , 25 , 330 );
            break;
            case RANCE:
            RangMap[2].draw(g);
            g.drawString( "敵1行に威力" + getAtk() + "%のダメージ." , 25 , 290 );
            g.drawString( "行で攻撃するため、後列のユニットに安定してダメージを与えられる。" , 25 , 310 );
            break;
            case ARROW:
            RangMap[1].draw(g);
            g.drawString( "敵単体に威力"+ getAtk() + "%のダメージ." , 25 , 290 );
            g.drawString( "威力は高いが、単体にしか攻撃できない。" , 25 , 310 );
            break;
            case RMISSILE:
            RangMap[4].draw(g);
            g.drawString( "ランダムな敵に威力" + getAtk() + "% ×　5回　のダメージ." , 25 , 290 );
            g.drawString( "一発の威力は低く、敵の狙い撃ちも難しいが敵の数が少ないほど高威力となる。" , 25 , 310 );
            break;
            case AMISSILE:
            RangMap[4].draw(g);
            g.drawString( "すべての敵に威力" + getAtk() + "%のダメージ." , 25 , 290 );
            g.drawString( "敵全体に攻撃できるが、威力は低い。" , 25 , 310 );
            break;
            default:
            g.drawString( "エラーメッセージだろう." , 25 , 290 );
        }
    }

    public void drawRef( Graphics g ){
        g.drawString( "" + getName() , 25 , 270 );
        g.drawString( "Lv." + getLv() , 95 , 270 );
        g.drawString( "威力：" + getAtk()+" % ", 130 , 270 );
        g.drawString( "範囲:" + getRng() , 200 , 270 );
        refs(g);

    }

}
