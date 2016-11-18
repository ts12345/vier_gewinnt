import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class LISTENER implements MouseMotionListener
{
    private CONTROLLER controller;
    static int lastX;
    static int lastY;
    private VIEW view;

    public LISTENER(CONTROLLER controller)
    {
        this.controller = controller;
        view = controller.getView();
    }

    public void mouseExited(MouseEvent e)
    {
        view.showPreview(false);
    }

    public void mouseMoved(MouseEvent e)
    {
       //System.out.println(e.getX());
       view.setSpalte(e.getX());
       view.repaint();
    }

    public void mouseClicked(MouseEvent e)
    {

        lastX = e.getX();
        lastY = e.getY();
        //System.out.println("Click " + lastX + " " + lastY);
    }

    public void mouseDragged(MouseEvent e){}

    public void mouseEntered(MouseEvent e){view.showPreview(true);}

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
