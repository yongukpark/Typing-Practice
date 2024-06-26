package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LongPractice extends Practice{
	private String[] arr;
	private boolean flg = false;
	private int count = 0;
	private int letterNum = 0;
	private JLabel character = new JLabel();
	private JLabel heart1;
	private JLabel heart2;
	private ImageIcon grayImg = null;
	private ImageIcon colorImg = null;
	
	public void importPractice(String s)
	{
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            String line;
            while ((line = br.readLine()) != null) {
              list.add(line);
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
		arr = list.toArray(new String[0]);
        
	}
	
	public void setImg(String s)
	{
		BufferedImage img = null;
		try {
			img= ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}     
    	Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    	colorImg = new ImageIcon(updateImg);
        character.setIcon(new ImageIcon(updateImg));
		character.setSize(200,200);
		character.setLocation(250,20);
		character.setOpaque(true);
		panel.add(character);
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int Y = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				img.setRGB(x, y, new Color(Y, Y, Y).getRGB());
			}
		}
		updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		grayImg = new ImageIcon(updateImg);
	}
	
	public void setHeart()
	{
		ImageIcon icon = new ImageIcon("image/heart.png");
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(updateImg);
		heart1 = new JLabel(icon);
		heart1.setLocation(230, 20);
		heart1.setSize(50, 50);
		panel.add(heart1);
		heart2 = new JLabel(icon);
		heart2.setLocation(430, 20);
		heart2.setSize(50, 50);
		panel.add(heart2);
		heart1.setVisible(false);
		heart2.setVisible(false);
	}
	
	public LongPractice(String s, String ch)
	{
		super();
		
	    input.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(flg==false)
	        	{
	        		thread.start();
	        		flg = true;
	        	}
	        	StringTokenizer st = new StringTokenizer(target.getText());
	        	StringTokenizer st2 = new StringTokenizer(input.getText());
	        	int len;
	        	if(st.countTokens() < st2.countTokens())
	        	{
	        		len = st.countTokens();
	        	}
	        	else {
					len = st2.countTokens();
				}
	        	for(int i = 0 ; i < len ; i++)
	        	{
	        		if(st.nextToken().equals(st2.nextToken())) letterNum++;
	        	}
	     
	        	if(target.getText().equals(input.getText()))
	        	{
	        		correct = correct + 1;
	        		character.setIcon(colorImg);
	        		if(correct != 0)
	        		{
	        			heart1.setVisible(true);
		        		heart2.setVisible(true);
	        		}
	        	}
	        	else {
	        		character.setIcon(grayImg);
	        		heart1.setVisible(false);
	        		heart2.setVisible(false);
				}
	        	
	    		if(count == arr.length)
	    		{
	    			thread.interrupt();
	    			if(count != 0 )
		        	{
			        	correctPercent.setText(Integer.toString(correct * 100 / count) + "%");
			        	
		        	}
		        	if(!realTime.getText().equals("0"))
		        	{
		        		wpm.setText(Integer.toString(letterNum*60 / Integer.parseInt(realTime.getText())));
		        	}
	    			String[] answer={"restart", "main"};
	    			String message= "time : " + timerLabel.getText() + "\ncorrect : " + correct * 100 / count + "%" + "\nWPM : " + Integer.toString(letterNum*60 / Integer.parseInt(realTime.getText())) +"\n\nrestart?";
	    			int res = JOptionPane.showOptionDialog(null, message, "Option"
	    					,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
	    			if(res == JOptionPane.YES_OPTION)
	    			{
	    				dispose();
	    				new LongPractice(s,ch);
	    			}
	    			else
	    			{
	    				dispose();
	    				new KeyboardMain();
	    			}
	    		}
	    		countnum.setText(Integer.toString(count + 1) +" / " + Integer.toString(arr.length));
	        	if(count != 0 )
	        	{
		        	correctPercent.setText(Integer.toString(correct * 100 / count) + "%");
		        	
	        	}
	        	if(!realTime.getText().equals("0"))
	        	{
	        		wpm.setText(Integer.toString(letterNum*60 / Integer.parseInt(realTime.getText())));
	        	}
	    		prevTarget.setText(target.getText());
	    		target.setText(arr[count]);
	    		prevInput.setText(input.getText());
	    		input.setText("");
	    		count = count + 1;
	    		
	        }
	    });
	    setHeart();
	    setImg(ch);
		setTitle("Long Practice");
		importPractice(s);
		prevInput.setFont(new Font("Arial",Font.PLAIN,24));
		input.setFont(new Font("Arial",Font.PLAIN,24));
		prevTarget.setFont(new Font("Arial",Font.PLAIN,24));
		target.setFont(new Font("Arial",Font.PLAIN,24));
		countnum.setText("0 / " + Integer.toString(arr.length));
	}

}
