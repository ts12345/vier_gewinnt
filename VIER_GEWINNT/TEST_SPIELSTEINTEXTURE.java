import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TEST_SPIELSTEINTEXTURE extends JPanel {
    private BufferedImage roterStein;
    private BufferedImage gelberStein;

    public TEST_SPIELSTEINTEXTURE () {
        loadImages();  
    }

    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));

        } catch (IOException ex) {

        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        int width = roterStein.getWidth();
        int height = roterStein.getHeight();

        int posX = 0;
        int posY = 0;

        g.drawImage(roterStein, posX, posY, width / 2, height / 2, null);
        g.drawImage(gelberStein, 100, 100, null);

    }

    public static void main(String s[]) {
        JFrame f = new JFrame();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        TEST_SPIELSTEINTEXTURE p = new TEST_SPIELSTEINTEXTURE();
        p.setBackground(Color.blue);

        f.getContentPane().add("Center", p);
        f.pack();
        f.setSize(new Dimension(800, 800));
        f.setVisible(true);
        
        ANIMATION_THREAD t = new ANIMATION_THREAD(100, 600, p);
        
        t.run();
    }

}
