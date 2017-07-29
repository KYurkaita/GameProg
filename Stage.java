import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.lang.Math;
import java.awt.Graphics2D;
import java.awt.Color;


public class Stage extends PANEL{

    public int stage;
    public int max_stage;

    public int wave;
    private int max_wave;

    Stage(){
        super();

        stage = 1;
        max_stage = 1;

        wave = 1;
        max_wave = 5;

        for(int i = 0; i < BATTLE_UNIT_MAX; i++){
            btmem[i] = new Unit();
            btnum[i] = NONE;
        }

        InputMemberBy(wave);

    }

    public boolean NextWave(){
        if( wave < max_wave ){
            wave++;
            InputMemberBy(wave);
            return true;
        }
        else{
            return false;
        }

    }

    private void InputMemberBy( int w ){
        for(int i = 0; i < BATTLE_UNIT_MAX; i++){
            btmem[i] = new Unit();
            btnum[i] = NONE;
        }
        
        switch (w) {
        case 1:
            btmem[0].set(30,30,30,30);
            btmem[0].put(10);
            btmem[0].set(eq[0],0,100);
            btnum[0] = 1 ;

            point = 30;
            break;

        case 2:
            btmem[0].set(70,50,70,10);
            btmem[0].put(10);
            btmem[0].set(eq[0],0,100);
            btnum[0] = 1 ;

            btmem[1].set(50,50,50,50);
            btmem[1].put(11);
            btmem[1].set(eq[1],0,100);
            btnum[1] = 1 ;

            btmem[2].set(30,70,20,80);
            btmem[2].put(12);
            btmem[2].set(eq[2],0,100);
            btnum[2] = 1 ;

            point = 30;
            break;

        case 3:
            btmem[0].set(200,50,50,51);
            btmem[0].put(10);
            btmem[0].set(new Equip(2),0,100);
            btnum[0] = 1 ;

            btmem[4].set(150,50,50,52);
            btmem[4].put(14);
            btmem[4].set(new Equip(4),0,100);
            btnum[4] = 1 ;

            btmem[5].set(100,50,50,150);
            btmem[5].put(15);
            btmem[5].set(new Equip(0),0,100);
            btnum[5] = 2 ;

            point = 30;
            break;

        case 4:
            btmem[0].set(200,50,50,51);
            btmem[0].put(10);
            btmem[0].set(new Equip(2),0,100);
            btnum[0] = 1 ;

            btmem[4].set(150,50,50,52);
            btmem[4].put(14);
            btmem[4].set(new Equip(4),0,100);
            btnum[4] = 1 ;

            btmem[5].set(100,50,50,150);
            btmem[5].put(15);
            btmem[5].set(new Equip(0),0,100);
            btnum[5] = 2 ;

            point = 30;
            break;

        case 5:
            btmem[0].set(200,50,50,51);
            btmem[0].put(10);
            btmem[0].set(new Equip(2),0,100);
            btnum[0] = 1 ;

            btmem[4].set(150,50,50,52);
            btmem[4].put(14);
            btmem[4].set(new Equip(4),0,100);
            btnum[4] = 1 ;

            btmem[5].set(100,50,50,150);
            btmem[5].put(15);
            btmem[5].set(new Equip(0),0,100);
            btnum[5] = 2 ;

            point = 30;
            break;

        default:
            break;
        }

    }

    public Unit[] LoadWave( ){
        return btmem;
    }

    public int[]  LoadWaveNum(){
        return btnum;
    }


}
