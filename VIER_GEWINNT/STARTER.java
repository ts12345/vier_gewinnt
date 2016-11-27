import java.util.Scanner;

/**
 * Objekte der Klasse STARTER starten ein neues Spiel
 * Eine grafische Oberfläche ist geplant
 * 
 * @author SFr682k
 */
public class STARTER {   
    public static void main(String[] args) {
        new STARTER();
    }
    
    public STARTER() {
        // Deklariert Variablen für Übergabe an OBERCONTROLLER
        int size, sp1, sp2, hauptport;
        
        // erstellt den Scanner
        Scanner scan = new Scanner(System.in);
        
        // Frägt die Sollspielfeldgröße ab und speichert sie in der sizeinput-Variable ab
        System.out.println("Bitte geben Sie die Spielfeldgröße ein.");
        System.out.println("(Mögliche Werte: 1 - 5 [3])");
        String sizeinput = scan.nextLine();
        System.out.println(" ");
        
        // weist der Eingabe den entsprechenden size-Wert zu
        switch(sizeinput) {
            case "1": size =  50; break;
            case "2": size =  75; break;
            case "4": size = 125; break;
            case "5": size = 150; break;
            default:  size = 100; break;
        }
        
        // Frägt die Typen von Spieler 1 und Spieler 2 ab
        System.out.println("Bitte geben Sie die Art von Spieler 1 ein:");
        System.out.println("(Mögliche Werte: [1] - Humanspieler, 2 - Computerspieler, 3/4 - Netzwerkspieler)");
        String player1 = scan.nextLine();
        System.out.println(" ");
        
        System.out.println("Bitte geben Sie die Art von Spieler 2 ein:");
        System.out.println("(Mögliche Werte: [1] - Humanspieler, 2 - Computerspieler, 3/4 - Netzwerkspieler)");
        String player2 = scan.nextLine();
        System.out.println(" ");
        
        // Bereinigt die Werteeingabe von oben:
        switch(player1) {
            case "2": sp1 = 2; break;
            case "3": sp1 = 3; break;
            case "4": sp1 = 4; break;
            default:  sp1 = 1; break;
        }
        
        switch(player2) {
            case "2": sp2 = 2; break;
            case "3": sp2 = 3; break;
            case "4": sp2 = 4; break;
            default:  sp2 = 1; break;
        }
        
        // Abfrage der IP-Adresse (derzeit auskommentiert und überritten):
            // System.out.println("Geben Sie die IP-Adresse des Servers ein:")
            // String serverIP = scan.nextLine()
        String serverIP = "localhost";
        
        
        // Festlegung des Hauptports, auf dem alle Anfragen einlaufen (ggf. im OBERCONTROLER überritten):
        hauptport = 2000;
        
        // legt einen neuen OBERCONTROLLER an
        OBERCONTROLLER hugoDerBoss = new OBERCONTROLLER(size, sp1, sp2, serverIP, hauptport);
    }
}
