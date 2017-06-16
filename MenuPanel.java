import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class MenuPanel extends JPanel implements MouseListener , MouseMotionListener{
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private String str;
    private Image image;
    private int x = 100;
    private int y = 100;
    private int mx = 0;
    private int my = 0;

    public static boolean changeFlag = false;

    JPanel menu;

    public MenuPanel(){
        /* panelsize */
        menu = new JPanel();

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds(0,0,640,480);
        //add(menu);



    }
    /*MouseEvent*/
    public void mouseClicked (MouseEvent e){;}
    public void mouseEntered (MouseEvent e){;}
    public void mouseExited  (MouseEvent e){;}
    public void mouseReleased(MouseEvent e){;}
    public void mousePressed (MouseEvent e){
        x = e.getX();
        y = e.getY();
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";
        String m = "menu";

        g.drawString(str, 0, 20);
        g.drawString(m,100,100);
    }

    // public int


    public boolean getFlag(){
        return this.changeFlag;
    }

    public void setFlag(boolean f){
        this.changeFlag = f;
    }

}
