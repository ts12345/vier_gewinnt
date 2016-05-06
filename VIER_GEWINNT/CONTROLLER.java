/**
 * Die Klasse CONTROLLER beschreibt die Steuerung des Spiels
 * legt ein Spielfeld und ein View an
 * @author (kinder)
 * 
 */

public class CONTROLLER
{
    SPIELFELD spielfeld;
    VIEW view;

    public CONTROLLER()
    {
        spielfeld = new SPIELFELD();
        view = new VIEW(spielfeld);

 
        spielfeld.spielSteinSetzen(1, 1);
        spielfeld.spielSteinSetzen(1, 2);
        view.printOutToConsole();

    }
    
    public static void main(String[] args)
    {
        CONTROLLER c = new CONTROLLER();
    }
    
    /**
     * Metode die das Feld mit n Steinen fuellt in abwechselnder Reihenfolge
     * 
     * @param n     Anzahl zu setzender Steine
     */
    
    public void fillRandomly(int n)
    {
        for(int i = 0; i < n; i++)
        {
            if((i%2) == 0)
            {
                spielfeld.spielSteinSetzen((int)(Math.random()*7), 1);
            }else
            {
                spielfeld.spielSteinSetzen((int)(Math.random()*7), 2);
            }
        }
    }

}
