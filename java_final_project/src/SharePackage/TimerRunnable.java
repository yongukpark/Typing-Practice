package SharePackage;

import javax.swing.JLabel;

public class TimerRunnable implements Runnable{
	private JLabel timerLabel;
	public TimerRunnable(JLabel timerLabel)
	{
		this.timerLabel = timerLabel;
	}
	@Override
	public void run()
	{
		int n = -1;
		while(true)
		{
			n++;
			String minute = String.format("%02d", n/60);
			String second = String.format("%02d", n%60);
			timerLabel.setText(minute + ":" + second);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				return;
			}
		}
	}
}
