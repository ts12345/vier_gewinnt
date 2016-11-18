import java.net.*;
import java.io.*;
/**
 * Beschreiben Sie hier die Klasse NETZWERKSPIELER.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class NETZWERKSPIELER extends SPIELER
{
    private Socket server = null;
    private PrintWriter zumServer = null;
    private BufferedReader vomServer = null;

    public NETZWERKSPIELER(String ipadresse, int port)
    {
        try{
            server = new Socket(ipadresse,port);
        }catch(Exception e){}
    }

    public boolean isHuman()
    {
        return false;
    }

    public int getNextMove()
    {
        return 1;
    }

    public void VerarbeiteGegnerischenZug(int gegnerischerSpieler)
    {

    }
}
