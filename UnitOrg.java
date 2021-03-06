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


public class UnitOrg extends PANEL{
    private static final int CREATE_COST = 30;
    private boolean createflag = false;

    private MENU Chr[] = new MENU[UNIT_MAX];
    private int selnum = 0;

    private MENU MWindow = new MENU();
    private int  place = NONE;
    private MENU LArr = new MENU();
    private MENU RArr = new MENU();
    private MENU AddUnBt= new MENU();
    private MENU Exit = new MENU();

    private boolean eqsel[][] = new boolean[EQ_MAX][EQ_SEL_MAX];
    private MENU AddBt = new MENU();
    private MENU SubBt = new MENU();

    private Unit crmem = new Unit();
    private int  crnum =0;
    private int eqnum ;
    private int cost;


    public UnitOrg(){
        /* panelsize */
        super();
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        setBounds( MENU_X , MENU_Y , MENU_WIDTH , MENU_HEIGHT );

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
        for(int y = 0 ; y < UNIT_COLUMN ; y++ ){
            for(int x = 0 ; x < UNIT_ROW ; x++ ){
                xy = y * UNIT_ROW + x ;
                Chr[ xy ] = new MENU();
                Chr[ xy ].set("IMG/ITEM/ch_menu.png");
                Chr[ xy ].put( x * CHRWIN_X , y * CHRWIN_Y );
            }
        }

        for( int j = 0 ; j < EQ_MAX ; j++ ){
            for( int i = 0 ; i < EQ_SEL_MAX ;i++){
                eqsel[j][i] = false;
            }
        }


    }


    /*MouseEvent*/
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);

        if( createflag == false ){
            selnum = SetWhM();
            if( 0 <= selnum && selnum < unum ){
                    // if( e.getButton() == MouseEvent.BUTTON3 ){
                crmem = un[selnum];
                crnum = selnum;
                createflag = true;

            }else{
                if ( selnum == -1 || selnum  == -2 ){
                    createflag = true;
                    crnum = unum;
                }
            }
        }else{
            OperateUnit();
        }

    }
    public void mouseMoved(MouseEvent e){
        super.mouseMoved(e);

        if( createflag == false ){
            selnum = SetWhM();
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + selnum + ":" + eqnum;

        /* MENU */
        MBak.draw(g);
        for (int i = 0 ; i < UNIT_MAX ; i++){
            Chr[ i ].draw(g);
        }

        /* CHARA DRAW */
        int sx = 0 , sy = 0;
        for (int i = 0 ; i < unum ; i++ ){
            sx = i % UNIT_COLUMN; sy = i / UNIT_ROW ;
            un[i].sdraw( g , sx * CHRWIN_X , sy * CHRWIN_Y );
        }

        /* SUB MENU */
        MSubBak.draw(g);
        MSubIcon.draw(g);
        if( selnum >= 0 ) un[ selnum ].drawSubMenu(g);
        else {
            g.drawString( "所持ポイント:" + point , 450 + 5 , 30 );
            g.drawString( "所持ユニット数:" + unum , 450 + 5 , 50 );
            g.drawString( "コスト最大値:" + unit_cost , 450 + 5 , 70 );

            g.drawString( "新規ユニットの作成" , 450 + 5 , 160 );
            g.drawString( "> 消費ポイント:30"  , 450 + 5 , 180 );
            g.drawString( ""  , 450 + 5 , 200 );
            g.drawString( "既存ユニットの更新" , 450 + 5 , 240 );
            g.drawString( "> 消費ポイント:30"  , 450 + 5 ,  260 );
        }

        if( createflag ) DrawCreateWindow(g);

        g.drawString(str, 0, 10);
    }


    private void DrawCreateWindow( Graphics g ){
        int cy = 0 ;
        int max = crmem.hp + crmem.atk + crmem.def + crmem.spd;

        MWindow.draw(g);

        if( crnum == unum ){
            g.drawString( "ユニット作成" , 60 , 25 );
        }else{
            g.drawString( "ユニット更新"  , 60 , 25 );
        }

        g.drawString( max + "/" + unit_cost  , 100 , 180 );
        crmem.draw( g, 60 , 30 );
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

        g.drawString( "  equip"  , 250 , 25 );
        g.drawString( "  LV: 威力:範囲"  , 320 , 25 );
        for(int i = 0; i < EQ_MAX ; i++ ){
            for (int j = 0 ; j < EQ_SEL_MAX ; j++){
                if( eqsel[i][j] == true )
                    g.setColor(Color.red);
            }

            g.drawString( "・" + eq[i].getName() , 250 , 45 + i * 40 );
            g.drawString( ":Lv." + eq[i].getLv() + " : " + eq[i].getAtk() + ":" + eq[i].getRng() , 320 , 45 + i * 40 );

            g.setColor(Color.black);
            for (int j = 0 ; j < EQ_SEL_MAX ; j++){
                if( eqsel[i][j] == false ){
                    AddBt.put( 400 + 40 * j , 25 + i * 40 );
                    AddBt.draw(g);
                }else{
                    SubBt.put( 400 + 40 * j , 25 + i * 40 );
                    SubBt.draw(g);
                }
            }

        }

        g.drawString( "所持ポイント:" + point + " → "  + ( point - 30 ) , 260 , 260 );

        g.setColor(Color.red);
        if( point < 30 )
            g.drawString( "所持ポイントが足りません。" , 260 , 280 );

        if( !( unum < UNIT_MAX ) )
            g.drawString( "所持ユニット数が上限です。" , 260 , 300 );
        g.setColor(Color.black);


        AddUnBt.draw(g);
        Exit.draw(g);

    }



    private void OperateUnit(){
        final int ADD = 10;

        int cy = 0 ;
        int max = crmem.hp + crmem.atk + crmem.def + crmem.spd;
        for( int i = 0 ; i < 4 ; i++ ){
            cy = 180 +  40 * i;
            if( 20 < x && x < 50 &&
                cy < y && y < cy + 30 ){
                if( crmem.hp  > 10 && i == 0 ) crmem.hp  -= ADD;
                if( crmem.atk > 10 && i == 1 ) crmem.atk -= ADD;
                if( crmem.def > 10 && i == 2 ) crmem.def -= ADD;
                if( crmem.spd > 10 && i == 3 ) crmem.spd -= ADD;
            }
            else if( 200 < x && x < 230 &&
                     cy  < y && y < cy + 30 &&
                     max < unit_cost ){
                if( i == 0 ) crmem.hp  += ADD;
                else if( i == 1 ) crmem.atk += ADD;
                else if( i == 2 ) crmem.def += ADD;
                else if( i == 3 ) crmem.spd += ADD;
            }
        }

        for(int i = 0; i < EQ_MAX; i++ ){
            for(int j = 0 ; j < EQ_SEL_MAX ; j++ ){
                if( 400 + 40 * j < x && x < 440 + 40 * j &&
                     25 + i * 40 < y && y < 65 + i * 40 ){
                    if( eqsel[i][j] ){
                        if( 0 <= eqnum  ){
                            eqsel[i][j] = false ;
                            eqnum--;
                        }
                    } else {
                        if( eqnum < EQ_SEL_MAX ){
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
            for( int x = 0 ; x < EQ_MAX ; x++ ){
                for( int y = 0 ; y < EQ_SEL_MAX ;y++){
                    eqsel[x][y] = false;
                }
            }
            eqnum = 0;
        }

        if( 440 < x && x < 590 &&
            255 < y && y < 255 + 85 &&
            eqnum != 0 && point >= CREATE_COST ){
            if( crnum == unum ){
                if( unum < UNIT_MAX ) CreateUnit( unum );
            }
            else{
                CreateUnit(crnum);
            }
        }

    }

    private void CreateUnit( int t ){
        un[t] = crmem;
        createflag = false;

        int i = 0 , num = 0;
        while( num < 3  && i < EQ_SEL_MAX ){
            for(int j = 0 ; j < EQ_MAX ; j++ ){
                if( eqsel[j][i] == true ){
                    un[t].set( eq[j] , num , 100 );
                    num++;
                }
            }
            i++;
        }
        if( t == unum ) unum += 1;
        point -= CREATE_COST;

        for( int x = 0 ; x < EQ_MAX ; x++ ){
            for( int y = 0 ; y < EQ_SEL_MAX ;y++){
                eqsel[x][y] = false;
            }
        }
        eqnum = 0;
        crmem = new Unit();
    }

    private int SetWhM(){
        for (int i = 0 ; i < UNIT_MAX ; i++ ){
            if( Chr[ i ].x < mx && mx < ( Chr[ i ].x + CHRWIN_X )  &&
                Chr[ i ].y < my && my < ( Chr[ i ].y + CHRWIN_Y )  &&
                i < unum )
            return i;
        }

        if( ( SUBMENU_X ) < mx && mx < ( MENU_WIDTH ) &&
            ( SUBMENU_Y ) < my && my < ( MENU_HEIGHT ) ){
            return -2;
        }
        else{
            return -1;
        }
    }



}
