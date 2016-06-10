
/**
 * Die Klasse SPIELFELD erstellt ein Spielfeld und setzt Spielsteine
 * gibt an, wenn man gewinnt
 * @author (kinder)
 * @version (08.06.16)
 * @since (21.04.16)
 * 
 */
public class SPIELFELD
{
    int[][] spielfeld;  //Spielfeldarray (Breite x Hoehe)

    /**
     * Konstruktor der neues Spielfeldarray anlegt
     */
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
        int i = freiesFeld(x);
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
     * @return die Hoehe des niedrigsten freien Felds (0 bis hoehe - 1  ist gueltig, wenn die spalte voll ist, wird hoehe ausgegeben)
     */
    public int freiesFeld(int s){
        boolean a = true;
        int i = 0;
        while(a){
            a=spielfeld[s][i] != 0;
            // ist das Feld leer (==0), dann wird a negativ gesetzt und die schleife abgebrochen
            // i ist logischerweis auch die Hoehe des leeren felds und wird zurueckgegeben
            if(a){
                a = spielfeld[s].length > i + 1;
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
     * gibt das spielfeldarray zurueck als 2D - Array
     * 
     * @return das 2D - array
     */
    public int[][] getSpielfeld()
    {
        return spielfeld;
    }

    /**
     * Methode die fuer einen spieler herausfindet ob er vier in einer Reihe hat
     * 
     * @param spieler Spieler fuer den getestet werden soll
     * @param x ist die x-coordinate des Spielsteins
     * @param y ist die y-coordinate des Spielsteins
     * @return ob der Stein in einer 4er Reihe liegt
     */
    public boolean checkFourInARow(int spieler, int x ,int y){

        int test = 0; //zum zaelen der gleichartigen Steine in einer Reihe
        boolean rueck = false; //wird wahr wenn vier Steine in einer Reihe liegen
        //checkt die Waagrechte
        for(int i = 3; i > -1; i--){ 
            if(test == 4){
                break; //bricht ab wenn eine Viererreihe gefunden worden ist
            }
            test =0;
            for(int j = 0; j < 4; j++){
                if((x - i) + j < getBreite() && (x - i)+j > -1 && spielfeld[(x - i) + j][y] == spieler){
                    test++; 
                }

                if(test == 4){
                    break; //bricht ab wenn eine Viererreihe gefunden worden ist
                }
            }
        }
        //checkt die Senkrechte
        if(test == 4){
            rueck = true;
        }else{
            for(int k = 3; k > -1; k--){ 
                if(test == 4){
                    break; //bricht ab wenn eine Viererreihe gefunden worden ist
                }
                test =0;
                for(int l= 0; l < 4; l++){
                    if((y + k)-l < getHoehe() &&(y + k)-l > -1 && spielfeld[x][(y + k)-l] == spieler){
                        test++; 
                    }

                    if(test == 4){
                        break; //bricht ab wenn eine Viererreihe gefunden worden ist
                    }
                }
            } 
        }
        //checkt die diagonale von klein nach groß
        if(test == 4){
            rueck = true;
        }else{
            for(int m = 3; m > -1; m--){ 
                if(test == 4){
                    break; //bricht ab wenn eine Viererreihe gefunden worden ist
                }
                test =0;
                for(int n= 0; n < 4; n++){
                    if((x - m) + n < getBreite() && (y - m) + n < getHoehe() && (x - m) + n > -1 && (y - m) + n > -1 && spielfeld[(x - m) + n][(y - m) + n] == spieler){
                        test++; 
                    }

                    if(test == 4){
                        break; //bricht ab wenn eine Viererreihe gefunden worden ist
                    }
                }
            } 
        }
        //checkt die Diagonale von groß nach kein
        if(test == 4){
            rueck = true;
        }else{
            for(int o= 3; o > -1 ; o--){
                if(test == 4){
                    break; //bricht ab wenn eine Viererreihe gefunden worden ist
                }
                test =0;
                for(int p = 0; p < 4; p++){ 
                    if((x + o) - p < getBreite() && (y + o) - p < getHoehe() &&(x + o) - p > -1 &&(y + o) - p > -1 && spielfeld[(x + o) - p][(y + o) - p] == spieler){
                        test++; 
                    }

                    if(test == 4){
                        break; //bricht ab wenn eine Viererreihe gefunden worden ist
                    }
                }
            } 
        }
        if(test == 4){
            rueck = true; //letzter (unnötiger) Check
        }
        return rueck;
    }

    public void leereSpielfeld () {
        for (int x = 0; x < 7; x++){

            for (int y = 0; y < 6; y++) {
                spielfeld [x][y] = 0;
            }

        }
    }
}
