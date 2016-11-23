import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.concurrent.*;

/**
 * Beschreiben Sie hier die Klasse HUMANPLAYER_LISTENER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class HUMANPLAYER_LISTENER extends MouseAdapter {
    private int lastX;
    private int lastY;
    private boolean listenForClick;
    private CyclicBarrier doneSignal;

    public HUMANPLAYER_LISTENER(CyclicBarrier doneSignal) {
        listenForClick = false;
        this.doneSignal =  doneSignal;
    }

    public void mouseClicked(MouseEvent e) {
        // nur, wenn uns der HUMANSPIELER dazu aufgefordert hat, wird sich die Position gemerkt
        // und gleichzeitig das Signal gegeben, dass ein Klick erfolgt ist
        if(listenForClick) {
            lastX = e.getX();
            lastY = e.getY();
            
            // in der Folgezeile das 7*100 bedeutet: 7 Spalten zu je 100 Pixel
            if(lastX > 7*100) {
                return;
            }
            
            listenForClick = false;
            
            try {
                doneSignal.await();
            } 
            catch (InterruptedException ex) { } 
            catch (BrokenBarrierException ex) { }       
        }   
    }

    public void startListening() {
        listenForClick = true;
    }

    public int getLastX() {
        return lastX;
    }
}