package MyKeyboard;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardMain extends JFrame{
	private MyPanel panel = new MyPanel();
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
		setTitle("Typing Trainer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}
	
	public void setTitle()
	{
		JLabel title = new JLabel("Welcome to KeyBoard practice");
		title.setLocation(100,80);
		title.setSize(1000,200);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		panel.add(title);
	}
	
	public void setButton(JButton button)
	{
		button.setSize(300,100);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.addMouseListener(new ButtonMouseAdapter());
		panel.add(button);
	}
	
	public void letterButton()
	{
		JButton button = new JButton("Letter Practice");
		button.setLocation(850,250);
		setButton(button);
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LetterPractice();
			}
		});
	}
	
	public void wordButton()
	{
		JButton button = new JButton("Word Practice");
		button.setLocation(850,320);
		setButton(button);
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WordPractice();
			}
		});
	}
	
	public void shortButton()
	{
		JButton button = new JButton("Short Practice");
		button.setLocation(850,390);
		setButton(button);
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ShortPractice();
			}
		});
	}
	
	public void longButton()
	{
		JButton button = new JButton("Long Practice");
		button.setLocation(850,460);
		setButton(button);	
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LongPracticeMenu();
			}
		});
	}
	
	public void CSButton()
	{
		JButton button = new JButton("Challenge");
		button.setLocation(850,530);
		setButton(button);	
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Challenge();
			}
		});
	}
	
	public void questionButton()
	{
		JButton button = new JButton("HELP ?");
		button.setLocation(850,630);
		setButton(button);
	}
	
	public void character()
	{
		
	}

	public KeyboardMain()
	{
		setWindow();
		setTitle();
		letterButton();
		wordButton();
		shortButton();
		longButton();
		CSButton();
		character();
		questionButton();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new KeyboardMain();
	}
}
