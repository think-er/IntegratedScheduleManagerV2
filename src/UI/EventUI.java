package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class EventUI extends JPanel {
	public int x;
	public int y;
	public String name;
	public EventUI() {
		// 추가
//		ImageIcon eventAddIcon = new ImageIcon("img/plus.png");
//		Image img = eventAddIcon.getImage();
//		Image eventAddImg = img.getScaledInstance(20, 20, img.SCALE_DEFAULT);
//		ImageIcon changeIcon = new ImageIcon(eventAddImg);
		
		
		
		JButton eventAddBtn = new JButton("+");
		eventAddBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		eventAddBtn.setBounds(0, 0, 90, 80);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(90, 80));
		setLayout(null);
		add(eventAddBtn);
	}
}
