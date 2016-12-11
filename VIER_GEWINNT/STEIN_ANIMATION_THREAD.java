import javax.swing.*;
import java.awt.*;  import java.awt.event.*; import java.awt.image.BufferedImage;
import java.io.*; import javax.imageio.ImageIO;

/**
 * STEIN_ANIMATION_THREAD: Thread für die Fallen-Animation der Spielsteine
 */
public class STEIN_ANIMATION_THREAD implements Runnable {
    private Graphics graphics;
    private int x, y, spieler, size;

    private VIEW view;
    private CONTROLLER controller;
    private Graphics onscreenGraphics;

    private BufferedImage offscreen, vorlage;
    private BufferedImage roterStein, gelberStein;

    private float acceleration = 5.81f * 2;
    private int timeToSleep = 5;

    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));
        } catch (IOException ex) { }
    }

    public STEIN_ANIMATION_THREAD(int x, int y, int spieler, VIEW view, CONTROLLER controller) {
        loadImages();

        this.x = x;
        this.y = y;
        this.view = view;
        this.spieler = spieler;
        this.controller = controller;
        this.size = controller.getSize();

        onscreenGraphics = view.getGraphics();         

        int width = view.getWidth();
        int height = view.getHeight();
        
        offscreen = createImage(view);
        vorlage = createImage(view);
    }

    private int calculateYPosition(int time) {
        float position = time * time * acceleration / 1000.0f;
        return (int) position;
    }

    public BufferedImage createImage(JPanel panel) {
        int width  = panel.getWidth();
        int height = panel.getHeight();
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        panel.paint(graphics2D);
        
        return bufferedImage;
    }

    public void run() {
        int currentX = x * size;
        int currentY = 0;
        int i = 0;

        BufferedImage stein;

        if(spieler == 1) { stein = roterStein; }
        else { stein  = gelberStein; }

        offscreen = createImage(view);     
        Graphics2D graphics2D = offscreen.createGraphics();            

        while(currentY < (6 - y)*size - size/3 )  { // Execute one update step
            graphics2D.drawImage(vorlage.getSubimage(currentX, currentY, size, size), currentX, currentY, null);

            currentY = i * i;            
            currentY = calculateYPosition(timeToSleep * i);
            i++;
            graphics2D.setColor(Color.BLACK);

            graphics2D.drawImage(stein, currentX, currentY, size, size, null);
            graphics2D.setColor(Color.BLUE);
            graphics2D.fill(view.getSpielbrett());

            onscreenGraphics.drawImage(offscreen, 0, 0, null);

            try { Thread.sleep(timeToSleep); } // timeToSleep in milliseconds
            catch (InterruptedException ex) { } 
        }
    }
}