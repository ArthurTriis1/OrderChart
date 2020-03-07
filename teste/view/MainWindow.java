package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainWindow extends JFrame {

    MyPanel pCanvas;
    JSpinner sQuantity;
    
    private void createWindow() {
        this.setPreferredSize(new Dimension(618, 726));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("Ordena��o");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(titulo);
        topPanel.setMaximumSize(new Dimension(640, 50));
        this.add(topPanel);
        
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sQuantity = new JSpinner(new SpinnerNumberModel(25, 1, 2000, 1));
        sQuantity.setToolTipText("Quantity of numbers to sort");
        middlePanel.add(sQuantity);
        JButton bShuffle = new JButton("Shuffle");
        
        
        
        bShuffle.addActionListener(ae -> pCanvas.shuffle());
        middlePanel.add(bShuffle);
        middlePanel.setMaximumSize(new Dimension(640, 50));
        this.add(middlePanel);

        pCanvas = new MyPanel(this);
        this.add(pCanvas);

        this.pack();
    }

    public JSpinner getsQuantity() {
        return sQuantity;
    }
    
    public MainWindow() {
        super("Sorting Algorithms");
    }
    
    public static void main(String[] args) {
        MainWindow w = new MainWindow();
        w.createWindow();
    }
}
