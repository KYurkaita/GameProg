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

public class War extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_ITEM_MAX = 4;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 350;
    private static final int MESSAGE_X = 15;
    private static final int MESSAGE_Y = 420;
    private static final int CHEAM_MEM = 0;
    private static final int ENEMY_MEM = 1;
    private static final boolean FIRST_LINE  = true;
    private static final boolean SECOND_LINE = false;
    private static final boolean PLAYER_SIDE = true;
    private static final boolean ENEMY_SIDE  = false;

    private String str;

    private int x = 0;
    private int y = 0;
    private int mx = 0;
    private int my = 0;


    public static boolean changeFlag = false;

    private MENU Sett;

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

    private Unit btmem[] = new Unit[6];
    private int btnum[] = new int[6];

    private Unit enmem[] = new Unit[6];
    private int ennum[] = new int[6];

    private Unit que[] = new Unit[12];
    private int qnum = 0;
    private int unum[] = new int[2];


    private int turn = 0;
    public  boolean t_next = false;


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

        /* draw ends */

        for(int i = 0; i < 6; i++){
            btmem[i] = new Unit();
            btnum[i] = -1;

            enmem[i] = new Unit();
            ennum[i] = -1;
        }

        enmem[0].set(200,50,50,10);
        ennum[0] = 1 ;


        for(int i = 0 ; i < 12 ; i++ ){
            que[i] = new Unit();
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
        if( TurnAdd() != 0 ){
            SetFlag(true);
        }

    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX() ;
        my = e.getY() ;
    }

    private void QueInput(){
        qnum = 0 ;

        int i = 0;
        unum[CHEAM_MEM] = 0;
        unum[ENEMY_MEM] = 0;

        while( qnum < 12 && i < 6){
            if( btnum[ i ] != -1 ){
                que[ qnum ] = btmem[ i ];
                qnum++;
                unum[CHEAM_MEM]++;
            }
            if( ennum[ i ] != -1 ){
                que[ qnum ] = enmem[ i ];
                qnum++;
                unum[ENEMY_MEM]++;
            }
            i++;
        }

        Unit tmp = new Unit();
        for ( int x = 0 ; x < qnum-1 ; x++ ){
            for ( int y = x ; y < qnum ; y++ ){
                if( que[x].spd < que[y].spd ){
                    tmp = que[x];
                    que[x] = que[y];
                    que[y] = tmp;
                }
            }
        }
    }

    private int UnitCheck(){
        for( int i = 0; i < 6 ;i++ ){
            if( btnum[ i ] != -1 ){
                if( !( btmem[i].nh > 0 ) ) btnum[i] = -1;
            }

            if( ennum[ i ] != -1 ){
                if( !(enmem[i].nh > 0 )  ) ennum[i] = -1;
            }
        }

        nhp  = 0;
        enhp = 0;
        for(int i = 0; i < 6; i++ ){
            if( btnum[i] != -1) nhp += this.btmem[i].nh;
            if( ennum[i] != -1) enhp += this.enmem[i].nh;
        }

        if( mhp != 0 ) chper = nhp * 100 / mhp;
        else chper = 1;

        if( emhp != 0 ) enper = enhp * 100 / emhp;
        else enper = 1;

        if( unum[ENEMY_MEM] == 0 ) return 2;
        if( unum[CHEAM_MEM] == 0 ) return 1;

        return 0;

    }

    private int SelectEqip( int n ){
        int e;
        e = que[n].eqnum;
        Random rnd = new Random();
        int ran = rnd.nextInt(100);

        for (int i = 0; i < e ; i++ ){
            if( ( 100 / e ) * (i) <= ran &&
                  ran < ( 100 / e ) * (i+1) )
                return i;
        }
        return 0;

    }

    private void DamageCalc( int at , int df ,int sel ){
        Unit Aunit;
        Unit Dunit;

        double lg;
        double dmg;

        Random rnd = new Random();
        int ran = rnd.nextInt(100);
        // if( at ==-1 || df == -1 )return ;

        if( ( at / 10 ) != 1 ){
            if( btnum[at] == -1 || ennum[df % 10] == -1) return;
            Aunit = btmem[ at ];
            Dunit = enmem[ df % 10 ] ;
        }else{
            if( btnum[df] == -1 || ennum[at % 10] == -1) return;
            Aunit = enmem[ at % 10  ];
            Dunit = btmem[ df ] ;
        }

        lg = (double)Aunit.atk / Dunit.def;

        dmg = ( (double)(Aunit.eq[sel].getAtk() + 100)/100) * Aunit.atk *  Math.log10( lg + 1);

        // random dmg
        dmg *= ( 1.00 - (double)( 50 - ran ) / 1000 );

        Dunit.nh -= dmg;
        System.out.println( at + " to " + df + ":" + Aunit.eq[sel].getName() + ":" + dmg );

    }

    private int CheckLine( boolean pos , boolean line ){
        //if line is true ,its 1st
        int LN = 0;
        if( line != FIRST_LINE ){ LN = 3;}

        if( pos == PLAYER_SIDE ){
            for (int i = 0; i < 3 ; i++ ){
                if( btnum[ i + LN ] != -1 ) return i;
            }
        }
        else {
            for (int i = 0; i < 3 ; i++ ){
                if( ennum[ i + LN ] != -1 ) return i;
            }
        }
        System.out.println("x");

        return -1;

    }

    private void UnitAct( int n ){
        int select;
        select = SelectEqip(n);
        int range= que[n].eq[ select ].getRng();
        int p = que[n].place;

        int at , df ;
        at = p;

        switch(range){
            case 1:
                if( ( p / 10 ) != 1 ){
                    if( ennum[ ( p % 10 ) % 3 ] != -1 )
                        df = ( p % 10 ) % 3 + 10;
                    else{
                        if( CheckLine( ENEMY_SIDE ,  FIRST_LINE ) != -1 )
                            df = CheckLine( ENEMY_SIDE ,  FIRST_LINE );
                        else df = CheckLine( ENEMY_SIDE ,  SECOND_LINE );
                    }
                }
                else{
                    if( btnum[ p % 10 ] != -1 ) df = p % 10 ;
                    else{
                        if( CheckLine( PLAYER_SIDE ,  FIRST_LINE ) != -1 )
                            df = CheckLine( PLAYER_SIDE ,  FIRST_LINE );
                        else df = CheckLine( PLAYER_SIDE ,  SECOND_LINE );
                    }
                }
                DamageCalc( at , df , select);
                break;
            case 2:
                if( ( p / 10 ) != 1 ){
                    if( ennum[ p % 3 ] != -1 ) df = p + 10;

                    else{
                        if( CheckLine( ENEMY_SIDE ,  FIRST_LINE ) != -1 )
                        df = CheckLine( ENEMY_SIDE ,  FIRST_LINE );
                        else df = CheckLine( ENEMY_SIDE ,  SECOND_LINE );
                    }
                }
                else{
                    if( btnum[ (p % 10) % 3 ] != -1 ) df = (p % 10) % 3 ;
                    else{
                        if( CheckLine( PLAYER_SIDE ,  FIRST_LINE ) != -1 )
                            df = CheckLine( PLAYER_SIDE ,  FIRST_LINE );
                        else df = CheckLine( PLAYER_SIDE ,  SECOND_LINE );
                    }
                }
                DamageCalc( at , df , select);
                DamageCalc( at , df + 3 , select);
                break;

            case 3:
                if( ( p / 10 ) != 1 ){
                    if( CheckLine( ENEMY_SIDE ,  FIRST_LINE ) != -1 )
                        df = 10;
                    else df = 13;
                }
                else{
                    if( CheckLine( PLAYER_SIDE ,  FIRST_LINE ) != -1 )
                        df = 0;
                    else df = 3;
                }
                DamageCalc( at , df , select);
                DamageCalc( at , df + 1 , select);
                DamageCalc( at , df + 2 , select);
                break;
            case 6:
                if( ( p / 10 ) != 1 )   df = 10;
                else                    df = 0;

                for(int i = 0 ; i < 6 ; i++ ){
                    DamageCalc( at , df + i , select);
                }

                break;
            default:
                break;
        }


    }

    private int TurnAdd(){
        //input que aft sort
        QueInput();
        if(qnum == 0) return 3;

        int i = 0;
        int ch =0;
        while( i < qnum && ch == 0){
            //check
            if( ( ch = UnitCheck() ) != 0 ) break;

            //act
            UnitAct(i);
            i++;
        }

        return ch;

    }


    public void paintComponent(Graphics g){


        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")" + btnum[0] + changeFlag;

        // ChrTile.put(x,y);
        /* menu panel */
        Sett.draw(g);
        Mess.draw(g);
        ChrTile.draw(g);
        EnmTile.draw(g);

        /*hp*/
        g.drawString( nhp + "/" + mhp + ":" + chper  + "%", 83 , 33);
        g.drawString( enhp + "/" + emhp + ":" + enper  + "%", 453 , 33);

        THpBar.draw( g , chper );
        EHpBar.draw( g , enper );
        Time.draw(g);
        TeamHp.draw(g);
        EnemyHp.draw(g);

        /* chara draw */
        int cx = 0 , cy = 0;

        for ( int i = 0; i < 6 ; i++ ){
            if( btnum[i] != -1 ) btmem[i].wdraw( g , i );
            if( ennum[i] != -1 ) enmem[i].edraw( g , i );
        }

        g.drawString(str, 0, 10);
        if( qnum > 0 ){
            for(int i = 0 ; i < qnum ; i++){
                g.drawString( que[i].spd + ":(" + que[i].place+ ")", MESSAGE_X + 50 * i , MESSAGE_Y );
            }
            g.drawString( unum[CHEAM_MEM] + ":" + unum[ENEMY_MEM] , MESSAGE_X  , MESSAGE_Y + 20 );
        }

        g.drawString( btnum[3] + "," + btnum[0] , 0, 130 );
        g.drawString( btnum[4] + "," + btnum[1] , 0, 140 );
        g.drawString( btnum[5] + "," + btnum[2] , 0, 150 );

        g.drawString( ennum[0] + "," + ennum[3] , 580, 130 );
        g.drawString( ennum[1] + "," + ennum[4] , 580, 140 );
        g.drawString( ennum[2] + "," + ennum[5] , 580, 150 );


    }

    //
    // public Unit LoadUnit(int i){
    //     return this.btmem[i];
    // }
    //
    // public int LoadUnitNum(){
    //     return this.btnum[];
    // }

    public void Init(){
        mhp = 0;
        emhp = 0;

        /*ENEMY DATA LOADED*/
        for(int i = 0; i < 6; i++){
            enmem[i] = new Unit();
            ennum[i] = -1;
        }

        enmem[0].set(200,50,50,10);
        enmem[0].put(10);
        enmem[0].set(new Equip(2),0,100);
        ennum[0] = 1 ;

        enmem[4].set(150,150,50,40);
        enmem[4].put(14);
        enmem[4].set(new Equip(2),0,100);
        // ennum[1] = 1;
        ennum[4] = 1 ;

        enmem[5].set(100,50,150,150);
        enmem[5].put(15);
        enmem[5].set(new Equip(2),0,100);
        ennum[5] = 2 ;

        for(int i = 0; i < 6; i++ ){
            this.btmem[i].nh = this.btmem[i].hp;

            if( ennum[i] != -1 ){
                this.enmem[i].nh = this.enmem[i].hp;
            }
        }

        for(int i = 0; i < 6; i++ ){
            if( btnum[i] != -1 ){
                mhp += this.btmem[i].hp;
            }

            if( ennum[i] != -1 ){
                emhp += this.enmem[i].hp;
            }
        }
        UnitCheck();
        QueInput();
    }

    public void Exit(){
        for(int i = 0; i < 6; i++ ){
            this.btmem[i].nh = this.btmem[i].hp;

            if( ennum[i] != -1 ){
                this.enmem[i].nh = this.enmem[i].hp;
            }
        }


    }


    public void SaveUnit(Unit u[], int[] n){
        for (int i = 0 ; i < 6 ; i++ ){
            if( n[i] != -1 ) this.btmem[i] = u[i];
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
