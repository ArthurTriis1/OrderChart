package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    

        private List<Integer> numbers;
        private String aparence = "Bars";

        public String getAparence() {
            return aparence;
        }

        public void changeAparence(String aparence) {
            this.aparence = aparence;
            repaint();
        }
    
        MyPanel(int range){
            super();

            this.numbers  = IntStream.range(1, range+1).boxed().collect(Collectors.toList());
        }
        
        private int range(){
           return this.numbers.size();
        }
        
        public void makeArray(int range){
            this.numbers  = IntStream.range(1, range+1).boxed().collect(Collectors.toList());
            Collections.shuffle(this.numbers);
            paintComponent(getGraphics());  
            
        }
        
        public void bubbleSort(int delay){
           
                    //Bubble Sort
                    for(int i=0; i < numbers.size(); i++) {
                      for(int j=i+1; j < numbers.size(); j++) {
                          Integer f1 =numbers.get(i);

                          Integer f2 =numbers.get(j);


                          refreshWithColor(getGraphics(), i, j);
                          try { Thread.sleep (delay); } catch (InterruptedException ex) {}

                          if(f1.compareTo(f2) > 0) {
                            numbers.set(i, f2);
                            numbers.set(j, f1);


                          }

                        paintComponent(getGraphics());  

                      }

                    }
            
          
        }
        
        public void insertionSort(int delay){
            
            
          
                //Inner sort
                for (int j = 1; j < range(); j++) {
                     int current = numbers.get(j);
                     int i = j-1;

                     while ((i > -1) && (numbers.get(i) > current)) {

                         
                         try { Thread.sleep (delay); } catch (InterruptedException ex) {}


                         int tst = numbers.get(i);
                         
                         numbers.set(i, numbers.get(i+1));
                         numbers.set(i+1, tst);
                         refreshWithColor(getGraphics(), i, i-1);

                         i--;


                     }


                     numbers.set(i+1, current);


                 }
                
                
                paintComponent(getGraphics());  
                
               
        }
        
        
        private void refreshWithColor(Graphics g, int k, int j){
            
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            int outerSpace = 5;
            int innerSpace = 1;
            int QTD = this.range();
            int width = (this.getWidth() - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
            int height = (this.getHeight() - 2 * outerSpace) / QTD;
            
            switch(this.aparence){
                case "Bars":
                    for(int i = 0; i< QTD; i++) {
                    if(i == k || i == j){
                        g.setColor(Color.red);
                    }else{
                        g.setColor(new Color(50, ((255/this.range())*this.numbers.get(i) ), (255 - ((255/this.range())*this.numbers.get(i) ))));
                    }
                    int n = this.numbers.get(i);
                    g.fillRect(i * (width + innerSpace) + outerSpace, this.getHeight() - n * height - outerSpace, width, n * height);
                    }
                    break;
                    

                case "Line":
                    
                    g.setColor(Color.white);
                    int[] x = new int[QTD];
                    int[] y = new int[QTD];

                    for (int idx = 0; idx < QTD; idx++) {
                        int n = numbers.get(idx);
                        x[idx] = idx * (width + innerSpace) + outerSpace;
                        y[idx] = this.getHeight() - n * height - outerSpace;
                    }

                    g.drawPolyline(x, y, QTD);
                    break;
            
            } 
            
        }
	
	@Override
	protected void paintComponent(Graphics g) {
		refreshWithColor(g, -1, -1);
		
	}
        
        
}