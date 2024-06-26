package SharePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderBoard extends JFrame{
	private MyPanel panel = new MyPanel();

	public void setWindow()
	{
		setTitle("LeaderBoard");
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
		panel.setLayout(null);
		this.panel.add(panel);
		
		JLabel keyboardJPanel = new JLabel("KeyBorad");
		keyboardJPanel.setLocation(330,250);
		keyboardJPanel.setSize(300,50);
		keyboardJPanel.setFont(new Font("Arial",Font.BOLD,30));
		this.panel.add(keyboardJPanel);
		
		JLabel name1 = new JLabel("Name");
		name1.setLocation(20, 50);
		name1.setSize(200,50);
		name1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(name1);
		JLabel correct1 = new JLabel("WPM");
		correct1.setLocation(150, 50);
		correct1.setSize(200,50);
		correct1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(correct1);
		JLabel wpm1 = new JLabel("Correct");
		wpm1.setLocation(220, 50);
		wpm1.setSize(200,50);
		wpm1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(wpm1);
		
		String filePath = "file/LeaderBoard/KeyBoard.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;
			int idx = 1;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				JLabel name = new JLabel(parts[0]);
				name.setLocation(20, 50 + idx*50);
				name.setSize(200,50);
				name.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(name);
				JLabel correct = new JLabel(parts[1]);
				correct.setLocation(150, 50+idx*50);
				correct.setSize(200,50);
				correct.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(correct);
				JLabel wpm = new JLabel(parts[2] + "%");
				wpm.setLocation(230, 50+idx*50);
				wpm.setSize(200,50);
				wpm.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(wpm);
				idx++;
			}
		} catch (IOException exception) {
			System.out.println("ERROR");
		}
	}
	
	public void setMouse()
	{
		
		JPanel panel = new JPanel();
		panel.setLocation(650,300);
		panel.setSize(300,400);
		panel.setOpaque(true);
		panel.setBackground(new Color(212, 250, 246));
		panel.setLayout(null);
		this.panel.add(panel);
		
		JLabel mouseJPanel = new JLabel("Mouse");
		mouseJPanel.setLocation(740,250);
		mouseJPanel.setSize(300,50);
		mouseJPanel.setFont(new Font("Arial",Font.BOLD,30));
		this.panel.add(mouseJPanel);
		
		JLabel name1 = new JLabel("Name");
		name1.setLocation(20, 50);
		name1.setSize(200,50);
		name1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(name1);
		JLabel correct1 = new JLabel("Speed");
		correct1.setLocation(150, 50);
		correct1.setSize(200,50);
		correct1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(correct1);
		JLabel wpm1 = new JLabel("Correct");
		wpm1.setLocation(220, 50);
		wpm1.setSize(200,50);
		wpm1.setFont(new Font("Arial",Font.BOLD, 15));
		panel.add(wpm1);
		
		String filePath = "file/LeaderBoard/Mouse.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;
			int idx = 1;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				JLabel name = new JLabel(parts[0]);
				name.setLocation(20, 50 + idx*50);
				name.setSize(200,50);
				name.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(name);
				JLabel correct = new JLabel(parts[1]);
				correct.setLocation(150, 50+idx*50);
				correct.setSize(200,50);
				correct.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(correct);
				JLabel wpm = new JLabel(parts[2] + "%");
				wpm.setLocation(230, 50+idx*50);
				wpm.setSize(200,50);
				wpm.setFont(new Font("Arial",Font.BOLD, 20));
				panel.add(wpm);
				idx++;
			}
		} catch (IOException exception) {
			System.out.println("ERROR");
		}
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
