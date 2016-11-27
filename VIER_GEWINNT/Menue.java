
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
import javax.swing.border.Border;
import javax.swing.*;

public class Menue extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel1;

    //Constructor 
    public Menue(){

        this.setTitle("Menue");
        this.setSize(500,400);
        //menu generate method

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(0,0,0));

        label1 = new JLabel();
        label1.setBounds(144,30,189,30);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(255,255,255));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",1,30));
        label1.setText("Vier Gewinnt");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(201,116,90,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(255,255,255));
        label2.setEnabled(true);
        label2.setFont(new Font("SansSerif",1,20));
        label2.setText("1 vs. 1");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(174,149,131,50);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(255,255,255));
        label3.setEnabled(true);
        label3.setFont(new Font("SansSerif",1,20));
        label3.setText("Single Player");
        label3.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(168,205,152,30);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(255,255,255));
        label4.setEnabled(true);
        label4.setFont(new Font("SansSerif",1,20));
        label4.setText("Network Game");
        label4.setVisible(true);

        label5 = new JLabel();
        label5.setBounds(201,245,90,35);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(255,255,255));
        label5.setEnabled(true);
        label5.setFont(new Font("SansSerif",1,16));
        label5.setText("Player 1");
        label5.setVisible(true);

        label6 = new JLabel();
        label6.setBounds(201,285,90,35);
        label6.setBackground(new Color(214,217,223));
        label6.setForeground(new Color(255,255,255));
        label6.setEnabled(true);
        label6.setFont(new Font("SansSerif",1,16));
        label6.setText("Player 2");
        label6.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(165,105,150,100);
        panel1.setBackground(new Color(214,217,223));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(false);

        //adding components to contentPane panel
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

        label2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    int size = 100;
                    int sp1 = 1;
                    int sp2 = 1;

                    System.out.println("starte spiel 1 vs 1");

                    Runnable spielRunnable = new SPIELTHREAD(size, sp1, sp2);
                    Thread spielThread = new Thread(spielRunnable);
                    spielThread.start();

                    System.out.println("gestartet! spiel 1 vs 1");

                }

            });

    }
    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Menue();
                }
            });

    }

}