import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border; import javax.swing.*;

public class MENUE extends JFrame {
    // Deklariert Variablen für Schaltflächen und das Panel an sich
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel1;

    public MENUE() {
        // Die hier gesetzten Zeichenketten erscheinen auf dem grafischen Menü
        String fenstertitel = "VIER GEWINNT!";
        String caption = "Vier gewinnt!";
        String mode1 = "Einzelspieler";
        String mode2 = "Mehrspieler";
        String mode3 = "Netzwerkspiel";
        String mode4 = "Spieler 1";
        String mode5 = "Spieler 2";
        
        // Die hier gesetzten Zahlenwerte werden an den Obercontroller weitergegeben
        int spmode1 = 2;       // Mensch gegen Computerspieler
        int spmode2 = 1;       // Mensch gegen Mensch
        int spmode4 = 3;
        int spmode5 = 4;
        
        // Legt den Titel des Fensters fest
        this.setTitle(fenstertitel);
        this.setSize(500,400);

        //Panel ohne Layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(0,0,0));
        
        // Definiert das Label für Überschrift
        label1 = new JLabel();
        label1.setBounds(144,30,189,30);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(255,255,255));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",1,30));
        label1.setText(caption);
        label1.setVisible(true);

        // Definiert das Label für Modus 1
        label2 = new JLabel();
        label2.setBounds(170,116,131,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(255,255,255));
        label2.setEnabled(true);
        label2.setFont(new Font("SansSerif",1,20));
        label2.setText(mode1);
        label2.setVisible(true);

        // Definiert das Label für Modus 2
        label3 = new JLabel();
        label3.setBounds(175,149,131,50);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(255,255,255));
        label3.setEnabled(true);
        label3.setFont(new Font("SansSerif",1,20));
        label3.setText(mode2);
        label3.setVisible(true);

        // Definiert das Label für Modus 3
        label4 = new JLabel();
        label4.setBounds(168,205,152,30);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(255,255,255));
        label4.setEnabled(true);
        label4.setFont(new Font("SansSerif",1,20));
        label4.setText(mode3);
        label4.setVisible(true);

        // Definiert das Label für Modus 4
        label5 = new JLabel();
        label5.setBounds(201,245,90,35);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(255,255,255));
        label5.setEnabled(true);
        label5.setFont(new Font("SansSerif",1,16));
        label5.setText(mode4);
        label5.setVisible(true);

        // Definiert das Label für Modus 5
        label6 = new JLabel();
        label6.setBounds(201,285,90,35);
        label6.setBackground(new Color(214,217,223));
        label6.setForeground(new Color(255,255,255));
        label6.setEnabled(true);
        label6.setFont(new Font("SansSerif",1,16));
        label6.setText(mode5);
        label6.setVisible(true);

        // Definiert das Panel
        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(165,105,150,100);
        panel1.setBackground(new Color(214,217,223));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(false);

        // Fügt die Labels dem Panel ohne Layout hinzu
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(panel1);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        // Mouse Listener für Modus 1
        label2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int size = 75;

                Runnable spielRunnable = new SPIELTHREAD(size, spmode1, "localhost", 2000);
                Thread spielThread = new Thread(spielRunnable);
                spielThread.start();
            }

        });
        
        // Mouse Listener für Modus 2
        label3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int size = 75;

                Runnable spielRunnable = new SPIELTHREAD(size, spmode2, "localhost", 2000);
                Thread spielThread = new Thread(spielRunnable);
                spielThread.start();
            }

        });

    }
    
    // main-Methode
    public static void main(String[] args) {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MENUE();
                }
            });

    }
}