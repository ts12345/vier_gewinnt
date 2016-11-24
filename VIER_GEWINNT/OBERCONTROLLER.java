import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse OBERCONTROLLER.
 * 
 * @author nobody knows cause nobody cares 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class OBERCONTROLLER {
    public static void main(String[] args) {
        OBERCONTROLLER oberkontrollrat = new OBERCONTROLLER();
    }

    public OBERCONTROLLER() {
        // Legt Variablen zur variablen Festlegung der Spielfeldgröße fest
        int size;
        
        // erstellt den Scanner
        Scanner scan = new Scanner(System.in);
        
        // Frägt im Starter die Sollspielfeldgröße ab und speichert den Wert in der sizeinput-Variable
        System.out.println("Bitte geben Sie die Spielfeldgröße ein. Mögliche Werte: 1 - 5 [3]");
        String sizeinput = scan.nextLine();
        
        // weist der Eingabe den entsprechenden size-Wert zu
        switch(sizeinput) {
            case "1":
                size = 60;
                break;
                
            case "2":
                size = 80;
                break;
                
            case "4":
                size = 120;
                break;
                
            case "5":
                size = 140;
                break;
            
            default:
                size = 100;
                break;
        }
        
        // Legt die Typen der Spieler 1 und 2 fest
        System.out.println("Bitte geben Sie die Art von Spieler 1 ein (1 - Humanspieler, 2 - Computerspieler, 3 - Netzwerkspieler)");
        String player = scan.nextLine();
        System.out.println("Und jetzt fuer Spieler 2:");
        String player2 = scan.nextLine();
        
        // deklariert Referenzattribute für beide Spieler
        SPIELER s1;
        SPIELER s2;
        
        // deklariert und erstellt Referenz auf einen Controller
        CONTROLLER commandante = new CONTROLLER(size);
       
        // erstellt Referenz auf die beiden Spieler
        switch(player){
            case "2":
            s1 = new COMPUTERSPIELER_TS(commandante.spielfeld);
            s2 = new HUMANSPIELER(size);
            break;
            case "3":
            s1 = new NETZWERKSPIELER("localhost", 2001);
            s2 = new HUMANSPIELER(size);
            break;
            case "4":
            s2 = new NETZWERKSPIELER("localhost", 2002);
            s1 = new HUMANSPIELER(size);
            break;
            default:
            s1 = new HUMANSPIELER(size);
            switch(player2){
                case "2":
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

