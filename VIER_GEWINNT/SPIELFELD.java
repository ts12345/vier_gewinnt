
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
        spielfeld = new int[7][6];        
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
        int i=freiesFeld(x);
        if(i == spielfeld[x].length){
            return false;
        }
        spielfeld[x][i] = spieler;
        return true;
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

    /**
     * Hilfsmethode fuer spielSteinSetzen; Gibt die Hoehe des niedrigsten freien Felds in der Spalte s aus
     * 
     * @param s die Spalte, in der gesucht wird
     * 
     * @return die Hoehe des niedrigsten freien Felds (0 bis hoehe-1 ist gueltig, wenn die spalte voll ist, wird hoehe ausgegeben)
     */
    private int freiesFeld(int s){
        boolean a = true;
        int i = 0;
        while(a){

            a=spielfeld[s][i]!=0;
            //ist das Feld leer (==0), dann wird a negativ gesetzt und die schleife abgebrochen
            //i ist logischerweis auch die Hoehe des leeren felds und wird zurueckgegeben
            if(a){
                a = spielfeld[s].length > i+1;
                //wenn i+1 == length, dann wurde das oberste Feld geprüft und es war besetzt
                //a wird dann negativ gesetzt, damit die Schleife abbricht
                //jetzt wird i vergroessert, um entweder
                // a) die Zaehlvariable der Schleife zu vergroessern, wenn dieselbe weiterlaeuft ODER
                // b) i auf length zu setzen, wenn die Schleife abbricht
                i++;
            }
        }
        return i;
    }

    /**
     * Hilfsmethode zum Testen; Erstellt ein komplett leeres Spielfeld
     * Sollte vorm Speichern immer auf private gesetzt werden
     */
    private void initialise(){
        spielfeld = new int[7][6];
        for (int i=0; i<spielfeld.length; i++){
            for(int i2=0; i2<spielfeld[i].length; i2++){
                spielfeld[i][i2]=0;
            }
        }
    }
}
