import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

/**
 *  Die Klasse LISTENER liest die Position des Mauszeigers aus
 */

public class LISTENER implements MouseMotionListener {
    // bereitet Referenzen auf Controller und grafische Oberfläche (VIEW) vor
    private CONTROLLER controller;
    private VIEW view;
    
    // deklariert Variablen für die Postition des Mauszeigers
    static int lastX;
    static int lastY;

    // Konstruktor der Klasse LISTENER
    public LISTENER(CONTROLLER controller) {
        this.controller = controller;
        view = controller.getView();
    }
    
    // Initialisiert die Variablen lastX und lastY
    public static void startListening() {
        lastX = 0;
        lastY = 0;
    }
    
    // Teilt dem VIEW-Objekt mit, dass die Maus innerhalb ist
    public void mouseEntered(MouseEvent e){
        view.showPreview(true);
    }
    
    // Teilt dem VIEW-Objekt mit, dass die Maus außerhalb ist 
    public void mouseExited(MouseEvent e) {
        view.showPreview(false);
    }

    // Teilt dem VIEW-Objekt die aktuelle X-Koordinate der Maus mit
    public void mouseMoved(MouseEvent e) {
       //System.out.println(e.getX());
       view.setSpalte(e.getX());
       view.repaint();
    }

    // ermittelt bei Mausklick Position der Maus und speichert sie
    public void mouseClicked(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        //System.out.println("Click " + lastX + " " + lastY);
    }

    // Leere Methoden, die derzeit nicht gebraucht werden
    public void mouseDragged(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    
    // Rückgabemethode für die letzte x-Koordinate
    public int getLastXCol() {
        int spalteX;
        int size = controller.getSize();
        
        spalteX = lastX / size;
        
        return spalteX;
    }

    // Rückgabemethode für die letzte y-Koordinate
    public static int getLastY() {
        return lastY;
    }
}
