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

    public VIEW(SPIELFELD spielfeld)
    {
        this.spielfeld = spielfeld;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = 100;
        g.setColor(Color.BLUE);
        g.fillRect(0,0,spielfeld.getBreite()*size-size,spielfeld.getHoehe()*size);
        for(int i=0;  i < spielfeld.getHoehe(); i++) {
            for(int j= 0 ;  j < spielfeld.getBreite(); j++) {
                int[][] s = spielfeld.getSpielfeld();
                
                if(s[j][i] == 1)
                {
                    g.setColor(Color.BLUE);
                }
                if(s[j][i] == 2)
                {
                    g.setColor(Color.RED);
                }
                
                g.setColor(Color.BLACK);
                g.fillOval(i * size,(-j+5) * size  ,size ,size);

            }

        }
    }
}