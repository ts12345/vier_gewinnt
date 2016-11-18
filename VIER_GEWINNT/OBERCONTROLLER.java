
/**
 * Beschreiben Sie hier die Klasse OBERCONTROLLER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class OBERCONTROLLER
{
    public static void main(String[] args){
        OBERCONTROLLER oberkontrollrat = new OBERCONTROLLER();
    }

    public OBERCONTROLLER(){
        SPIELER s1 = new HUMANSPIELER();
        SPIELER s2 = new HUMANSPIELER();
        CONTROLLER commandante = new CONTROLLER();
        while(true){
            commandante.spielStarten(s1,s2);
            commandante.reset();
        }
    }
}
