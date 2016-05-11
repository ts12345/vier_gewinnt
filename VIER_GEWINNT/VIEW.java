/**
 * Die Klasse VIEW zeigt das Spielfeld an und interagiert mit dem Spieler.
 * 
 * @author (kinder) 
 *
 */
public class VIEW
{
    SPIELFELD spielfeld;

    public VIEW(SPIELFELD spielfeld)
    {
        this.spielfeld = spielfeld;
    }

    /**
     * Druckmethode, die das Spielfeld auf der Konsole ausgibt
     */
    public void printOutToConsole() {
        print(spielfeld.getSpielfeld());
    }

    /**
     * Hilfsmethode, die ein zweidimensionales int Array ausdrucken kann
     */
    private void print(int[][] sp){
        for (int i=sp[0].length-1; i>=0; i--){
            for(int i2=0; i2<sp.length; i2++){
                System.out.print(sp[i2][i]+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
