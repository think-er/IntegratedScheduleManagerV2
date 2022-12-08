package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Control.ViewCompSchedule;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import DB.DB_Conn_Query;
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
	private JButton eventNameBtn;
	
	private JButton eventCompBtn;
	
	private boolean fixMode = false;
	// 고정 여부
	
	public void setFixMode(boolean mode) {
		this.fixMode = mode;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
		String htmlText = "<HTML><body>"+ this.eventName + "</body></HTML>";
		this.eventNameBtn.setText(htmlText);
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
			eventNameBtn.setVisible(false);
			// 이벤트 이름을 끈다.
		}
		
		// 그것이 아니라면
		else if(this.eventMode == 1) {
			eventAddBtn.setVisible(false);
			// 이벤트 추가 버튼을 끈다.
			eventNameBtn.setVisible(true);
			if(this.fixMode == false)
				eventNameBtn.setBackground(Color.WHITE);
			else 
				eventNameBtn.setBackground(Color.LIGHT_GRAY);
			// 이벤트 이름을 킨다.
		}
		
		else if(this.eventMode == 2) {
			eventAddBtn.setVisible(false);
			// 이벤트 추가 버튼을 끈다.
			eventNameBtn.setVisible(true);
			// 이벤트 이름을 킨다.
			if(this.fixMode == false)
				eventNameBtn.setBackground(Color.CYAN);
			else 
				eventNameBtn.setBackground(Color.BLUE);
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
			eventNameBtn.setVisible(false);
			eventCompBtn.setVisible(true);
		}
	}
	
	public EventUI() {
		
		// 추가
		eventAddBtn = new JButton("+");
		eventAddBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		eventAddBtn.setBounds(0, 0, 90, 80);
		
		eventAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HomeUI.LEVEL.equals("일반")) {
					PersonalUI viewUI = new PersonalUI(HomeUI.ID);
					// 0 1 2 3 4 5 6
					// ... 13 index
					LocalDate calDate = HomeUI.startOfCurrentWeek;
					calDate = calDate.plusDays(y);
					viewUI.yearField.setText(Integer.toString(calDate.getYear()));
					viewUI.monthBox.setSelectedIndex(calDate.getMonthValue()-1);
					viewUI.dayBox.setSelectedIndex(calDate.getDayOfMonth()-1);
					viewUI.stHourBox.setSelectedIndex(x);
					if(x+1 == 14)
						viewUI.edHourBox.setSelectedIndex(x);
					else
						viewUI.edHourBox.setSelectedIndex(x+1);
					}
				else if(HomeUI.LEVEL.equals("관리")) {
					if(HomeUI.viewCompEventMode == false) {
						PersonalUI viewUI = new PersonalUI(HomeUI.ID);
						// 0 1 2 3 4 5 6
						// ... 13 index
						LocalDate calDate = HomeUI.startOfCurrentWeek;
						calDate = calDate.plusDays(y);
						viewUI.yearField.setText(Integer.toString(calDate.getYear()));
						viewUI.monthBox.setSelectedIndex(calDate.getMonthValue()-1);
						viewUI.dayBox.setSelectedIndex(calDate.getDayOfMonth()-1);
						viewUI.stHourBox.setSelectedIndex(x);
						if(x+1 == 14)
							viewUI.edHourBox.setSelectedIndex(x);
						else
							viewUI.edHourBox.setSelectedIndex(x+1);
						}
					else {
						IntegrationUI viewUI = new IntegrationUI(HomeUI.ID);
						viewUI.Integration.setVisible(true);
						// 0 1 2 3 4 5 6
						// ... 13 index
						LocalDate calDate = HomeUI.startOfCurrentWeek;
						calDate = calDate.plusDays(y);
						viewUI.yearField.setText(Integer.toString(calDate.getYear()));
						viewUI.monthBox.setSelectedIndex(calDate.getMonthValue()-1);
						viewUI.dayBox.setSelectedIndex(calDate.getDayOfMonth()-1);
						viewUI.stHourBox.setSelectedIndex(x);
						if(x+1 == 14)
							viewUI.edHourBox.setSelectedIndex(x);
						else
							viewUI.edHourBox.setSelectedIndex(x+1);
						}
					}
				}
			});
		
		
		
		// 이벤트 보기
		eventNameBtn = new JButton();
		eventNameBtn.setFont(new Font("나눔고딕", Font.BOLD, 11));
		eventNameBtn.setBounds(0, 0, 90, 80);
		eventNameBtn.setHorizontalAlignment(SwingConstants.CENTER); 
		
		// 이벤트 이름 버튼 클릭시 정보 조회/수정/삭제
		eventNameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (eventMode == 1) {
					PersonalUI viewUI = new PersonalUI(HomeUI.ID);
					DB_Conn_Query db = new DB_Conn_Query();
					String sql = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 "
							+ "FROM 스케줄 "
							+ "WHERE 스케줄_이름 = '"+eventName+"' AND 유저_아이디 = "+HomeUI.viewUser;
					ResultSet rs = db.executeQuery(sql);
					viewUI.personalList.setSelectedValue(eventName, true);
					try {
						while(rs.next()) {
							viewUI.titleField.setText(eventName);
							if(rs.getString("고정여부").equals("1")) {		//고정 : 날짜 입력 필요 x
								//yearField.setText((d.getYear()).toString());
								viewUI.enabled("1");
								viewUI.weekBox.setSelectedIndex(viewUI.getWeek(rs.getString("요일")));
							}
							else {	//비고정 : 날짜 입력 필요, 요일 자동 표시
								Date date = rs.getDate("날짜");
								viewUI.enabled("0");
								SimpleDateFormat y_date = new SimpleDateFormat("yyyy");
								SimpleDateFormat m_date = new SimpleDateFormat("MM");
								SimpleDateFormat d_date = new SimpleDateFormat("dd");
								viewUI.yearField.setText(y_date.format(date));
								viewUI.monthBox.setSelectedIndex(Integer.parseInt(m_date.format(date))-1);
								viewUI.dayBox.setSelectedIndex(Integer.parseInt(d_date.format(date))-1);
								
							}
							viewUI.stHourBox.setSelectedIndex(rs.getInt("시작시간")-9);
							viewUI.edHourBox.setSelectedIndex(rs.getInt("종료시간")-9);
							viewUI.memoArea.setText(rs.getString("메모"));
						}
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				else if(eventMode == 2) {
					IntegrationUI viewUI = new IntegrationUI(HomeUI.ID);
					viewUI.Integration.setVisible(true);
					DB_Conn_Query db = new DB_Conn_Query();
					String query = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 "
							+ "FROM 통합스케줄 "
							+ "WHERE 통합스케줄_이름 = '"+eventName+"' AND "
									+ "통합스케줄.팀_번호 = (SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+HomeUI.viewUser+")";
					ResultSet rs = db.executeQuery(query);
					viewUI.integrationList.setSelectedValue(eventName, true);
					try {
						while(rs.next()) {
							viewUI.titleField.setText(eventName);
							if(rs.getString("고정여부").equals("1")) {		//고정 : 날짜 입력 필요 x
								//yearField.setText((d.getYear()).toString());
								viewUI.enabled("1");
								viewUI.weekBox.setSelectedIndex(viewUI.getWeek(rs.getString("요일")));
							}
							else {	//비고정 : 날짜 입력 필요, 요일 자동 표시
								Date date = rs.getDate("날짜");
								viewUI.enabled("0");
								SimpleDateFormat y_date = new SimpleDateFormat("yyyy");
								SimpleDateFormat m_date = new SimpleDateFormat("MM");
								SimpleDateFormat d_date = new SimpleDateFormat("dd");
								viewUI.yearField.setText(y_date.format(date));
								viewUI.monthBox.setSelectedIndex(Integer.parseInt(m_date.format(date))-1);
								viewUI.dayBox.setSelectedIndex(Integer.parseInt(d_date.format(date))-1);
								
							}
							viewUI.stHourBox.setSelectedIndex(rs.getInt("시작시간")-9);
							viewUI.edHourBox.setSelectedIndex(rs.getInt("종료시간")-9);
							viewUI.memoArea.setText(rs.getString("메모"));
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		
		// -------------------------------------------------------------------------------------------
		
		
		// 통합 일정 겹쳐지는 칸 보기
		eventCompBtn = new JButton();
		eventCompBtn.setBackground(new Color(0, 0, 0));
		eventCompBtn.setBounds(0, 0, 90, 80);
		eventCompBtn.setEnabled(false);
		eventCompBtn.setVisible(false);
		
		add(eventAddBtn);
		add(eventNameBtn);
		add(eventCompBtn);
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
	}
}
