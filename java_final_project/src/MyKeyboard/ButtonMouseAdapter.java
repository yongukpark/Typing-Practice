package MyKeyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

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
