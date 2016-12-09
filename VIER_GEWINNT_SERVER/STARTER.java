import java.util.Scanner;

/**
 * Objekte der Klasse STARTER starten ein neues Spiel
 * 
 * @author SFr682k
 */
public class STARTER {   
    public static void main(String[] args) {
        new STARTER();
    }
    
    public STARTER() {
        // Deklariert Variablen für Übergabe an den SERVER
        int hauptport;
        boolean chatSupport;
        boolean zensieren;
                
        // erstellt den Scanner
        Scanner scan = new Scanner(System.in);
        
        // Frägt ab, ob Chatfkt verwendet werden soll
        System.out.println("Soll die Chatfunktion zur Verfügung stehen?");
        System.out.println("[1]: Ja          2: Nein");
        String chatSupportIn = scan.nextLine();
        System.out.println(" ");
        
        switch(chatSupportIn) {
            case "2": chatSupport = false; break;
            default:  chatSupport = true;  break;
        }
        
        // Wenn Chatfkt aktiviert frägt nach, ob indizierte Wörter zensiert werden sollen
        if(chatSupport == true) {
            System.out.println("Sollen im Chat geäußerte, indizierte Wörter zensiert werden?");
            System.out.println("[1]: Ja          2: Nein");
            String zensierenIn = scan.nextLine();
            System.out.println(" ");
            
            switch(zensierenIn) {
                case "2": zensieren = false; break;
                default:  zensieren = true;  break;
            }
        } else {
            zensieren = false;
        }
        
        // Frägt den Hauptport ab
        System.out.println("Bitte geben Sie den Hauptport, auf dem alle Anfragen einlaufen, an:");
        hauptport = scan.nextInt();
        System.out.println(" ");
        
        // startet einen neuen SERVER
        try {
            new SERVER(hauptport, chatSupport, zensieren);
        } catch (Exception e) {
            // Müdes Nichts - Gähnende Leere
        }
    }
}