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

public class CONTROLLER
{
    SPIELFELD spielfeld;    //Referenz auf das Spielfeld
    VIEW view;              //Referenz auf die Darstellung
    LISTENER listener;
    SOUNDENGINE soundengine;
    int spieleramzug;
    JFrame frameView = new JFrame("Vier gewinnt!");
    int size;

    public CONTROLLER(int size)
    {
        this.size = size;
        spielfeld = new SPIELFELD();         //Neues Spielfeld
        view = new VIEW(spielfeld,this);     //Neuer View
        view.setsize(size); 
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
                spielsteinSetzen((int)(Math.random()*7), 1);
            }else
            {
                spielsteinSetzen((int)(Math.random()*7), 2);
            }
        }
    }

    /**
     * Startet das Spiel und fragt die Spieler abwechselnd nach ihren Zügen, bis einer gewonnen hat
     */
    public void spielStarten(SPIELER s1, SPIELER s2){
        int pause = 0;

        s1.activateListener(frameView);
        s2.activateListener(frameView);

        SPIELER[] players = new SPIELER[3];

        players[1] = s1;
        players[2] = s2;

        int currentPlayer = 1;

        int lastx=0;
        int playerwon=4;

        boolean spielZuEnde = false;
        while ( !spielZuEnde ){
            int anzahlVersuche = 0;
            boolean gueltigerZug = false;
            spieleramzug = nextplayer(currentPlayer);
            if(players[currentPlayer].isHuman())
            {
                view.showPreview(true);
                pause = 0;
            }

            else
            {
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

                spieleramzug=currentPlayer;

                gueltigerZug = spielsteinSetzen(lastx, currentPlayer);                                
                anzahlVersuche++;

                if(anzahlVersuche == 3){
                    spielZuEnde = true;
                    playerwon = nextplayer(currentPlayer);
                }
            }
            players[nextplayer(currentPlayer)].VerarbeiteGegnerischenZug(lastx);
            if(spielfeld.checkFourInARow(currentPlayer,lastx,spielfeld.freiesFeld(lastx)-1)){
                spielZuEnde = true;
                playerwon = currentPlayer;
            }
            currentPlayer = nextplayer(currentPlayer);
        }

        //System.out.println("Spieler " + (playerwon+1) + " hat gewonnen");
        soundengine.playFanfare();
        view.showWinner(playerwon);
    }

    /**
     * Hilfsmethode, die spielStarten() den naechsten Spieler übergibt
     * 
     * @param p Der vorherige Spieler
     * @return Der naechste Spieler
     */
    private int nextplayer(int p){
        switch (p){
            case 1: return 2;
            case 2: return 1;
        }
        return 0;
    }

    /**
     * Hilsmethode fuer spielStarten(), die zufällig 1 oder 0 zurückgibt
     * 
     * @return 0 oder 1
     */
    private int randomtwo(){
        double a=Math.random();
        if(a>0.5){
            return 2;
        }
        return 1;
    }

    public boolean spielsteinSetzen(int x, int spieleramzug)
    {
        boolean geklappt = spielfeld.spielSteinSetzen(x, spieleramzug);
        if (geklappt)
        {
            view.showPreview(false);
            spielfeld.entferneOberstenStein(x);
            int y = spielfeld.freiesFeld(x);
            STEIN_ANIMATION_THREAD t = new STEIN_ANIMATION_THREAD(x, y, spieleramzug, view);
            t.run();
            spielfeld.spielSteinSetzen(x, spieleramzug);
            soundengine.playClick();
            view.repaint();
        }
        return geklappt;
    }

    public void testModelAndView() {
        //System.out.println("Teste Model und Ausgabe");
        view.repaint();
        view.printOutToConsole();       

        //System.out.println("Fülle zufällig mit 10 Steinen");

        fillRandomly(10);

        view.repaint();
        view.printOutToConsole();       
    }

    public VIEW getView()
    {
        return view;
    }
    
    public void alleSteinefallen(){
        boolean[][] check = spielfeld.besetzt();
        STEIN_ANIMATION_THREAD[][] t = new STEIN_ANIMATION_THREAD[check.length][check[0].length];
        for (int i1=0; i1<check.length; i1++){
            for (int i2=0; i2<check[i1].length; i2++){
                t[i1][i2]= new STEIN_ANIMATION_THREAD(i1, i2, spielfeld.getSpielfeld()[i1][i2], view);
                t[i1][i2].run2();
            }
        }
    }

    public void reset() {
        //alleSteinefallen();
        spielfeld.leereSpielfeld();
        view.showWinner = false;
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