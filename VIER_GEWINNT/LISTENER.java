import javax.swing.*;
import java.awt.*; import java.awt.event.*;

/**
 *  Die Klasse LISTENER liest die Position des Mauszeigers aus
 */

public class LISTENER implements MouseMotionListener {
    private CONTROLLER controller;     // Referenz auf CONTROLLER
    private VIEW view;                 // Referenz auf VIEW-Objekt
    static int lastX, lastY;           // Var für Mauszeigerposition

    public LISTENER(CONTROLLER controller) {
        this.controller = controller;
        view = controller.getView();
    }
    
    public static void startListening() {
        lastX = 0;     lastY = 0;      // Leert lastX und lastY
    }
    
    // Maus innerhalb: Vorschau an / Maus außerhalb: Vorschau aus
    public void mouseEntered(MouseEvent e) { view.showPreview(true); }
    public void mouseExited(MouseEvent e)  { view.showPreview(false); }

    // Teilt dem VIEW-Objekt die aktuelle X-Koordinate der Maus mit
    public void mouseMoved(MouseEvent e) {
       view.setSpalte(e.getX());
       view.repaint();
    }

    // ermittelt und speichert bei Mausklick Position der Maus
    public void mouseClicked(MouseEvent e) {
        lastX = e.getX();     lastY = e.getY();
    }

    // Leere Methoden, die derzeit nicht gebraucht werden
    public void mouseDragged(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e)  {}
    
    public int getLastXCol() {
        int spalteX, size = controller.getSize();
        
        spalteX = lastX / size;
        return spalteX;
    }

    public static int getLastY() { return lastY; }
}