import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class STEIN_ANIMATION_THREAD implements Runnable
{
    private Graphics g;
    private int x;
    private int y;
    int spieler;

    private VIEW p;
    private Graphics onscreenGraphics;

    private BufferedImage offscreen;
    private BufferedImage vorlage;

    private BufferedImage roterStein;
    private BufferedImage gelberStein;

    //    private float acceleration = 5.81f / 2;
    private float acceleration = 5.81f * 2;
    private int timeToSleep = 5;

    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));
        } catch (IOException ex) {

        }
    }

    public STEIN_ANIMATION_THREAD(int x, int y, int spieler, VIEW p)
    {
        loadImages();

        this.x = x;
        this.y = y;
        this.p = p;
        this.spieler = spieler;

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
        int curX = x * 100;
        int curY = 0;
        int i = 0;
        int size = 100;

        BufferedImage stein;

        if(spieler == 1) {
            stein = roterStein;
        } else {
            stein  = gelberStein;
        }

        offscreen = createImage(p);     
        Graphics2D g = offscreen.createGraphics();            

        while(curY < (6 - y)*size - 30 )  { // Execute one update step
            g.drawImage(vorlage.getSubimage(curX, curY, 100, 100), curX, curY, null);

            curY = i * i;            
            curY = calculateYPosition(timeToSleep * i);
            i++;
            g.setColor(Color.BLACK);

            g.drawImage(stein, curX, curY, size, size, null);
            g.setColor(Color.BLUE);
            g.fill(p.getSpielbrett());


            onscreenGraphics.drawImage(offscreen, 0, 0, null);

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