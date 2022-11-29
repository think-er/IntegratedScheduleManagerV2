package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EventUI extends JPanel {
	private int x;
	private int y;
	public boolean eventMode = false;
	// false == eventAddBtn / true == eventNameLabel
	private String eventName;
	private String eventMemo;
	private JButton eventAddBtn;
	private JLabel eventNameLabel;
	
	private JButton eventCompBtn;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setEventMemo(String eventMemo) {
		this.eventMemo = eventMemo;
	}
	
	public void setEventName(String eventName, EventUI event) {
		this.eventName = eventName;
		event.eventNameLabel.setText(this.eventName);
	}
	
	public void setEventMode(Boolean Mode) {
		this.eventMode = Mode;
	}
	
	public void viewEventMode() {
		if(!this.eventMode) {
			eventAddBtn.setVisible(true);
			eventNameLabel.setVisible(false);
		}
		else {
			eventAddBtn.setVisible(false);
			eventNameLabel.setVisible(true);
		}
	}
	
	public EventUI() {
		
		eventAddBtn = new JButton("+");
		eventAddBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		eventAddBtn.setBounds(0, 0, 90, 80);
		
		eventNameLabel = new JLabel();
		eventNameLabel.setFont(new Font("나눔고딕", Font.BOLD, 10));
		eventNameLabel.setBounds(0, 0, 90, 80);
		
		eventCompBtn = new JButton();
		eventCompBtn.setBackground(new Color(0, 0, 0));
		eventCompBtn.setBounds(0, 0, 90, 80);
		eventCompBtn.setEnabled(false);
		eventCompBtn.setVisible(false);
		
		add(eventAddBtn);
		add(eventNameLabel);
		add(eventCompBtn);
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
	}
}
