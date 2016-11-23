import java.util.ArrayList;
import java.util.Random;

/**
 * Computerspieler
 * 
 * @author TS 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class COMPUTERSPIELER_TS extends SPIELER {
    private SPIELFELD feld;
    private Random random;
    private SPIELFELD mySpielfeld;
    int[][] copy_of_board;
    private int playerNumber;
    private int opponentNumber;

    public COMPUTERSPIELER_TS(SPIELFELD feld) {
        this.feld = feld;
        this.random = new Random();
        this.mySpielfeld = new SPIELFELD();
        this.copy_of_board = new int[7][6];
    }

    private void copyToMyBoard() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.copy_of_board[i][j] = this.feld.spielfeld[i][j];
            }
        }
    }

    private void copyToMySpielfeld() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.mySpielfeld.spielSteinSetzen(i, this.copy_of_board[i][j]);
            }
        }
    }

    public int playerNumber() {
        int sum = 0;
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {
                sum += this.copy_of_board[i][j];
            }
        }
        if (sum % 3 == 0) {
            return 1;
        }
        return 2;
    }

    public int getNextMove() {
        int j;
        int i;
        int i2;
        int[] goodMoveScore = new int[7];
        int[] badMoveScore = new int[7];
        boolean foundBestMove = false;
        int bestMove = -1;
        this.copyToMyBoard();
        this.mySpielfeld.leereSpielfeld();
        this.copyToMySpielfeld();
        this.playerNumber = this.playerNumber();
        this.playerNumber = 1;
        this.opponentNumber = 3 - this.playerNumber;
        this.logIt("iCH BIN: " + this.playerNumber + " Du bist: " + this.opponentNumber);
        for (i = 0; i < this.feld.getBreite(); ++i) {
            for (j = 0; j < this.feld.getBreite(); ++j) {
                this.mySpielfeld.spielSteinSetzen(i, this.playerNumber);
                this.mySpielfeld.spielSteinSetzen(j, this.opponentNumber);
                if (this.mySpielfeld.checkFourInARow(this.opponentNumber, j, this.mySpielfeld.freiesFeld(j) - 1)) {
                    this.logIt("Really bad move: " + i);
                    badMoveScore[i] = -1;
                }
                this.mySpielfeld.leereSpielfeld();
                this.copyToMySpielfeld();
            }
        }
        for (i = 0; i < this.feld.getBreite(); ++i) {
            this.mySpielfeld.spielSteinSetzen(i, this.playerNumber);
            if (this.mySpielfeld.checkFourInARow(this.playerNumber, i, this.mySpielfeld.freiesFeld(i) - 1)) {
                foundBestMove = true;
                bestMove = i;
            }
            this.mySpielfeld.leereSpielfeld();
            this.copyToMySpielfeld();
        }
        if (foundBestMove) {
            this.logIt("Winner Move");
            return bestMove;
        }
        for (i = 0; i < this.feld.getBreite(); ++i) {
            this.mySpielfeld.spielSteinSetzen(i, this.opponentNumber);
            if (this.mySpielfeld.checkFourInARow(this.opponentNumber, i, this.mySpielfeld.freiesFeld(i) - 1)) {
                foundBestMove = true;
                bestMove = i;
            }
            this.mySpielfeld.leereSpielfeld();
            this.copyToMySpielfeld();
        }
        if (foundBestMove) {
            this.logIt("Necessary Destroy Move " + bestMove);
            return bestMove;
        }
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
                this.mySpielfeld.leereSpielfeld();
                this.copyToMySpielfeld();
            }
        }
        int maxScore = 0;
        ArrayList<Integer> moves = new ArrayList<Integer>();
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
        if (moves.size() == 0) {
            for (i2 = 0; i2 < 7; ++i2) {
                if (this.mySpielfeld.spielSteinSetzen(i2, this.playerNumber)) {
                    moves.add(i2);
                }
                this.logIt("Random Move");
            }
        }
        bestMove = (Integer)moves.get(this.random.nextInt(moves.size()));
        this.logIt("My move: " + bestMove);
        return bestMove;
    }

    public boolean isHuman() {
        return false;
    }

    public void logIt(String str) {
    }
}
