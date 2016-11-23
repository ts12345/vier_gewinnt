import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * modifizierter Server für JAVACHAT
 * @author S. Friedl (Basierend auf "Wie geht's?" - ISB-Arbeitskreis)
 * @version 1.0
 */


public class SERVER {
    /** bidir Schnittstelle zur Netzwerkprotokoll-Implementierung des Servers*/
    private ServerSocket serverSocket1 = null;
    private ServerSocket serverSocket2 = null;
    /** bidir Schnittstelle zur Netzwerkprotokoll-Implementierung des Clients*/
    private Socket client1Socket = null;
    private Socket client2Socket = null;

    /** Schreibkanal zum Client*/
    private PrintWriter zumClient1 = null;
    private PrintWriter zumClient2 = null;
    /** Lesekanal vom Client*/
    private BufferedReader vomClient1 = null;
    private BufferedReader vomClient2 = null;

    /** Botschaft von Client zum Server*/
    private String client1Nachricht = null;
    private String client2Nachricht = null;

    
    /**
     * IOException tritt auf, falls es Probleme mit dem Socket gibt
     * �BERARBEITEN!!!!!!!!
     */
    public SERVER() throws IOException {
        ServerStarten();
        boolean EXIT = false;

        Client1VerbindungStarten(); //auf Client 1 warten und verbinden
        Client2VerbindungStarten(); //auf Client 2 warten und verbinden

        do {//lesen und antworten
            System.out.println("warte auf Botschaft von Client 1...");
            client1Nachricht = vomClient1.readLine();
            zumClient2.println(client1Nachricht);
            
            System.out.println("warte auf Botschaft von Client 2...");
            client2Nachricht = vomClient2.readLine();
            zumClient1.println(client2Nachricht);            
        } while (EXIT == false);

        Client1VerbindungBeenden();
        Client2VerbindungBeenden();
        ServerStoppen();
    }

    /**
     * fragt den Port ab und erzeugt den Serversocket
     */
    private void ServerStarten() throws IOException {
        Scanner s = new Scanner(System.in);

        int port1;
        int port2;
        
        // System.out.println("Port fuer Client 1 eingeben: ");
        // port1 = s.nextInt();

        // Provisorische Zuweisung der Netzwerkports:
        port1 = 2001;
        port2 = 2002;
        
        serverSocket1 = new ServerSocket(port1);
        
        // System.out.println("Port fuer Client 2 eingeben:" );
        // port2 = s.nextInt();
        
        serverSocket2 = new ServerSocket(port2);

        System.out.println("Server gestartet...");
    }

    /**
     * schliesst den Serversocket
     */
    private void ServerStoppen() throws IOException {
        serverSocket1.close();
        serverSocket2.close();
        System.out.println("Server gestoppt...");
    }

    /**
     * wartet auf Verbindung zu Client 1 und erzeugt noetige Lese- und Schreibobjekte
     * nachdem eine Verbindung hergestellt wurde
     */
    private void Client1VerbindungStarten() throws IOException {
        //warten auf die Verbindung
        client1Socket = serverSocket1.accept();  

        // Der clientSocket wird zum Lesen und Schreiben geoeffnet 
        zumClient1 = new PrintWriter(client1Socket.getOutputStream(), true);
        vomClient1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));

        //Begruessung des Clients senden
        zumClient1.println("Verbunden - Sie sind Client 1");
        System.out.println("Client 1 verbunden");
    }

    /**
     * wartet auf Verbindung zu Client 2 und erzeugt noetige Lese- und Schreibobjekte
     * nachdem eine Verbindung hergestellt wurde
     */
    private void Client2VerbindungStarten() throws IOException {
        //warten auf die Verbindung
        client2Socket = serverSocket2.accept();  

        // Der clientSocket wird zum Lesen und Schreiben geoeffnet 
        zumClient2 = new PrintWriter(client2Socket.getOutputStream(), true);
        vomClient2 = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));

        //Begruessung des Clients senden
        zumClient2.println("Verbunden - Sie sind Client 2");
        System.out.println("Client 2 verbunden");
    }
    
    /**
     * beendet die Verbindung zu Client 1
     */
    private void Client1VerbindungBeenden() throws IOException {
        zumClient1.close();
        vomClient1.close();
        client1Socket.close();
        System.out.println("Verbindung zu Client 1 beendet");
    }

    /**
     * beendet die Verbindung zu Client 2
     */
    private void Client2VerbindungBeenden() throws IOException {
        zumClient2.close();
        vomClient2.close();
        client2Socket.close();
        System.out.println("Verbindung zu Client 2 beendet");
    }
    
    /**
     * Hauptprogramm zum Erzeugen des Serverobjekts
     * @param args keine Parameter beim Programmaufruf erforderlich
     */
    public static void main(String[] args) {
        try {
            new SERVER();
        } catch (Exception e) {
            System.err.println("Fehler in der Serververabeitung.");
            System.exit(1);
        }
    }
}