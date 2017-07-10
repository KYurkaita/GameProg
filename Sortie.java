import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Sortie extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int SUBMENU_X = 450;
    private static final int SUBMENU_Y = 0;

    private static final int BUT_X = 450;
    private static final int BUT_Y = 270;
    private static final int BUT_W = 150;
    private static final int BUT_H = 80;

    private static final int TILE_X = 10;
    private static final int TILE_Y = 48;
    private static final int TILE_W = 98;
    private static final int TILE_H = 87;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;

    private MENU MBak = new MENU();
    private MENU MSubBak = new MENU();
    private MENU But = new MENU();
    private MENU Tile;
    private MENU Chr[] = new MENU[25];
    private int  btl_mem[] = new int[6];

    private boolean CreateWinF = false;
    private MENU CreWin = new MENU();


    private boolean ch_flag = false;
    private int bt = 0 ;

    private Unit un[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int unum = 1 ;
    private int selnum = 0;

    public Sortie(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds( 20 , 170 , WIDTH , HEIGHT );

        /*MENU Panel create*/
        MBak.set("IMG/ITEM/menu.png");
        MSubBak.set("IMG/ITEM/submenu.png");
        MSubBak.put(SUBMENU_X,SUBMENU_Y);
        But.set("IMG/ITEM/sortie.png");
        But.put(450,290);


        Tile = new MENU();
        Tile.set("IMG/ITEM/char.png");
        Tile.put( TILE_X , TILE_Y );
        CreWin.set("IMG/ITEM/mwin2.png");
        // CreWin.put(210,0);
        //85,66

        int xy;
        for(int y = 0 ; y < 5 ; y++ ){
            for(int x = 0 ; x < 5 ; x++ ){
                xy = y * 5 + x ;
                Chr[ xy ] = new MENU();
                Chr[ xy ].set("IMG/ITEM/ch_menu2.png");
                Chr[ xy ].put( x * 85 + 12 , y * 66 +10);
            }
        }

        for( int i = 0 ; i < 6 ; i++ ){
            btl_mem[i] = 0;
        }


        for( int i = 0 ; i < 25 ; i++ ){
            un[i] = new Unit();
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

        if( CreateWinF == false ){
            bt = SetWhM();
            if( bt == 7 )  ch_flag = true;
            if( 0 <= bt && bt < 6 ) {
                SetBtNum(bt);
            }
        } else{
            CreateWinF = false;
        }


    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ bt;

        /*Main Menu Panel*/
        MBak.draw(g);
        /*Sub Menu Panel*/
        MSubBak.draw(g);
        But.draw(g);


        Tile.draw(g);

        int tx = 0 , ty = 0;
        for (int i = 0 ; i < 2 ; i++ ){
            for ( int j = 0 ; j < 3 ; j++){
                tx = TILE_W * ( 1 - i ) ;
                ty = TILE_H * j ;
                if( btl_mem[ i * 2 + j ] != 0)
                    un[ btl_mem[ i * 2 + j ] ].sdraw( g , TILE_X + tx , TILE_Y + ty );
            }
        }


        if( CreateWinF ) DrawWindow(g);

        g.drawString(str, 0, 10);

    }

    private void DrawWindow(Graphics g){
        CreWin.draw( g );

        int sx = 0 , sy = 0;
        for (int i = 0 ; i < unum ; i++ ){
            sx = i % 5; sy = i / 5 ;
            un[i].sdraw( g , 85 * sx + 10 , 66 * sy + 10 );
        }

        for (int i = 0 ; i < 25 ; i++){
            Chr[ i ].draw( g );
        }

    }

    private void SetBtNum( int num ){

        for( int i = 0 ; i < 5 ; i++ ){
            for( int j = 0 ; j < 5 ; j++ ){
                if( 85 * i + 10 < x && x < 85 * i + 95 &&
                    66 * j + 10 < y && y < 66 * j + 76 ){
                    un[ i * 5 + j ].put( num );
                    btl_mem[ num ] = i * 5 + j;
                }
            }
        }

        CreateWinF = true;

    }

    private int SetWhM(){
        int tx , ty ;

        for (int i = 0 ; i < 2 ; i++ ){
            for ( int j = 0 ; j < 3 ; j++){
                tx = TILE_W * ( 1 - i ) ;
                ty = TILE_H * j ;

                if ( ( TILE_X + tx ) < mx && mx < ( TILE_X + TILE_W + tx ) &&
                     ( TILE_Y + ty ) < my && my < ( TILE_Y + TILE_H + ty ) )
                    return  j + i * 3;
            }
        }

        if( ( BUT_X ) < mx && mx < ( BUT_X + BUT_W ) &&
            ( BUT_Y ) < my && my < ( BUT_Y + BUT_H ) ){
            return 7;
        }

        return -1;

    }


    public Unit LoadUnit(Unit u[], int i){
        return this.un[i];
    }

    public int LoadUnitNum(){
        return this.unum;
    }

    public void SaveUnit(Unit u[], int n){
        for (int i = 0; i < n ; i++ ){
            this.un[i] = u[i];
        }
        this.unum = n;
    }

    public boolean GetSorFlag(){
        return this.ch_flag;
    }

    public void SetSorFlag( boolean f ){
        this.ch_flag = f;
    }

}
