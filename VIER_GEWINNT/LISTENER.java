import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class LISTENER implements MouseListener
{
    private CONTROLLER controller;
    private static CLICKABLE clicker;
    
    public LISTENER(CONTROLLER controller)
    {
        this.controller = controller;
    }
    
    
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
        clicker.clicked(e);
    }
    public void mouseDragged(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    
    public static void startListening(CLICKABLE clicker)
    {
        LISTENER.clicker = clicker; 
    }
}
