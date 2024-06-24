package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Graph extends JPanel{
	private ArrayList<Integer> arr;
    public Graph(ArrayList<Integer> arr) {
    	this.arr = arr;
   		for(int i = 0 ; i < 5; i++)
		{
			JTextField label = new JTextField(Integer.toString(i*50));
			label.setLocation(3,180 - i*50);
			label.setFont(new Font("Arial",Font.BOLD,10));
			label.setSize(20,10);
			label.setBorder(null);
			label.setBackground(null);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setEditable(false);
			this.add(label);
		}
   	 
    };

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        if(arr == null)
        {
        	
            g.drawLine(20, getHeight() - 20, getWidth() - 20, getHeight() - 20);
            g.drawLine(20, 20, 20, getHeight() - 20);
            return;
        }
 
        g.drawLine(20, getHeight() - 20, getWidth() - 20, getHeight() - 20);
        g.drawLine(20, 20, 20, getHeight() - 20);

        g.setColor(Color.RED);
        int count = 0;
        int idx = 0;
        if(arr.size()>39)
        {
        	idx = arr.size() - 39;
        }
 
        for (int i = arr.size() - 1; i > idx; i--) {
        	
        	
        	
            int x1 = getWidth() - 20 - count*20;
            int y1 = getHeight() -20 - arr.get(i);
            count++;
            int x2 = getWidth() - 20 - count*20;
            int y2 = getHeight() - 20 - arr.get(i-1);
            
            g.drawLine(x1, y1, x2, y2);
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }
        
        
    }
}
