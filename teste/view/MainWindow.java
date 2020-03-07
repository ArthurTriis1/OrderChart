package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private Integer flagThread = 0;

	public MainWindow() {
		super("Ordenação");
                
		setPreferredSize(new Dimension(900, 900));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
                
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titulo = new JLabel("Ordenação");
		titulo.setFont(new Font("Serif", Font.BOLD, 24));
		topPanel.add(titulo);
		topPanel.setMaximumSize(new Dimension(300, 300));
		add(topPanel);
                
                JPanel configPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                configPanel.setPreferredSize(new Dimension(200,80));
                JLabel lbRange = new JLabel("Range: ");
                JTextField tfRange = new JTextField();
                tfRange.setPreferredSize(new Dimension(60, 20));
                JLabel lbDelay = new JLabel("Delay: ");
                JTextField tfDelay = new JTextField();
                tfDelay.setPreferredSize(new Dimension(60, 20));
                JButton btGerarArray = new JButton("Gerar array");
                JButton btOrdenar = new JButton("Ordenar");
                JButton btParar = new JButton("Parar");
                btParar.setEnabled(false);
                
                String[] itensAparences = new String[2];
                itensAparences[0] =  "Bars";
                itensAparences[1] =  "Line";
                JComboBox cbOptAparences = new JComboBox(itensAparences);
                
                
                
                String[] itensOrders = new String[2];
                itensOrders[0] = "SelectionSort";
                itensOrders[1] = "InsertionSort";
                JComboBox cbOptOrders = new JComboBox(itensOrders);
               
                
                configPanel.add(lbRange);
                configPanel.add(tfRange);
		configPanel.add(lbDelay);
                configPanel.add(tfDelay);
                configPanel.add(cbOptOrders);
                configPanel.add(cbOptAparences);
                configPanel.add(btGerarArray);
                configPanel.add(btOrdenar);
                //configPanel.add(btParar);
                
                configPanel.setMaximumSize(new Dimension(900, 900));
                add(configPanel);
                
                MyPanel pCanvas = new MyPanel(100);
		add(pCanvas);
                
                cbOptAparences.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        String option = cbOptAparences.getSelectedItem().toString();
                        pCanvas.changeAparence(option);
                         
                    }
                
                });
                
                
                btGerarArray.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            pCanvas.makeArray(Integer.parseInt(tfRange.getText()));
                        }catch(java.lang.NumberFormatException ex){
                            tfRange.setText("100");
                            pCanvas.makeArray(Integer.parseInt(tfRange.getText()));
                        }
                        
                   }
                 });
                
                 
                 
                
                
                
                
                btOrdenar.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        flagThread = 0;
                        
                        
                        
                        Thread ordenar = new Thread(new Runnable() {
                        @Override
                        public void run(){

                            btGerarArray.setEnabled(false);
                            btOrdenar.setEnabled(false);
                            cbOptOrders.setEnabled(false);
                            btParar.setEnabled(true);

                            int delay = 100;
                            try{
                                delay = Integer.parseInt(tfDelay.getText());
                            }catch(java.lang.NumberFormatException ex){
                                tfDelay.setText("100");
                                delay =  Integer.parseInt(tfDelay.getText());
                            }finally{
                                switch(cbOptOrders.getSelectedItem().toString()){
                                    case "SelectionSort":
                                        pCanvas.bubbleSort(delay);
                                        break;
                                    case "InsertionSort":
                                        pCanvas.insertionSort(delay);
                                        break;
                                }
                            }
                            btGerarArray.setEnabled(true);
                            btOrdenar.setEnabled(true);
                            cbOptOrders.setEnabled(true);
                            btParar.setEnabled(false);

                        }// end run()

                     });//ende thread
                        

                        ordenar.start();

                   }
                });
                
                
                btParar.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        
                        flagThread = 1;
                        btGerarArray.setEnabled(true);
                        btOrdenar.setEnabled(true);
                        btParar.setEnabled(false);

                   }
                });
                
		
		pack();
	}
	
	public static void main(String[] args) {
		MainWindow w = new MainWindow();
	}
}
