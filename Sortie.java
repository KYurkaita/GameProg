import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Color;

public class Sortie extends PANEL{
    private static final int SOR_TILE_X = 10;
    private static final int SOR_TILE_Y = 48;

    private static final int CHARA_WIN_X = 85;
    private static final int CHARA_WIN_Y = 66;

    private MENU But = new MENU();
    private MENU Tile;
    private MENU Chr[] = new MENU[UNIT_MAX];

    private boolean CreateWinF = false;
    private MENU CreWin = new MENU();
    private MENU SelWin = new MENU();
    private int selnum = 0;

    private MENU RabFight = new MENU();

    private int bt_sec = 0;

    public Sortie(){
        /* panelsize */
        super();
        setBounds( MENU_X , MENU_Y , MENU_WIDTH , MENU_HEIGHT );

        /*MENU Panel create*/
        But.set("IMG/ITEM/sortie.png");
        But.put(450,290);


        Tile = new MENU();
        Tile.set("IMG/ITEM/char.png");
        Tile.put( SOR_TILE_X , SOR_TILE_Y );
        CreWin.set("IMG/ITEM/mwin2.png");
        SelWin.set("IMG/ITEM/ch_menu4.png");

        RabFight.set("IMG/EFF/fight.png");
        RabFight.put( 460 , 10 );

        int xy;
        for(int y = 0 ; y < 5 ; y++ ){
            for(int x = 0 ; x < 5 ; x++ ){
                xy = y * 5 + x ;
                Chr[ xy ] = new MENU();
                Chr[ xy ].set("IMG/ITEM/ch_menu2.png");
                Chr[ xy ].put( x * CHARA_WIN_X + 12 , y * CHARA_WIN_Y + 10 );
            }
        }

    }


    /*MouseEvent*/
    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);

        if( CreateWinF != true ){
            selnum = SetWhM();
            if( selnum == -2 && HasBtnumUnit() ){
                SetFlag(true);
            }
            if( 0 <= selnum && selnum < BATTLE_UNIT_MAX ) CreateWinF = true;
        } else{
            CreateBtMem( selnum , SetBtNum() );
            CreateWinF = false;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e){
        super.mouseMoved(e);

        if( CreateWinF == false ){
            selnum = SetWhM();
        }else{
            bt_sec = SetBtNum();
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")"+ selnum + "," + bt_sec ;

        /*Main Menu Panel*/
        MBak.draw(g);
        /*Sub Menu Panel*/
        MSubBak.draw(g);
        But.draw(g);
        Tile.draw(g);

        DrawUnit(g);


        if( CreateWinF ){
            DrawWindow(g);
            if( 0 <= bt_sec && bt_sec <= unum ) un[ bt_sec ].drawSubMenu(g);
        }else {
            if( 0 <= selnum && selnum < BATTLE_UNIT_MAX && btnum[ selnum ] != NONE ){
                    un[ btnum[ selnum ] ].drawSubMenu(g);
            }
            else RabFight.draw( g , 130 , 270 );
        }

        g.drawString(str, 0, 10);
        g.drawString( btnum[3] + "," + btnum[0] , 400, 20 );
        g.drawString( btnum[4] + "," + btnum[1] , 400, 30 );
        g.drawString( btnum[5] + "," + btnum[2] , 400, 40 );

    }

    private void DrawUnit(Graphics g){
        int tx = 0 , ty = 0 , xy = 0;
        for (int y = 0 ; y < TILE_ROW ; y++ ){
            for ( int x = 0 ; x < TILE_COLUMN ; x++ ){
                tx = TILE_W * ( 1 - y ) ;
                ty = TILE_H * x ;
                xy = y * TILE_COLUMN + x;
                if( 0 <= btnum[ xy ] && btnum[ xy ] < unum )
                    g.drawImage(btmem[ xy ].img , SOR_TILE_X + 100 + tx , SOR_TILE_Y + ty ,-80 , 80 , this);
            }
        }

        int bt = 0;
        int maxhp = 0 , matk = 0 , mdef = 0 , mspd = 0;
        for( int i = 0; i < BATTLE_UNIT_MAX ; i++ ){
            if( btnum[i] != NONE ){
                bt++;
                maxhp += un[ btnum[ i ] ].hp;
                matk += un[ btnum[ i ] ].atk;
                mdef += un[ btnum[ i ] ].def;
                mspd += un[ btnum[ i ] ].spd;
            }
        }
        if(bt == 0) bt = 1;

        g.drawString( "ユニット情報" , 260 , 80 );
        g.drawString( "総合HP：" + maxhp , 260 , 100 );

        g.drawString( "平均ATK：" + matk / bt , 260 , 120 );
        g.drawString( "平均DEF：" + mdef / bt , 260 , 140 );
        g.drawString( "平均SPD：" + mspd / bt  , 260 , 160 );

    }

    private void DrawWindow(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AlphaComposite half = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);
        AlphaComposite def = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);

        CreWin.draw( g );
        int sx = 0 , sy = 0;
        for (int i = 0 ; i < unum ; i++ ){
            sx = i % 5; sy = i / 5 ;
            un[i].sdraw( g , CHARA_WIN_X * sx + 10 , CHARA_WIN_Y * sy + 10 );
        }

        for ( int x = 0 ; x < 5 ; x++ ){
            for ( int y = 0 ; y < 5 ; y++ ){
                if( IsBtHas( x * 5 + y ) != -1 ){
                    g2.setComposite(half);
                    SelWin.put( y * CHARA_WIN_X + 12 , x * CHARA_WIN_Y + 10 );
                    SelWin.draw(g2);
                    g2.setComposite(def);
                }
                else Chr[ x * 5 + y ].draw( g );
            }
        }

    }

    private boolean HasBtnumUnit(){
        for(int i = 0 ; i < BATTLE_UNIT_MAX ; i++ ){
            if( btnum[i] != -1 ) return true;
        }
        return false;
    }

    private int IsBtHas( int num ){
        for( int i = 0; i < BATTLE_UNIT_MAX ; i++ ){
            if( btnum[i] == num ) return i;
        }
        return -1;
    }

    private void CreateBtMem( int num , int n ){
        if( !( 0 <= num && num < BATTLE_UNIT_MAX ) )  return ;
        if( !( 0 <= n   && n < unum ) ){
            btnum[ num ] = NONE;
            return;
        }

        int x;
        if( ( x = IsBtHas( n ) ) != -1 ){
            btnum[ num ] = NONE;
            return;
        }

        btmem[ num ] = un[ n ];
        btmem[ num ].put(num);
        btnum[ num ] = n;

    }

    private int SetBtNum(){
        for( int i = 0 ; i < 5 ; i++ ){
            for( int j = 0 ; j < 5 ; j++ ){
                if( ( CHARA_WIN_X * j + 10 ) < mx && mx < ( CHARA_WIN_X * ( j + 1 ) + 10 ) &&
                    ( CHARA_WIN_Y * i + 10 ) < my && my < ( CHARA_WIN_Y * ( i + 1 ) + 76 ) ){
                    if( ( i * 5 + j ) < unum )  return i * 5 + j ;
                }
            }
        }
        return -1;
    }

    private int SetWhM(){
        int tx , ty ;

        for (int i = 0 ; i < TILE_ROW ; i++ ){
            for ( int j = 0 ; j < TILE_COLUMN ; j++){
                tx = TILE_W * ( 1 - i ) ;
                ty = TILE_H * j ;

                if ( ( SOR_TILE_X + tx ) < mx && mx < ( SOR_TILE_X + TILE_W + tx ) &&
                     ( SOR_TILE_Y + ty ) < my && my < ( SOR_TILE_Y + TILE_H + ty ) )
                    return  j + i * 3;
            }
        }

        if( ( BUT_X ) < mx && mx < ( BUT_X + BUT_W ) &&
            ( BUT_Y ) < my && my < ( BUT_Y + BUT_H ) ){
            return -2;
        }

        return -1;

    }


}
