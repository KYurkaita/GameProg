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
            g.drawString( "�G1��ɈЗ�" + getAtk() + "%�̃_���[�W." , 25 , 290 );
            g.drawString( "��ōU���ł��A�З͂������B" , 25 , 310 );
            g.drawString( "�����A�O��Ɉ�̂ł����j�b�g���c���Ă���ƌ��ɂ͍U���ł��Ȃ��B" , 25 , 330 );
            break;
            case RANCE:
            RangMap[2].draw(g);
            g.drawString( "�G1�s�ɈЗ�" + getAtk() + "%�̃_���[�W." , 25 , 290 );
            g.drawString( "�s�ōU�����邽�߁A���̃��j�b�g�Ɉ��肵�ă_���[�W��^������B" , 25 , 310 );
            break;
            case ARROW:
            RangMap[1].draw(g);
            g.drawString( "�G�P�̂ɈЗ�"+ getAtk() + "%�̃_���[�W." , 25 , 290 );
            g.drawString( "�З͍͂������A�P�̂ɂ����U���ł��Ȃ��B" , 25 , 310 );
            break;
            case RMISSILE:
            RangMap[4].draw(g);
            g.drawString( "�����_���ȓG�ɈЗ�" + getAtk() + "% �~�@5��@�̃_���[�W." , 25 , 290 );
            g.drawString( "�ꔭ�̈З͂͒Ⴍ�A�G�̑_��������������G�̐������Ȃ��قǍ��З͂ƂȂ�B" , 25 , 310 );
            break;
            case AMISSILE:
            RangMap[4].draw(g);
            g.drawString( "���ׂĂ̓G�ɈЗ�" + getAtk() + "%�̃_���[�W." , 25 , 290 );
            g.drawString( "�G�S�̂ɍU���ł��邪�A�З͂͒Ⴂ�B" , 25 , 310 );
            break;
            default:
            g.drawString( "�G���[���b�Z�[�W���낤." , 25 , 290 );
        }
    }

    public void drawRef( Graphics g ){
        g.drawString( "" + getName() , 25 , 270 );
        g.drawString( "Lv." + getLv() , 95 , 270 );
        g.drawString( "�З́F" + getAtk()+" % ", 130 , 270 );
        g.drawString( "�͈�:" + getRng() , 200 , 270 );
        refs(g);

    }

}
