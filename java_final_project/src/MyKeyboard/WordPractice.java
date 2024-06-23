package MyKeyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
	        	if(count != 0 )
	        	{
	        		correctCount.setText(Integer.toString(correct));
		        	correctPercent.setText(Integer.toString(correct * 100 / count));
		        	wpm.setText(Integer.toString(correct*60 / Integer.parseInt(realTime.getText())));
	        	}
	        	
	        	count = count + 1;
	    		random = new Random();
	    		if(count > 20)
	    		{
	    			thread.interrupt();
	    		}
	    		int n = random.nextInt(arr.length);
	    		prevTarget.setText(target.getText());
	    		target.setText(arr[n]);
	    		prevInput.setText(input.getText());
	    		input.setText("");
	    		
	        }
	    });
	    
		setTitle("Word Practice");
		importPractice();
	}

}
