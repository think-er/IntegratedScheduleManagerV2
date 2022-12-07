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
	
	public int eventMode = 0;
	
	// 0 = 추가 전
	// 1 = 개인
	// 2 = 통합
	
	public boolean eventCompView = false;
	
	private String eventName;
	private JButton eventAddBtn;
	private JLabel eventNameLabel;
	
	private JButton eventCompBtn;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
		String htmlText = "<HTML><body><center>"+ this.eventName + "</center></body></HTML>";
		System.out.println(htmlText);
		this.eventNameLabel.setText(htmlText);
	}
	
	public void setEventMode(int Mode) {
		this.eventMode = Mode;
	}
	
	public void setEventCompViewMode(boolean Mode) {
		this.eventCompView = Mode;
	}
	
	
	public void viewEventMode() {
		if(this.eventMode == 0) {
			eventAddBtn.setVisible(true);
			// 이벤트 추가 버튼을 킨다. 
			eventNameLabel.setVisible(false);
			// 이벤트 이름을 끈다.
		}
		
		// 그것이 아니라면
		else if(this.eventMode == 1) {
			eventAddBtn.setVisible(false);
			// 이벤트 추가 버튼을 끈다.
			eventNameLabel.setVisible(true);
			setBackground(Color.CYAN);
			// 이벤트 이름을 킨다.
		}
		
		else if(this.eventMode == 2) {
			eventAddBtn.setVisible(false);
			// 이벤트 추가 버튼을 끈다.
			eventNameLabel.setVisible(true);
			// 이벤트 이름을 킨다.
			setBackground(Color.BLUE);
		}
	}
	
	public void viewEventCompMode() {
		// 이벤트 공통 보기를 끄고 키는 경우
		if(eventCompView == false) {
			eventCompBtn.setVisible(false);
			// 이벤트 공통 보기를 끈다.
			viewEventMode();
			// 이벤트 모드를 보여준다.
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
		eventNameLabel = new JLabel("", JLabel.CENTER);
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
