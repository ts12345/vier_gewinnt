import java.util.ArrayList;
import java.util.Random;

/**
 * Computerspieler
 * 
 * @author TS 
 */
public class COMPUTERSPIELER_TS extends SPIELER {
    // Referenzattribut für das "globale" Spielfeld
    private SPIELFELD feld;
    
    private Random random;
    
    // Referenzattribut und Array für das Testspielfeld (-> Zwischenspeicher)
    private SPIELFELD mySpielfeld;
    int[][] copy_of_board = new int[7][6];
    
    private int playerNumber, opponentNumber;

    // Konstruktor für den Computerspieler
    public COMPUTERSPIELER_TS(SPIELFELD feld) {
        this.random = new Random();
        
        this.feld = feld;                      // Referenz auf globales Spielfeld
        this.mySpielfeld = new SPIELFELD();    // Referenz auf Testspielfeld
    }

    // Lädt aktuellen Zustand des Spielfeldes in Zwischenspeicher
    private void copyToMyBoard() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.copy_of_board[i][j] = this.feld.spielfeld[i][j];
            }
        }
    }

    // Überträgt Inhalt des Zwischenspeichers auf Testspielfeld
    private void copyToMySpielfeld() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.mySpielfeld.spielSteinSetzen(i, this.copy_of_board[i][j]);
            }
        }
    }

    // Ermittelt Spielernummer des Computerspielers
    public int playerNumber() {
        int sum = 0;    // Variable für Anzahl der Spielsteine
        
        // (sum + 1) für Stein von Spieler 1, (sum + 2) für Stein von Spieler 2
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) { sum += this.copy_of_board[i][j]; }
        }
        
        // Anz. Steine Spieler 1 == Anz. Steine Spieler 2: $sum \in V_3$
        if (sum % 3 == 0) { return 1; }
        else { return 2; }
    }

    // Ermittelt den "optimalen Zug"
    public int getNextMove() {
        int i, i2, j;                           // Zählvariablen
        
        int[] goodMoveScore = new int[7];       // Bewertung d. Zugmöglichkeiten
        int[] badMoveScore = new int[7];
        
        boolean foundBestMove = false;          // optimaler Zug gefunden?
        int bestMove = -1;                      // bester Zug
        
        // übernimmt mit Hilfe der oben deklarierten Methoden den Spielfeldstatus
        this.copyToMyBoard();
        this.mySpielfeld.leereSpielfeld();
        this.copyToMySpielfeld();
        
        // lädt die Spielernummer und schreibt sie in die LOG-Datei
        this.playerNumber = this.playerNumber();
        this.opponentNumber = 3 - this.playerNumber;
        this.logIt("Ich bin: " + this.playerNumber + " Du bist: " + this.opponentNumber);
        
        // Kennzeichnet Züge, die zum Sieg des Gegners führen, als "schlecht"
        for (i = 0; i < this.feld.getBreite(); ++i) {
            for (j = 0; j < this.feld.getBreite(); ++j) {
                this.mySpielfeld.spielSteinSetzen(i, this.playerNumber);
                this.mySpielfeld.spielSteinSetzen(j, this.opponentNumber);
                
                if (this.mySpielfeld.checkFourInARow(this.opponentNumber, j, this.mySpielfeld.freiesFeld(j) - 1)) {
                    this.logIt("Really bad move: " + i);
                    badMoveScore[i] = -1;
                }
                
                // setzt Testspielfeld zu Ursprungszustand
                this.mySpielfeld.leereSpielfeld();
                this.copyToMySpielfeld();
            }
        }
        
        // Kontrolliert, ob ein Stein so gesetzt werden kann, dass er zum Sieg führt
        for (i = 0; i < this.feld.getBreite(); ++i) {
            this.mySpielfeld.spielSteinSetzen(i, this.playerNumber);
            
            if (this.mySpielfeld.checkFourInARow(this.playerNumber, i, this.mySpielfeld.freiesFeld(i) - 1)) {
                foundBestMove = true;
                bestMove = i;
            }
            
            // setzt Testspielfeld zu Ursprungszustand
            this.mySpielfeld.leereSpielfeld();
            this.copyToMySpielfeld();
        }
        
        // Protokollierung und Rückgabe des ggf. in obiger Schleife gefundenen Siegzuges
        if (foundBestMove) {
            this.logIt("Winner Move");
            return bestMove;
        }
        
        // Ermittelt Löcher in unvollständigen Viererreihen des Gegners und stopft diese
        for (i = 0; i < this.feld.getBreite(); ++i) {
            this.mySpielfeld.spielSteinSetzen(i, this.opponentNumber);
            
            if (this.mySpielfeld.checkFourInARow(this.opponentNumber, i, this.mySpielfeld.freiesFeld(i) - 1)) {
                foundBestMove = true;
                bestMove = i;
            }
            
            // setzt Testspielfeld zu Ursprungszustand
            this.mySpielfeld.leereSpielfeld();
            this.copyToMySpielfeld();
        }
        
        // Protokollierung und Rückgabe des ggf. in obiger Schleife gefundenen "Zerstörungszuges"
        if (foundBestMove) {
            this.logIt("Necessary Destroy Move " + bestMove);
            return bestMove;
        }
        
        // Ermittelt den "Good Move Score" der einzelnen Möglichkeiten
        for (i = 0; i < this.feld.getBreite(); ++i) {
            for (j = 0; j < this.feld.getBreite(); ++j) {
                this.mySpielfeld.spielSteinSetzen(i, this.playerNumber);
                this.mySpielfeld.spielSteinSetzen(j, this.playerNumber);
                
                if (this.mySpielfeld.checkFourInARow(this.playerNumber, j, this.mySpielfeld.freiesFeld(j) - 1)) {
                    this.logIt("HopefulMove: " + i + " " + j);
                    int[] arrn = goodMoveScore;
                    int n = i;
                    arrn[n] = arrn[n] + 1;
                }
                
                // setzt Testspielfeld zu Ursprungszustand
                this.mySpielfeld.leereSpielfeld();
                this.copyToMySpielfeld();
            }
        }
        
        int maxScore = 0;
        ArrayList<Integer> moves = new ArrayList<Integer>();
        
        // Filtert die Züge mit dem besten "Good Move Score" heraus
        for (i2 = 0; i2 < this.feld.getBreite(); ++i2) {
            if (badMoveScore[i2] < 0) continue;
            
            if (goodMoveScore[i2] > maxScore) {
                this.logIt("Adding Move " + i2);
                moves.clear();
                moves.add(i2);
                maxScore = goodMoveScore[i2];
            }
            
            if (goodMoveScore[i2] != maxScore) continue;
            
            this.logIt("Adding Move " + i2);
            moves.add(i2);
        }
        
        // Hilft der "Good Move Score" nicht weiter, wird ein Zufallszug ermittelt
        if (moves.size() == 0) {
            for (i2 = 0; i2 < 7; ++i2) {
                if ( this.mySpielfeld.spielSteinSetzen(i2, this.playerNumber) ) { moves.add(i2); }
                
                this.logIt("Random Move");
            }
        }
        
        // Gibt besten Zug zürück; mehrere beste Züge: zufällige Auswahl
        bestMove = (Integer)moves.get(this.random.nextInt(moves.size()));
        this.logIt("My move: " + bestMove);
        return bestMove;
    }

    public boolean isHuman() { return false; }

    public void logIt(String str) { }
}