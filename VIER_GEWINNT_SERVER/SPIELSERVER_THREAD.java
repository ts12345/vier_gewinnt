import java.net.*;
import java.io.*;

/**
 * Die Klasse SPIELSERVER_THREAD übernimmt für ein Netzwerkspiel nötige Funktionen
 * 
 * @author SFr682k
 * @version 2016-12-21
 */
public class SPIELSERVER_THREAD  implements Runnable {
    private Socket client1Socket = null;
    private Socket client2Socket = null;

    private PrintWriter zumClient1 = null;
    private PrintWriter zumClient2 = null;
    
    private BufferedReader vomClient1 = null;
    private BufferedReader vomClient2 = null;

    private String inbox  = null;
    private String outbox = null;
    
    private int     startsp;
    private boolean running = false;
    
    /**
     * Verbindet einen neuen Client
     * @param client1Socket ist der Socket von Client 1
     * @param client2Socket ist der Socket von Client 2
     */
    public SPIELSERVER_THREAD(Socket client1Socket, Socket client2Socket) throws IOException {
        running = false;
        
        this.client1Socket = client1Socket;
        this.client2Socket = client2Socket;
        
        vomClient1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
        zumClient1 = new PrintWriter(client1Socket.getOutputStream(), true);
        
        vomClient2 = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));
        zumClient2 = new PrintWriter(client2Socket.getOutputStream(), true);

        zumClient1.println("CONNECTED");
        zumClient2.println("CONNECTED");
        System.out.println("Clients verbunden");
    }

    /**
     * Legt den Startspieler fest
     * @param spielerNr ist 1, wenn mein Client beginnen darf, 2, wenn der gegnerische Client beginnen darf
     */
    public void spielStarten(int spielerNr) {
        startsp = spielerNr;
        
        if(spielerNr == 1) {
            zumClient1.println("NEWGME: PLY 1");
            zumClient2.println("NEWGME: PLY 2");
        } else {
            zumClient2.println("NEWGME: PLY 1");
            zumClient1.println("NEWGME: PLY 2");
        }
        
        running = true;
    }

    /**
     * Beendet die Verbindung zum Client
     */
    private void clientverbindungenBeenden() {
        try {
            zumClient1.close();
            vomClient1.close();
            
            zumClient2.close();
            vomClient2.close();
            
            client1Socket.close();
            client2Socket.close();
            System.out.println("Clientverbindungen beendet");
        } catch (Exception e) { }
    }

    /**
     * Hauptmethode: Empfängt Züge und leitet sie weiter
     */
    public void main() throws IOException {
        int spieleramzug = startsp;
        
        do {
            if(spieleramzug == 1) {
                System.out.println("Warte auf Zug von Client 1");
                inbox = vomClient1.readLine();
                System.out.println("Client 1: " + inbox);
                
                if(inbox != "GAME OVER") {
                    outbox = inbox;
                    zumClient2.println(outbox);
                    
                    spieleramzug = 2;
                } else { running = false; }
            } else if(spieleramzug == 2) {
                System.out.println("Warte auf Zug von Client 2");
                inbox = vomClient2.readLine();
                System.out.println("Client 2: " + inbox);
                
                if(inbox != "GAME OVER") {
                    outbox = inbox;
                    zumClient1.println(outbox);
                    
                    spieleramzug = 1;
                } else { running = false; }
            } else { System.out.println("HELP!!! I'M A BIG FAT BUG!!! FIX ME!!!"); }
        } while (running);

        clientverbindungenBeenden();
    }
    
    /**
     * run-Methode des Threads - führt die lediglich main-Methode aus
     */
    public void run() {
        try { main(); }
        catch (Exception e) { }
    }
}