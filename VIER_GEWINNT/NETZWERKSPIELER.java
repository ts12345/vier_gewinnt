import java.net.*;
import java.io.*;

/**
 * Objekte der Klasse NETZWERKSPIELER ermöglichen Spiele über das Internet
 */
public class NETZWERKSPIELER extends SPIELER {
    private Socket server = null;
    private PrintWriter zumServer = null;
    private BufferedReader vomServer = null;

    public NETZWERKSPIELER(String ipadresse, int port) {
        try {
            server = new Socket(ipadresse,port);
            
            zumServer = new PrintWriter(server.getOutputStream(), true);
            vomServer = new BufferedReader(new InputStreamReader(server.getInputStream()) );
            
            String text = vomServer.readLine();
            System.out.println("Empfangen: " + text);
        } catch(Exception e) {
            System.out.println("Keine Verbindung am Server!");
            System.exit(1);
        }
    }

    public boolean isHuman() { return false; }

    public int getNextMove() {
        int Zug = 1;
        
        try { 
            String move = vomServer.readLine();
            System.out.println("Empfangen: " + move);
            Zug = Integer.parseInt(move);
        } catch(Exception e) { System.out.println("Kein Zug empfangen"); }
        
        return Zug;
    }

    public void VerarbeiteGegnerischenZug(int ZuggegnerischerSpieler) {
        System.out.println("Sende: " + ZuggegnerischerSpieler);
        zumServer.println(ZuggegnerischerSpieler);
    }
}