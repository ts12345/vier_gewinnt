import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class LISTENER implements MouseListener
{
    private CONTROLLER controller;
    static int lastX;
    static int lastY;
    
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
        //System.out.println("Click");
        lastX = e.getX();
        lastY = e.getY();
    }
    public void mouseDragged(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    
    public static void startListening()
    {
        lastX = 0;
        lastY = 0;
    }
    
    public static int getLastX()
    {
        return lastX;
    }
    
    public static int getLastY()
    {
        return lastY;
    }
}
