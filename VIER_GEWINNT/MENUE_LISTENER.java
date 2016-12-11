import javax.swing.*;
import java.awt.*; import java.awt.event.*;
import java.util.concurrent.*;

/**
 * Die Klasse MENUE_LISTENER liest für die MENUE-Klasse die Position des Mauszeigers aus.
 * 
 * @author SFr682k
 * @version 2016-12-11
 */
public class MENUE_LISTENER extends MouseAdapter {
    private MENUE menue;
    static int lastX, lastY;
    private CyclicBarrier doneSignal;

    public MENUE_LISTENER(CyclicBarrier doneSignal, MENUE menue) {
        this.menue = menue;
        this.doneSignal = doneSignal;
    }

    public static void startListening() {
        lastX = 0;
        lastY = 0;
    }

    // bei Bewegung der Maus wird das Menü neu gezeichnet
    public void mouseMoved(MouseEvent e) {menue.repaint(); }
    
    // Speichert bei Mausklick die letzte Position des Cursors
    public void mouseClicked(MouseEvent e) {
        lastX = e.getX() - 10;     // Ungenauigkeit horizontal: ca. 10px
        lastY = e.getY() - 30;     // Ungenauigkeit vertikal:   ca. 30px

        try {  doneSignal.await(); } 
        catch (InterruptedException ex) { } 
        catch (BrokenBarrierException ex) { }
    }

    // Gibt die letzten Koordinate der Maus aus
    public static int getLastX() { return lastX; }

    public static int getLastY() { return lastY; }

    // derzeit nicht benötigte Methoden
    public void mouseEntered(MouseEvent e)  { }
    public void mouseExited(MouseEvent e)   { }
    public void mouseDragged(MouseEvent e)  { }
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e)  { }
}