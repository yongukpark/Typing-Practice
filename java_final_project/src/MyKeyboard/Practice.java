package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import SharePackage.TimerRunnable;

public class Practice extends JFrame{
	protected MyPanel panel = new MyPanel();
	private ImageIcon timerIcon = new ImageIcon("image/timer.jpeg");
	protected JLabel timerLabel = new JLabel();
	protected JLabel realTime = new JLabel();
	protected JTextField input = new JTextField();
	protected JTextField target = new JTextField();
	protected JTextField prevTarget = new JTextField("Press 'Enter' to start practice");
	protected JTextField prevInput = new JTextField("Press 'Enter' to start practice");
	private TimerRunnable timerRunnable = new TimerRunnable(timerLabel, realTime);
	protected Thread thread = new Thread(timerRunnable);
	protected JLabel countnum = new JLabel("0");
	protected JLabel correctPercent = new JLabel("0%");
	protected JLabel wpm = new JLabel("0");
	protected int correct = -1;
	
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
		JLabel w = new JLabel("WPM  : ");
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
	
	
	
	public void setTarget()
	{
		prevTarget.setEditable(false);
		prevTarget.setFont(new Font("Arial",Font.PLAIN,40));
		prevTarget.setLocation(200,240);
		prevTarget.setSize(800,90);
		prevTarget.setBorder(null);
		prevTarget.setForeground(new Color(194, 192, 188));
		prevTarget.setBackground(new Color(232, 233, 235));
		panel.add(prevTarget);
		target.setEditable(false);
		target.setFont(new Font("Arial",Font.PLAIN,40));
		target.setLocation(200,350);
		target.setSize(800,90);
		target.setBorder(new LineBorder(new Color(124, 163, 242), 1));
		panel.add(target);
	}
	public void setInput()
	{
		prevInput.setEditable(false);
		prevInput.setFont(new Font("Arial",Font.PLAIN,40));
		prevInput.setLocation(200,460);
		prevInput.setForeground(new Color(194, 192, 188));
		prevInput.setSize(800,90);
		prevInput.setBorder(null);
		prevInput.setBackground(new Color(232, 233, 235));
		panel.add(prevInput);
		input.setFont(new Font("Arial",Font.PLAIN,40));
		input.setLocation(200,570);
		input.setSize(800,90);
		input.setBorder(new LineBorder(new Color(124, 163, 242), 1));
		panel.add(input);
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
	
	public Practice()
	{
		homeButton();
		setTimer();
		setWindow();
		infoView();
		setTarget();
		setInput();
		this.setVisible(true);
		input.setFocusable(true);
		input.requestFocusInWindow(); 
	}
}
