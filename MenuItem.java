import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class MENU_ITEM {
    Image img;
    Image change;
    int x;
    int y;
    String msg;

    void MENU_ITEM(){
        this.x = 0;
        this.y = 0;
        this.msg ="";
    }

    void put(int x, int y){
        this.x = x;
        this.y = y;
    }

    void set(String i){
        ImageIcon icon = new ImageIcon(getClass().getResource(i));
        this.img = icon.getImage();
    }

    void change(String i){
        ImageIcon icon = new ImageIcon(getClass().getResource(i));
        this.change = icon.getImage();
    }

    public void draw(Graphics g){
        g.drawImage( this.img, this.x, this.y, null );
    }

    public void c_draw(Graphics g){
        g.drawImage( this.change, this.x, this.y, null );
    }

    public void message(Graphics g){
        g.drawString( this.msg, 0, 450);
    }

}

public class MenuItem extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int M_WIDTH = 150;
    private static final int M_HEIGHT = 85;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private MENU_ITEM menu[] = new MENU_ITEM[MENU_ITEM_MAX];
    private int wh_menu = 5;
    private int ch_menu = 5;

    public static boolean changeFlag = false;



    public MenuItem(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds(0,0,640,480);



        /*img*/
        for (int i = 0; i < MENU_ITEM_MAX ; i++){
            menu[i] = new MENU_ITEM();
            menu[i].set("IMG/ITEM/menu" + i + ".png" );
            menu[i].change("IMG/ITEM/change" + i + ".png" );
            menu[i].put( M_WIDTH * i + 20 , 20 );
            menu[i].msg = new String ("a");
        }
        menu[0].msg = new String ("test");
    }


    /*MouseEvent*/
    public void mouseClicked (MouseEvent e){;}
    public void mouseEntered (MouseEvent e){;}
    public void mouseExited  (MouseEvent e){;}
    public void mouseReleased(MouseEvent e){;}
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
        ch_menu = RetLocMenu(x,y,ch_menu);
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
        wh_menu = RetLocMenu(mx,my,5);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";
        String m = "menuuuuuuu";
        // menu[0].put(x,y);
        for ( int i = 0 ; i < MENU_ITEM_MAX; i++){
            if( i != wh_menu && i != ch_menu)
                menu[i].draw(g);
            else {
                menu[i].c_draw(g);
                g.drawString( menu[i].msg , 20, 470);
            }
        }
        // g.drawImage(menu[0],x,y,this);

        g.drawString(str, 0, 10);
        // g.drawString(m,100,100);

    }

    // public int

    private int RetLocMenu(int x,int y,int t){
        for (int i = 0; i < MENU_ITEM_MAX; i++){
            if( menu[i].x < x &&  x < ( menu[i].x + M_WIDTH ) &&
                menu[i].y < y &&  y < ( menu[i].y + M_HEIGHT) )
                return i;
        }
        return t;
    }


    public boolean getFlag(){
        return this.changeFlag;
    }

    public void setFlag(boolean f){
        this.changeFlag = f;
    }

}
