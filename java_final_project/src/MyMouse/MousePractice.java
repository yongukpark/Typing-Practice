package MyMouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MyKeyboard.KeyboardMain;
import MyKeyboard.ShortPractice;
import SharePackage.index;

public class MousePractice extends JFrame{
	protected MyPanel panel = new MyPanel();
	private ImageIcon timerIcon = new ImageIcon("image/timer.jpeg");
	protected JLabel timerLabel = new JLabel();
	private double realTime;
	private JPanel ground = new JPanel();
	private TimerRunnable timerRunnable = new TimerRunnable();
	protected Thread thread = new Thread(timerRunnable);
	protected JLabel countnum = new JLabel("0 / 20");
	protected JLabel correctPercent = new JLabel("0%");
	protected JLabel wpm = new JLabel("0");
	protected int correct = 0;
	protected JTextField startMessage = new JTextField("Click Empty place to start practice");
	private JLabel dot = new JLabel();
	private boolean flg = false;
	private Random random = new Random();
	private int count = -1;
	private String imgFile;
	
	class MyPanel extends JPanel
	{
		private ImageIcon backgroundImgIcon = new ImageIcon("image/background_image.jpeg");
		private Image backgroundImg = backgroundImgIcon.getImage();
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	public class TimerRunnable implements Runnable{

		public TimerRunnable()
		{

		}
		@Override
		public void run()
		{
			int n = 0;
			while(true)
			{
				n = n + 1;
				realTime = n / 600.0;
				String minute = String.format("%02d", n/600);
				String second = String.format("%03d", n%600);
				timerLabel.setText(minute + ":" + second);
				try {
					Thread.sleep(1000/600);
				} catch (Exception e) {
					return;
				}
			}
		}

	}

	class PanelMouseAdapter extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			int x = e.getX();
			int y = e.getY();
			if(flg&& x >= dot.getX() && x <= dot.getX() + 50 && y >= dot.getY() && y <= dot.getY()+50)
			{
				correct++;
			}
			if(!flg)
			{
				thread.start();
				flg = true;
				startMessage.setVisible(false);
			}
			
			count++;
			if(count == 20)
    		{
				thread.interrupt();
				if(correct != 0)
				{
					wpm.setText(String.format("%.3f", realTime/correct));
				}
				if(count != 0)
				{
					correctPercent.setText(Integer.toString(correct * 100 / count) + "%");	
				}
    			String[] answer={"restart", "main"};
    			String message= "time : " + timerLabel.getText() + "\ncorrect : " + correct * 100 / count + "%" + "\nSpeed: " + wpm.getText() +"\n\nrestart?";
    			int res = JOptionPane.showOptionDialog(null, message, "Option"
    					,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null );
    			if(res == JOptionPane.YES_OPTION)
    			{
    				dispose();
    				new MousePractice(imgFile);
    			}
    			else
    			{
    				dispose();
    				new index();
    			}
    		}
			
			int nextX = random.nextInt(ground.getWidth()-50);
			int nextY = random.nextInt(ground.getHeight()-50);
			dot.setLocation(nextX,nextY);
			if(correct != 0)
			{
				wpm.setText(String.format("%.3f", realTime/correct));
			}
			countnum.setText(Integer.toString(count+1)+" / 20");
			if(count != 0)
			{
				correctPercent.setText(Integer.toString(correct * 100 / count) + "%");
			}
			System.out.println(correct);
			System.out.println(count);
		}
	}
	
	public void setWindow()
	{
		setTitle("Typing practice");
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
		
		timerLabel.setSize(200,50);
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
		JLabel w = new JLabel("Speed  : ");
		w.setLocation(450,30);
		w.setSize(200,40);
		w.setFont(new Font("Arial",Font.BOLD,20));
		wpm.setLocation(530,30);
		wpm.setSize(200,40);
		wpm.setFont(new Font("Arial",Font.PLAIN,20));
		p.add(cc);
		p.add(cp);
		p.add(w);
		p.add(countnum);
		p.add(correctPercent);
		p.add(wpm);
		panel.add(p);
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
				new index();
            }

        });

        panel.add(button);
	}
	
	public void setGround()
	{
		ground.setBackground(new Color(252, 251, 247));
		ground.setSize(1000,500);
		ground.setLocation(100,250);
		ground.setLayout(null);
		panel.add(ground);
	}
	
	public void setStart()
	{
		startMessage.setEditable(false);
		startMessage.setFont(new Font("Arial",Font.PLAIN,40));
		startMessage.setLocation(220,140);
		startMessage.setSize(800,90);
		startMessage.setBorder(null);
		startMessage.setBackground(null);
		ground.add(startMessage);
	}
	
	
	public void setDot(String s)
	{
		ImageIcon icon = new ImageIcon(s);
		Image img = icon.getImage();     
    	Image updateImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(updateImg);  
        dot.setIcon(icon);
		dot.setSize(50,50);
		dot.setLocation(ground.getWidth()/2, ground.getHeight()/2);
		dot.setOpaque(true);
		ground.add(dot);
	}
	public MousePractice(String s)
	{
		this.imgFile = s;
		homeButton();
		setTimer();
		setWindow();
		infoView();
		setStart();
		setGround();
		setDot(s);
		ground.addMouseListener(new PanelMouseAdapter());
		this.setVisible(true);
	}
}
