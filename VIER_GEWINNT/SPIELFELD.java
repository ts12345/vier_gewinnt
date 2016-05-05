
/**
 * Beschreiben Sie hier die Klasse SPIELFELD.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SPIELFELD
{
    int breite;
    int hoehe;
    int[][] spielfeld;

    public SPIELFELD()
    {

    }

    /**
     * setzt Spielstein in Spalte x
     * ganz links 0
     * ganz rechts breite-1
     * 
     * @param x Spalte 
     * @param spieler 1: Spieler1; 2: Spieler2
     * @return setzen ausgefuehrt?
     */
    public boolean spielSteinSetzen (int x, int spieler)
    {
        return false;
    }
    
    /**
     * gibt die hoehe des felds als integer zurueck
     * 
     * @return die hoehe des spielfelds
     */
    public int getHoehe(){
        return spielfeld[0].length;
    }
    
    /**
     * gibt die breite des felds als integer zurueck
     * 
     * @return die breite des spielfelds
     */
    public int getBreite(){
        return spielfeld.length;
    }
}
