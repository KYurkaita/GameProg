import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;

public class MENU{
    Image img;
    int x;
    int y;

    MENU(){
        this.x = 0;
        this.y = 0;
    }

    void put(int x, int y){
        this.x = x;
        this.y = y;
    }

    void set(String i){
        ImageIcon icon = new ImageIcon(getClass().getResource(i));
        this.img = icon.getImage();
    }

    public void draw(Graphics g){
        g.drawImage( this.img, this.x, this.y, null );
    }
}
