import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.awt.geom.*;

/**
 * Die Klasse VIEW zeigt das Spielfeld an und interagiert mit dem Spieler.
 * 
 * @author (kinder) 
 *
 */
public class VIEW extends JPanel
{
    SPIELFELD spielfeld;

    // Groesse eines "Basisquadrats
    private final static int size = 100;
    private int breite;
    private int hoehe;
    private BufferedImage roterStein;
    private BufferedImage gelberStein;
    private CONTROLLER controller;

    private int spalte;
    static boolean drawNeeded;

    public boolean showWinner;
    private int winner;

    private Area spielbrett;

    public VIEW(SPIELFELD spielfeld, CONTROLLER controller)
    {
        this.spielfeld = spielfeld;
        this.controller = controller;
        breite = spielfeld.getBreite();
        hoehe  = spielfeld.getHoehe();

        setPreferredSize(new Dimension(breite * size, (hoehe + 1) * size));
        setBorder(BorderFactory.createLineBorder(Color.yellow));

        loadImages();

        spielbrettMitLoecherVorbereiten();
    }
    
    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));

        } catch (IOException ex) {

        }
    }

    public Area getSpielbrett() {
        return spielbrett;
    }

    public void spielbrettMitLoecherVorbereiten() {
        Rectangle2D blauesFeld = new Rectangle2D.Float(0, 0 + size , breite * size, hoehe * size + size);
        Ellipse2D loch = new Ellipse2D.Double(0, 0, 0, 0);

        spielbrett = new Area(blauesFeld);

        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {

                {
                    loch = new Ellipse2D.Double(i * size,(-j + 5) * size + size, (size*96)/100, (size*96)/100);
                    spielbrett.subtract(new Area(loch));
                }

            }
        }
    }

    /**
     * Druckmethode, die das Spielfeld auf der Konsole ausgibt
     */
    public void printOutToConsole() {
        print(spielfeld.getSpielfeld());
    }

    /**
     * Hilfsmethode, die ein zweidimensionales int Array ausdrucken kann
     */
    private void print(int[][] sp){
        for (int i=sp[0].length-1; i>=0; i--){
            for(int i2=0; i2<sp.length; i2++){
                //System.out.print(sp[i2][i]+"   ");
            }
            //System.out.println();
        }
        //System.out.println();
    }

    public int getsize()
    {
        return size;
    }

    public void repaintPreview() {
        //        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Blaue Spielfläche mit Löcher auf schwarzem Hintergrund        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 + size , breite * size, hoehe * size + size);

        // Vorschau zeichnen (falls noetig)
        int[][] s = spielfeld.getSpielfeld();
        if(controller.spieleramzug == 2 && drawNeeded)
        {
            g.drawImage(roterStein, spalte * size,0 , size, size, null);
        }
        if(controller.spieleramzug == 1 && drawNeeded)
        {
            g.drawImage(gelberStein, spalte * size,0 , size, size, null);
        }

        // Jetzt werden rote oder blaue Spielsteine gezeichnet, falls noetig
        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {

                if(s[i][j] == 1)
                {
                    g.drawImage(roterStein, i * size,(-j + 5) * size + size, size, size, null);
                }
                if(s[i][j] == 2)
                {
                    g.drawImage(gelberStein, i * size,(-j + 5) * size + size, size, size, null);
                }
                if(s[i][j] == 0)
                {
                    g.setColor(Color.BLACK);
                    //                    g.fillOval(i * size,(-j + 5) * size + size, size, size);
                }

            }
        }

        g.setColor(Color.BLUE);
        g2d.fill(spielbrett);

        if(showWinner)
        {
            String str;
            if (winner==0)
                str = "Spieler 1 hat gewonnen!";
            else
                str = "Spieler 2 hat gewonnen!";
            g.setColor(Color.GREEN);
            g.drawString(str, 300, 500);
        }

    }

    /**
     * ändert interne Werte, damit der Gewinner in View ausgegeben wird
     */
    public void showWinner (int winner)
    {
        this.winner = winner;
        showWinner = true;
    }

    public void showPreview(boolean showPreview)
    {
        this.drawNeeded = showPreview;
    }

    public void setSpalte(int xWert)
    {
        spalte = (xWert / size);
        
        if(spalte > (breite - 1)) {
            spalte = breite - 1;
        }
    }
}