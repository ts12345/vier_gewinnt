import javax.swing.*;
import java.awt.*; import java.awt.event.*; import java.awt.image.BufferedImage;

/**
 * ANIMATION_THREAD: Thread für die Fallen-Animation der Spielsteine
 */
public class ANIMATION_THREAD implements Runnable {
    private Graphics graphics;
    private int x, y;

    private GET_SPIELSTEINTEXTURES pictures;
    private Graphics onscreenGraphics;

    private BufferedImage offscreen, vorlage;

    private float acceleration = 5.81f / 4;
    private int timeToSleep = 10;

    public ANIMATION_THREAD(int x, int y, GET_SPIELSTEINTEXTURES pictures) {
        this.x = x;
        this.y = y;
        this.pictures = pictures;

        onscreenGraphics = pictures.getGraphics();         

        int width = pictures.getWidth(), height = pictures.getHeight();
        
        offscreen = createImage(pictures);
        vorlage = createImage(pictures);
    }

    private int calculateYPosition(int time) {
        float position = time * time * acceleration / 1000.0f;
        return (int) position;
    }

    public BufferedImage createImage(JPanel panel) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        panel.paint(graphics2D);
        
        return bufferedImage;
    }

    public BufferedImage createImage(JPanel panel, int x, int y) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        
        panel.paint(graphics2D);
        
        return bufferedImage;
    }

    public void run() {
        int currentX = 0, currentY = 0;
        int i = 0;
        
        offscreen = createImage(pictures);
        Graphics2D graphics2D = offscreen.createGraphics();            

        while(currentY < y )  { // Execute one update step
            graphics2D.drawImage(vorlage.getSubimage(currentX, currentY, 100, 100), currentX, currentY, null);

            currentX = currentX + 5;
            currentY = i * i;            
            currentY = calculateYPosition(timeToSleep * i);
            
            i++;
            
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillOval(currentX, currentY, 100, 100);

            onscreenGraphics.drawImage(offscreen, 0, 0, null);

            try { Thread.sleep(timeToSleep); } // timeToSleep in milliseconds
            catch (InterruptedException ex) { } 
        }
    }
}