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

}
