import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.concurrent.*;

/**
 * Beschreiben Sie hier die Klasse HUMANSPIELER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class HUMANSPIELER extends SPIELER {
    // Signal, das wir benutzen um vom Listener zu erfahren, wann geklickt wurde
    private CyclicBarrier doneSignal;
    private HUMANPLAYER_LISTENER listener;
    private CONTROLLER controller;
    /**
     * Konstruktor für Objekte der Klasse HUMANSPIELER
     */
    public HUMANSPIELER(CONTROLLER controller) {
        // CylclicBarrier(2)
        // Die 2 bedeutet, dass zweimal await() aufgerufen werden muss, bis alle Threads weitermachen
        doneSignal = new CyclicBarrier(2);

        this.controller = controller;
        
        // Der Listener braucht eine Referenz auf das Signal
        listener = new HUMANPLAYER_LISTENER(doneSignal, controller);
    }

    public int getNextMove() {
        // Signal zurücksetzen
        doneSignal.reset();
        listener.startListening();

        try {
            doneSignal.await();
            // System.out.println("wait for click");
        } 
        catch (InterruptedException ex) { } 
        catch (BrokenBarrierException ex) { }

        // System.out.println("got click");
        
        int xSpalte = listener.getLastXCol();
        
        if(xSpalte > 7) {
            xSpalte = 7;
        }
        
        return xSpalte;
    }

    public boolean isHuman() {
        return true;
    }

    public void activateListener(JFrame f) {
        f.addMouseListener(listener);
    }
}