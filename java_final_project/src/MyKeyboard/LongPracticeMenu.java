package MyKeyboard;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import SharePackage.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LongPracticeMenu extends JFrame {
	private MyPanel panel = new MyPanel();
	private JTextArea textArea = new JTextArea();
	private JList<String> strList;
	private String character;

	public void setWindow() {
		setTitle("Choose Long Article");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}

	public void setTitle() {
		JLabel title = new JLabel("Choose Article");
		title.setLocation(100, 80);
		title.setSize(1000, 200);
		title.setFont(new Font("Arial", Font.BOLD, 50));
		panel.add(title);
	}

	public void preView() {
		JLabel label = new JLabel("Preview");
		label.setLocation(200, 230);
		label.setSize(100, 100);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setBackground(new Color(247, 226, 161));
		textArea.setEditable(false);
		textArea.setLocation(200, 300);
		textArea.setSize(400, 300);
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); 
		panel.add(textArea);
		panel.add(label);
	}

	public void selectBar() {
		File dir = new File("file/LongPractice");
		File[] subFiles = dir.listFiles();
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < subFiles.length; i++) {
			arrayList.add(subFiles[i].getName().replaceAll("\\.(.*)", ""));
		}
		String[] sarr = arrayList.toArray(new String[0]);
		strList = new JList<String>(sarr);
		strList.setSize(300, 400);
		strList.setFont(new Font("Arial", Font.PLAIN, 20));
		strList.setLocation(800, 200);
		strList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(strList);
		scrollPane.setSize(300, 400);
		scrollPane.setLocation(800, 200);
		panel.add(scrollPane);

		strList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textArea.setText("");
				try (BufferedReader br = new BufferedReader(
						new FileReader("file/LongPractice/" + strList.getSelectedValue() + ".txt"))) {

					String line;
					while ((line = br.readLine()) != null) {
						textArea.setText(textArea.getText()+ ' ' + line);
					}
				} catch (IOException exception) {
					System.out.println("ERROR");
				}
				

			}
		});
	}

	public void fileUpload()
	{
		JButton button = new JButton("File Upload");
		button.setSize(100,50);
		button.setLocation(800,600);
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","txt");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION)
				{
					return;
				}
				String pathName = chooser.getSelectedFile().getPath();
				String fileName = chooser.getSelectedFile().getName();
				textArea.setText("");
				try (BufferedReader br = new BufferedReader(
						new FileReader(pathName))) {
					FileWriter fout = new FileWriter("file/LongPractice/"+fileName);
					String line;
					while ((line = br.readLine()) != null) {
						fout.write(line);
			            fout.write("\n",0,1);
					}
					fout.close();
					dispose();
					new LongPracticeMenu(character);
					
				} catch (IOException exception) {
					System.out.println("ERROR");
				}
			}
		});
		panel.add(button);
	}

	public void startButton()
	{
		JButton button = new JButton("Start");
		button.setSize(100,50);
		button.setLocation(1000,600);
		button.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(strList.getSelectedValue() == null)
				{
					return;
				}
				dispose();
				new LongPractice("file/LongPractice/" + strList.getSelectedValue() + ".txt", character);
			}
		});
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
				new KeyboardMain();
            }

        });

        panel.add(button);
	}
	public LongPracticeMenu(String character) {
		this.character = character;
		homeButton();
		setWindow();
		setTitle();
		selectBar();
		preView();
		fileUpload();
		startButton();
		this.setVisible(true);
	}

}
