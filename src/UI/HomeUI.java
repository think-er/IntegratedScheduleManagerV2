package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

//import Control.Show_Schedule;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;
//import Entity.Schedule;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.util.ArrayList;

public class HomeUI extends JFrame{
	
	// homePanel
	public JPanel homePanel;
	private JLabel homeUserLabel;
	private JLabel homeManagerAuthLabel;
	private JCalendar homeCalendar;
	private JButton PersonalBtn;
	
	// homeIntegrationPanel
	private JPanel homeIntegrationPanel;
	
	// homeScheduleListPanel
	private JPanel homeScheduleListPanel;
	
	// homeSchedulePanel
	private JPanel homeSchedulePanel;
	private JScrollPane homeScheduleScrollPane;
	
	public static final int HOME_FRAME_X = 0;
	public static final int HOME_FRAME_Y = 0;
	public static final int HOME_FRAME_WIDTH = 1000;
	public static final int HOME_FRAME_HEIGHT = 620;
	
	public static final int HOME_SCHEDULELIST_PANEL_X = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_Y = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_WIDTH = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_HEIGHT = 0; 
	
	public static final int HOME_SCHEDULE_PANEL_X = 212; 
	public static final int HOME_SCHEDULE_PANEL_Y = 86; 
	public static final int HOME_SCHEDULE_PANEL_WIDTH = 734; 
	public static final int HOME_SCHEDULE_PANEL_HEIGHT = 456; 
	
	public static final int HOME_SCHEDULE_SCROLLPANE_X = 100;
	public static final int HOME_SCHEDULE_SCROLLPANE_Y = 10;
	public static final int HOME_SCHEDULE_SCROLLPANE_WIDTH = 595;
	public static final int HOME_SCHEDULE_SCROLLPANE_HEIGHT = 436;
	
	public static final int HOME_USER_LABEL_X = 850;
	public static final int HOME_USER_LABEL_Y = 30;
	public static final int HOME_USER_LABEL_WIDTH = 100;
	public static final int HOME_USER_LABEL_HEIGHT = 30;
	
	public static final int HOME_CALENDAR_X = 0;
	public static final int HOME_CALENDAR_Y = 0;
	public static final int HOME_CALENDAR_WIDTH = 200;
	public static final int HOME_CALENDAR_HEIGHT = 200;
	
	private JScrollPane teamListScrollPane;
	private JButton IntegrationButton;

	public static String[] dayOfWeekColumn = { "", "월", "화", "수", "목", "금", "토", "일" };
	private JLabel idLabel;
	private JLabel levelLabel;
	
	private String ID;
	private String LEVEL;
	
	private JPanel homeScheduleColumnPanel;
	public static EventUI[][] event;
	
	private JButton viewCompEventBtn;
	private Boolean viewCompEventBoolean;
	
	public static int Y;
	public static int M;
	public static int D;
	public static String StartOfWeekFormat;
	public static String EndOfWeekFormat;
	
	public String viewUser;
	
	DB_Conn_Query db = new DB_Conn_Query();
	
	public HomeUI() {
		
		this("20203089","관리");
	}

	public HomeUI(String id, String level) {
		viewUser = id;
		ID=id;
		LEVEL=level;
		

		MainFrame.frame.getContentPane().setLayout(null);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 홈 패널 (화면 전환을 위한)
		homePanel = new JPanel();
		homePanel.setBounds(HOME_FRAME_X, HOME_FRAME_Y, 
				1000, 580);
		homePanel.setLayout(null);
		MainFrame.frame.getContentPane().add(homePanel);
		
		// 홈 패널: 학번
		homeUserLabel = new JLabel(String.format("학번: %s", RegisterUI.ID));
		homeUserLabel.setBounds(850, 30, 
				49, 30);
		homePanel.add(homeUserLabel);
		
		// 홈 패널: 달력
		homeCalendar = new JCalendar();
		homeCalendar.setBounds(HOME_CALENDAR_X, HOME_CALENDAR_Y, 
				HOME_CALENDAR_WIDTH, HOME_CALENDAR_HEIGHT);
		homePanel.add(homeCalendar);
		
		
		// 홈 패널: 홈 일정 목록 패널
		homeScheduleListPanel = new JPanel();
		homeScheduleListPanel.setBounds(HOME_SCHEDULELIST_PANEL_X,
				HOME_SCHEDULELIST_PANEL_Y, HOME_SCHEDULELIST_PANEL_WIDTH,
				HOME_SCHEDULELIST_PANEL_HEIGHT);
		homeScheduleListPanel.setLayout(null);
		
		// 홈 패널: 권한
		
		homeManagerAuthLabel = new JLabel("등급: ");
		homeManagerAuthLabel.setBounds(850, 50, 
				49, 30);
		homePanel.add(homeManagerAuthLabel);
		
		// -----------------------------------------------------------------
		idLabel = new JLabel("");
		idLabel.setBounds(901, 38, 73, 15);
		homePanel.add(idLabel);
		
		levelLabel = new JLabel("");
		levelLabel.setBounds(901, 58, 73, 15);
		homePanel.add(levelLabel);
		
		idLabel.setText(ID);
		levelLabel.setText(LEVEL);
		
		MainFrame.frame.setTitle("통합 일정 관리 프로그램");
		MainFrame.frame.setSize(HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	
		
		
		// -----------------------------------------------------------------
		// 날짜 변경 패널
		homeScheduleColumnPanel = new JPanel();
		homeScheduleColumnPanel.setBounds(212, 86, 730, 50);
		homeScheduleColumnPanel.setBorder(new LineBorder(new Color(255, 0, 0)));

		homeScheduleColumnPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		JPanel homeScheduleColumn_0 = new JPanel();
		homeScheduleColumn_0.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_1 = new JPanel();
		homeScheduleColumn_1.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_2 = new JPanel();
		homeScheduleColumn_2.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_3 = new JPanel();
		homeScheduleColumn_3.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_4 = new JPanel();
		homeScheduleColumn_4.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_5 = new JPanel();
		homeScheduleColumn_5.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_6 = new JPanel();
		homeScheduleColumn_6.setBorder(new LineBorder(new Color(255, 0, 0)));
		JPanel homeScheduleColumn_7 = new JPanel();
		homeScheduleColumn_7.setBorder(new LineBorder(new Color(255, 0, 0)));
		
		homeScheduleColumnPanel.add(homeScheduleColumn_0);
		homeScheduleColumnPanel.add(homeScheduleColumn_1);
		
		//----------------------------------- 캘린더에서 년 월 일 받아오는 코드----------------------------------------\\
		
		JLabel Sun = new JLabel();
		Sun.setForeground(Color.RED);
		homeScheduleColumn_1.add(Sun);
		homeScheduleColumnPanel.add(homeScheduleColumn_2);
		
		JLabel Mon = new JLabel();
		homeScheduleColumn_2.add(Mon);
		homeScheduleColumnPanel.add(homeScheduleColumn_3);
		
		JLabel Tue = new JLabel();
		homeScheduleColumn_3.add(Tue);
		homeScheduleColumnPanel.add(homeScheduleColumn_4);
		
		JLabel Wed = new JLabel();
		homeScheduleColumn_4.add(Wed);
		homeScheduleColumnPanel.add(homeScheduleColumn_5);
		
		JLabel Thu = new JLabel();
		homeScheduleColumn_5.add(Thu);
		homeScheduleColumnPanel.add(homeScheduleColumn_6);
		
		JLabel Fri = new JLabel();
		homeScheduleColumn_6.add(Fri);
		homeScheduleColumnPanel.add(homeScheduleColumn_7);
		
		JLabel Sat = new JLabel();
		Sat.setForeground(Color.BLUE);
		homeScheduleColumn_7.add(Sat);
		
		homePanel.add(homeScheduleColumnPanel);
		// -----------------------------------------------------------------
		
		Y = homeCalendar.getYearChooser().getYear();
		M = homeCalendar.getMonthChooser().getMonth();
		D = homeCalendar.getDayChooser().getDay();
		
		LocalDate now = LocalDate.of(Y, M+1, D); 

		// determine country (Locale) specific first day of current week
		DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
		LocalDate startOfCurrentWeek = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

		// determine last day of current week
		DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6); // or minus(1)
		LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

		// Print the dates of the current week
		LocalDate printDate = startOfCurrentWeek;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//for (int i=0; i < 7; i++) {
		//    System.out.println(printDate.format(formatter));
		//    printDate = printDate.plusDays(1);
		//}
		Sun.setText(printDate.format(formatter));
		StartOfWeekFormat = printDate.format(formatter);
		
		printDate = printDate.plusDays(1);
		Mon.setText(printDate.format(formatter));
		printDate = printDate.plusDays(1);
		Tue.setText(printDate.format(formatter));
		printDate = printDate.plusDays(1);
		Wed.setText(printDate.format(formatter));
		printDate = printDate.plusDays(1);
		Thu.setText(printDate.format(formatter));
		printDate = printDate.plusDays(1);
		Fri.setText(printDate.format(formatter));
		printDate = printDate.plusDays(1);
		Sat.setText(printDate.format(formatter));
		
		EndOfWeekFormat = printDate.plusDays(1).format(formatter);
		
		// -----------------------------------------------------------------
		// 스케줄 표현
		
		event = new EventUI[14][7];
		
		homeSchedulePanel = new JPanel();
		homeSchedulePanel.setLayout(new GridLayout(14, 8));
		
		// 표 배치
		for(int i=0; i<14; i++) {
			
			// 시간 행 패널
			JPanel timeUI = new JPanel();
			timeUI.setPreferredSize(new Dimension(90, 80));
			timeUI.setBorder(new LineBorder(new Color(0, 0, 0)));
			homeSchedulePanel.add(timeUI);
			
			// 시간 행 패널의 라벨
			JLabel timeLabel = new JLabel();
			timeUI.add(timeLabel);
			timeLabel.setText(PersonalUI.hourCb[i] + ": 00");
			
			// 세부 일정 데이터가 띄워지는 패널
			for(int j=0; j<7; j++) {
				event[i][j] = new EventUI();
				event[i][j].setX(i);
				event[i][j].setY(j);
				homeSchedulePanel.add(event[i][j]);
			}
		}
		
		update_Schedule(viewUser);
		
		homeCalendar.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent evt)
			{
				Y = homeCalendar.getYearChooser().getYear();
				M = homeCalendar.getMonthChooser().getMonth();
				D = homeCalendar.getDayChooser().getDay();
				
				LocalDate now = LocalDate.of(Y, M+1, D); 

				// determine country (Locale) specific first day of current week
				DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
				LocalDate startOfCurrentWeek = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

				// determine last day of current week
				DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6); // or minus(1)
				LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

				// Print the dates of the current week
				LocalDate printDate = startOfCurrentWeek;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				//for (int i=0; i < 7; i++) {
				//    System.out.println(printDate.format(formatter));
				//    printDate = printDate.plusDays(1);
				//}
				Sun.setText(printDate.format(formatter));
				StartOfWeekFormat = printDate.format(formatter);
				
				printDate = printDate.plusDays(1);
				Mon.setText(printDate.format(formatter));
				printDate = printDate.plusDays(1);
				Tue.setText(printDate.format(formatter));
				printDate = printDate.plusDays(1);
				Wed.setText(printDate.format(formatter));
				printDate = printDate.plusDays(1);
				Thu.setText(printDate.format(formatter));
				printDate = printDate.plusDays(1);
				Fri.setText(printDate.format(formatter));
				printDate = printDate.plusDays(1);
				Sat.setText(printDate.format(formatter));
				
				EndOfWeekFormat = printDate.plusDays(1).format(formatter);
				
				update_Schedule(viewUser);
				
			}
		});
		//----------------------------------- 캘린더에서 년 월 일 받아오는 코드----------------------------------------\\
		
		homeScheduleScrollPane = new JScrollPane(homeSchedulePanel);
		homeScheduleScrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		homeScheduleScrollPane.setBounds(212, 136, 750, 407);
		homePanel.add(homeScheduleScrollPane);	
		// -----------------------------------------------------------------
		
		// -----------------------------------------------------------------
		// 공통된 시간표들을 잠시 표현하는 버튼
		viewCompEventBtn = new JButton("공통 시간 켜기");
		viewCompEventBtn.setBounds(700, 30, 130, 30);
		homePanel.add(viewCompEventBtn);
		viewCompEventBoolean = false;
		viewCompEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 공통 시간 켜기
				if (viewCompEventBoolean == false) {
					viewCompEventBtn.setText("공통 시간 끄기");
					viewCompEventBoolean = true;
					view_CompSchedule(viewCompEventBoolean);
				}
				
				// 공통 시간 끄기
				else {
					viewCompEventBtn.setText("공통 시간 켜기");
					viewCompEventBoolean = false;
					view_CompSchedule(viewCompEventBoolean);
				}
			}
		});
		
		
		// 홈 통합 일정 패널: 통합 일정 패널
		homeIntegrationPanel = new JPanel();
		homeIntegrationPanel.setBounds(0, 210, 200, 356);
		homePanel.add(homeIntegrationPanel);
		
		// 홈 통합 일정 패널: 학생 리스트 스크롤 패널
		teamListScrollPane = new JScrollPane();
		teamListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		IntegrationButton = new JButton("통합 일정 관리");
		
		
		// 관리자가 아닐 시 통합일정생성 버튼 숨기기
		if (LEVEL.equals("일반")) {
			IntegrationButton.setVisible(false);
		}

		
		IntegrationButton.setFont(new Font("굴림", Font.PLAIN, 12));
		IntegrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//HomeUI.setVisible(false) 삭제
				IntegrationUI Integration = new IntegrationUI(id);
				Integration.Integration.setVisible(true);
			}
		});
		
		

		
		JLabel PersonalLabel = new JLabel("팀원 시간표 조회");
		PersonalLabel.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		
		PersonalBtn = new JButton("개인 일정 관리");
		PersonalBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		PersonalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PersonalUI(ID);
			}
		});
		
		GroupLayout gl_homeIntegrationPanel = new GroupLayout(homeIntegrationPanel);
		gl_homeIntegrationPanel.setHorizontalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_homeIntegrationPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_homeIntegrationPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(teamListScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(PersonalBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(IntegrationButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(PersonalLabel))
					.addContainerGap())
		);
		gl_homeIntegrationPanel.setVerticalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
					.addComponent(PersonalBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationButton)
					.addGap(10)
					.addComponent(PersonalLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(teamListScrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		//-----------------------------팀원 이름 리스트 추가--------------------------
		JList<String> teamList = new JList<String>();
		
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		try {
			String sql = "SELECT 이름 "
					+ "FROM 유저,소속 "
					+ "WHERE 유저.유저_아이디=소속.유저_아이디 "
					+ "AND 소속.팀_번호 in (SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+ID+")";
			
			ResultSet src = db.executeQuery(sql);
			System.out.println(ID);
			while(src.next()) {
				listModel.addElement(src.getString("이름"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		teamList.setModel(listModel);
		
		teamList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	//이거 없으면 mouse 눌릴때, 뗄때 각각 한번씩 호출되서 총 두번 호출
					System.out.println("selected :"+teamList.getSelectedValue());
				}
			}
		});
		//--------------------------------------------------------------------------
		
		teamListScrollPane.setViewportView(teamList);
		homeIntegrationPanel.setLayout(gl_homeIntegrationPanel);
		
		
	}
	
	public void view_CompSchedule(boolean mode) {
		try {
			String sql = "SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 ="+ID;
			ResultSet rs = db.executeQuery(sql);
			
			String _teamNum = "";
			ArrayList<String> _teamUser = new ArrayList<String>();
			
			while(rs.next()) {
				_teamNum = rs.getString(1);
				System.out.println("팀_번호 = " + _teamNum);
			}
			
			sql = "SELECT 유저_아이디 FROM 소속 WHERE 팀_번호 = "+_teamNum;
			rs = db.executeQuery(sql);
			
			while(rs.next()) {
				_teamUser.add(rs.getString(1));
			}
			
			for(int k=0; k<_teamUser.size(); k++) {
				sql = "SELECT 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE 유저_아이디="+_teamUser.get(k) +
						"AND 날짜 BETWEEN TO_DATE('" + StartOfWeekFormat + "', 'YYYY-MM-DD') "+ 
						"AND TO_DATE('" + EndOfWeekFormat + "', 'YYYY-MM-DD')" +
						"OR 고정여부=1";
				System.out.println("팀원 = " + _teamUser.get(k));
				rs = db.executeQuery(sql);
				while(rs.next()) {
					String days = rs.getString(1);
					int startTime = rs.getInt(3);
					int endTime = rs.getInt(4);
					
					System.out.println(_teamUser.get(k) + " : " + startTime + " : " + endTime);
					
					// 요일 문자가 아닌 숫자로 받기
					int days2 = 0;
					
					if (days.equals("일"))
						days2 = 0;
					else if (days.equals("월"))
						days2 = 1;
					else if (days.equals("화"))
						days2 = 2;
					else if (days.equals("수"))
						days2 = 3;
					else if (days.equals("목"))
						days2 = 4;
					else if (days.equals("금"))
						days2 = 5;
					else if (days.equals("토"))
						days2 = 6;
					
					
					for(int i=startTime - 9; i <= endTime - 9; i++) {
						event[i][days2].setEventCompMode(mode);
						event[i][days2].viewEventCompMode();
					}
				}
			}
		} catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	public void update_Schedule(String id) {
		
		for(int i=0; i<14; i++) {
			for(int j=0; j<7; j++) {
				event[i][j].setEventMode(false);
				event[i][j].viewEventMode();
			}
		}
		// 데이터베이스에서 로그인한 개인 시간표 가져오기
		try {
			String sql = "SELECT 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE 유저_아이디="+id +
					"AND 날짜 BETWEEN TO_DATE('" + StartOfWeekFormat + "', 'YYYY-MM-DD') " +
					"AND TO_DATE('" + EndOfWeekFormat + "', 'YYYY-MM-DD')" +
					"OR 고정여부=1";

			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				// 요일 문자가 아닌 숫자로 받기
				String days = rs.getString(2);	
				
//				String date = rs.getString(6);
//				String[] dateFormat;
				
				// 고정일 경우 필요 없음
//				if(fix.equals("0")) {
//					dateFormat = date.split("-");
//					int year = Integer.parseInt(dateFormat[0]);
//					int month = Integer.parseInt(dateFormat[1]);
//					int day = Integer.parseInt(dateFormat[2].substring(0,2));
//				}
//				
				int days2 = 0;
				
				if (days.equals("일"))
					days2 = 0;
				else if (days.equals("월"))
					days2 = 1;
				else if (days.equals("화"))
					days2 = 2;
				else if (days.equals("수"))
					days2 = 3;
				else if (days.equals("목"))
					days2 = 4;
				else if (days.equals("금"))
					days2 = 5;
				else if (days.equals("토"))
					days2 = 6;
				int startTime = rs.getInt(3);
				int endTime = rs.getInt(4);
				String memo = rs.getString(6);
				
				for(int i=startTime - 9; i <= endTime - 9; i++) {
					event[i][days2].setEventName(name, event[i][days2]);
					event[i][days2].setEventMemo(memo);
					event[i][days2].setEventMode(true);
					event[i][days2].viewEventMode();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		};
	}
}