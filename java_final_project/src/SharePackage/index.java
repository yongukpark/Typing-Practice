package SharePackage;

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
import javax.swing.border.BevelBorder;
import MyKeyboard.KeyboardMain;
import MyMouse.MousePractice;
import MyMouse.SelectIcon;


public class index extends JFrame{
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
		JLabel title = new JLabel("Welcome to Practice");
		title.setLocation(380,80);
		title.setSize(1000,200);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		panel.add(title);
	}
	
	public void setButton(JButton button)
	{
		button.setSize(300,400);
		button.setFocusable(false);
		button.setFont(new Font("Arial", Font.BOLD, 40));
		button.setOpaque(true);
		button.setBackground(new Color(245, 244, 242));
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		button.addMouseListener(new ButtonMouseAdapter());
		panel.add(button);
	}
	
	public void setKeyBoard()
	{
		JButton button = new JButton("<html>KeyBoard<br><br>Practice</html>");
		button.setLocation(250,300);
		setButton(button);	
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new KeyboardMain();
			}
		});
	}
	
	public void setMouse()
	{
		JButton button = new JButton("<html>Mouse<br><br>Practice</html>");
		button.setLocation(650,300);
		setButton(button);	
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SelectIcon();
			}
		});
	}
	
	public index()
	{
		setKeyBoard();
		setMouse();
		setTitle();
		setWindow();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new index();
	}
}