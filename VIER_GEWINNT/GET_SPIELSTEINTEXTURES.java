import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.WindowAdapter; import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame; import javax.swing.JPanel;
import java.io.File; import java.io.IOException; import javax.imageio.ImageIO;

public class GET_SPIELSTEINTEXTURES extends JPanel {
    private BufferedImage roterStein, gelberStein;

    public GET_SPIELSTEINTEXTURES() { loadImages(); }

    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));
        } catch (IOException ex) { }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        int width = roterStein.getWidth();
        int height = roterStein.getHeight();
        int posX = 0, posY = 0;

        graphics.drawImage(roterStein, posX, posY, width / 2, height / 2, null);
        graphics.drawImage(gelberStein, 100, 100, null);
    }

    public static void main(String s[]) {
        GET_SPIELSTEINTEXTURES pictures = new GET_SPIELSTEINTEXTURES();
        pictures.setBackground(Color.blue);
        
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add("Center", pictures);
        frame.pack();
        frame.setSize(new Dimension(800, 800));
        frame.setVisible(true);
        
        ANIMATION_THREAD animationThread = new ANIMATION_THREAD(100, 600, pictures);        
        animationThread.run();
    }
}