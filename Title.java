import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class Title extends PANEL {
	private MENU rab[] = new MENU[2];

    public Title(){
		super();

		setBounds(0,0,640,480);
        str = "("+ x + ","+ y + ")";

		for(int i = 0; i < 2 ; i++){
			rab[i] = new MENU();
			rab[i].set("IMG/EFF/ch_rabbit_1.png");
			rab[i].put( 300 + 200 * i , 300 + 200 * i );
		}

    }

	@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")";

		rab[0].draw(g);
		rab[1].draw(g);

        g.drawString(str, 0, 20);
    }


}
