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

    public CONTROLLER()
    {
        spielfeld = new SPIELFELD();    //Neues Spielfeld
        view = new VIEW(spielfeld,this);     //Neuer View
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
    public void spielStarten(){
        int pause = 0;
        SPIELER[] players = new SPIELER[2];

        players[0] = new COMPUTERSPIELER_TS(spielfeld);
        players[1] = new HUMANSPIELER(frameView);

        int player=randomtwo();
        int lastx=0;
        int playerwon=4;
        while ((playerwon!=2) && (playerwon!=1)){
            player = nextplayer(player);
            int i = 0;
            boolean a = false;

            if(players[player-1].isHuman())
            {
                view.showPreview(true);
                pause = 0;
            }

            else
            {
                view.showPreview(false);
                pause = 1000;
            }

            while((!a) && (i!=3)){
                lastx = players[player-1].getNextMove();
                try {
                    Thread.sleep(pause); 
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                spieleramzug=player;
                a = spielsteinSetzen(lastx,player);
                i++;
                if(i == 3){
                    playerwon = nextplayer(player);
                }
            }

            if(spielfeld.checkFourInARow(player,lastx,spielfeld.freiesFeld(lastx)-1)){
                playerwon = player;
            }
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
            case 2: return 1;
            case 1: return 2;
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

    public void reset() {
        spielfeld.leereSpielfeld();
        view.showWinner = false;
    }

}
