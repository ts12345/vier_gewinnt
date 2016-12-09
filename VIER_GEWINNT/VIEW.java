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
 */

public class VIEW extends JPanel {
    // Deklaration von Referenzattributen für Spielfeld, -brett und Controller
    SPIELFELD spielfeld;
    private CONTROLLER controller;
    private Area spielbrett;

    // Groesse eines Basisquadrats
    private int size = 100;
    
    // Deklaration der Variablen für Feldbreite und -höhe
    private int breite;
    private int hoehe;
    
    // Deklariert Zwischenspeicher für Spielstein-Bilder
    private BufferedImage roterStein;
    private BufferedImage gelberStein;
    private BufferedImage gelberGewinner;
    private BufferedImage roterGewinner;
    

    // Variable für die aktuell ausgewählte Spalte
    private int spalte;
    
    // Variable, die besagt, ob eine Stein-Vorschau gezeichnet werden muss
    static boolean drawNeeded;

    // deklariert die für eine Siegeranzeige nötigen Variablen
    public boolean showWinner;
    private int winner;

    // Konstruktor für Objekte der Klasse VIEW
    public VIEW(SPIELFELD spielfeld, CONTROLLER controller) {
        // Erzeugt Referenz auf Spielfeld und Controller
        this.spielfeld = spielfeld;
        this.controller = controller;
        
        // Ermittelt Feldbreite und -höhe
        breite = spielfeld.getBreite();
        hoehe  = spielfeld.getHoehe();

        // Setzt bevorzugte Fenstergröße und zeichnet die Außenlinie des Felds
        setPreferredSize(new Dimension(breite * size, (hoehe + 1) * size));
        setBorder(BorderFactory.createLineBorder(Color.yellow));

        // Lädt die Spielstein-Bilder
        loadImages();

        // Zeichnet ein neues Spielfeld
        spielbrettMitLoecherVorbereiten();
    }

    // Methode zum Laden der Spielstein-Bilder
    private void loadImages() {
        try {
            roterStein = ImageIO.read(new File("textures/roter_Stein.png"));
            gelberStein = ImageIO.read(new File("textures/gelber_Stein.png"));
            gelberGewinner =  ImageIO.read(new File("textures/gelber Gewinner.jpg"));
            roterGewinner =  ImageIO.read(new File("textures/roter Gewinner.jpg"));
        } catch (IOException ex) {
            // nichts zum Auffangen vorhanden
        }
    }

    // gibt den Wert des Attributs spielbrett zurück
    public Area getSpielbrett() {
        return spielbrett;
    }

    // bereitet ein Spielfeld vor
    public void spielbrettMitLoecherVorbereiten() {
        // deklariert und initialisiert benötigte Variablen
        Rectangle2D blauesFeld = new Rectangle2D.Float(0, 0 + size , breite * size, hoehe * size + size);
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

    // Druckmethode, die das Spielfeld auf der Konsole ausgibt
    public void printOutToConsole() {
        print(spielfeld.getSpielfeld());
    }

    // Hilfsmethode, die ein zweidimensionales int Array ausdrucken kann
    private void print(int[][] sp){
        for (int i=sp[0].length-1; i>=0; i--){
            for(int i2=0; i2<sp.length; i2++){
                //System.out.print(sp[i2][i]+"   ");
            }
            //System.out.println();
        }
        //System.out.println();
    }

    // gibt den Wert der Rastergröße zurück
    public int getsize() {
        return size;
    }

    // setzt den Wert der Rastergröße
    public void setsize(int size) {
        this.size = size;
        setPreferredSize(new Dimension(breite * size, (hoehe + 1) * size));
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        spielbrettMitLoecherVorbereiten();
    }

    // Methode zur Vorschau der Repaint-Methode
    public void repaintPreview() {
        // repaint();
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Blaue Spielfläche mit Löcher auf schwarzem Hintergrund        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 + size , breite * size, hoehe * size + size);

        // Vorschau zeichnen (falls noetig)
        int[][] s = spielfeld.getSpielfeld();
        
        // Zeichnet Stein-Vorschau Spieler 2, falls drawNeeded == true
        if(controller.spieleramzug == 2 && drawNeeded) {
            g.drawImage(roterStein, spalte * size,0 , size, size, null);
        }
        
        // Zeichnet Stein-Vorschau Spieler 1, falls drawNeeded == true
        if(controller.spieleramzug == 1 && drawNeeded) {
            g.drawImage(gelberStein, spalte * size,0 , size, size, null);
        }

        // Jetzt werden platzierte rote / gelbe Spielsteine auf dem Spielfeld gezeichnet
        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {

                // "Belegt" die Löcher des Spielfels
                if(s[i][j] == 1) {
                    g.drawImage(roterStein, i * size,(-j + 5) * size + size, size, size, null);
                } else if(s[i][j] == 2) {
                    g.drawImage(gelberStein, i * size,(-j + 5) * size + size, size, size, null);
                } else if(s[i][j] == 0) {
                    g.setColor(Color.BLACK);
                    // g.fillOval(i * size,(-j + 5) * size + size, size, size);
                }
            }
        }

        // Setzt die Farbe auf blau und füllt das Spielfeld aus
        g.setColor(Color.BLUE);
        g2d.fill(spielbrett);

        // Zeigt, wenn gewollt, den Sieger an
        if(showWinner) {
            if (winner==0) {
                // Gelb hat gewonnen
                g.drawImage(gelberGewinner,0,0,breite * size, (hoehe + 1) * size, null);
            } else {
                // Rot hat gewonnen
                g.drawImage(roterGewinner,0,0,breite * size, (hoehe + 1) * size, null);
            }
        }
    }

    // ändert die Attribute, damit der Gewinner in View ausgegeben wird
    public void showWinner (int winner) {
        this.winner = winner;
        showWinner = true;
    }

    // Methode, die eine Vorschau anzeigt
    public void showPreview(boolean showPreview) {
        this.drawNeeded = showPreview;
    }

    // Ermittelt die aktuelle Spalte
    public void setSpalte(int xWert) {
        spalte = (xWert / size);

        // x-Wert außerhalb: Rückgabe des letztmöglichen x-Werts
        if(spalte > (breite - 1)) {
            spalte = breite - 1;
        }
    }
}
