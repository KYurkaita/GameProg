import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.* ;


public class Title extends JPanel implements MouseListener {
	private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private String str;
    private Image image;
    private int x = 100;
    private int y = 100;

    public Title(){
        //panel size
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        str = "("+ x + ","+ y + ")";

        /*image initialize*/
        image = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("IMG/ch_rabbit_1.png"));

        /*mediatracker input*/
        MediaTracker tracker = new MediaTracker(this);
        /*add(image,id)*/
        tracker.addImage(image,0);

        addMouseListener(this);

        try{
            tracker.waitForID(0);
        }catch(InterruptedException e){
            e.printStackTrace();
            return;
        }

    }
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();
	}
    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mousePressed(MouseEvent e){ }

    public void mouseReleased(MouseEvent e){ }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int wh = image.getWidth(this)/4;
        int hg = image.getHeight(this)/4;
        str = "("+ x + ","+ y + ")";

        g.drawImage(image,x,y,-wh,hg,this);
        g.drawString(str, 0, 20);
    }

}
