import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse OBERCONTROLLER.
 * 
 * @author nobody knows cause nobody cares 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class OBERCONTROLLER
{
    public static void main(String[] args){
        OBERCONTROLLER oberkontrollrat = new OBERCONTROLLER();
    }

    public OBERCONTROLLER(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Art von Spieler 1 ein (1 - Humanspieler, 2 - Computerspieler, 3 - Netzwerkspieler)");
        String player = scan.nextLine();
        CONTROLLER commandante = new CONTROLLER();
        SPIELER s1;
        SPIELER s2;
        switch(player){
            case "2":
            s1 = new COMPUTERSPIELER_TS(commandante.spielfeld);
            s2 = new HUMANSPIELER();
            break;
            case "3":
            s1 = new NETZWERKSPIELER("localhost", 2001);
            s2 = new HUMANSPIELER();
            break;
            case "4":
            s2 = new NETZWERKSPIELER("localhost", 2002);
            s1 = new HUMANSPIELER();
            break;
            default:
            s1 = new HUMANSPIELER();
            System.out.println("Und jetzt fuer Spieler 2:");
            String player2 = scan.nextLine();
            switch(player2){
                case "2":
                s2 = new COMPUTERSPIELER_TS(commandante.spielfeld);
                break;
                default:
                s2 = new HUMANSPIELER();
            }
        }
        while(true){
            commandante.spielStarten(s1,s2);
            commandante.reset();
        }
    }
}