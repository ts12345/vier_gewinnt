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
        System.out.println("am zug");     
        LISTENER.startListening();

        while(LISTENER.getLastX() == 0)
        {
            System.out.println(LISTENER.getLastX());

            try {
                Thread.sleep(100);  // milliseconds
            } catch (InterruptedException ex) { } 
            //Warten darauf dass geklickt wird
            //evtl. wait(100)
        }

        System.out.println("Next Move : " + (LISTENER.getLastX()/100));
        return (int)(LISTENER.getLastX()/100);
    }
}
