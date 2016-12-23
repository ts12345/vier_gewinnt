import java.net.*;
import java.io.*;

/**
 * NETZWERKSTARTER geben dem Obercontroller eine Information über das zu startende Netzwerkspiel
 * 
 * @author SFr682k
 * @version 2016-12-21
 */
public class NETZWERKSTARTER {
    private Socket server = null;
    private BufferedReader vomServer = null;
    
    int spielerNr = 0;

    /**
     * Konstruktormethode - erstellt einen neuen NETZWERKSTARTER
     * 
     * @param ipadresse ist die IP-Adresse des Spielservers
     * @param port ist der Port des Spielservers
     */
    public NETZWERKSTARTER(String ipadresse, int port) {
        try { server = new Socket(ipadresse, port); }
        catch(Exception e) { System.out.println("DAMN IT!"); }
            
        try { vomServer = new BufferedReader(new InputStreamReader(server.getInputStream()) ); }
        catch(Exception e) { System.out.println("DOOM!"); }
            
        boolean spielStarten = false;
        String inbox = null;
        char playerNr;
        do {
            try { inbox = vomServer.readLine(); }
            catch(Exception e) { System.out.println("When some trouble comes around, you must reget ..."); }
            
            if(inbox.startsWith("NEWGME")) {  spielStarten = true; }
        } while(!spielStarten);
            
        playerNr = inbox.charAt(12);
        System.out.println("SpielerNr: " + playerNr);
        
        switch(playerNr) {
            case '2': spielerNr = 2; break;
            default:  spielerNr = 1; break;
        }          
            //} catch(Exception e) { System.out.println("Ich bin ein Fehler - DEBUGGT MICH HIER RAUS!!!!"); }
    }

    /**
     * Gibt die Spielernummer des Humanspielers zurück
     * 
     * @return die Spielernummer des Netzwerkspielers
     */
    public int getSpielerNr() { return spielerNr; }
    
    /**
     * Gibt dem Obercontroller den Socket zurück
     * 
     * @return der Socket, an dem sich der Netzwerkspieler verbindet
     */
    public Socket getSocket() { return server; }
}