
/**
 * Beschreiben Sie hier die Klasse HUMANSPIELER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class HUMANSPIELER extends SPIELER
{
    /**
     * Konstruktor für Objekte der Klasse HUMANSPIELER
     */
    public HUMANSPIELER()
    {
    }
    public int getNextMove()
    {
        LISTENER.startListening();
        while(LISTENER.lastX == 0)
        {}
        return (int)(LISTENER.lastX / 100);
    }
}
