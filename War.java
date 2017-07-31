import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.lang.Math;
import java.awt.Graphics2D;
import java.awt.Color;


class HPBAR extends MENU{
    int width;
    boolean f;

    HPBAR(){
        f = true;
        super.set("IMG/ITEM/warcol.png");
        this.width = 210;
        super.put( 78 , 10 );
    }

    HPBAR(int t){
        f = false;
        super.set("IMG/ITEM/warcol.png");
        this.width = 210;
        super.put( 352 , 10 );
    }

    void draw( Graphics g , int x ){
        if(f)
            g.drawImage( this.img, this.x + 210 , this.y , - width * x / 100 , 70 , null );
        else
            g.drawImage( this.img, this.x , this.y , width * x / 100 , 70 , null );
    }


}

public class War extends PANEL{
    private static final int DEFAULT = 0;
    private static final int LOSE = 1;
    private static final int WIN  = 2;
    private static final int LOADING = -1;

    private MENU Sett;
    private MENU BackGround;
    private MENU Clouds[] = new MENU[2];

    private MENU TeamHp;
    private MENU EnemyHp;
    private HPBAR THpBar;
    private HPBAR EHpBar;
    private int chper = 100;
    private int enper = 100;
    private int mhp = 0;
    private int nhp = 0;
    private int emhp = 0;
    private int enhp = 0;

    private MENU Time;

    private MENU Mess;

    private MENU ChrTile;
    private MENU EnmTile;

    private Unit enmem[] = new Unit[BATTLE_UNIT_MAX];
    private int[] ennum = new int[BATTLE_UNIT_MAX];

    private Unit que[] = new Unit[ BATTLE_UNIT_MAX * 2 ];
    private int qnum = 0;
    private int unit_num[] = new int[2];

    private Stage stage = new Stage();
    private int turn = 0;

    private int[] sumdmg = new int[2];

    private int CreateWinFlag = 0;

    private MENU ExitMessage;
    private MENU ContinueArr;
    private int deadmem = 0;

    public War(){
        /* panelsize */
        super();
        setBounds( 0 , 0 , WIDTH , HEIGHT );

        /* draw */
        Sett = new MENU();
        Sett.set("IMG/ITEM/config.png");
        Sett.put(10,20);
        BackGround = new MENU();
        BackGround.set("IMG/EFF/mounten.png");
        BackGround.put( 100 , 10 );

        /* 0 to 100 */
        Clouds[0] = new MENU();
        Clouds[0].set("IMG/EFF/cloud0.png");
        Clouds[0].put(50,5);
        Clouds[1] = new MENU();
        Clouds[1].set("IMG/EFF/cloud1.png");

        /* Time and HP */
        TeamHp = new MENU();
        TeamHp.set("IMG/ITEM/warbar.png");
        TeamHp.put(75,10);
        EnemyHp = new MENU();
        EnemyHp.set("IMG/ITEM/warbar2.png");
        EnemyHp.put(355,10);
        Time = new MENU();
        Time.set("IMG/ITEM/warque.png");
        Time.put(285,10);

        THpBar = new HPBAR();
        EHpBar = new HPBAR(0);

        /* CHARA TILE */
        ChrTile = new MENU();
        ChrTile.set("IMG/ITEM/char.png");
        ChrTile.put(78,120);
        EnmTile = new MENU();
        EnmTile.set("IMG/ITEM/char.png");
        EnmTile.put(355,120);

        /* Message */
        Mess = new MENU();
        Mess.set("IMG/ITEM/warmess.png");
        Mess.put(0,400);

        ExitMessage = new MENU();
        ExitMessage.set("IMG/ITEM/mwin3.png");
        ContinueArr = new MENU();
        ContinueArr.set("IMG/ITEM/tobe.png");

        // ExitMessage.put();

        /* draw ends */
        for(int i = 0; i < BATTLE_UNIT_MAX; i++){
            enmem[i] = new Unit();
            ennum[i] = NONE;
        }

        enmem[0].set(200,50,50,10);
        ennum[0] = 1 ;

        for(int i = 0 ; i < BATTLE_UNIT_MAX * 2 ; i++ ){
            que[i] = new Unit();
        }

        sumdmg[PLAYER] = 0;
        sumdmg[ENEMY] = 0;

    }

    @Override
    public void mousePressed (MouseEvent e){
        super.mousePressed(e);

        if( move != STOP ){
            if( LoadStage() != false ){
                return;
            }else{
                SetFlag(true);
                return;
            }
        }

        if( CreateWinFlag == DEFAULT ){
            CreateWinFlag = TurnAdd();
            if( CreateWinFlag == WIN ) point += stage.point;
            else if( CreateWinFlag == LOSE ) point += stage.point/2;

        }
        else if( CreateWinFlag == WIN || CreateWinFlag == LOSE ){
            StartMove();
        }

    }



    private void QueInput(){
        qnum = 0 ;

        int i = 0;
        unit_num[ PLAYER ] = 0;
        unit_num[ ENEMY  ] = 0;

        while( qnum < BATTLE_UNIT_MAX * 2 && i < BATTLE_UNIT_MAX ){
            if( btnum[ i ] != NONE ){
                que[ qnum ] = btmem[ i ];
                qnum++;
                unit_num[ PLAYER ]++;
            }
            if( ennum[ i ] != NONE ){
                que[ qnum ] = enmem[ i ];
                qnum++;
                unit_num[ ENEMY ]++;
            }
            i++;
        }

        /*sorting*/
        Unit tmp = new Unit();
        for ( int x = 0 ; x < qnum -1 ; x++ ){
            for ( int y = x ; y < qnum ; y++ ){
                if( que[ x ].spd < que[ y ].spd ){
                    tmp = que[ x ];
                    que[ x ] = que[ y ];
                    que[ y ] = tmp;
                }
            }
        }

    }

    private int UnitCheck(){

        for( int i = 0; i < BATTLE_UNIT_MAX ;i++ ){
            if( btnum[ i ] != NONE ){
                if( !( btmem[i].nh > 0 ) ){
                    System.out.println("my team [" + i + "] dead" );
                    btnum[i] = NONE;
                }
            }
            if( ennum[ i ] != NONE ){
                if( !(enmem[i].nh > 0 )  ){
                    System.out.println("enemy [" + i + "] dead" );
                    ennum[i] = NONE;
                    deadmem++;
                }
            }
        }

        nhp  = 0;
        enhp = 0;
        for(int i = 0; i < BATTLE_UNIT_MAX ; i++ ){
            if( btnum[i] != NONE ) nhp += this.btmem[i].nh;
            if( ennum[i] != NONE ) enhp += this.enmem[i].nh;
        }

        if( mhp != 0 ) chper = nhp * 100 / mhp;
        else chper = 1;

        if( emhp != 0 ) enper = enhp * 100 / emhp;
        else enper = 1;

        if( unit_num[ENEMY] == 0 )  return WIN;
        if( unit_num[PLAYER] == 0 ) return LOSE;

        return DEFAULT;

    }

    private void DamageCalc( int at , int df ,int sel ){
        Unit Aunit;
        Unit Dunit;

        double lg;
        double dmg;

        Random rnd = new Random();
        int ran = rnd.nextInt(100);

        if( ! ( 0 <= ( at % 10 ) && ( at % 10 ) < BATTLE_UNIT_MAX) ||
            ! ( 0 <= ( df % 10 ) && ( df % 10 ) < BATTLE_UNIT_MAX) )
            return;

        if( ( at / 10 ) == PLAYER ){
            if( ennum[ df % 10 ] == NONE || btnum[at] == NONE )
                return;
            Aunit = btmem[ at ];
            Dunit = enmem[ df % 10 ] ;
        }else{
            if( btnum[df] == NONE || ennum[at % 10] == NONE )
                return;
            Aunit = enmem[ at % 10  ];
            Dunit = btmem[ df ] ;
        }

        lg = (double)Aunit.atk / Dunit.def;

        dmg = ( (double)( Aunit.eq[sel].getAtk() ) /100) * Aunit.atk *  Math.log10( lg + 1 );

        // random dmg
        dmg *= ( 1.00 - (double)( 50 - ran ) / 1000 );

        Dunit.nh -= dmg;
        sumdmg[at/10] += dmg;
        System.out.println( at + " to " + df + ":" + Aunit.eq[sel].getName() + ":" + dmg );

    }

    private int CheckRow( int row ){
        int pos = ( row % 10 ) % 3;

        if( ( row / 10 ) == PLAYER ){
            if( ennum[ pos ] != NONE ) return pos + 10;
            else if( ennum[ pos + 3 ] != NONE ) return pos + 13;
        }
        else {
            if( btnum[ pos ] != NONE ) return pos;
            else if( btnum[ pos + 3 ] != NONE ) return pos + 3;
        }
        return -1;
    }

    private int CheckColumn( int pos , int line ){
        //if line is true ,its 1st
        int LN = 0;
        if( line == SECOND_LINE ){ LN = 3; }

        if( pos == PLAYER ){
            for (int i = 0; i < 3 ; i++ ){
                if( btnum[ i + LN ] != NONE )    return i + LN;
            }
        }
        else {
            for (int i = 0; i < 3 ; i++ ){
                if( ennum[ i + LN ] != NONE )  return i + LN;
            }
        }

        return -1;

    }

    private int SelDefFromRange( int p , int range ){
        int df = -1 ;

        switch( range ){
        case 1: case 2:
            if( CheckRow( p ) != -1 ) df = CheckRow(p);
            else {
                if( CheckColumn( 1 - p / 10 , FIRST_LINE) != -1 )
                    df = CheckColumn( 1 - p / 10 , FIRST_LINE);
                else
                    df = CheckColumn( 1 - p / 10 , SECOND_LINE);
            }
            break;
        case 3:
            if( CheckColumn( 1 - p / 10 , FIRST_LINE) != -1 )
                df = 10 - ( p / 10 ) * 10;
            else
                df = 13 - ( p / 10 ) * 10;
            break;
        case 6:
            df = 10 - ( p / 10 ) * 10;
            break;
        case 5:
            break;
        default:
            break;
        }

        return df;

    }

    private int RandomDmg( int p ){
        Random rnd = new Random();
        int ran = rnd.nextInt( 6 );
        int count = 0;
        if( p / 10 == PLAYER ){
            while( ennum[ran] == NONE && count < 10000 ){
                ran = rnd.nextInt( 6 );
                count++;
            }
        }
        else {
            while( btnum[ran] == NONE && count < 10000 ){
                ran = rnd.nextInt( 6 );
                count++;
            }
        }
        return ran;

    }

    private void UnitAct( int n ){
        int select = que[n].SelectEqip();
        int range = que[n].eq[ select ].getRng();
        int p = que[n].place;

        Random rnd = new Random();

        int at , df ;
        at = p;

        df = SelDefFromRange( p , range );

        switch(range){
            case 1:
                DamageCalc( at , df , select);
                break;

            case 2:
                DamageCalc( at , df , select);
                DamageCalc( at , df + 3 , select);
                break;

            case 3:
                DamageCalc( at , df , select);
                DamageCalc( at , df + 1 , select);
                DamageCalc( at , df + 2 , select);
                break;
            case 6:
                for(int i = 0 ; i < BATTLE_UNIT_MAX ; i++ ){
                    DamageCalc( at , df + i , select);
                }
                break;
            case 5:
                for(int i = 0 ; i < 5 ; i++){
                    df = RandomDmg(p);
                    DamageCalc( at , df, select );
                }
                break;
            default:
                break;
        }


    }

    private int TurnAdd(){
        //input que aft sort
        sumdmg[PLAYER] = 0;
        sumdmg[ENEMY] = 0;

        QueInput();
        if(qnum == 0) return 3;

        int ch = 0;
        if( ( ch = UnitCheck()) != 0) return ch;

        turn++;
        System.out.println( "turn(" + turn + "):start"  );

        int i = 0;
        while( i < qnum && ch == 0 ){
            //check
            if( ( ch = UnitCheck() ) != 0 ) break;

            //act
            UnitAct(i);
            i++;
        }
        UnitCheck();//update

        System.out.println( "CHEAM_DMG = [" + sumdmg[PLAYER] + "]:ENEMY_DMG = [" + sumdmg[ENEMY] + "]");

        return ch;

    }

    private void MessageDraw( Graphics g ){
        Graphics2D g2 = (Graphics2D)g;
        AlphaComposite half = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.95f);
        AlphaComposite def = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);

        ExitMessage.put( 220 , 160 );
        g2.setComposite(half);
        ExitMessage.draw(g);
        g2.setComposite(def);

        if( CreateWinFlag == LOSE ){
            g.drawString( "敗北..." , 230 , 180 );
            g.drawString( "仕方ないね。こんな日もあるさ。" , 230 , 210 );
        }else if( CreateWinFlag == WIN ){
            g.drawString( "勝利" , 230 , 180 );
            g.drawString( "勝った!勝った!夕食はドンペリだ!" , 230 , 210 );
        }else{
            g.drawString("ERROR MESSAGE : YABEE HENSUU NI NATERU", 230 , 190 );
            return;
        }

        g.drawString( "< 戦闘振り返り >" , 275 , 230 );
        g.drawString( "進行wave数: " + stage.wave + "/" + stage.max_wave , 230 , 250 );
        g.drawString( "残ったチームメンバー: " + unit_num[PLAYER] , 230 , 270 );
        g.drawString( "倒した敵の数: " + deadmem, 230 , 290 );
        g.drawString( "獲得ポイント:  " + point , 230 , 310 );

        ContinueArr.put( 260 + 20 * animate , 455);
        if( 260 + 20 * animate > 441 ) EndMove();
        if( move != STOP ) ContinueArr.draw(g);

    }

    public void paintComponent(Graphics g){


        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + time;

        // ChrTile.put(x,y);
        /* BG draw */
        DrawBackGround(g);

        /*hp*/
        g.drawString( nhp + "/" + mhp + ":" + chper  + "%", 83 , 33);
        g.drawString( enhp + "/" + emhp + ":" + enper  + "%", 453 , 33);

        THpBar.draw( g , chper );
        EHpBar.draw( g , enper );

        Time.draw(g);
        g.setColor(Color.white);
        g.drawString( "WAVE", 304 , 45);
        g.drawString( "" + stage.wave , 317 , 55);
        g.setColor(Color.black);
        g.drawString( "TURN" + turn , 300 , 92);

        TeamHp.draw(g);
        EnemyHp.draw(g);

        g.drawString(str, 0, 10);

        /* chara draw */
        int cx = 0 , cy = 0;
        for ( int i = 0; i < BATTLE_UNIT_MAX ; i++ ){
            if( btnum[i] != NONE ) btmem[i].wdraw( g , i );
            if( ennum[i] != NONE ) enmem[i].edraw( g , i );
        }


        /**/
        if( qnum > 0 ){
            for(int i = 0 ; i < qnum ; i++){
                g.drawString( que[i].spd + ":(" + que[i].place+ ")", MESSAGE_X + 50 * i , MESSAGE_Y );
            }
            g.drawString( unit_num[PLAYER] + ":" + unit_num[ENEMY] , MESSAGE_X  , MESSAGE_Y + 20 );
        }


        /* MESSAGE */
        if( CreateWinFlag != 0 ) MessageDraw(g);


        /* test */
        g.drawString( btnum[3] + "," + btnum[0] , 0, 130 );
        g.drawString( btnum[4] + "," + btnum[1] , 0, 140 );
        g.drawString( btnum[5] + "," + btnum[2] , 0, 150 );

        g.drawString( ennum[0] + "," + ennum[3] , 580, 130 );
        g.drawString( ennum[1] + "," + ennum[4] , 580, 140 );
        g.drawString( ennum[2] + "," + ennum[5] , 580, 150 );


    }

    private void DrawBackGround(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AlphaComposite half = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);
        AlphaComposite def = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);

        g2.setComposite(half);
        Clouds[0].draw( g2 , 100 , 60 );
        BackGround.draw( g2 , 300 , 100 );
        g2.setComposite(def);

        /* menu panel */
        Sett.draw(g);
        Mess.draw(g);
        ChrTile.draw(g);
        EnmTile.draw(g);
    }

    private boolean LoadStage(){
        if( CreateWinFlag != LOSE && stage.NextWave() == true ){
            StopMove();
            Init();
            turn = 0;
            CreateWinFlag = 0;

            return true;
        }
        else{
            return false;
        }
    }


    public void Init(){
        mhp = 0;
        emhp = 0;
        /*ENEMY DATA LOADED*/
        enmem = stage.LoadWave();
        ennum = stage.LoadWaveNum();

        for(int i = 0; i < BATTLE_UNIT_MAX; i++ ){
            this.btmem[i].nh = this.btmem[i].hp;
        }

        for(int i = 0; i < BATTLE_UNIT_MAX; i++ ){
            if( btnum[i] != NONE ){
                mhp += this.btmem[i].hp;
            }
            if( ennum[i] != NONE ){
                emhp += this.enmem[i].hp;
            }
        }
        UnitCheck();
        System.out.println("\n wave " + stage.wave);
        QueInput();
    }

    public void Exit(){
        point = 0;

        for(int i = 0; i < BATTLE_UNIT_MAX; i++ ){
            this.btmem[i].nh = this.btmem[i].hp;
            this.btmem[i].place = NONE;
            if( ennum[i] != NONE ){
                this.enmem[i].nh = this.enmem[i].hp;
            }
        }

        deadmem = 0;
        turn = 0;
        stage.wave = 0;
        stage.NextWave();
        CreateWinFlag = 0;
        StopMove();

    }


}
