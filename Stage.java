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
    public int max_wave;

    Stage(){
        super();

        stage = 1;
        max_stage = 1;

        wave = 1;
        max_wave = 8;

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
            btmem[0].set("DATA/ENM/zonbi.png");
            btmem[0].put(10);
            btmem[0].set(eq[ARROW],0,100);
            btnum[0] = 1 ;

            point = 30;
            break;

        case 2:
            btmem[0].set(100,70,40,50);
            btmem[0].set("DATA/ENM/frogsol.png");
            btmem[0].put(10);
            btmem[0].set(eq[SWORD],0,100);
            btnum[0] = 1 ;

            btmem[1].set(100,70,40,50);
            btmem[1].set("DATA/ENM/frogsol.png");
            btmem[1].put(11);
            btmem[1].set(eq[SWORD],0,100);
            btnum[1] = 1 ;

            btmem[2].set(100,70,40,50);
            btmem[2].set("DATA/ENM/frogsol.png");
            btmem[2].put(12);
            btmem[2].set(eq[SWORD],0,100);
            btnum[2] = 1 ;

            point = 50;
            break;

        case 3:
            btmem[0].set(150,100,80,70);
            btmem[0].set("DATA/ENM/frogsol.png");
            btmem[0].put(10);
            btmem[0].set(eq[SWORD],0,100);
            btnum[0] = 1 ;

            btmem[1].set(200,130,60,50);
            btmem[1].set("DATA/ENM/PANDA.png");
            btmem[1].put(11);
            btmem[1].set(eq[RANCE],0,100);
            btnum[1] = 1 ;

            btmem[2].set(150,100,80,70);
            btmem[2].set("DATA/ENM/frogsol.png");
            btmem[2].put(12);
            btmem[2].set(eq[SWORD],0,100);
            btnum[2] = 1 ;

            point = 50;
            break;

        case 4:
            btmem[0].set(100,100,80,30);
            btmem[0].set("DATA/ENM/SARU.png");
            btmem[0].put(10);
            btmem[0].set(eq[RMISSILE],0,100);
            btnum[0] = 1 ;

            btmem[1].set(100,100,80,30);
            btmem[1].set("DATA/ENM/SARU.png");
            btmem[1].put(11);
            btmem[1].set(eq[RMISSILE],0,100);
            btnum[1] = 1 ;

            btmem[2].set(100,100,80,30);
            btmem[2].set("DATA/ENM/SARU.png");
            btmem[2].put(12);
            btmem[2].set(eq[RMISSILE],0,100);
            btnum[2] = 1 ;

            btmem[3].set(100,100,80,30);
            btmem[3].set("DATA/ENM/SARU.png");
            btmem[3].put(13);
            btmem[3].set(eq[RMISSILE],0,100);
            btnum[3] = 1 ;

            btmem[4].set(100,100,80,30);
            btmem[4].set("DATA/ENM/SARU.png");
            btmem[4].put(14);
            btmem[4].set(eq[RMISSILE],0,100);
            btnum[4] = 1 ;

            btmem[5].set(100,100,80,30);
            btmem[5].set("DATA/ENM/SARU.png");
            btmem[5].put(15);
            btmem[5].set(eq[RMISSILE],0,100);
            btnum[5] = 1 ;

            point = 80;
            break;

        case 5:
            btmem[0].set(200,130,60,50);
            btmem[0].set("DATA/ENM/PANDA.png");
            btmem[0].put(10);
            btmem[0].set(eq[RANCE],0,100);
            btnum[0] = 1 ;

            btmem[1].set(200,130,60,50);
            btmem[1].set("DATA/ENM/PANDA.png");
            btmem[1].put(11);
            btmem[1].set(eq[RANCE],0,100);
            btnum[1] = 1 ;


            btmem[2].set(200,130,60,50);
            btmem[2].set("DATA/ENM/PANDA.png");
            btmem[2].put(12);
            btmem[2].set(eq[RANCE],0,100);
            btnum[2] = 1 ;

            btmem[4].set(300,70,50,50);
            btmem[4].set("DATA/ENM/NAMAZU.png");
            btmem[4].put(14);
            btmem[4].set(eq[AMISSILE],0,100);
            btmem[4].set(eq[RMISSILE],1,100);
            btnum[4] = 1 ;

            point = 100;
            break;

        case 6:
            btmem[0].set(200,150,100,80);
            btmem[0].set("DATA/ENM/SHARK.png");
            btmem[0].put(10);
            btmem[0].set(new Equip(ARROW),0,100);
            btnum[0] = 1 ;

            btmem[2].set(200,150,100,80);
            btmem[2].set("DATA/ENM/SHARK.png");
            btmem[2].put(12);
            btmem[2].set(new Equip(ARROW),0,100);
            btnum[2] = 1 ;

            btmem[4].set(50,150,500,500);
            btmem[4].set("DATA/ENM/HIMIKO.png");
            btmem[4].put(14);
            btmem[4].set(new Equip(ARROW),0,100);
            btnum[4] = 1 ;

            point = 100;
            break;

        case 7:
            btmem[0].set(1000,200,200,200);
            btmem[0].set("DATA/ENM/HUUJIN.png");
            btmem[0].put(10);
            btmem[0].set(new Equip(RMISSILE),0,100);
            btmem[0].set(new Equip(RMISSILE),1,100);
            btmem[0].set(new Equip(AMISSILE),2,100);
            btnum[0] = 1 ;

            btmem[5].set(1000,200,200,500);
            btmem[5].set("DATA/ENM/RAIJIN.png");
            btmem[5].put(15);
            btmem[5].set(new Equip(AMISSILE),0,100);
            btmem[5].set(new Equip(AMISSILE),1,100);
            btmem[5].set(new Equip(RMISSILE),2,100);
            btnum[5] = 2 ;

            point = 300;
            break;
        case 8:
            btmem[0].set(800,200,200,300);
            btmem[0].set("DATA/ENM/KIRIN.png");
            btmem[0].put(10);
            btmem[0].set(eq[RANCE],0,100);
            btmem[0].set(eq[RANCE],1,100);
            btmem[0].set(eq[ARROW],2,100);
            btnum[0] = 1 ;

            btmem[1].set(1200,700,30,10);
            btmem[1].set("DATA/ENM/win_megami.png");
            btmem[1].put(11);
            btmem[1].set(eq[AMISSILE],0,100);
            btnum[1] = 1 ;


            btmem[2].set(800,200,200,300);
            btmem[2].set("DATA/ENM/KIRIN.png");
            btmem[2].put(12);
            btmem[2].set(eq[RANCE],0,100);
            btmem[2].set(eq[RANCE],1,100);
            btmem[2].set(eq[ARROW],2,100);
            btnum[2] = 1 ;

            btmem[4].set(2500,180,400,100);
            btmem[4].set("DATA/ENM/frogod.png");
            btmem[4].put(14);
            btmem[4].set(eq[SWORD],0,100);
            btmem[4].set(eq[RMISSILE],1,100);
            btnum[4] = 1 ;


            point = 500;
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
