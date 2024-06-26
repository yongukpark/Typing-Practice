package SharePackage;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	private ImageIcon backgroundImgIcon = new ImageIcon("image/background_image.jpeg");
	private Image backgroundImg = backgroundImgIcon.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
	}
}