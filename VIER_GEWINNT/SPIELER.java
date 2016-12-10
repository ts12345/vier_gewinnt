import javax.swing.JFrame;

public abstract class SPIELER {
    public abstract int getNextMove();
    public abstract boolean isHuman();
    
    public SPIELER() { }
    public void activateListener(JFrame f) { }
    public void VerarbeiteGegnerischenZug(int gegnerischerSpieler) { }   
}