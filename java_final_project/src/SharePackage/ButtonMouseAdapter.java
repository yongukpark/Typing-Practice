package SharePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class ButtonMouseAdapter extends MouseAdapter{
	public void mouseEntered(MouseEvent e)
	{
		JButton button = (JButton)e.getSource();
		button.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		button.setFont(new Font("Arial", Font.ITALIC, 40));
	}
	public void mouseExited(MouseEvent e)
	{
		JButton button = (JButton)e.getSource();
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		button.setFont(new Font("Arial", Font.BOLD, 40));
	}
}
