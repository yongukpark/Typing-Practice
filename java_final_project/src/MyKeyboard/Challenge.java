package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
	
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import SharePackage.MyPanel;

import javax.swing.text.Document;
import javax.swing.text.Element;

public class Challenge extends JFrame {
	private MyPanel panel = new MyPanel();
	private ImageIcon timerIcon = new ImageIcon("image/timer.jpeg");
	private JLabel timerLabel = new JLabel();
	private JLabel realTime = new JLabel();
	private JTextPane preInput = new JTextPane();
	private JTextField postInput = new JTextField();
	private TimerRunnable timerRunnable = new TimerRunnable();
	private Thread thread = new Thread(timerRunnable);
	private JLabel countnum = new JLabel("0");
	private JLabel correctPercent = new JLabel("0%");
	private JLabel wpm = new JLabel("0");
	private boolean flg = false;
	private SimpleAttributeSet set = new SimpleAttributeSet();
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	private Graph graph = new Graph(arr);
	private int count = 0;
	private int correctFlg = 0;
	private int correct = 0;
	private int [][]report = new int [26][26];
	private String name;

	
	class TimerRunnable implements Runnable {
		@Override
		public void run() {
			int n = 0;
			arr.add(0);
			while (n < 60) {
				postInput.requestFocusInWindow(); 
				postInput.setCaretPosition(0);     
				panel.requestFocusInWindow();
				n++;
				realTime.setText(Integer.toString(n));
				String minute = String.format("%02d", (60-n) / 60);
				String second = String.format("%02d", (60-n) % 60);
				timerLabel.setText(minute + ":" + second);
				arr.add(Integer.parseInt(wpm.getText()));
				if(!realTime.getText().equals("0"))
				{
					wpm.setText(Integer.toString(correct * 60 / Integer.parseInt(realTime.getText())));
				}
				if(n != 0)
				{
					graph.repaint();
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					return;
				}
			}
			dispose();
			new ChallengeReport(report, count, correct, wpm, name);
		}

	}

	class MykeyListener2 extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char keyChar = e.getKeyChar();
			
			if (flg == false) {
				thread.start();
				flg = true;
				correctFlg = 0;
			}
			
			StyledDocument doc = preInput.getStyledDocument();
			if(keyChar == 8) {
				if(preInput.getText().length() == 0)
				{
					return;
				}
				else if(preInput.getText().charAt(preInput.getText().length()-1) == ' ')
				{
					return;
				}
				else
				{
					int idx = preInput.getText().length();
					Element element = doc.getCharacterElement(idx-1);
					AttributeSet attribute = element.getAttributes();
					Color color = StyleConstants.getForeground(attribute);
					if(color.getRed() == 255)
					{
						correctFlg--;
						try {
							doc.remove(idx-1, 1);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
					else {
						postInput.setText(preInput.getText().charAt(idx - 1) + postInput.getText());
						try {
							doc.remove(idx-1, 1);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
			else if (keyChar == ' ') {
				if (postInput.getText().charAt(0) == ' ') {
					if (correctFlg == 0) {
						correct++;
						count++;
						StyleConstants.setForeground(set, Color.black);
						try {
							doc.insertString(doc.getLength(), Character.toString(keyChar), set);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						postInput.setText(postInput.getText().substring(1, postInput.getText().length()));
					} else {
						count++;
						correctFlg = 0;
						StyleConstants.setForeground(set, Color.black);
						try {
							doc.insertString(doc.getLength(), Character.toString(keyChar), set);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						postInput.setText(postInput.getText().substring(1, postInput.getText().length()));
					}
				} else {
					correctFlg = 0;
					count++;
					StyleConstants.setForeground(set, Color.red);
					int idx = postInput.getText().indexOf(' ');
					try {
						doc.insertString(doc.getLength(), postInput.getText().substring(0, idx+1), set);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					postInput.setText(postInput.getText().substring(idx+1, postInput.getText().length()));
				}
				
				correctPercent.setText(Integer.toString(correct * 100 / count) + "%");		
			} else if (keyChar == postInput.getText().charAt(0)) {
				if(keyChar >= 65 && keyChar <= 90)
				{
					report[keyChar-65][keyChar-65]++;
				}
				else if(keyChar >= 97 && keyChar <= 122)
				{
					report[keyChar-97][keyChar-97]++;
				}
				
				StyleConstants.setForeground(set, Color.black);
				try {
					doc.insertString(doc.getLength(), Character.toString(keyChar), set);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				postInput.setText(postInput.getText().substring(1, postInput.getText().length()));
			} else if (keyChar >= 32 && keyChar <= 126) {
				int idxx = -1;
				if(postInput.getText().charAt(0) >= 65 && postInput.getText().charAt(0) <= 90)
				{
					idxx = postInput.getText().charAt(0) - 65;
				}
				else if(postInput.getText().charAt(0) >= 97 && postInput.getText().charAt(0) <= 122)
				{
					idxx = postInput.getText().charAt(0) - 97;
				}
				
				int idxy = -1;
				if(keyChar >= 65 && keyChar <= 90)
				{
					idxy = keyChar-65;
				}
				else if(keyChar >= 97 && keyChar <= 122)
				{
					idxy = keyChar - 97;
				}
				if(idxx != -1 && idxy !=-1)
				{
					report[idxx][idxy]++;
				}
				correctFlg++;
				StyleConstants.setForeground(set, Color.red);
				try {
					doc.insertString(doc.getLength(), Character.toString(keyChar), set);
				} catch (Exception e1) {
					e1.printStackTrace();
					
				}
			}
			postInput.requestFocusInWindow(); // 텍스트 필드에 포커스를 설정합니다.
			postInput.setCaretPosition(0);     // 커서 위치를 0으로 설정하여 맨 앞으로 옮깁니다.
			panel.requestFocusInWindow();
			
		}
	}

	public void importPractice()
	{
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("file/shortPractice.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
              list.add(line);
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
		boolean[] visited = new boolean[list.size()];
		int count = 0;
		while(count < 50)
		{
			Random random = new Random();
			int n = random.nextInt(list.size());
			if(visited[n] != true)
			{
				visited[n] = true;
				postInput.setText(postInput.getText()+list.get(n)+" ");
				count++;
			}
		}
	}
	
	public class DocumentLengthLimitListener implements DocumentListener {
		private int maxLength;

		public DocumentLengthLimitListener(int maxLength) {
			this.maxLength = maxLength;
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			limitLength(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}

		private void limitLength(DocumentEvent e) {
			Document doc = e.getDocument();
			if (doc.getLength() > maxLength) {
				SwingUtilities.invokeLater(() -> {
					try {
						int n = doc.getLength() - maxLength;
						doc.remove(0, n);
					} catch (BadLocationException ex) {
						ex.printStackTrace();
					}
				});
			}
		}
	}

	public void setWindow() {
		setTitle("Challenge");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel.setLayout(null);
	}

	public void setTimer() {
		Image img = timerIcon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		timerIcon = new ImageIcon(updateImg);
		JLabel label = new JLabel(timerIcon);
		label.setLocation(50, 50);
		label.setSize(50, 50);
		panel.add(label);

		timerLabel.setSize(80, 50);
		timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
		timerLabel.setLocation(120, 50);
		panel.add(timerLabel);

	}

	public void infoView() {
		JPanel p = new JPanel();
		p.setBackground(new Color(247, 226, 161));
		p.setLayout(null);
		p.setLocation(200, 100);
		p.setSize(800, 100);
		JLabel cp = new JLabel("Correct Percent  : ");
		cp.setLocation(10, 30);
		cp.setSize(200, 40);
		cp.setFont(new Font("Arial", Font.BOLD, 20));
		correctPercent.setLocation(190, 30);
		correctPercent.setSize(200, 40);
		correctPercent.setFont(new Font("Arial", Font.PLAIN, 20));
		JLabel w = new JLabel("WPM  : ");
		w.setLocation(450, 30);
		w.setSize(200, 40);
		w.setFont(new Font("Arial", Font.BOLD, 20));
		wpm.setLocation(530, 30);
		wpm.setSize(200, 40);
		wpm.setFont(new Font("Arial", Font.PLAIN, 20));
		p.add(cp);
		p.add(w);
		p.add(correctPercent);
		p.add(wpm);
		panel.add(p);
	}

	public void setInput() {
		preInput.setFont(new Font("Arial", Font.PLAIN, 40));
		preInput.setLocation(0, 500);
		preInput.setSize(600, 50);
		preInput.setBorder(null);
		preInput.setEditable(false);
		StyledDocument doc = preInput.getStyledDocument();
		StyleConstants.setAlignment(set, StyleConstants.ALIGN_RIGHT);
		doc.setParagraphAttributes(0, doc.getLength(), set, false);
		doc.addDocumentListener(new DocumentLengthLimitListener(30));
		preInput.setAlignmentY(CENTER_ALIGNMENT);
		postInput.setFont(new Font("Arial", Font.PLAIN, 40));
		postInput.setLocation(600, 500);
		postInput.setSize(600, 50);
		postInput.setHorizontalAlignment(SwingConstants.LEFT);
		postInput.setBorder(null);
		postInput.setEditable(false);
		postInput.setCaretPosition(0);
		postInput.setForeground(new Color(194, 192, 188));
		panel.add(preInput);
		panel.add(postInput);
	}

	public void setGraph() {
		graph.setLocation(200, 250);
		graph.setSize(800, 200);
		graph.setLayout(null);
		panel.add(graph);
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
	public Challenge(String name) {
		this.name = name;
		homeButton();
		importPractice();
		setGraph();
		setTimer();
		setWindow();
		infoView();
		setInput();
		this.setVisible(true);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(new MykeyListener2());
	}
}
