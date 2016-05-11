import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

/**
 * Beschreiben Sie hier die Klasse TEST_VIEW.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class TEST_VIEW
{
    VIEW test_view;
    public TEST_VIEW()
    {
        SPIELFELD s = new SPIELFELD();
        test_view = new VIEW(s);

        JFrame frameView = new JFrame("Vier gewinnt!");
        frameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        frameView.add(test_view);

        frameView.pack();
        frameView.setVisible(true);
    }

}
