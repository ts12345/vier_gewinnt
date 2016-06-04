import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

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

    public VIEW(SPIELFELD spielfeld)
    {
        this.spielfeld = spielfeld;

        breite = spielfeld.getBreite();
        hoehe  = spielfeld.getHoehe();
        
        setPreferredSize(new Dimension(breite * size, hoehe * size));
        setBorder(BorderFactory.createLineBorder(Color.yellow));
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
                System.out.print(sp[i2][i]+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getsize()
    {
        return size;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Blaue Spielfläche ohne Löcher
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, breite * size, hoehe * size);

        int[][] s = spielfeld.getSpielfeld();

        // Jetzt werden rote oder blaue Spielsteine gezeichnet bzw. schwarze Loecher
        for(int i = 0;  i < spielfeld.getBreite(); i++) {
            for(int j = 0 ;  j < spielfeld.getHoehe(); j++) {

                if(s[i][j] == 1)
                {
                    g.setColor(Color.RED);
                }
                if(s[i][j] == 2)
                {
                    g.setColor(Color.YELLOW);
                }
                if(s[i][j] == 0)
                {
                    g.setColor(Color.BLACK);
                }
                g.fillOval(i * size,(-j + 5) * size, size, size);

            }
        }
    }
}