import java.awt.event.*;
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
        LISTENER.startListening(this);
        return 1;
    }
    
    public void clicked(MouseEvent e)
    {
        
    }
}
