import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class MENU {

    Image img;
    int x;
    int y;
    void MENU(){
        this.x = 0;
        this.y = 0;
    }

    void put(int x, int y){
        this.x = x;
        this.y = y;
    }

    void set(Image i){
        this.img = i;
    }

    void add (MediaTracker t){
        t.addImage(this.img,0);
    }
    public void draw(Graphics g){
        g.drawImage( this.img, this.x, this.y, null );
    }

}


public class MenuPanel extends JPanel implements MouseListener , MouseMotionListener{
    private static final int MENU_MAX = 4;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private String str;
    private int x = 100;
    private int y = 100;
    private int mx = 0;
    private int my = 0;

    public static boolean changeFlag = false;
	private MENU menu[] = new MENU[MENU_MAX];

    public MenuPanel(){
        /* panelsize */

        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";

        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setBounds(0,0,640,480);
        //add(menu);


        /*img*/
        for (int i = 0; i < MENU_MAX ; i++){
            menu[i] = new MENU();
            menu[i].set(
            Toolkit.getDefaultToolkit().getImage(
            getClass().getResource("IMG/ITEM/menu"+i+".png"))
            );
        }

		/*mediatracker input*/
		MediaTracker tracker = new MediaTracker(this);
		/*add(image,id)*/
        menu[0].add(tracker);
		menu[1].add(tracker);

        try{
            tracker.waitForID(0);
        }catch(InterruptedException e){
            e.printStackTrace();
            return;
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
    }
    public void mouseDragged(MouseEvent e){;}
    public void mouseMoved(MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        str = "("+ x + ","+ y + ")" + "m("+ mx + "," + my + ")";
        String m = "menuuuuuuu";
        menu[0].put(x,y);
        menu[1].draw(g);
        menu[0].draw(g);
        // g.drawImage(menu[0],x,y,this);

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
