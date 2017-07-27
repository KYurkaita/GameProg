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
        this.msg = "";
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

public class MenuPanel extends PANEL{
    private JPanel card;
    private CardLayout CL ;

    private MENU_LIST menu[] = new MENU_LIST[MENU_ITEM_MAX];
    private MENU select;
    private int wh_menu = 5;
    private int ch_menu = 5;
    private int before_menu = 0;

    private UnitOrg unorg;
    private ConfSit confs;
    private TactRes tact;
    private Sortie sortie;

    public MenuPanel(){
        /* panelsize */
        super();
        setLayout(null);
        setBounds(0,0,WIN_WIDTH,WIN_HEIGHT);

        /*card make*/
        card = new JPanel();
        card.setLayout(new CardLayout());
        card.setBounds(20,110,600,350);
        CL = (CardLayout)(card.getLayout());


        /*add menu*/

        confs = new ConfSit();
        unorg = new UnitOrg();
        tact = new TactRes();
        sortie = new Sortie();

        card.add( confs  , "first" );
        card.add( unorg  , "second" );
        card.add( tact   , "third" );
        card.add( sortie , "fourth" );

        add(card);

        /*menu list*/
        for (int i = 0; i < MENU_ITEM_MAX ; i++){
            menu[i] = new MENU_LIST();
            menu[i].set("IMG/ITEM/menu" + i + ".png" );
            menu[i].change("IMG/ITEM/change" + i + ".png" );
            menu[i].put( M_WIDTH * i + 20 , 20 );
        }

        menu[0].msg = new String ("現在の状況を確認します.");
        menu[1].msg = new String ("ユニットを管理・追加します.");
        menu[2].msg = new String ("ユニット用の戦術を研究します.");
        menu[3].msg = new String ("ユニットを率いてステージに挑戦します.");

        /*select */
        select = new MENU();
        select.set("IMG/ITEM/select.png");

        /* Unit first menu */
        un[0] = new Unit();
        un[0].set("IMG/CHARA/ch_rabbit1.png");
        un[0].set(150,50,50,50);
        un[0].set(eq[2],0,100);


        un[1] = new Unit();
        un[1].set("IMG/CHARA/ch_rabbit.png");
        un[1].set(100,70,80,40);
        un[1].set(eq[0],0,100);
        unum = 2;

    }

    public void mousePressed (MouseEvent e){
        super.mousePressed(e);
        ch_menu = RetLocMenu(x,y,ch_menu);

        switch(before_menu){
            case 0: break;
            case 1:
                unum = unorg.LoadUnitNum();
                un = unorg.LoadUnit();
                break;
            case 2:
                point = tact.LoadPoint();
                eq = tact.LoadEquip();
                break;
            case 3: break;
            default:
                break;
        }

        before_menu = ch_menu;

        switch(ch_menu){
            case 0: ShowMenuFirst(); break;
            case 1: ShowMenuSecond(); break;
            case 2: ShowMenuThird(); break;
            case 3: ShowMenuFourth(); break;
            default:
                break;
        }

    }
    public void mouseMoved(MouseEvent e){
        super.mouseMoved(e);
        wh_menu = RetLocMenu(mx,my,ch_menu);
    }

    /*drawing*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ ch_menu +"," + btnum[2] ;

        for ( int i = 0 ; i < MENU_ITEM_MAX; i++){
            if( i != ch_menu){
                menu[i].draw(g);
            }
            else {
                menu[i].c_draw(g);
            }

            if( wh_menu != 5 ){
                select.put( menu[wh_menu].x, menu[wh_menu].y );
                select.draw(g);
                g.drawString( menu[wh_menu].msg , 20, 470);
            }

        }

        g.drawString(str, 0, 10);

    }

    private int RetLocMenu(int x,int y,int t){
        for (int i = 0; i < MENU_ITEM_MAX; i++){
            if( menu[i].x < x &&  x < ( menu[i].x + M_WIDTH ) &&
                menu[i].y < y &&  y < ( menu[i].y + M_HEIGHT) )
                return i;
        }
        return t;
    }

    /* menu show */
    public void ShowMenuFirst(){
        confs.SaveUnit ( un , unum );
        confs.SaveEquip( eq );
        confs.SavePoint( point );
        CL.show( card , "first" );
    }

    public void ShowMenuSecond(){
        unorg.SaveUnit( un , unum );
        unorg.SaveEquip(eq);
        CL.show( card , "second" );
    }

    public void ShowMenuThird(){
        tact.SaveEquip(eq);
        tact.SavePoint(point);
        CL.show( card , "third" );
    }

    public void ShowMenuFourth(){
        sortie.SaveUnit( un , unum );
        CL.show( card , "fourth" );
    }

    public Unit[] LoadBtMember(){
        this.btmem = sortie.LoadBtUnit();
        return this.btmem;
    }

    public int[] LoadBtNumber(){
        this.btnum = sortie.LoadBtUnitNum();
        return this.btnum;
    }

    /*flag*/
    public boolean GetFlag(){
        return sortie.GetFlag();
    }

    public void SetFlag(boolean f){
        sortie.SetFlag(f);
    }

    public void Init(){
        before_menu = 0 ;
        ch_menu = 0;
        wh_menu = 0;
        for (int i = 0; i < 6 ; i++){
            btmem[i] = new Unit();
            btnum[i] = -1;
        }
        ShowMenuFirst();
    }

}
