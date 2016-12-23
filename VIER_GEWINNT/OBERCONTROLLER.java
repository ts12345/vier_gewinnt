import java.util.Random;
import java.net.*;

/**
 * Objekte der Klasse OBERCONTROLLER verwalten den Ablauf eines Netzwerkspiels
 */
public class OBERCONTROLLER {
    public OBERCONTROLLER(int size, int spieler, String serverIP, int hauptport) {
        SPIELER s1, s2;
        
        Random random = new Random();
        
        CONTROLLER commandante = new CONTROLLER(size);
       
        // erstellt Referenz auf die beiden Spieler
        switch(spieler) {
            case 2:
                // Startspieler wird zufällig ermittelt
                int startspieler = random.nextInt(2);
                
                if(startspieler == 0) {
                    s1 = new HUMANSPIELER(commandante);
                    s2 = new COMPUTERSPIELER(commandante.spielfeld);
                } else {
                    s1 = new COMPUTERSPIELER(commandante.spielfeld);
                    s2 = new HUMANSPIELER(commandante);
                }
                break;
                
            case 3:
                NETZWERKSTARTER netzwerkstarter = new NETZWERKSTARTER(serverIP, hauptport);
                int spielerNr = netzwerkstarter.getSpielerNr();
                Socket netzwerksocket = netzwerkstarter.getSocket();
                
                if(spielerNr == 1) {
                    s1 = new HUMANSPIELER(commandante);
                    s2 = new NETZWERKSPIELER(netzwerksocket);
                } else {
                    s1 = new NETZWERKSPIELER(netzwerksocket);
                    s2 = new HUMANSPIELER(commandante);
                }
                break;
                
            default:
                s1 = new HUMANSPIELER(commandante);
                s2 = new HUMANSPIELER(commandante);
                break;
        }

        // while(true) {
            commandante.spielStarten(s1, s2);
            commandante.reset();
        // }
    }
}