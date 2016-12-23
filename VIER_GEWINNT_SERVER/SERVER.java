import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

/**
 * Server für VIER GEWINNT
 * @author S. Friedl (Basierend auf "Wie geht's?" - ISB-Arbeitskreis)
 * @version 1.0
 */
public class SERVER {
    SPIELSERVER_THREAD spielsrvthread;
    Random random;
    
    /** bidir Schnittstelle zur Netzwerkprotokoll-Implementierung des Servers*/
    private ServerSocket serverSocket  = null;
    
    /** bidir Schnittstelle zur Netzwerkprotokoll-Implementierung des Clients*/
    private Socket client1Socket = null;
    private Socket client2Socket = null;
    
    /** Zwischenspeicher für die aktuellen Einstellungen */
    private boolean chatSupport;
    private boolean zensieren;
    private int     zensAb;
    private String  zzeichen;
    private boolean zlueckenSchliessen;
    
    /**
     * Startet den "Vier Gewinnt"-Server
     */
    public SERVER(int hauptport, boolean chatSupport, boolean zensieren, int zensAb, String zzeichen, boolean zlueckenSchliessen) throws IOException {
        random = new Random();
        
        this.chatSupport = chatSupport;
        this.zensieren = zensieren;
        this.zensAb = zensAb;
        this.zzeichen = zzeichen;
        this.zlueckenSchliessen = zlueckenSchliessen;
        
        serverStarten(hauptport);        
        main();
    }

    /**
     * frägt den Port ab und erzeugt den Serversocket
     */
    private void serverStarten(int hauptport) throws IOException {
        serverSocket = new ServerSocket(hauptport);
        System.out.println("Server gestartet...");
    }

    /**
     * HAUPTMETHODE
     * ============
     * 1. Wartet, bis sich zwei Spieler mit dem Server verbunden haben
     * 2. Übergibt die beiden Spieler den entsprechenden Serverthreads
     * 3. "Telefonnummerntausch" beider Spieler
     * 4. das Ganze von vorne ...
     */
    private void main() throws IOException {
        do {
            client1Socket = serverSocket.accept();
            client2Socket = serverSocket.accept();
            
            spielsrvthread = new SPIELSERVER_THREAD(client1Socket, client2Socket);            
            new Thread(spielsrvthread).start();

            int startspieler = startspielerLosen();
            spielsrvthread.spielStarten(startspieler);
        } while (true);
    }
    
    /**
     * schließt den Serversocket
     */
    private void serverStoppen() throws IOException {
        serverSocket.close();
        System.out.println("Server gestoppt...");
    }
    
    /** Lost den Startspieler aus */
    private int startspielerLosen() {
        int startspieler = random.nextInt(2) + 1;        
        return startspieler;
    }
}