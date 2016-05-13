/**
 * Die Klasse CONTROLLER beschreibt die Steuerung des Spiels
 * legt ein Spielfeld und ein View an
 * @author (kinder)
 * 
 */

public class CONTROLLER
{
    SPIELFELD spielfeld;
    VIEW view;

    public CONTROLLER()
    {
        spielfeld = new SPIELFELD();
        view = new VIEW(spielfeld);

        spielfeld.spielSteinSetzen(1, 1);
        spielfeld.spielSteinSetzen(1, 2);
        view.printOutToConsole();

    }

    public static void main(String[] args)
    {
        CONTROLLER c = new CONTROLLER();
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

    public void spielStarten(){
        COMPUTERSPIELER[] players=new COMPUTERSPIELER[2];
        players[0]=new COMPUTERSPIELER();
        players[1]=new COMPUTERSPIELER();
        int player=(int)Math.random()*2;
        int lastx=0;
        int playerwon=3;
        boolean a=false;
        while (playerwon!=0&&playerwon!=1){
            player=nextplayer(player);
            int i=0;
            while(!a||i!=3){
                lastx=players[player].getNextMove();
                a = spielfeld.spielSteinSetzen(lastx,player+1);
                i++;
                if(i==3){
                    playerwon=nextplayer(player)+1;
                }
            }

            if(spielfeld.checkFourInARow(player+1,lastx,spielfeld.freiesFeld(lastx)-1)){
                playerwon=player;
            }
            view.printOutToConsole();
        }
        System.out.println("Spieler "+(player+1)+" hat gewonnen");
    }

    private int nextplayer(int p){
        switch (p){
            case 0: return 1;
            case 1: return 0;
        }
        return 0;
    }
}
