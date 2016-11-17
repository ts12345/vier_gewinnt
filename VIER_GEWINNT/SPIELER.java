import javax.swing.JFrame;
 

public abstract class SPIELER
{
    public SPIELER()
    {

    }

    public abstract int getNextMove();

    public abstract boolean isHuman();

    public void activateListener(JFrame f) {
    }
}
