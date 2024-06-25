package MyKeyboard;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class LongPractice extends Practice{
	private String[] arr;
	private boolean flg = false;
	private int count = 0;
	private int letterNum = 0;
	
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
	
	public LongPractice(String s)
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
	        	}
	    		if(count == arr.length)
	    		{
	    			thread.interrupt();
	    			String[] answer={"restart", "main"};
	    			String message= "time : " + timerLabel.getText() + "\ncorrect : " + correct * 100 / count + "%" + "\nWPM : " + Integer.toString(letterNum*60 / Integer.parseInt(realTime.getText())) +"\n\nrestart?";
	    			int res = JOptionPane.showOptionDialog(null, message, "Option"
	    					,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
	    			if(res == JOptionPane.YES_OPTION)
	    			{
	    				dispose();
	    				new LongPractice(s);
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
	    
		setTitle("Word Practice");
		importPractice(s);
		prevInput.setFont(new Font("Arial",Font.PLAIN,24));
		input.setFont(new Font("Arial",Font.PLAIN,24));
		prevTarget.setFont(new Font("Arial",Font.PLAIN,24));
		target.setFont(new Font("Arial",Font.PLAIN,24));
		countnum.setText("0 / " + Integer.toString(arr.length));
	}

}
