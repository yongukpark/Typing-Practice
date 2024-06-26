package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import SharePackage.MyPanel;
import SharePackage.TimerRunnable;

public class LetterPractice extends JFrame{
	private MyPanel panel = new MyPanel();
	private ImageIcon timerIcon = new ImageIcon("image/timer.jpeg");
	private JLabel timerLabel = new JLabel();
	private JLabel realTime = new JLabel();
	private JTextField target = new JTextField();
	private TimerRunnable timerRunnable = new TimerRunnable(timerLabel, realTime);
	private Thread thread = new Thread(timerRunnable);
	private JLabel countnum = new JLabel("0");
	private JLabel correctPercent = new JLabel("0%");
	private JLabel startJLabel = new JLabel("Press 'enter' to start");
	private boolean flg = false;
	private int correct = 0;
	private int count = 0;
	private int n = 0;
	private int curIdx = 0;
	private Random random = new Random();
	private ArrayList<JLabel> arr = new ArrayList<JLabel>();
	int[] codes = {
		    81, 87, 69, 82, 84, 89, 85, 73, 79, 80, 
		    65, 83, 68, 70, 71, 72, 74, 75, 76,     
		    90, 88, 67, 86, 66, 78, 77             
		};
	
	class MykeyListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();			
			if(n-32 == keyCode)
			{
				correct = correct + 1;
			}
			if(keyCode == KeyEvent.VK_ENTER)
			{
	        	if(flg==false)
	        	{
	        		startJLabel.setVisible(false);
	        		thread.start();
	        		flg = true;
	        	}
			}
			else if(!flg){
				return;
			}
    		random = new Random();
    		if(count == 30)
    		{
    			thread.interrupt();
    			if(count != 0 )
            	{
    	        	correctPercent.setText(Integer.toString(correct * 100 / count)+ "%");
            	}
    			String[] answer={"restart", "main"};
    			String message = "time : " + timerLabel.getText() + "\ncorrect : " + correct * 100 / count + "%" + "\n\nrestart?";

    			
    			int res = JOptionPane.showOptionDialog(null, message, "Option"
    					,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
    			if(res == JOptionPane.YES_OPTION)
    			{
    				dispose();
    				new LetterPractice();
    			}
    			else
    			{
    				dispose();
    				new KeyboardMain();
    			}
    			
    		}
    		countnum.setText(Integer.toString(count+1)+" / 30");
        	if(count != 0 )
        	{
	        	correctPercent.setText(Integer.toString(correct * 100 / count)+ "%");
        	}
        	arr.get(curIdx).setBackground(Color.white);
    		n = random.nextInt(97, 123);
    		target.setText(new String(Character.toChars(n)));
    		for(int i = 0 ; i < 26 ; i++)
    		{
    			if(n-32 == codes[i])
    			{
    				arr.get(i).setBackground(new Color(140, 217, 255));
    				curIdx = i;
    			}
    		}
    		count = count + 1;
		}
	}

	public void setKeyBoard()
	{
		
		for(int i = 0 ; i < 10 ; i++)
		{
			JLabel label = new JLabel(new String(Character.toChars(codes[i])));
			label.setFont(new Font("Arial",Font.PLAIN,30));
			label.setBorder(new LineBorder(new Color(124, 163, 242), 1));
			label.setLocation(250+70*i,450);
			label.setSize(70,70);
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBackground(Color.white);
			arr.add(label);
			panel.add(label);
		}
		for(int i = 10 ; i < 19 ; i++)
		{
			JLabel label = new JLabel(new String(Character.toChars(codes[i])));
			label.setFont(new Font("Arial",Font.PLAIN,30));
			label.setBorder(new LineBorder(new Color(124, 163, 242), 1));
			label.setLocation(285+70*(i-10),520);
			label.setSize(70,70);
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBackground(Color.white);
			arr.add(label);
			panel.add(label);
		}
		for(int i = 19 ; i < 26; i++)
		{
			JLabel label = new JLabel(new String(Character.toChars(codes[i])));
			label.setFont(new Font("Arial",Font.PLAIN,30));
			label.setBorder(new LineBorder(new Color(124, 163, 242), 1));
			label.setLocation(310+70*(i-19),590);
			label.setSize(70,70);
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBackground(Color.white);
			arr.add(label);
			panel.add(label);
		}
			
	}
	
	public void setWindow()
	{
		setTitle("letter practice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}
	
	public void setTimer()
	{
		Image img = timerIcon.getImage();     
    	Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        timerIcon = new ImageIcon(updateImg);  
        JLabel label = new JLabel(timerIcon);
		label.setLocation(50,50);
		label.setSize(50,50);
		panel.add(label);
		
		timerLabel.setSize(80,50);
		timerLabel.setFont(new Font("Arial",Font.BOLD,30));
		timerLabel.setLocation(120,50);
		panel.add(timerLabel);
		
	}
	
	public void infoView()
	{
		JPanel p = new JPanel();
		p.setBackground(new Color(247, 226, 161));
		p.setLayout(null);
		p.setLocation(200,100);
		p.setSize(800,100);
		JLabel cc = new JLabel("Progress : ");
		cc.setLocation(39, 5);
		cc.setSize(200,40);
		cc.setFont(new Font("Arial",Font.BOLD,20));
		countnum.setLocation(190,5);
		countnum.setSize(200,40);
		countnum.setFont(new Font("Arial",Font.PLAIN,20));
		JLabel cp = new JLabel("Correct Percent  : ");
		cp.setLocation(10,50);
		cp.setSize(200,40);
		cp.setFont(new Font("Arial",Font.BOLD,20));
		correctPercent.setLocation(190,50);
		correctPercent.setSize(200,40);
		correctPercent.setFont(new Font("Arial",Font.PLAIN,20));
		p.add(cc);
		p.add(cp);
		p.add(countnum);
		p.add(correctPercent);
		panel.add(p);
	}
	
	
	public void setTarget()
	{
		target.setEditable(false);
		target.setFont(new Font("Arial",Font.PLAIN,100));
		target.setLocation(520,230);
		target.setSize(150,150);
		target.setBorder(new LineBorder(new Color(124, 163, 242), 1));
		target.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(target);
	}
	
	public void setStartJLabel()
	{
		
		startJLabel.setLocation(510, 350);
		startJLabel.setSize(300,100);
		startJLabel.setFont(new Font("Arial",Font.ITALIC,20));
		panel.add(startJLabel);
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
                thread.interrupt();
				dispose();
				new KeyboardMain();
            }

        });

        panel.add(button);
	}
	
	public LetterPractice()
	{
		
		homeButton();
		setKeyBoard();
		setStartJLabel();
		setTimer();
		setWindow();
		infoView();
		setTarget();
		this.setVisible(true);
		panel.setFocusable(true);
		panel.requestFocusInWindow(); 
		countnum.setText("0 / 30");
		panel.addKeyListener(new MykeyListener());
	}
}
