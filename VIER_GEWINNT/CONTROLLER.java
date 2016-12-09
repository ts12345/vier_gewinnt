import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

/**
 * Die Klasse CONTROLLER beschreibt die Steuerung des Spiels
 * legt ein Spielfeld und ein View an
 * @author (kinder)
 * @version (08.06.16)
 * @since (21.04.16)
 */

public class CONTROLLER {
    SPIELFELD spielfeld;    //Referenz auf das Spielfeld
    VIEW view;              //Referenz auf die Darstellung
    LISTENER listener;
    SOUNDENGINE soundengine;
    int spieleramzug;
    JFrame frameView = new JFrame("Vier gewinnt!");
    
    private int size;

    public CONTROLLER(int size) {
        this.size = size;
        spielfeld = new SPIELFELD();          //Neues Spielfeld
        view = new VIEW(spielfeld, this);     //Neuer View
        
        listener = new LISTENER(this);
        frameView.addMouseMotionListener(listener);
        soundengine = new SOUNDENGINE();
        spieleramzug = 0;

        frameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        frameView.add(view);

        frameView.pack();
        frameView.setVisible(true);
    }

    /**
     * Startet das Spiel und fragt die Spieler abwechselnd nach ihren Zügen, bis einer gewonnen hat
     */
    public void spielStarten(SPIELER s1, SPIELER s2) {
        int pause = 0;

        int belegteFelder = 0;
        int spielfeldgroesse = spielfeld.getBreite() * spielfeld.getHoehe();

        s1.activateListener(frameView);
        s2.activateListener(frameView);

        SPIELER[] players = new SPIELER[3];

        players[1] = s1;
        players[2] = s2;

        int currentPlayer = 1;

        int lastx = 0;
        int playerwon = 4;

        boolean spielZuEnde = false;

        while ( !spielZuEnde ) {
            if (belegteFelder == spielfeldgroesse) {
                spielZuEnde = true;
                playerwon = 3;
            } else {
                int anzahlVersuche = 0;
                boolean gueltigerZug = false;

                spieleramzug = nextplayer(currentPlayer);

                if(players[currentPlayer].isHuman()) {
                    view.showPreview(true);
                    pause = 0;
                } else {
                    view.showPreview(false);
                    pause = 1000;
                }

                while(( !gueltigerZug ) && ( anzahlVersuche !=3 )){

                    lastx = players[currentPlayer].getNextMove();

                    try {
                        Thread.sleep(pause);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    gueltigerZug = spielsteinSetzen(lastx, currentPlayer);                                

                    if (gueltigerZug == false) {
                        anzahlVersuche++;
                        if(players[currentPlayer].isHuman()) { soundengine.playIllegal(); }
                    }

                    if(anzahlVersuche == 3) {
                        spielZuEnde = true;
                        playerwon = nextplayer(currentPlayer);
                    }
                }

                belegteFelder++;

                players[nextplayer(currentPlayer)].VerarbeiteGegnerischenZug(lastx);

                if(spielfeld.checkFourInARow(currentPlayer,lastx,spielfeld.freiesFeld(lastx)-1)){
                    spielZuEnde = true;
                    playerwon = currentPlayer;
                }

                currentPlayer = nextplayer(currentPlayer);
            }
        }

        //System.out.println("Spieler " + (playerwon+1) + " hat gewonnen");
        view.showWinner(playerwon);

        if (playerwon == 3) {
            soundengine.playDrawer();
        } else {
            soundengine.playFanfare();
        }
    }

    /**
     * Hilfsmethode, die spielStarten() den naechsten Spieler übergibt
     * 
     * @param p Der vorherige Spieler
     * @return Der naechste Spieler
     */
    private int nextplayer(int p) {
        switch (p) {
            case 1: return 2;
            case 2: return 1;
        }
        return 0;
    }

    public boolean spielsteinSetzen(int x, int spieleramzug) {
        boolean geklappt = spielfeld.spielSteinSetzen(x, spieleramzug);
        if (geklappt) {
            view.showPreview(false);
            spielfeld.entferneOberstenStein(x);
            int y = spielfeld.freiesFeld(x);
            STEIN_ANIMATION_THREAD t = new STEIN_ANIMATION_THREAD(x, y, spieleramzug, view, this);
            t.run();
            spielfeld.spielSteinSetzen(x, spieleramzug);
            soundengine.playClick();
            view.repaint();
        }
        return geklappt;
    }

    public VIEW getView() {
        return view;
    }

    public void reset() {
        spielfeld.leereSpielfeld();
        view.showWinner = true;
        try {
            Thread.sleep(4000); 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        frameView.dispose();
    }

    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        if(size < 25) {
            size = 25;
        } else if (size > 150) {
            size = 150;
        }
        
        this.size = size;
    }
    
    public void testeDiagonalengewinnMethode() {
        reset();
        int player = 1;
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j < 5 - i; j++) {
                spielsteinSetzen(j, player);
            }
            player = 3 - player;
        }
        for(int i = 0; i < 4; i++) {
            spielsteinSetzen(2 + i, 2);
        }
        System.out.println(spielfeld.checkFourInARow(2, 3, spielfeld.freiesFeld(3) - 1));
    }
}