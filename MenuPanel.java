import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class MENU_LIST extends MENU {
    Image change;
    String msg;

    MENU_LIST(){
        super();
        this.msg ="";
    }

    void change(String i){
        ImageIcon icon = new ImageIcon(getClass().getResource(i));
        this.change = icon.getImage();
    }

    public void c_draw(Graphics g){
        g.drawImage( this.change, this.x, this.y, null );
    }

    public void message(Graphics g){
        g.drawString( this.msg, 0, 450);
    }

}

public class MenuPanel extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_MAX = 4;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int M_WIDTH = 150;
    private static final int M_HEIGHT = 85;
    private static final int UNIT_NUM = 6;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private String str;

    private JPanel card;
    private CardLayout CL ;

    public static boolean changeFlag = false;
    private MENU_LIST menu[] = new MENU_LIST[MENU_MAX];
    private MENU select;
    private int wh_menu = 5;
    private int ch_menu = 5;
    private MenuItem p_menu[] = new MenuItem[MENU_MAX];
    private UnitOrg unorg;

    private Unit u[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int unum = 1;


    public MenuPanel(){
        /* panelsize */
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        setLayout(null);
        setBounds(0,0,640,480);

        addMouseListener(this);
        addMouseMotionListener(this);

        // JPanel jp = new JPanel();
        // jp.setLayout(null);
        // jp.setBounds(400,350,100,100);
        // add (jp);

        /*card make*/
        card = new JPanel();
        card.setLayout(new CardLayout());
        card.setBounds(20,110,600,350);
        CL = (CardLayout)(card.getLayout());


        /*add menu*/
        for( int i = 0; i < MENU_MAX ; i++){
            p_menu[i] = new MenuItem();
        }
        unorg = new UnitOrg();

        card.add(p_menu[0],"first");
        card.add(unorg,"second");
        card.add(p_menu[2],"third");
        card.add(p_menu[3],"fourth");

        add(card);

        /*menu list*/
        for (int i = 0; i < MENU_MAX ; i++){
            menu[i] = new MENU_LIST();
            menu[i].set("IMG/ITEM/menu" + i + ".png" );
            menu[i].change("IMG/ITEM/change" + i + ".png" );
            menu[i].put( M_WIDTH * i + 20 , 20 );
            menu[i].msg = new String ("a");
        }
        menu[0].msg = new String ("現在の状況を確認します.");
        menu[1].msg = new String ("ユニットを管理・追加します.");
        menu[2].msg = new String ("ユニット用の戦術を研究します.");
        menu[3].msg = new String ("ユニットを率いてステージに挑戦します.");

        select = new MENU();
        select.set("IMG/ITEM/select.png");

        u[0] = new Unit();
        u[0].set("IMG/CHARA/ch_frog.png");
        u[1] = new Unit();
        u[1].set("IMG/CHARA/ch_rabbit.png");
        u[1].set(40,20,20,20);
        unum = 2;

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

        switch(ch_menu){
            case 0: ShowMenuFirst(); break;
            case 1: ShowMenuSecond(); break;
            case 2: ShowMenuThird(); break;
            case 3: ShowMenuFourth(); break;
            default:
                break;
        }

    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
        wh_menu = RetLocMenu(mx,my,ch_menu);
    }

    /*drawing*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ ch_menu +"," + changeFlag;
        String m = "menuuuuuuu";
        // menu[0].put(x,y);
        for ( int i = 0 ; i < MENU_MAX; i++){
            if( i != ch_menu)
                menu[i].draw(g);
            else {
                menu[i].c_draw(g);
            }

            if( wh_menu != 5 ){
                select.put( menu[wh_menu].x, menu[wh_menu].y );
                select.draw(g);
                g.drawString( menu[wh_menu].msg , 20, 470);
            }

        }
        // g.drawImage(menu[0],x,y,this);

        g.drawString(str, 0, 10);
        // g.drawString(m,100,100);

    }

    private int RetLocMenu(int x,int y,int t){
        for (int i = 0; i < MENU_MAX; i++){
            if( menu[i].x < x &&  x < ( menu[i].x + M_WIDTH ) &&
                menu[i].y < y &&  y < ( menu[i].y + M_HEIGHT) )
                return i;
        }
        return t;
    }

    private void ShowMenuFirst(){
        CL.show( card , "first" );
    }

    private void ShowMenuSecond(){
        unorg.SaveUnit( u , unum );
        CL.show( card , "second" );
    }

    private void ShowMenuThird(){
        CL.show( card , "third" );
    }

    private void ShowMenuFourth(){
        CL.show( card , "fourth" );
    }

    public void SaveWar(Unit u[], int n){
        for(int i = 0 ; i < n ; i++){
            this.btmem[i] = u[i];
        }
        this.unum = n;
    }

    public void LoadWar(Unit u[] , int n){
        for(int i = 0 ; i < n ; i++){
            u[i] = this.btmem[i];
        }
        n = this.unum;
    }

    public boolean getFlag(){
        return this.changeFlag;
    }

    public void setFlag(boolean f){
        this.changeFlag = f;
    }

}
