import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class HPBAR extends MENU{
    int width;
    boolean f;

    HPBAR(){
        f = true;
        super.set("IMG/ITEM/warcol.png");
        this.width = 210;
        super.put( 68 , 10 );
    }

    HPBAR(int t){
        f = false;
        super.set("IMG/ITEM/warcol.png");
        this.width = 210;
        super.put( 342 , 10 );
    }

    void draw( Graphics g , int x ){
        if(f)
            g.drawImage( this.img, this.x + 210 , this.y , - width * x / 100 , 70 , null );
        else
            g.drawImage( this.img, this.x , this.y , width * x / 100 , 70 , null );
    }


}

public class War extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int MESSAGE_X = 15;
    private static final int MESSAGE_Y = 420;


    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private int turn = 0;
    public  boolean t_next = false;

    public static boolean changeFlag = false;

    private MENU Sett;

    private MENU TeamHp;
    private MENU EnemyHp;
    private HPBAR THpBar;
    private HPBAR EHpBar;

    private MENU Time;

    private MENU Mess;

    private MENU ChrTile;
    private MENU EnmTile;

    private Unit btmem[] = new Unit[6];
    private int btnum = 5;


    public War(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 0 , 0 , WIDTH , HEIGHT );

        /* draw */
        Sett = new MENU();
        Sett.set("IMG/ITEM/config.png");
        Sett.put(10,20);

        /* Time and HP */
        TeamHp = new MENU();
        TeamHp.set("IMG/ITEM/warbar.png");
        TeamHp.put(65,10);
        EnemyHp = new MENU();
        EnemyHp.set("IMG/ITEM/warbar2.png");
        EnemyHp.put(345,10);
        Time = new MENU();
        Time.set("IMG/ITEM/warque.png");
        Time.put(275,10);

        THpBar = new HPBAR();
        EHpBar = new HPBAR(0);

        /* CHARA TILE */
        ChrTile = new MENU();
        ChrTile.set("IMG/ITEM/char.png");
        ChrTile.put(50,120);
        EnmTile = new MENU();
        EnmTile.set("IMG/ITEM/char2.png");
        EnmTile.put(370,120);

        /* Message */
        Mess = new MENU();
        Mess.set("IMG/ITEM/warmess.png");
        Mess.put(0,400);

        /* draw ends */

        for(int i = 0; i < 6; i++){
            btmem[i] = new Unit();
        }

    }


    /*MouseEvent*/
    public void mouseClicked (MouseEvent e){;}
    public void mouseEntered (MouseEvent e){;}
    public void mouseExited  (MouseEvent e){;}
    public void mouseReleased(MouseEvent e){;}
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
    //    SetFlag(true);
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        int chper = 100;
        int enper = 100;

        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + btnum + changeFlag;

        /* menu panel */
        Sett.draw(g);
        Mess.draw(g);
        ChrTile.draw(g);
        EnmTile.draw(g);

        int mhp = 0 , nhp = 0;
        for(int i = 0; i < this.btnum; i++ ){
            mhp += this.btmem[i].hp;
            nhp += this.btmem[i].nh;
        }
        // nhp -= 1;
        chper = nhp * 100 / mhp;

        /*hp*/
        g.drawString( nhp + "/" + mhp + ":" + chper  + "%", 73 , 33);

        THpBar.draw( g , chper );
        EHpBar.draw( g , enper );
        Time.draw(g);
        TeamHp.draw(g);
        EnemyHp.draw(g);

        /* chara draw */
        int cx = 0 , cy = 0;

        for ( int i = 0; i < this.btnum ; i++ ){
            switch(i){
                case 0: cx = 175; cy =  90; break;
                case 1: cx = 170; cy = 170; break;
                case 2: cx = 165; cy = 270; break;

                case 3: cx = 100; cy =  90; break;
                case 4: cx =  90; cy = 170; break;
                case 5: cx =  80; cy = 270; break;
                default:
                    break;
            }
            btmem[i].draw( g , cx , cy );
        }

        g.drawString(str, 0, 10);
        g.drawString( "" + btmem[0].spd , MESSAGE_X , MESSAGE_Y );

    }

    public Unit LoadUnit(int i){
        return this.btmem[i];
    }

    public int LoadUnitNum(){
        return this.btnum;
    }

    public void SaveUnit(Unit u[], int n){
        for (int i = 0 ; i < n ; i++ ){
            this.btmem[i] = u[i];
        }
        this.btnum = n;
    }


    public void SetFlag(boolean b){
        this.changeFlag = b;
    }


    public boolean GetFlag(){
        return this.changeFlag;
    }

}
