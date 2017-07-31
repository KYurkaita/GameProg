import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class PANEL extends JPanel implements MouseListener , MouseMotionListener{
    /* size */
    protected static final int WIN_WIDTH = 640;
    protected static final int WIN_HEIGHT = 480;
    protected static final int TILE_W = 98;
    protected static final int TILE_H = 87;
    protected static final int BUT_W = 150;
    protected static final int BUT_H = 80;
    protected static final int M_WIDTH = 150;
    protected static final int M_HEIGHT = 85;

    /* place data */
    protected static final int MESSAGE_X = 15;
    protected static final int MESSAGE_Y = 420;

    protected static final int CHRWIN_X = 90;
    protected static final int CHRWIN_Y = 70;

    protected static final int BUT_X = 450;
    protected static final int BUT_Y = 270;

    /*BATTLE data */
    protected static final int TILE_COLUMN = 3;
    protected static final int TILE_ROW    = 2;

    /* MENU data */
    protected static final int MENU_WIDTH = 600;
    protected static final int MENU_HEIGHT = 350;
    protected static final int MENU_X = 20;
    protected static final int MENU_Y = 170;
    protected static final int MENU_ITEM_MAX = 4;
    protected static final int SUBMENU_X = 450;
    protected static final int SUBMENU_Y = 0;

    /* user data max */

    protected static final int EQ_MAX = 5;
    protected static final int EQ_SEL_MAX = 3;
    
    protected static final int SWORD = 0;
    protected static final int RANCE = 1;
    protected static final int ARROW = 2;
    protected static final int RMISSILE = 3;
    protected static final int AMISSILE = 4;

    protected static final int BATTLE_UNIT_MAX = 6;
    protected static final int UNIT_MAX = 25;
    protected static final int UNIT_COLUMN = 5;
    protected static final int UNIT_ROW = 5;

    protected static final int NONE = -1;

    protected static final int FIRST_LINE  = 0;
    protected static final int SECOND_LINE = 1;

    protected static final int PLAYER = 0;
    protected static final int ENEMY  = 1;

    protected static final int ENEMY_SIDE = 10;
    protected static final int NEXT_LINE = 3;

    protected String str;

    protected int x = 0;
    protected int y = 0;
    protected int mx = 0;
    protected int my = 0;

    protected MENU MBak = new MENU();
    protected MENU MSubBak = new MENU();
    protected MENU MSubIcon = new MENU();

    protected  boolean changeFlag = false;

    protected Unit un[]    = new Unit[ UNIT_MAX ];
    protected int  unum ;
    protected Unit btmem[] = new Unit[ BATTLE_UNIT_MAX ];
    protected int  btnum[] = new int [ BATTLE_UNIT_MAX ];

    protected int unit_cost;
    protected Equip eq[] = new Equip[ EQ_MAX ];

    protected int point;

    protected int time;
    protected int move;
    protected int animate;
    protected static final int STOP = 0;
    protected static final int MOVING = 1;
    protected static final int ENDING = 2;

    PANEL(){
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);

        MBak.set("IMG/ITEM/menu.png");

        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(SUBMENU_X,SUBMENU_Y);

        MSubIcon.set("IMG/ITEM/submenuicon.png");
        MSubIcon.put(SUBMENU_X,SUBMENU_Y);

        for( int i = 0 ; i < UNIT_MAX ; i++ ){
            un[i] = new Unit();
        }
        unum = 1;

        for( int i = 0 ; i < BATTLE_UNIT_MAX ; i++ ){
            btmem[i] = new Unit();
            btnum[i] = NONE;
        }

        for( int i = 0 ; i < EQ_MAX ; i++ ){
            eq[i] = new Equip(i);
        }

        point = 0;
        unit_cost = 400;

        time = 0;
        animate = 0;
        move = 0;
    }

    /*MouseEvent*/
    public void mouseClicked (MouseEvent e){;}
    public void mouseEntered (MouseEvent e){;}
    public void mouseExited  (MouseEvent e){;}
    public void mouseReleased(MouseEvent e){;}
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }


    /* basic class */
    public Equip[] LoadEquip(){
        return this.eq;
    }

    public void SaveEquip(Equip[] e){
        this.eq = e;
    }

    public Unit[] LoadBtUnit(){
        return this.btmem;
    }

    public int[] LoadBtUnitNum(){
        return this.btnum;
    }

    public void SaveBtUnit(Unit u[], int[] n){
        for (int i = 0 ; i < BATTLE_UNIT_MAX ; i++ ){
            if( n[i] != NONE ) this.btmem[i] = u[i];
        }
        this.btnum = n;
    }

    public Unit[] LoadUnit(){
        return this.un;
    }

    public int LoadUnitNum(){
        return this.unum;
    }

    public void SaveUnit(Unit u[], int n){
        this.un = u;
        this.unum = n;
    }


    public void SavePoint(int p){
        this.point = p;
    }

    public int LoadPoint(){
        return this.point;
    }


    public void SaveCost( int p ){
        this.unit_cost = p;
    }

    public int LoadCost(){
        return this.unit_cost;
    }


    public void SetFlag(boolean b){
        this.changeFlag = b;
    }

    public boolean GetFlag(){
        return this.changeFlag;
    }

    synchronized public void Time(){
        time++;
        if( time > 60 ) time = 0;
        if( move == MOVING ){
            animate++;
        }
    }

    synchronized public void AddAnimated(){
        animate++;
    }

    protected void StartMove(){
        move = MOVING;
        animate = 0;
    }

    protected void EndMove(){
        move = ENDING;
    }

    protected void StopMove(){
        move = STOP;
        animate = 0;
    }

}
