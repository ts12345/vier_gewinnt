import java.awt.*; import java.awt.event.*; import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File; import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.concurrent.*;

public class MENUE extends JPanel {
    private static CyclicBarrier doneSignal = new CyclicBarrier(2);
    private static MENUE_LISTENER mouseListener;

    private BufferedImage startbildschirm;
    private Runnable spielRunnable;
    private Thread spielThread;

    /** Hier gesetzte Werte werden an den Obercontroller weitergegeben */
    private int     einzelspieler  =            2;  // Mensch gegen Computerspieler
    private int     mehrspieler    =            1;  // Mensch gegen Mensch
    private int     netzwerkspiel  =            3;  // Netzwerkspiel, Login an Hauptport
    private String  serverIP       =  "localhost";  // IP-Adresse des Vier-Gewinnt-Servers
    private int     hauptport      =         2000;  // Verbindungsport des Vier-Gewinnt-Servers
    private int     size           =           75;  // Größe eines Basisquadrats

    /** Variablen für Schaltflächen */
    private int     esOLx, esOLy, esURx, esURy;     // Namen der Variablen setzen sich folgendermaßen zusammen:
    private int     msOLx, msOLy, msURx, msURy;     // es: Einzelspieler, ms: Mehrspieler, ns: Netzwerkspiel,
    private int     nsOLx, nsOLy, nsURx, nsURy;     // ip: IP-Adresse, hp: Hauptport
    private int     ipOLx, ipOLy, ipURx, ipURy;     // OL: Oben links, UR: Unten rechts, ENA: aktiv (enabled)
    private int     hpOLx, hpOLy, hpURx, hpURy;     // x: x-Koordinate (in px), y: y-Koordinate (in px)
    private boolean esENA, msENA, nsENA, ipENA, hpENA;

    public MENUE() {
        mouseListener = new MENUE_LISTENER(doneSignal, this);
        Graphics graphics;

        setPreferredSize( new Dimension(525, 525) );

        try { startbildschirm = ImageIO.read(new File("textures/startbildschirm.png")); }
        catch (IOException ex) {}

        setSizes();
        repaint();

        JFrame frameView = new JFrame("Vier gewinnt!");
        frameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        frameView.add(this);
        frameView.pack();
        frameView.setVisible(true);

        frameView.addMouseListener(mouseListener);
        listen();
    }

    /**
      * Einstellungen für Anzahl, Position, Größe und Aktivität der Schaltflächen
      */
    private void setSizes() {
        esOLx =  51;     esOLy = 248;
        esURx = 214;     esURy = 275;
        esENA = true;

        msOLx = 317;     msOLy = 248;
        msURx = 472;     msURy = 275;
        msENA = true;

        nsOLx = 170;     nsOLy = 322;
        nsURx = 355;     nsURy = 349;
        nsENA = true;

        ipOLx = 263;     ipOLy = 386;
        ipURx = 525;     ipURy = 401;
        ipENA = false;

        hpOLx = 263;     hpOLy = 418;
        hpURx = 525;     hpURy = 433;
        hpENA = false;
    }

    public void paintComponent(Graphics graphics) {        
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics.drawImage(startbildschirm, 0, 0, 525, 525, null);
    }

    /**
     * Überprüft, ob sich der Mauszeiger beim letzten Klick auf einer Schaltfläche befand.
     * Wenn ja, wird das Kürzel der Schaltfläche (s. Variablendeklaration) an die "run"-Methode weitergegeben
     */
    private void execute(int x, int y) {
        if     ( inBereich(x, y, esOLx, esURx, esOLy, esURy) && esENA) { run("es"); }
        else if( inBereich(x, y, msOLx, msURx, msOLy, msURy) && msENA) { run("ms"); }
        else if( inBereich(x, y, nsOLx, nsURx, nsOLy, nsURy) && nsENA) { run("ns"); }
        else if( inBereich(x, y, ipOLx, ipURx, ipOLy, ipURy) && ipENA) { run("ip"); }
        else if( inBereich(x, y, hpOLx, hpURx, hpOLy, hpURy) && hpENA) { run("hp"); }
        else { listen(); }
    }

    /** 
     * Hilfsmethode für execute
     * Kontrolliert, ob das Koordinatenpaar (x|y) im angegebenen Bereich liegt
     */
    private boolean inBereich(int x, int y, int xUGrenze, int xOGrenze, int yUGrenze, int yOGrenze) {
        if( (x <= xOGrenze) && (x >= xUGrenze) && (y <= yOGrenze) && (y >= yUGrenze) ) {return true;}
        else { return false; }
    }

    /**
     * Startet einen neuen Spielthread
     */
    private void run(String schaltflaeche) {
        switch(schaltflaeche) {
            case "es":
                spielRunnable = new SPIELTHREAD(size, einzelspieler, serverIP, hauptport);
                spielThread   = new Thread(spielRunnable);
                spielThread.start();
                break;
                
            case "ms":
                spielRunnable = new SPIELTHREAD(size, mehrspieler, serverIP, hauptport);
                spielThread   = new Thread(spielRunnable);
                spielThread.start();
                break;
                
            case "ns":
                spielRunnable = new SPIELTHREAD(size, netzwerkspiel, serverIP, hauptport);
                spielThread   = new Thread(spielRunnable);
                spielThread.start();
                break;
                
            case "ip":  break;
            case "hp":  break;
            default:    break;
        }

        listen();
    }

    /**
     * Wartet auf einen Mausklick und gibt die Zeigerposition an die Methode "execute" weiter
     */
    private void listen() {
        doneSignal.reset();
        mouseListener.startListening();

        try { doneSignal.await(); }
        catch (InterruptedException ex) { }
        catch (BrokenBarrierException ex) { }

        execute(mouseListener.getLastX(), mouseListener.getLastY());
    }

    public static void main(String[] args) { MENUE menue = new MENUE(); }
}