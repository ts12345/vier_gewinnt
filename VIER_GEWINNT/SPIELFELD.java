/**
 * Die Klasse SPIELFELD erstellt ein Spielfeld, setzt Spielsteine und gibt an, wenn man gewinnt
 * 
 * @author (kinder) und SFr682k
 * @version (10.12.16)
 * @since (21.04.16)
 * 
 */
public class SPIELFELD {
    int[][] spielfeld;  //Spielfeldarray (Breite x Hoehe)

    /**
     * Konstruktor, der neues Spielfeldarray anlegt
     */
    public SPIELFELD() { spielfeld = new int[7][6]; }

    /**
     * Setzt einen Spielstein in die Spalte x
     * Ganz links:  0
     * Ganz rechts: breite - 1
     * 
     * @param x Spalte 
     * @param spieler 1: Spieler1; 2: Spieler2
     * @return setzen ausgefuehrt?
     */
    public boolean spielSteinSetzen (int x, int spieler) {
        int i = freiesFeld(x);
        if(i == spielfeld[x].length) {
            return false;
        }

        spielfeld[x][i] = spieler;
        return true;
    }

    /**
     * Gibt die Höhe des Felds als integer zurück
     * 
     * @return die Höhe des Spielfelds
     */
    public int getHoehe() { return spielfeld[0].length; }

    /**
     * Gibt die Breite des Felds als integer zurück
     * 
     * @return die Breite des Spielfelds
     */
    public int getBreite() { return spielfeld.length; }

    /**
     * Hilfsmethode für spielSteinSetzen; Gibt die Höhe des niedrigsten freien Felds in der Spalte s aus
     * 
     * @param s die Spalte, in der gesucht wird
     * 
     * @return die Höhe des niedrigsten freien Felds (0 bis hoehe - 1  ist gültig, wenn die Spalte voll ist, wird hoehe ausgegeben)
     */
    public int freiesFeld(int s) {        
        boolean a = true;
        int i = 0;

        while(a) {
            a = spielfeld[s][i] != 0;
            // ist das Feld leer (==0), dann wird a negativ gesetzt und die schleife abgebrochen
            // i ist logischerweis auch die Hoehe des leeren felds und wird zurueckgegeben
            if(a) {
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
     * Gibt zurück, ob die Spalte spalte voll ist
     * 
     * @param spalte die Spalte, die untersucht werden soll
     * @return Wahrheitswert (wahr, wenn Spalte voll)
     * 
     */
    public boolean spalteVoll(int spalte) {
        int returned = freiesFeld(spalte);

        if (returned == 6) { return true; }
        else { return false; }
    }

    /**
     * Gibt das Spielfeldarray als 2D - Array zurück
     * 
     * @return das 2D - array
     */
    public int[][] getSpielfeld() { return spielfeld; }

    /**
     * Methode, die für einen Spieler herausfindet, ob er vier Steine in einer Reihe hat
     * 
     * @param spieler Spieler, für den getestet werden soll
     * @param x ist die x-Koordinate des Spielsteins
     * @param y ist die y-Koordinate des Spielsteins
     * @return ist true, wenn der Stein in einer 4er Reihe liegt
     */
    public boolean checkFourInARow(int spieler, int x ,int y) {
        int counter  = 0;                                                      // zum Zählen der gleichartigen Steine in einer Reihe
        boolean dir1 = false, dir2 = false, dir3 = false, dir4 = false;        // Richtungen, in der vier Steine in einer Reihe gefunden wurden
        boolean ret = false;                                                   // Hilfsvariable für Rückgabewert

        // checkt die Waagrechte (dir1)
        for(int i = 3; i > -1; i--) { 
            counter = 0;

            for(int j = 0; j < 4; j++) {
                if((x - i) + j < getBreite() && (x - i) + j > -1 && spielfeld[(x - i) + j][y] == spieler) {
                    counter++; 
                }

                if(counter == 4) {
                    dir1 = true;
                    ret = true; // Rückgabewert wahr wenn vier Steine in einer Reihe liegen
                }
            }
        }

        // checkt die Senkrechte (dir2)
        for(int k = 3; k > -1; k--) {              
            counter = 0;

            for(int l = 0; l < 4; l++) {
                if((y + k) - l < getHoehe() &&(y + k) - l > -1 && spielfeld[x][(y + k) - l] == spieler){
                    counter++; 
                }

                if(counter == 4){
                    dir2 = true;
                    ret = true; // Rückgabewert wahr wenn vier Steine in einer Reihe liegen
                }
            }
        }

        // checkt die Diagonale von rechts nach links (dir3)
        for(int m = 3; m > -1; m--) { 
            counter = 0;

            for(int n = 0; n < 4; n++) {
                if((x - m) + n < getBreite() && (y - m) + n < getHoehe() && (x - m) + n > -1 && (y - m) + n > -1 && spielfeld[(x - m) + n][(y - m) + n] == spieler){
                    counter++; 
                }

                if(counter == 4) {
                    dir3 = true;
                    ret = true; // Rückgabewert wahr wenn vier Steine in einer Reihe liegen
                }
            }
        }

        //checkt die Diagonale links nach rechts (dir4)
        for(int o = 3; o > -1 ; o--) {
            counter = 0;

            for(int p = 0; p < 4; p++) { 
                if((x - o) + p < getBreite() && (y + o) - p < getHoehe() &&(x - o) + p > -1 &&(y + o) - p > -1 && spielfeld[(x - o) + p][(y + o) - p] == spieler){
                    counter++; 
                }

                if(counter == 4){
                    dir4 = true;
                    ret = true; // Rückgabewert wahr wenn vier Steine in einer Reihe liegen
                }
            }
        }

        if (ret) { steineMarkieren(spieler, x, y, dir1, dir2, dir3, dir4); }

        return ret;
    }

    /**
     * Methode, die in einer Viererreihe liegende Steine markiert
     * 
     * @param spieler, Spieler für den getestet werden soll
     * @param x ist die x-Koordinate des zuletzt gesetzten Spielsteins
     * @param y ist die y-Koordinate des zuletzt gesetzten Spielsteins
     * @param dir1 ist true, wenn die Viererreihe horizontal verläuft
     * @param dir2 ist true, wenn die Viererreihe vertikal verläuft
     * @param dir3 ist true, wenn die Viererreihe diagonal von rechts nach links verläuft
     * @param dir4 ist true, wenn die Viererreihe diagonal von links nach rechts verläuft
     */
    private void steineMarkieren(int spieler, int x, int y, boolean dir1, boolean dir2, boolean dir3, boolean dir4) {
        // Untersucht die Waagrechte (dir1)
        if(dir1 == true) {
            for(int i = 3; i > -1; i--) {         
                for(int j = 0; j < 4; j++) {
                    if((x - i) + j < getBreite() && (x - i) + j > -1 && spielfeld[(x - i) + j][y] == spieler) {
                        spielfeld[(x - i) + j][y]   =   spielfeld[(x - i) + j][y]   +   2;
                    }
                }
            }
        }

        // Untersucht die Senkrechte (dir2)
        if(dir2 == true) {
            for(int k = 3; k > -1; k--) {              
                for(int l = 0; l < 4; l++) {
                    if((y + k) - l < getHoehe() &&(y + k) - l > -1 && spielfeld[x][(y + k) - l] == spieler){
                        spielfeld[x][(y + k) - l]   =   spielfeld[x][(y + k) - l]   +   2;
                    }
                }
            }
        }

        // Untersucht die Diagonale von rechts nach links (dir3)
        if(dir3 == true) {
            for(int m = 3; m > -1; m--) { 
                for(int n = 0; n < 4; n++) {
                    if((x - m) + n < getBreite() && (y - m) + n < getHoehe() && (x - m) + n > -1 && (y - m) + n > -1 && spielfeld[(x - m) + n][(y - m) + n] == spieler){
                        spielfeld[(x - m) + n][(y - m) + n]   =   spielfeld[(x - m) + n][(y - m) + n]   +   2;
                    }
                }
            }
        }

        // Untersucht die Diagonale von links nach rechts (dir4)
        if(dir4 == true) {
            for(int o = 3; o > -1 ; o--) {
                for(int p = 0; p < 4; p++) { 
                    if((x - o) + p < getBreite() && (y + o) - p < getHoehe() &&(x - o) + p > -1 &&(y + o) - p > -1 && spielfeld[(x - o) + p][(y + o) - p] == spieler){
                        spielfeld[(x - o) + p][(y + o) - p]   =   spielfeld[(x - o) + p][(y + o) - p]   +   2;
                    }
                }
            }
        }
    }

    public void leereSpielfeld () {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                spielfeld [x][y] = 0;
            }
        }
    }

    public void entferneOberstenStein(int spalte) { spielfeld[spalte][freiesFeld(spalte) - 1] = 0; }

    public boolean[][] besetzt() {
        boolean[][] ret = new boolean[spielfeld.length][spielfeld[0].length];

        for (int i1 = 0; i1 < spielfeld.length; i1++) {
            for (int i2 = 0; i2 < spielfeld[i1].length; i2++) {
                ret[i1][i2] = (spielfeld[i1][i2] != 0);
            }
        }

        return ret;
    }
}