package MyKeyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class WordPractice extends Practice{
	private String[] arr;
	private boolean flg = false;
	private int count = 0;
	private Random random;
	
	public void importPractice()
	{
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("file/wordPractice.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
              list.add(line);
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
		arr = list.toArray(new String[0]);
        
	}
	
	
	public WordPractice()
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
	        	if(target.getText().equals(input.getText()))
	        	{
	        		correct = correct + 1;
	        	}

	    		random = new Random();
	    		if(count == 20)
	    		{
	    			thread.interrupt();
	    			if(count != 0 )
		        	{
			        	correctPercent.setText(Integer.toString(correct * 100 / count)+ "%");
		        	}
		        	if(!realTime.getText().equals("0"))
		        	{
		        		wpm.setText(Integer.toString(correct*60 / Integer.parseInt(realTime.getText())));
		        	}
	    			String[] answer={"restart", "main"};
	    			String message= "time : " + timerLabel.getText() + "\ncorrect : " + correct * 100 / count + "%" + "\nWPM : " + Integer.toString(correct*60 / Integer.parseInt(realTime.getText())) +"\n\nrestart?";
	    			int res = JOptionPane.showOptionDialog(null, message, "Option"
	    					,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
	    			if(res == JOptionPane.YES_OPTION)
	    			{
	    				dispose();
	    				new WordPractice();
	    			}
	    			else
	    			{
	    				dispose();
	    				new KeyboardMain();
	    			}
	    			
	    		}
	    		countnum.setText(Integer.toString(count+1)+" / 20");
	        	if(count != 0 )
	        	{
		        	correctPercent.setText(Integer.toString(correct * 100 / count)+ "%");
	        	}
	        	if(!realTime.getText().equals("0"))
	        	{
	        		wpm.setText(Integer.toString(correct*60 / Integer.parseInt(realTime.getText())));
	        	}
	    		int n = random.nextInt(arr.length);
	    		prevTarget.setText(target.getText());
	    		target.setText(arr[n]);
	    		prevInput.setText(input.getText());
	    		input.setText("");
	    		count = count + 1;
	        }
	    });
	    
		setTitle("Word Practice");
		importPractice();
		countnum.setText("0 / 20");
	}

}
