package MyKeyboard;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.PriorityQueue;
	
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SharePackage.MyPanel;


public class ChallengeReport extends JFrame {
	private MyPanel panel = new MyPanel();
	private JLabel correctPercent = new JLabel();
	private JLabel wpmLabel = new JLabel();
	private JLabel wpm;
	private int count = 0;
	private int correct = 0;
	private JPanel context = new JPanel();
	private int [][]report = new int [26][26];
	private String name;
	
	public void setWindow() {
		setTitle("Challenge Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}


	
	public void homeButton()
	{
		
        ImageIcon icon = new ImageIcon("image/home.png");
        Image img = icon.getImage();
        
		Image updateImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(updateImg);
		JButton button = new JButton(icon);
        button.setLocation(1050,50);
        button.setSize(50,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				dispose();
				new KeyboardMain();
            }

        });

        panel.add(button);
	}
	
	class Pair implements Comparable<Pair> {
	    int first, second;
	    int elem;
	    Pair(int first, int second, int elem) {
	        this.first = first;
	        this.second = second;
	        this.elem = elem;
	    }
	    
	    public int compareTo(Pair p) {
	        if(this.elem < p.elem) {
	            return -1; 
	        }
	        else if(this.elem == p.elem) {
	            if(this.first > p.first) {
	                return -1;
	            }
	        }
	        else if(this.first == p.first)
	        {
	        	if(this.second > p.second)
	        	{
	        		return -1;
	        	}
	        }
	        return 1;
	    }
	}

		
	void setLabel()
	{
		
		PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		correctPercent.setLocation(900,200);
		correctPercent.setSize(200,100);
		correctPercent.setFont(new Font("Arial",Font.BOLD,30));
		correctPercent.setText("Correct(%)");
		panel.add(correctPercent);
		
		wpmLabel.setLocation(940,400);
		wpmLabel.setSize(200,100);
		wpmLabel.setFont(new Font("Arial",Font.BOLD,30));
		wpmLabel.setText("WP	M");
		panel.add(wpmLabel);
		
		JLabel correctVal = new JLabel();
		correctVal.setLocation(950,300);
		correctVal.setSize(200,100);
		correctVal.setFont(new Font("Arial",Font.PLAIN,30));
		if(count == 0)
		{
			correctVal.setText("0%");
		}
		else {
			correctVal.setText(correct * 100 / count + "%");
		}
		panel.add(correctVal);
		
		wpm.setSize(200,100);
		wpm.setLocation(965,500);
		wpm.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(wpm);
		
		JLabel underLineJLabel = new JLabel();
		underLineJLabel.setText("____________________________________");
		underLineJLabel.setLocation(850,350);
		underLineJLabel.setSize(300,100);
		panel.add(underLineJLabel);
		
		for(int i = 0 ; i < 26 ; i++)
		{
			for(int j = 0 ; j < 26 ; j++)
			{
				if(report[i][j] != 0 && i != j)
				{
					pq.add(new Pair(i, j, report[i][j]));
				}
			}
		}
		JLabel title = new JLabel();
		title.setLocation(350,30);
		title.setSize(400,50);
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setText("Error Analysis");
		panel.add(title);
		
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		label1.setLocation(80, 40);
		label2.setLocation(270,40);
		label3.setLocation(400,40);
		
		label1.setSize(150,20);
		label2.setSize(150,20);
		label3.setSize(150,20);
		
		label1.setFont(new Font("Arial",Font.BOLD,20));
		label2.setFont(new Font("Arial",Font.BOLD,20));
		label3.setFont(new Font("Arial",Font.BOLD,20));
		
		label1.setText("Computer");
		label2.setText("User");
		label3.setText("Error Count");

		context.add(label1);
		context.add(label2);
		context.add(label3);
		int idx = 0;
		while(!pq.isEmpty())
		{
			JLabel firstLabel = new JLabel();
			JLabel secondLabel = new JLabel();
			JLabel elemLabel = new JLabel();
			
			firstLabel.setLocation(120, 80+30*idx);
			secondLabel.setLocation(280,80+30*idx);
			elemLabel.setLocation(440,80+30*idx);
			
			firstLabel.setSize(20,20);
			secondLabel.setSize(20,20);
			elemLabel.setSize(20,20);
			
			firstLabel.setFont(new Font("Arial",Font.BOLD,20));
			secondLabel.setFont(new Font("Arial",Font.BOLD,20));
			elemLabel.setFont(new Font("Arial",Font.BOLD,20));
			
			firstLabel.setText(Character.toString(pq.peek().first+65));
			secondLabel.setText(Character.toString(pq.peek().second+65));
			elemLabel.setText(Integer.toString(pq.peek().elem));
			
			context.add(firstLabel);
			context.add(secondLabel);
			context.add(elemLabel);
			
			pq.remove();
			idx++;
		}
		

	}
	
	class Pair2 implements Comparable<Pair2> {
		String name;
		int correct, speed;

		Pair2(String name, int speed, int correct) {
			this.name = name;
			this.speed = speed;
			this.correct = correct;
		}
		@Override
		public int compareTo(Pair2 p) {
			if (this.speed > p.speed) {
				return -1;
			} 
			else if (this.speed == p.speed) {
				if (this.correct > p.correct) {
					return -1;
				}
			}
			return 1;
		}
	}

	void setLeaderBoard() {
		String filePath = "file/LeaderBoard/KeyBoard.txt";
		PriorityQueue<Pair2> pq = new PriorityQueue<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				pq.add(new Pair2(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
			}
		} catch (IOException exception) {
			System.out.println("ERROR");
		}
		if (count != 0 && correct * 100 / count >= 90) {
			if(name.equals(""))
			{
				name = "UNKNOWN";
			}
			pq.add(new Pair2(name, Integer.parseInt(wpm.getText()), correct * 100 / count));
		}
		Path path = Paths.get(filePath);
		try {
			Files.delete(path);

			FileWriter fout = new FileWriter(filePath);
			int maxi = 5;
			if(pq.size() < 5)
			{
				maxi = pq.size();
			}
			for(int i = 0 ; i < maxi ; i++) {
				fout.write(pq.peek().name+","+pq.peek().speed+","+pq.peek().correct + '\n');
				pq.remove();
			}
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	void setContext()
	{
		context.setLayout(null);
		context.setSize(600,600);
		context.setLocation(200,100);
		panel.add(context);
	}
	
	public ChallengeReport(int [][]arr, int count, int correct, JLabel wpm, String name) {
		this.report = arr;
		this.count = count;
		this.correct = correct;
		this.wpm = wpm;
		this.name = name;
		homeButton();
		setWindow();
		setContext();
		setLabel();
		setLeaderBoard();
		this.setVisible(true);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}
}
