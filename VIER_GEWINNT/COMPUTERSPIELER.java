import java.awt.event.*;
/**
 * Beschreiben Sie hier die Klasse COMPUTERSPIELER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class COMPUTERSPIELER extends SPIELER
{
    private SPIELFELD feld;
    /**
     * Konstruktor für Objekte der Klasse COMPUTERSPIELER
     */
    public COMPUTERSPIELER(SPIELFELD feld)
    {
        this.feld = feld;
    }

    public int getNextMove()
    {
        return 1;
    }

    public boolean isHuman() {
        return false;
    }
}