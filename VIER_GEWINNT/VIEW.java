
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
        for (int i=0; i<sp.length; i++){
            for(int i2=0; i2<sp[i].length; i2++){
                System.out.print(sp[i][i2]+"   ");
            }
            System.out.println();
        }
    }
}
