package ui;

import javax.swing.JFrame;
public class MainFrame {
	public static JFrame frame;
	public MainFrame() {
		init();
	}
	
	private void init() {
		frame = new JFrame();
		new LoginUI();
		frame.setVisible(true);
	}
}
