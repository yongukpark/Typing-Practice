package SharePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import MyKeyboard.KeyboardMain;
import MyMouse.MousePractice;
import MyMouse.SelectIcon;


public class LeaderBoard extends JFrame{
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
		JLabel title = new JLabel("LeaderBoard");
		title.setLocation(450,80);
		title.setSize(1000,200);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		panel.add(title);
	}
	
	public void setKeyBoard()
	{
		JPanel panel = new JPanel();
		panel.setLocation(250,300);
		panel.setSize(300,400);
		panel.setOpaque(true);
		panel.setBackground(new Color(220, 250, 212));
		this.panel.add(panel);
	}
	
	public void setMouse()
	{
		JPanel panel = new JPanel();
		panel.setLocation(650,300);
		panel.setSize(300,400);
		panel.setOpaque(true);
		panel.setBackground(new Color(212, 250, 246));
		this.panel.add(panel);
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
				new index();
            }

        });

        panel.add(button);
	}
	public LeaderBoard() {

		setKeyBoard();
		setMouse();
		setTitle();
		setWindow();
		homeButton();
		this.setVisible(true);
	}

}
