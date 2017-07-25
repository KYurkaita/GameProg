import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class UnitOrg extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int SUBMENU_X = 450;
    private static final int SUBMENU_Y = 0;
    private static final int MAX_EQ_NUM = 5;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private boolean createflag = false;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();
    private MENU MSubIcom = new MENU();

    private MENU Chr[] = new MENU[25];
    private int selnum = 0;

    private MENU MWindow = new MENU();
    private int  place = -1;
    private MENU LArr = new MENU();
    private MENU RArr = new MENU();
    private MENU AddUnBt= new MENU();
    private MENU Exit = new MENU();

    private Equip eq[] = new Equip[MAX_EQ_NUM];
    private int eqnum = 0;
    private boolean eqsel[][] = new boolean[MAX_EQ_NUM][3];
    private MENU AddBt = new MENU();
    private MENU SubBt = new MENU();

    private Unit un[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int unum = 1 ;

    private final int MAX_UNIT = 400;
    private Unit crmem = new Unit();
    private int max = 0;

    public UnitOrg(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 20 , 170 , WIDTH , HEIGHT );

        MBak.set("IMG/ITEM/menu.png");
        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(SUBMENU_X,SUBMENU_Y);
        MSubIcom.set("IMG/ITEM/submenuicon.png");
        MSubIcom.put(SUBMENU_X,SUBMENU_Y);

        MWindow.set("IMG/ITEM/mwin.png");
        LArr.set("IMG/ICON/left.png");
        RArr.set("IMG/ICON/right.png");
        Exit.set("IMG/ICON/exit.png");
        Exit.put(550,15);
        AddUnBt.set("IMG/ITEM/addun.png");
        AddUnBt.put(440,255);

        AddBt.set("IMG/ICON/add.png");
        SubBt.set("IMG/ICON/sub.png");


        int xy;
        for(int y = 0 ; y < 5 ; y++ ){
            for(int x = 0 ; x < 5 ; x++ ){
                xy = y * 5 + x ;
                Chr[ xy ] = new MENU();
                Chr[ xy ].set("IMG/ITEM/ch_menu.png");
                Chr[ xy ].put( x * 90 , y * 70 );
            }
        }

        for( int i = 0 ; i < 25 ; i++ ){
            un[i] = new Unit();
        }

        for( int i = 0 ; i < MAX_EQ_NUM ; i++ ){
            eq[i] = new Equip(i);
        }

        for( int j = 0 ; j < MAX_EQ_NUM ; j++ ){
            for( int i = 0 ; i < 3 ;i++){
                eqsel[j][i] = false;
            }
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

        if( createflag == false ){
            selnum = SetWhM();
            if ( selnum == -2 )  createflag = true;
        }else{
            SetWhWin();
        }

    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
        if( createflag == false ){
            selnum = SetWhM();
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + selnum + ":" + eqnum;

        /* MENU */
        MBak.draw(g);
        for (int i = 0 ; i < 25 ; i++){
            Chr[ i ].draw(g);
        }

        /* CHARA DRAW */
        int sx = 0 , sy = 0;
        for (int i = 0 ; i < unum ; i++ ){
            sx = i % 5; sy = i / 5 ;
            un[i].sdraw( g , 90 * sx , 70 * sy );
        }

        /* SUB MENU */
        MSubBak.draw(g);
        MSubIcom.draw(g);
        if( selnum >= 0 ) un[ selnum ].drawSubMenu(g);
        else {
            g.drawString( "新規ユニットを作成する." , 450 + 5 , 160 );
        }

        if( createflag ) DrawCreateWindow(g);

        g.drawString(str, 0, 10);
    }


    private void DrawCreateWindow( Graphics g ){
        int cy = 0 ;
        MWindow.draw(g);
        max = crmem.hp + crmem.atk + crmem.def + crmem.spd;
        g.drawString( max + "/400" , 100 , 180 );

        for( int i = 0 ; i < 4 ; i++ ){
            cy = 180 +  40 * i;
            g.drawString( "減　　　　　　　　　増" , 60 , cy + 20 );
            if( i == 0 ) g.drawString( " HP:" + crmem.hp  , 100 , cy + 20 );
            else if( i == 1 ) g.drawString( "ATK:" + crmem.atk , 100 , cy + 20 );
            else if( i == 2 ) g.drawString( "DEF:" + crmem.def , 100 , cy + 20 );
            else if( i == 3 ) g.drawString( "SPD:" + crmem.spd , 100 , cy + 20 );

            LArr.put(  20 , cy );
            LArr.draw(g);
            RArr.put( 200 , cy );
            RArr.draw(g);
        }

        // Font font = new Font("", Font.PLAIN, 12);
        // setFont(font);
        g.drawString( "equip"  , 250 , 15 );
        for(int i = 0; i < MAX_EQ_NUM ; i++ ){
            for (int j = 0 ; j < 3 ; j++){
                if( eqsel[i][j] == true )
                    g.setColor(Color.red);
            }

            g.drawString( "・" + eq[i].getName() , 250 , 35 + i * 40 );
            g.drawString( ":Lv." + eq[i].getLv() + " : " + eq[i].getAtk() + ":" + eq[i].getRng() , 320 , 35 + i * 40 );

            g.setColor(Color.black);
            for (int j = 0 ; j < 3 ; j++){
                if( eqsel[i][j] == false ){
                    AddBt.put( 400 + 40 * j , 15 + i * 40 );
                    AddBt.draw(g);
                }else{
                    SubBt.put( 400 + 40 * j , 15 + i * 40 );
                    SubBt.draw(g);
                }
            }

        }

        g.setColor(Color.black);


        AddUnBt.draw(g);
        Exit.draw(g);

    }



    private void SetWhWin(){
        int cy = 0 ;
        final int ADD = 10;

        for( int i = 0 ; i < 4 ; i++ ){
            cy = 180 +  40 * i;
            max = crmem.hp + crmem.atk + crmem.def + crmem.spd;
            if( 20 < x && x < 50 &&
                cy < y && y < cy + 30 ){
                if( crmem.hp  > 10 && i == 0 ) crmem.hp  -= ADD;
                if( crmem.atk > 10 && i == 1 ) crmem.atk -= ADD;
                if( crmem.def > 10 && i == 2 ) crmem.def -= ADD;
                if( crmem.spd > 10 && i == 3 ) crmem.spd -= ADD;
            }
            else if( 200 < x && x < 230 &&
                     cy  < y && y < cy + 30 &&
                     max < 400 ){
                if( i == 0 ) crmem.hp  += ADD;
                else if( i == 1 ) crmem.atk += ADD;
                else if( i == 2 ) crmem.def += ADD;
                else if( i == 3 ) crmem.spd += ADD;
            }
        }

        for(int i = 0; i < MAX_EQ_NUM; i++ ){
            for(int j = 0 ; j < 3 ; j++ ){
                if( 400 + 40 * j < x && x < 440 + 40 * j &&
                     15 + i * 40 < y && y < 55 + i * 40 ){
                    if( eqsel[i][j] ){
                        if( 0 <= eqnum  ){
                            eqsel[i][j] = false ;
                            eqnum--;
                        }
                    } else {
                        if( eqnum < 3 ){
                            eqsel[i][j] = true ;
                            eqnum++;
                        }
                    }
                }
            }
        }

        if( 550 < x && x < 580 &&
             15 < y && y < 45 ){
            createflag = false;
            crmem = new Unit();
            for( int x = 0 ; x < MAX_EQ_NUM ; x++ ){
                for( int y = 0 ; y < 3 ;y++){
                    eqsel[x][y] = false;
                }
            }
            eqnum = 0;
        }

        if( 440 < x && x < 590 &&
            255 < y && y < 255 + 85 &&
            unum < 25  && 0 < eqnum ){
            un[unum] = crmem;
            createflag = false;

            int i = 0 , num = 0;
            while( num < 3  && i < 3 ){
                for(int j = 0 ; j < MAX_EQ_NUM ; j++ ){
                    if( eqsel[j][i] == true ){
                        un[unum].set( eq[j] , num , 100 );
                        num++;
                    }
                }
                i++;
            }
            unum += 1;

            for( int x = 0 ; x < MAX_EQ_NUM ; x++ ){
                for( int y = 0 ; y < 3 ;y++){
                    eqsel[x][y] = false;
                }
            }
            eqnum = 0;
            crmem = new Unit();
        }



    }

    private int SetWhM(){
        for (int i = 0 ; i < 25 ; i++ ){
            if( Chr[ i ].x < mx && mx < ( Chr[ i ].x + 90)  &&
                Chr[ i ].y < my && my < ( Chr[ i ].y + 70)  &&
                i < unum )
            return i;
        }

        if( (SUBMENU_X) < mx && mx < (WIDTH) &&
            (SUBMENU_Y) < my && my < (HEIGHT) ){
            return -2;
        }
        else{
            return -1;
        }
    }
    public void SaveEquip(Equip[] e){
        this.eq = e;
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


}
