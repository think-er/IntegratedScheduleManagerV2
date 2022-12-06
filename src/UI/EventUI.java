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
	public boolean eventCompViewMode = false;
	// false == 기본 모드 / true == eventComp 모드
	public boolean eventCompMode = false;
	
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
	
	// 공통 시간을 보여주는 모드
	public void setEventCompViewMode(Boolean CompViewMode) {
		this.eventCompViewMode = CompViewMode;
	}
	
	public void setEventCompMode(Boolean CompMode) {
		this.eventCompMode = CompMode;
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
	
	public void viewEventCompMode() {
		if(!this.eventCompViewMode) {
			eventCompBtn.setVisible(false);
			viewEventMode();
		}
		else {
			eventAddBtn.setVisible(false);
			eventNameLabel.setVisible(false);
			eventCompBtn.setVisible(true);
		}
	}
	
	public EventUI() {
		
		// 추가
		eventAddBtn = new JButton("+");
		eventAddBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		eventAddBtn.setBounds(0, 0, 90, 80);
		
		// 이벤트 보기
		eventNameLabel = new JLabel();
		eventNameLabel.setFont(new Font("나눔고딕", Font.BOLD, 10));
		eventNameLabel.setBounds(0, 0, 90, 80);
		
		// 통합 일정 겹쳐지는 칸 보기
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
