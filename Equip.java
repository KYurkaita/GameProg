import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;

enum EQ{
    SWORD, RANCE, ARROW, SMISSILE, LMISSILE ,

};

public class Equip{
    private String name;

    private int atk;
    private int def;
    private int rng;

    Equip(){
        set("NONE",1,1,1);
    }

    Equip(int name){
        switch(name){
            case SWORD:
                set( "sword" , 10, 10, 3);
                break;
             case RANCE:
                set( "rance" , 10, 10, 2);
                break;
            case ARROW:
                set( "arrow" , 10 ,10, 1);
                break;
            case SMISSILE:
                set( "s-missile" , 10 , 10, 6);
                break;
            case LMISSILE:
                set( "l-missile" , 10 , 10 , 6);
                break;
            default:
                Equip();
         }
    }

    void set(String s,int a, int d, int r){
        this.s = new String(s);
        this.atk = a;
        this.def = d;
        this.rng = r;
    }

    int getAtk(){ return atk; }
    int getDef(){ return def; }
    int getRng(){ return rng; }
    String getName(){ return name; }

}
