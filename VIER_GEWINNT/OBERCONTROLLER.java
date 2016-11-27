import java.util.Scanner;

/**
 * Objekte der Klasse OBERCONTROLLER verwalten den Ablauf eines Netzwerkspiels
 */
public class OBERCONTROLLER {
    // Konstruktor für Objekte der Klasse OBERCONTROLLER
    // Übergabewerte für Feldgröße und Typ der Spieler
    public OBERCONTROLLER(int size, int sp1, int sp2, String serverIP, int hauptport) {
        // erstellt den Scanner
        Scanner scan = new Scanner(System.in);
        
        // deklariert Referenzattribute für beide Spieler
        SPIELER s1;
        SPIELER s2;
        
        // deklariert und erstellt Referenz auf einen Controller
        CONTROLLER commandante = new CONTROLLER(size);
       
        // erstellt Referenz auf die beiden Spieler
        switch(sp1){
            case 2:
            s1 = new COMPUTERSPIELER_TS(commandante.spielfeld);
            s2 = new HUMANSPIELER(size);
            break;
                
            case 3:
            s1 = new NETZWERKSPIELER(serverIP, 2001);
            s2 = new HUMANSPIELER(size);
            break;

            case 4:
            s2 = new NETZWERKSPIELER(serverIP, 2002);
            s1 = new HUMANSPIELER(size);
            break;
                
            default:
            s1 = new HUMANSPIELER(size);
            switch(sp2){
                case 2:
                s2 = new COMPUTERSPIELER_TS(commandante.spielfeld);
                break;

                default:
                s2 = new HUMANSPIELER(size);
            }
        }

        while(true){
            commandante.spielStarten(s1,s2);
            commandante.reset();
        }
    }
}
