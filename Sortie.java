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

    private boolean CreateWinF = false;
    private MENU CreWin = new MENU();
    private int selnum = 0;


    private boolean ch_flag = false;
    private int bt_sec = 0;

    private Unit un[] = new Unit[25];
    private Unit btmem[] = new Unit[6];
    private int  unum = 1 ;
    private int  btnum[] = new int[6];



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
            btnum[i] = -1;
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

        if( CreateWinF != true ){
            selnum = SetWhM();
            if( selnum == -2 && HasBtnumUnit() ){
                ch_flag = true;
            }
            if( 0 <= selnum && selnum < 6 ) CreateWinF = true;
        } else{
            btnum[ selnum ] = SetBtNum();
            CreateBtMem( selnum , SetBtNum() );
            CreateWinF = false;
        }


    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;

        if( CreateWinF == false ){
            selnum = SetWhM();
        }else{
            bt_sec = SetBtNum();
        }

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ selnum + "," + bt_sec ;

        /*Main Menu Panel*/
        MBak.draw(g);
        /*Sub Menu Panel*/
        MSubBak.draw(g);
        But.draw(g);


        Tile.draw(g);


        int tx = 0 , ty = 0;
        for (int i = 0 ; i < 2 ; i++ ){
            for ( int j = 0 ; j < 3 ; j++ ){
                tx = TILE_W * ( 1 - i ) ;
                ty = TILE_H * j ;
                if( 0 <= btnum[ i * 3 + j ] && btnum[ i * 3 + j ] < unum )
                    btmem[ i * 3 + j ].sdraw( g , TILE_X + tx , TILE_Y + ty );
            }
        }


        if( CreateWinF ){
            DrawWindow(g);
            if( 0 <= bt_sec && bt_sec <= unum ) un[ bt_sec ].drawSubMenu(g);
        }else {
            if( 0 <= selnum && selnum < unum  )
                if( btnum[ selnum ] != -1 )
                    un[ btnum[ selnum ] ].drawSubMenu(g);
        }

        g.drawString(str, 0, 10);
        g.drawString( btnum[3] + "," + btnum[0] , 400, 20 );
        g.drawString( btnum[4] + "," + btnum[1] , 400, 30 );
        g.drawString( btnum[5] + "," + btnum[2] , 400, 40 );

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

    private boolean HasBtnumUnit(){
        for(int i = 0 ; i < 6 ; i++ ){
            if( btnum[i] != -1 ) return true;
        }
        return false;
    }

    private void CreateBtMem( int num , int n ){
        if( !( 0 <= num && num < 6 ) )  return ;
        if( !( 0 <= n   && n < unum ) ) return ;

        btmem[ num ] = un[ n ];
        btmem[ num ].put(num);
    }

    private int SetBtNum(){
        for( int i = 0 ; i < 5 ; i++ ){
            for( int j = 0 ; j < 5 ; j++ ){
                if( ( 85 * j + 10 ) < mx && mx < ( 85 * j + 95 ) &&
                    ( 66 * i + 10 ) < my && my < ( 66 * i + 76 ) ){
                    if( ( i * 5 + j ) < unum )  return i * 5 + j ;
                }
            }
        }
        return -1;
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
            return -2;
        }

        return -1;

    }

    public Unit[] LoadBtUnit(){
        return this.btmem;
    }

    public int[] LoadBtUnitNum(){
        return this.btnum;
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
