import java.net.*;
import java.io.*;

/**
 * Objekte der Klasse NETZWERKSPIELER ermöglichen Spiele über das Internet
 */
public class NETZWERKSPIELER extends SPIELER {
    private Socket server = null;
    private PrintWriter zumServer = null;
    private BufferedReader vomServer = null;

    public NETZWERKSPIELER(Socket srvsocket) {
        try {
            server = srvsocket;

            zumServer = new PrintWriter(server.getOutputStream(), true);
            vomServer = new BufferedReader(new InputStreamReader(server.getInputStream()) );
        } catch(Exception e) { System.out.println("Keine Verbindung zum Server!"); }
    }

    public boolean isHuman() { return false; }

    public int getNextMove() {
        int zug = -1;

        do {
            try { 
                String inbox = vomServer.readLine();
                
                System.out.println("Empfangen: " + inbox);

                char move = inbox.charAt(5);
                switch(move) {
                    case '0': zug =  0; break;
                    case '1': zug =  1; break;
                    case '2': zug =  2; break;
                    case '3': zug =  3; break;
                    case '4': zug =  4; break;
                    case '5': zug =  5; break;
                    case '6': zug =  6; break;
                    default:  zug = -1; break;
                }

                System.out.println("Konvertiert: " + zug);
            } catch(Exception e) { System.out.println("Kein Zug empfangen"); }
        } while(zug != -1);

        return zug;
    }

    public void VerarbeiteGegnerischenZug(int zugGegnerischerSpieler) {
        System.out.println("Sende: " + zugGegnerischerSpieler);
        zumServer.println("MVE: " + zugGegnerischerSpieler);
    }
}