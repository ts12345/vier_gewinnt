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

    public CONTROLLER()
    {
        spielfeld = new SPIELFELD();    //Neues Spielfeld
        view = new VIEW(spielfeld);     //Neuer View
        TEST_VIEW v = new TEST_VIEW();

        //spielfeld.spielSteinSetzen(1, 1);
        //spielfeld.spielSteinSetzen(1, 2);
        view.printOutToConsole();

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
       
        COMPUTERSPIELER[] players = new COMPUTERSPIELER[2];
        players[0]=new COMPUTERSPIELER();
        players[1]=new COMPUTERSPIELER();
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
                playerwon = player;
            }
            view.printOutToConsole();
        }
        System.out.println("Spieler " + (player+1) + " hat gewonnen");
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
}
