package UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
public class MainFrame extends JFrame{
	public static JFrame frame;
	public MainFrame() {
		init();
	}
	
	private void init() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon("img/IntegratedScheduleManager.png");
		frame.setIconImage(img.getImage());
		new LoginUI();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	//화면 중앙 배치
	}
}
