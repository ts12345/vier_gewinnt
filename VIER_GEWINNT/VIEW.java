import javax.swing.*;
import java.awt.*; import java.awt.event.*; import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File; import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Die Klasse VIEW zeigt das Spielfeld an und interagiert mit dem Spieler.
 * 
 * @author (kinder) 
 */

public class VIEW extends JPanel {
    SPIELFELD spielfeld;
    private CONTROLLER controller;
    private Area spielbrett;

    private int breite, hoehe;       // Feldbreite und -höhe
    private int size = 100;          // Größe eines Basisquadrats

    // Deklariert Zwischenspeicher für (Spielstein-)Bilder
    private BufferedImage roterStein, gelberStein;
    private BufferedImage roterSteinReihe, gelberSteinReihe;
    private BufferedImage gelberGewinner, roterGewinner, unentschieden;
    private BufferedImage gelbGibtAuf, rotGibtAuf;

    private int spalte;              // aktuell ausgewählte Spalte
    static boolean drawNeeded;       // Stein-Vorschau zeichen - ja/nein

    // deklariert die für eine Siegeranzeige nötigen Variablen
    public boolean showWinner;
    private int winner;

    public VIEW(SPIELFELD spielfeld, CONTROLLER controller) {
        this.spielfeld  = spielfeld;         // Referenz auf Spielfeld
        this.controller = controller;        // Referenz auf Controller

        breite = spielfeld.getBreite();
        hoehe  = spielfeld.getHoehe();
        size   = controller.getSize();

        setPreferredSize(new Dimension(breite*size, (hoehe + 1)*size) );     // setzt bevorzugte Fenstergröße

        loadImages();

        spielbrettMitLoecherVorbereiten();   // zeichnet leeres Spielfeld
    }

    private void loadImages() {
        try {
            roterStein  = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));
            
            roterSteinReihe  = ImageIO.read(new File("textures/roter_Stein_Reihe.png"));
            gelberSteinReihe = ImageIO.read(new File("textures/gelber_Stein_Reihe.png"));
            
            roterGewinner  = ImageIO.read(new File("textures/roter Gewinner.jpg"));
            gelberGewinner = ImageIO.read(new File("textures/gelber Gewinner.jpg"));
            unentschieden  = ImageIO.read(new File("textures/unentschieden.jpg"));
            
            gelbGibtAuf = ImageIO.read(new File("textures/gelbGibtAuf.jpg"));
            rotGibtAuf  = ImageIO.read(new File("textures/rotGibtAuf.jpg"));
        } catch (IOException ex) { }
    }

    public void spielbrettMitLoecherVorbereiten() {
        size = controller.getSize();
        
        // deklariert und initialisiert benötigte Variablen
        Rectangle2D blauesFeld = new Rectangle2D.Float(0, 0 + size , breite * size, hoehe * size);
        Ellipse2D loch = new Ellipse2D.Double(0, 0, 0, 0);

        // zeichnet das leere Spielfeld
        spielbrett = new Area(blauesFeld);

        // spart die "Fenster" für die Spielsteine aus
        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {
                loch = new Ellipse2D.Double(i * size,(-j + 5) * size + size, (size*96)/100, (size*96)/100);
                spielbrett.subtract(new Area(loch));
            }
        }
    }

    // Legt fest, ob Vorschau gezeigt werden soll
    public void showPreview(boolean showPreview) { this.drawNeeded = showPreview; }
    
    public void paintComponent(Graphics graphics) {
        size = controller.getSize();
        
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Blaue Spielfläche mit Löchern auf schwarzem Hintergrund        
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0 + size , breite * size, hoehe * size);

        int[][] s = spielfeld.getSpielfeld();     // lädt aktuelle Belegung des Spielfelds

        // Zeichnet Stein-Vorschau, falls drawNeeded == true
        if(controller.spieleramzug == 2 && drawNeeded) { graphics.drawImage(roterStein, spalte * size, 0, size, size, null); }
        if(controller.spieleramzug == 1 && drawNeeded) { graphics.drawImage(gelberStein, spalte * size, 0, size, size, null); }

        // Jetzt werden platzierte rote / gelbe Spielsteine auf dem Spielfeld gezeichnet
        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {
                if      (s[i][j] == 1) { graphics.drawImage(roterStein, i * size,(-j + 5) * size + size, size, size, null); }
                else if (s[i][j] == 2) { graphics.drawImage(gelberStein, i * size,(-j + 5) * size + size, size, size, null); }
                else if (s[i][j] == 3) { graphics.drawImage(roterSteinReihe, i * size, (-j + 5) * size + size, size, size, null); }
                else if (s[i][j] == 4) { graphics.drawImage(gelberSteinReihe, i * size, (-j + 5) * size + size, size, size, null); }
                else                   { graphics.setColor(Color.BLACK); }
            }
        }

        // Setzt die Farbe auf blau und füllt das Spielfeld aus
        graphics.setColor(Color.BLUE);
        graphics2D.fill(spielbrett);

        // Zeigt, wenn gewollt, den Sieger an
        if(showWinner) {
            if      (winner == 1) { graphics.drawImage(roterGewinner, 0, 0, breite * size, (hoehe + 1) * size, null); }  // Rot hat gewonnen
            else if (winner == 2) { graphics.drawImage(gelberGewinner, 0, 0, breite * size, (hoehe + 1) * size, null); } // Gelb hat gewonnen
            else if (winner == 3) { graphics.drawImage(gelbGibtAuf, 0, 0, breite * size, (hoehe + 1) * size, null); }    // Gelb gibt auf, Rot gewinnt
            else if (winner == 4) { graphics.drawImage(rotGibtAuf, 0, 0, breite * size, (hoehe + 1) * size, null); }     // Rot gibt auf, Gelb gewinnt
            else if (winner == 5) { graphics.drawImage(unentschieden, 0, 0, breite * size, (hoehe + 1) * size, null); }  // Unentschieden
        }
    }

    // ändert die Attribute, damit der Gewinner in View ausgegeben wird
    public void showWinner(int winner) {
        this.winner = winner;
        showWinner = true;
    }
    
    // Eingabemethode für aktuell ausgewählte Spalte
    public void setSpalte(int xWert) {
        size = controller.getSize();
        
        spalte = (xWert / size);

        // x-Wert außerhalb: Rückgabe des letztmöglichen x-Werts
        if(spalte > (breite - 1)) { spalte = breite - 1; }
    }
    
    public Area getSpielbrett() { return spielbrett; }
}