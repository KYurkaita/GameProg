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


    private int stage;
    private int max_stage;

    private int wave;
    private int max_wave;

    Stage(){
        super();

        stage = 0;
        max_stage = 0;
        wave = 0;
        max_wave = 0;

    }

    public Unit[] LoadEnmMem( int t ){

        return btmem;
    }

    public int[] LoadEnmNum( int t ){

        return btnum;
    }




}
