import javax.swing.JFrame;

public abstract class SPIELER
{
    public SPIELER()
    {

    }

    public void VerarbeiteGegnerischenZug(int gegnerischerSpieler)
    {

    }

    public abstract int getNextMove();

    public abstract boolean isHuman();

    public void activateListener(JFrame f) {
    }
}
