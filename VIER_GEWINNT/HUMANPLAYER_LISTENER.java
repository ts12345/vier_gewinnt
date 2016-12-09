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
    private int size;
    
    private boolean listenForClick;
    private CyclicBarrier doneSignal;
    private CONTROLLER controller;

    public HUMANPLAYER_LISTENER(CyclicBarrier doneSignal, CONTROLLER controller) {
        listenForClick = false;
        this.doneSignal =  doneSignal;
        this.controller = controller;
        
        size = controller.getSize();
    }

    public void mouseClicked(MouseEvent e) {
        // nur, wenn uns der HUMANSPIELER dazu aufgefordert hat, wird sich die Position gemerkt
        // und gleichzeitig das Signal gegeben, dass ein Klick erfolgt ist
        if(listenForClick) {
            lastX = e.getX();
            lastY = e.getY();
            size = controller.getSize();
            
            // 7 ist hier die Anzahl der Spalten, size die Größe der Spalten
            if(lastX > 7*size) {
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
    
    public int getLastXCol() {
        int spalteX;
        size = controller.getSize();
        
        spalteX = lastX / size;
        
        return spalteX;
    }
}