import java.util.Scanner;
import java.util.Random;

/**
 * Objekte der Klasse OBERCONTROLLER verwalten den Ablauf eines Netzwerkspiels
 */
public class OBERCONTROLLER {
    public OBERCONTROLLER(int size, int spieler, String serverIP, int hauptport) {
        Scanner scan = new Scanner(System.in);
        
        // deklariert Referenzattribute für beide Spieler
        SPIELER s1, s2;
        
        // erstellt auf ein Objekt, das Zufallszahlen erzeugt, Referenz
        Random random = new Random();
        
        CONTROLLER commandante = new CONTROLLER(size);
       
        // erstellt Referenz auf die beiden Spieler
        switch(spieler) {
            case 2:
                // Startspieler wird zufällig ermittelt
                int startspieler = random.nextInt(2);
                
                if(startspieler == 0) {
                    s1 = new HUMANSPIELER(commandante);
                    s2 = new COMPUTERSPIELER_TS(commandante.spielfeld);
                } else {
                    s1 = new COMPUTERSPIELER_TS(commandante.spielfeld);
                    s2 = new HUMANSPIELER(commandante);
                }
                break;
                
            case 3:
                s1 = new NETZWERKSPIELER(serverIP, 2002);
                s2 = new HUMANSPIELER(commandante);
                break;

            case 4:
                s2 = new NETZWERKSPIELER(serverIP, 2001);
                s1 = new HUMANSPIELER(commandante);
                break;
                
            default:
                s1 = new HUMANSPIELER(commandante);
                s2 = new HUMANSPIELER(commandante);
                break;
        }

        while(true) {
            commandante.spielStarten(s1, s2);
            commandante.reset();
        }
    }
}