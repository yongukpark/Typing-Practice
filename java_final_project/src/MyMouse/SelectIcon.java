package MyMouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import SharePackage.MyPanel;
import SharePackage.index;

public class SelectIcon extends JFrame {
	private MyPanel panel = new MyPanel();
	private ArrayList<String> iconList = new ArrayList<String>();
	private int curIdx = 0;
	private JLabel label = new JLabel();
	private JButton rightButton = new JButton(">");
	private JButton leftButton = new JButton("<");
	private JLabel name = new JLabel("Name");
	private JTextField nameInput = new JTextField();
	
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
	public void importIcon() {
		File dir = new File("image/MousePracticeIcon");
		File[] subFiles = dir.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			iconList.add(subFiles[i].getName());
		}
		ImageIcon icon = new ImageIcon("image/MousePracticeIcon/" + iconList.get(curIdx));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		icon = new ImageIcon(updateImg);
		label = new JLabel(icon);
		label.setLocation(485, 250);
		label.setSize(250, 250);
		panel.add(label);

		if (curIdx == iconList.size() - 1) {
			rightButton.setVisible(false);
		}
		if (curIdx == 0) {
			leftButton.setVisible(false);
		}
	}

	public void setRightButton() {

		rightButton.setSize(70, 150);
		rightButton.setLocation(800, 300);
		rightButton.setFont(new Font("Arial", Font.BOLD, 40));
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				curIdx++;
				ImageIcon icon = new ImageIcon("image/MousePracticeIcon/" + iconList.get(curIdx));
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
		leftButton.setLocation(350, 300);
		leftButton.setFont(new Font("Arial", Font.BOLD, 40));
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				curIdx--;
				ImageIcon icon = new ImageIcon("image/MousePracticeIcon/" + iconList.get(curIdx));
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

	public void setWindow() {
		setTitle("Mouse Practice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}

	public void startButton() {
		JButton button = new JButton("Start");
		button.setSize(150, 70);
		button.setLocation(650, 600);
		button.setFont(new Font("Arial", Font.BOLD, 40));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MousePractice("image/MousePracticeIcon/" + iconList.get(curIdx), nameInput.getText());
			}
		});
		panel.add(button);
	}

	public void importImage() {
		JButton button = new JButton("Import");
		button.setSize(150, 70);
		button.setLocation(450, 600);
		button.setFont(new Font("Arial", Font.BOLD, 40));
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
				File dest = new File("image/MousePracticeIcon/"+fileName);
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
					new SelectIcon();

				} catch (IOException exception) {
					System.out.println("ERROR");
				}
			}
		});
		panel.add(button);
	}

	public void setTitle() {
		JLabel title = new JLabel("Select Icon");
		title.setLocation(480, 80);
		title.setSize(1000, 200);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		panel.add(title);
	}

	public void setName()
	{
		name.setLocation(450,450);
		name.setSize(200,200);
		name.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(name);
		
		
		nameInput.setBackground(new Color(252, 237, 230));
		nameInput.setLocation(550,530);
		nameInput.setSize(200,50);
		nameInput.setFont(new Font("Arial",Font.PLAIN,30));
		panel.add(nameInput);
	}
	public SelectIcon() {
		setName();
		importImage();
		startButton();
		setRightButton();
		setLeftButton();
		setTitle();
		setWindow();
		importIcon();
		homeButton();
		this.setVisible(true);
	}
}
