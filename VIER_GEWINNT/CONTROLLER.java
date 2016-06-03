import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
/**
 * Die Klasse CONTROLLER beschreibt die Steuerung des Spiels
 * legt ein Spielfeld und ein View an
 * @author (kinder)
 * 
 */

public class CONTROLLER
{
    SPIELFELD spielfeld;    //Referenz auf das Spielfeld
    VIEW view;              //Referenz auf die Darstellung
    SOUNDENGINE soundengine;
    int spieleramzug;
    JFrame frameView = new JFrame("Vier gewinnt!");
    
    public CONTROLLER()
    {
        spielfeld = new SPIELFELD();    //Neues Spielfeld
        view = new VIEW(spielfeld);     //Neuer View

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
                spielfeld.spielSteinSetzen((int)(Math.random()*7), 1);
            }else
            {
                spielfeld.spielSteinSetzen((int)(Math.random()*7), 2);
            }
        }
    }

    /**
     * Startet das Spiel und fragt die Spieler abwechselnd nach ihren Zügen, bis einer gewonnen hat
     */
    public void spielStarten(){

        SPIELER[] players = new SPIELER[2];
        players[0]=new COMPUTERSPIELER();
        players[1]=new HUMANSPIELER();
        int player=randomtwo();
        int lastx=0;
        int playerwon=3;
        while ((playerwon!=0) && (playerwon!=1)){
            player = nextplayer(player);
            int i = 0;
            boolean a = false;
            while((!a) && (i!=3)){
                lastx = players[player].getNextMove();
                a = spielfeld.spielSteinSetzen(lastx,player+1);
                i++;
                if(i == 3){
                    playerwon = nextplayer(player);
                }
            }
            
            if(spielfeld.checkFourInARow(player+1,lastx,spielfeld.freiesFeld(lastx)-1)){
                playerwon = nextplayer(player);
            }
            view.printOutToConsole();
            //hier kann auch auf View gepainted werden
        }
        System.out.println("Spieler " + (playerwon+1) + " hat gewonnen");
    }

    /**
     * Hilfsmethode, die spielStarten() den naechsten Spieler übergibt
     * 
     * @param p Der vorherige Spieler
     * @return Der naechste Spieler
     */
    private int nextplayer(int p){
        switch (p){
            case 0: return 1;
            case 1: return 0;
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
            return 0;
        }
        return 1;
    }

    public boolean spielsteinsetzen(int x)
    {
        boolean geklappt = spielfeld.spielSteinSetzen(x, spieleramzug);
        if (geklappt)
        {
            soundengine.playClick();
        }
        return geklappt;

    }

    public void testModelAndView() {
        System.out.println("Teste Model und Ausgabe");
        view.repaint();
        view.printOutToConsole();       

        System.out.println("Fülle zufällig mit 10 Steinen");

        fillRandomly(10);

        view.repaint();
        view.printOutToConsole();       
    }

}
