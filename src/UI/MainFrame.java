package UI;

import javax.swing.JFrame;
public class MainFrame extends JFrame{
	public static JFrame frame;
	public MainFrame() {
		init();
	}
	
	private void init() {
		frame = new JFrame();
		
		new LoginUI();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	//화면 중앙 배치
	}
}
