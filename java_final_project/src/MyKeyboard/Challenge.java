package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Document;

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
	private JLabel wpm = new JLabel("1");
	private boolean flg = false;
	private SimpleAttributeSet set = new SimpleAttributeSet();
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	private Graph graph = new Graph(arr);
	private int count = 0;
	private boolean correctFlg = true;
	private int correct = 0;

	class MyPanel extends JPanel {
		private ImageIcon backgroundImgIcon = new ImageIcon("image/background_image.jpeg");
		private Image backgroundImg = backgroundImgIcon.getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
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
			String message;
			if(count == 0)
			{
				message = "correct : 0%" + "\nWPM : "
						+ Integer.toString(correct * 60 / Integer.parseInt(realTime.getText())) + "\n\nrestart?";
			}
			else {
				message = "correct : " + correct * 100 / count + "%" + "\nWPM : "
						+ Integer.toString(correct * 60 / Integer.parseInt(realTime.getText())) + "\n\nrestart?";
			}
			String[] answer = { "restart", "main" };
			int res = JOptionPane.showOptionDialog(null, message, "Option", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, answer, null);
			if (res == JOptionPane.YES_OPTION) {
				dispose();
				new LetterPractice();
			} else {
				dispose();
				new KeyboardMain();
			}
		}

	}

	class MykeyListener2 extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char keyChar = e.getKeyChar();
			
			if (flg == false) {
				thread.start();
				flg = true;
				correctFlg = true;
			}
			
			StyledDocument doc = preInput.getStyledDocument();
			if (keyChar == ' ') {
				if (postInput.getText().charAt(0) == ' ') {
					if (correctFlg == true) {
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
						correctFlg = true;
						StyleConstants.setForeground(set, Color.black);
						try {
							doc.insertString(doc.getLength(), Character.toString(keyChar), set);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						postInput.setText(postInput.getText().substring(1, postInput.getText().length()));
					}
				} else {
					correctFlg = true;
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
				StyleConstants.setForeground(set, Color.black);
				try {
					doc.insertString(doc.getLength(), Character.toString(keyChar), set);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				postInput.setText(postInput.getText().substring(1, postInput.getText().length()));
			} else if (keyChar >= 32 && keyChar <= 126) {
				correctFlg = false;
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
		setTitle("Typing practice");
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
		JLabel cc = new JLabel("Progress : ");
		cc.setLocation(39, 5);
		cc.setSize(200, 40);
		cc.setFont(new Font("Arial", Font.BOLD, 20));
		countnum.setLocation(190, 5);
		countnum.setSize(200, 40);
		countnum.setFont(new Font("Arial", Font.PLAIN, 20));
		JLabel cp = new JLabel("Correct Percent  : ");
		cp.setLocation(10, 50);
		cp.setSize(200, 40);
		cp.setFont(new Font("Arial", Font.BOLD, 20));
		correctPercent.setLocation(190, 50);
		correctPercent.setSize(200, 40);
		correctPercent.setFont(new Font("Arial", Font.PLAIN, 20));
		JLabel w = new JLabel("WPM  : ");
		w.setLocation(450, 30);
		w.setSize(200, 40);
		w.setFont(new Font("Arial", Font.BOLD, 20));
		wpm.setLocation(530, 30);
		wpm.setSize(200, 40);
		wpm.setFont(new Font("Arial", Font.PLAIN, 20));
		p.add(cc);
		p.add(cp);
		p.add(w);
		p.add(countnum);
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

	public Challenge() {

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
