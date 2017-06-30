import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;


public class MainPanel extends JPanel{
    /*tyu kai nin class*/
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private MenuPanel menu;
    private War war;

    private CardLayout chmain;

    public MainPanel(){
        /* panelsize */
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        /*menu*/
        menu = new MenuPanel();

        /*war*/
        war  = new War();


        /*card create */
        setLayout(new CardLayout());
        add(menu,"menu");
        add(war,"war");

        chmain = (CardLayout)getLayout();

    }

    public void ChangeShow(String s){
        chmain.show( this , s );
    }

    /*flag*/
    public boolean GetMenuFlag(){
        return menu.GetFlag();
    }

    public void SetMenuFlag(boolean f){
        menu.SetFlag(f);
    }

    public boolean GetWarFlag(){
        return war.GetFlag();
    }

    public void SetWarFlag(boolean f){
        war.SetFlag(f);
    }


    /*loading*/
    public Unit LoadBtUnit(int i){
        return menu.LoadBtMember(i);
    }

    public int LoadBtUnitNum(){
        return menu.LoadBtNumber();
    }

    public void SaveBtUnitToWar(Unit u[], int n){
        war.SaveUnit(u,n);
    }

}
