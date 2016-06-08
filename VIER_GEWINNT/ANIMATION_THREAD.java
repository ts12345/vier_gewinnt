import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse ANIMATION_THREAD.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ANIMATION_THREAD implements Runnable
{
    private Graphics g;
    private int x;
    private int y;

    private TEST_SPIELSTEINTEXTURE p;
    private Graphics onscreenGraphics;

    private BufferedImage offscreen;
    private BufferedImage vorlage;

    //    private float acceleration = 5.81f / 2;
    private float acceleration = 5.81f / 4;
    private int timeToSleep = 10;

    public ANIMATION_THREAD(int x, int y, TEST_SPIELSTEINTEXTURE p)
    {
        this.x = x;
        this.y = y;
        this.p = p;

        onscreenGraphics = p.getGraphics();         

        int w = p.getWidth();
        int h = p.getHeight();
        offscreen = createImage(p);

        vorlage = createImage(p);
    }

    private int calculateYPosition(int time) {
        float pos = time * time * acceleration / 1000.0f;
        return (int) pos;
    }

    public BufferedImage createImage(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;
    }

    public BufferedImage createImage(JPanel panel, int x, int y) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;
    }

    public void run() {
        int curX = 0;
        int curY = 0;
        int i = 0;
        offscreen = createImage(p);     
        Graphics2D g = offscreen.createGraphics();            

        while(curY < y )  { // Execute one update step
            g.drawImage(vorlage.getSubimage(curX, curY, 100, 100), curX, curY, null);

            // System.out.println(timeToSleep * i + " " + curY );
            curX = curX + 5;
            curY = i * i;            
            curY = calculateYPosition(timeToSleep * i);
            i++;
            g.setColor(Color.BLACK);
            g.fillOval(curX, curY, 100, 100);

            onscreenGraphics.drawImage(offscreen, 0, 0, null);
            //             onscreenGraphics.drawImage(offscreen.getSubimage(
            //                     max(0, curX - 100) , 
            //                     max(0, curY - 100), 
            //                     200, 
            //                     200), 
            //                 max(0, curX - 100) , 
            //                 max(0, curY - 100),  null);
            try {
                Thread.sleep(timeToSleep);  // milliseconds
            } catch (InterruptedException ex) { } 

        }
    }

    public int max(int a, int b) {
        if(a > b)
            return a;
        else 
            return b;
    }

    public int min(int a, int b) {
        if(a < b)
            return a;
        else 
            return b;
    }
}
