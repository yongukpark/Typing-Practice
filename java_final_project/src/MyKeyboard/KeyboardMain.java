package MyKeyboard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import SharePackage.MyPanel;
import SharePackage.index;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class KeyboardMain extends JFrame{
	private MyPanel panel = new MyPanel();
	private JButton rightButton = new JButton(">");
	private JButton leftButton = new JButton("<");
	private ArrayList<String> iconList = new ArrayList<String>();
	private int curIdx = 0;
	private JLabel label = new JLabel();
	private JLabel name = new JLabel("Name");
	private JTextField nameInput = new JTextField();

	public class ButtonMouseAdapter extends MouseAdapter{
		public void mouseEntered(MouseEvent e)
		{
			JButton button = (JButton)e.getSource();
			button.setForeground(Color.blue);
			button.setFont(new Font("Arial", Font.BOLD, 25));
		}
		public void mouseExited(MouseEvent e)
		{
			JButton button = (JButton)e.getSource();
			button.setForeground(Color.black);
			button.setFont(new Font("Arial", Font.BOLD, 20));
		}
	}

	public void setWindow()
	{
		setTitle("KeyBoard Practice");
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
				new WordPractice("image/Character/" + iconList.get(curIdx));
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
				new ShortPractice("image/Character/" + iconList.get(curIdx));
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
				new LongPracticeMenu("image/Character/" + iconList.get(curIdx));
			}
		});
	}
	
	public void ChallengeButton()
	{
		JButton button = new JButton("Challenge");
		button.setLocation(850,530);
		setButton(button);	
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Challenge(nameInput.getText());
			}
		});
	}
	
	public void importCharacter() {
		File dir = new File("image/Character");
		File[] subFiles = dir.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			iconList.add(subFiles[i].getName());
		}
		ImageIcon icon = new ImageIcon("image/Character/" + iconList.get(curIdx));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		icon = new ImageIcon(updateImg);
		label = new JLabel(icon);
		label.setLocation(185, 350);
		label.setSize(250, 250);
		panel.add(label);
		
		JLabel l = new JLabel("Choose Character");
		l.setLocation(190,180);
		l.setSize(250,250);
		l.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(l);
		if (curIdx == iconList.size() - 1) {
			rightButton.setVisible(false);
		}
		if (curIdx == 0) {
			leftButton.setVisible(false);
		}
	}
	public void importImage() {
		JButton button = new JButton("Import");
		button.setSize(100, 50);
		button.setLocation(240, 650);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("images","jpeg", "jpg", "png");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				String pathName = chooser.getSelectedFile().getPath();
				String fileName = chooser.getSelectedFile().getName();
				File src = new File(pathName);
				File dest = new File("image/Character/"+fileName);
				FileInputStream fin = null;
				FileOutputStream fos = null;
				int count;
				try {
					fin = new FileInputStream(src);
					fos = new FileOutputStream(dest);
					while ((count = fin.read()) != -1) {
						fos.write((byte)count);
					}
					fin.close();
					fos.close();
					dispose();
					new KeyboardMain();

				} catch (IOException exception) {
					System.out.println("ERROR");
				}
			}
		});
		panel.add(button);
	}
	public void setName()
	{
		name.setLocation(700,200);
		name.setSize(200,200);
		name.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(name);
		
		
		nameInput.setBackground(new Color(252, 237, 230));
		nameInput.setLocation(640,350);
		nameInput.setSize(200,50);
		nameInput.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(nameInput);
	}
	
	public void setRightButton() {

		rightButton.setSize(70, 150);
		rightButton.setLocation(515, 400);
		rightButton.setFont(new Font("Arial", Font.BOLD, 40));
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				curIdx++;
				ImageIcon icon = new ImageIcon("image/Character/" + iconList.get(curIdx));
				Image img = icon.getImage();
				Image updateImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
				icon = new ImageIcon(updateImg);
				label.setIcon(icon);
				if (curIdx == iconList.size() - 1) {
					rightButton.setVisible(false);
				}
				if (curIdx != 0) {
					leftButton.setVisible(true);
				}
			}
		});
		panel.add(rightButton);
	}

	public void setLeftButton() {
		leftButton.setSize(70, 150);
		leftButton.setLocation(65, 400);
		leftButton.setFont(new Font("Arial", Font.BOLD, 40));
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				curIdx--;
				ImageIcon icon = new ImageIcon("image/Character/" + iconList.get(curIdx));
				Image img = icon.getImage();
				Image updateImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
				icon = new ImageIcon(updateImg);
				label.setIcon(icon);
				if (curIdx == 0) {
					leftButton.setVisible(false);
				}
				if (curIdx != iconList.size() - 1) {
					rightButton.setVisible(true);
				}
			}
		});
		panel.add(leftButton);
	}

	public KeyboardMain()
	{
		importCharacter();
		setName();
		setLeftButton();
		setRightButton();
		importImage();
		setWindow();
		setTitle();
		letterButton();
		wordButton();
		shortButton();
		longButton();
		ChallengeButton();
		homeButton();
		this.setVisible(true);
	}
}
