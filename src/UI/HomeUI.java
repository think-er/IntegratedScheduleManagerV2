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
import java.awt.Frame;

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
import javax.swing.SwingConstants;

import Control.UpdateSchedule;
import Control.ViewCompSchedule;
import javax.swing.border.EmptyBorder;

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
	
	public static String ID;
	private String LEVEL;
	
	private JPanel homeScheduleColumnPanel;
	public static EventUI[][] event;
	
	private JButton viewCompEventBtn;
	
	public static int Y;
	public static int M;
	public static int D;
	public static String StartOfWeekFormat;
	public static String EndOfWeekFormat;
	
	public static String viewUser;
	// 프로그램이 돌아가는 와중 켜져있는지 확인한다.
	public static Boolean viewCompEventMode = false;
	
	
	public static LocalDate startOfCurrentWeek;
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
		homePanel.setBackground(Color.WHITE);
		
		
		// 홈 패널: 학번
		homeUserLabel = new JLabel("학번: ");
		homeUserLabel.setBounds(850, 30, 
				49, 30);
		homePanel.add(homeUserLabel);
		
		// 홈 패널: 달력
		homeCalendar = new JCalendar();
		homeCalendar.getDayChooser().getDayPanel().setBorder(null);
		homeCalendar.getYearChooser().setBorder(null);
		homeCalendar.getMonthChooser().setBorder(null);
		homeCalendar.getMonthChooser().getComboBox().setBackground(new Color(255, 255, 255));
		homeCalendar.getMonthChooser().getComboBox().setForeground(new Color(0, 0, 0));
		homeCalendar.getDayChooser().getDayPanel().setBackground(new Color(255, 255, 255));
		homeCalendar.getDayChooser().setDecorationBackgroundColor(new Color(255, 255, 255));
		homeCalendar.getYearChooser().getSpinner().setBackground(new Color(255, 255, 255));
		homeCalendar.getYearChooser().setBackground(new Color(255, 255, 255));
		homeCalendar.getMonthChooser().getSpinner().setBackground(new Color(255, 255, 255));
		homeCalendar.getMonthChooser().setBackground(new Color(255, 255, 255));
		homeCalendar.getDayChooser().setBorder(null);
		homeCalendar.getDayChooser().setDayBordersVisible(true);
		homeCalendar.getDayChooser().setBackground(new Color(255, 255, 255));
		homeCalendar.getDayChooser().setWeekOfYearVisible(false);
		homeCalendar.setBounds(HOME_CALENDAR_X, HOME_CALENDAR_Y, 
				HOME_CALENDAR_WIDTH, HOME_CALENDAR_HEIGHT);
		homeCalendar.setDecorationBackgroundColor(new Color(255, 255, 255));
		homeCalendar.setFont(new Font("나눔고딕", Font.BOLD, 13));
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
		MainFrame.frame.setLocationRelativeTo(null);	//화면 중앙 배치
		
		
		// -----------------------------------------------------------------
		// 날짜 변경 패널
		homeScheduleColumnPanel = new JPanel();
		homeScheduleColumnPanel.setBounds(212, 86, 730, 50);

		homeScheduleColumnPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		JPanel homeScheduleColumn_0 = new JPanel();
		homeScheduleColumn_0.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_0.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_1 = new JPanel();
		homeScheduleColumn_1.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_2 = new JPanel();
		homeScheduleColumn_2.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_3 = new JPanel();
		homeScheduleColumn_3.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_4 = new JPanel();
		homeScheduleColumn_4.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_5 = new JPanel();
		homeScheduleColumn_5.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_6 = new JPanel();
		homeScheduleColumn_6.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel homeScheduleColumn_7 = new JPanel();
		homeScheduleColumn_7.setBackground(new Color(255, 255, 255));
		homeScheduleColumn_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		homeScheduleColumnPanel.add(homeScheduleColumn_0);
		homeScheduleColumnPanel.add(homeScheduleColumn_1);
		homeScheduleColumn_1.setLayout(null);
		
		JLabel SunLabel = new JLabel();
		SunLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SunLabel.setBounds(8, 28, 75, 15);
		SunLabel.setText("일");
		SunLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		SunLabel.setForeground(Color.RED);
		homeScheduleColumn_1.add(SunLabel);
		
		//----------------------------------- 캘린더에서 년 월 일 받아오는 코드----------------------------------------\\
		
		JLabel Sun = new JLabel();
		Sun.setHorizontalAlignment(SwingConstants.CENTER);
		Sun.setBounds(8, 10, 75, 15);
		Sun.setFont(new Font("나눔고딕", Font.BOLD, 13));
		Sun.setForeground(Color.RED);
		homeScheduleColumn_1.add(Sun);
		homeScheduleColumnPanel.add(homeScheduleColumn_2);
		homeScheduleColumn_2.setLayout(null);
		
		JLabel Mon = new JLabel();
		Mon.setHorizontalAlignment(SwingConstants.CENTER);
		Mon.setBounds(8, 10, 75, 15);
		Mon.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_2.add(Mon);
		homeScheduleColumnPanel.add(homeScheduleColumn_3);
		homeScheduleColumn_3.setLayout(null);
		
		JLabel Tue = new JLabel();
		Tue.setHorizontalAlignment(SwingConstants.CENTER);
		Tue.setBounds(8, 10, 75, 15);
		Tue.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_3.add(Tue);
		homeScheduleColumnPanel.add(homeScheduleColumn_4);
		homeScheduleColumn_4.setLayout(null);
		
		JLabel Wed = new JLabel();
		Wed.setHorizontalAlignment(SwingConstants.CENTER);
		Wed.setBounds(8, 10, 75, 15);
		Wed.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_4.add(Wed);
		homeScheduleColumnPanel.add(homeScheduleColumn_5);
		homeScheduleColumn_5.setLayout(null);
		
		JLabel Thu = new JLabel();
		Thu.setHorizontalAlignment(SwingConstants.CENTER);
		Thu.setBounds(8, 10, 75, 15);
		Thu.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_5.add(Thu);
		homeScheduleColumnPanel.add(homeScheduleColumn_6);
		homeScheduleColumn_6.setLayout(null);
		
		JLabel Fri = new JLabel();
		Fri.setHorizontalAlignment(SwingConstants.CENTER);
		Fri.setBounds(8, 10, 75, 15);
		Fri.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_6.add(Fri);
		homeScheduleColumnPanel.add(homeScheduleColumn_7);
		homeScheduleColumn_7.setLayout(null);
		
		JLabel Sat = new JLabel();
		Sat.setHorizontalAlignment(SwingConstants.CENTER);
		Sat.setBounds(8, 10, 75, 15);
		Sat.setFont(new Font("나눔고딕", Font.BOLD, 13));
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
		startOfCurrentWeek = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
		
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
		Sun.setText("2022-12-04");
		StartOfWeekFormat = printDate.format(formatter);
		
		printDate = printDate.plusDays(1);
		Mon.setText(printDate.format(formatter));
		
		JLabel MonLabel = new JLabel();
		MonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MonLabel.setText("월");
		MonLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		MonLabel.setBounds(15, 28, 60, 15);
		homeScheduleColumn_2.add(MonLabel);
		printDate = printDate.plusDays(1);
		Tue.setText(printDate.format(formatter));
		
		JLabel TueLabel = new JLabel();
		TueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TueLabel.setText("화");
		TueLabel.setBounds(15, 28, 60, 15);
		TueLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_3.add(TueLabel);
		printDate = printDate.plusDays(1);
		Wed.setText(printDate.format(formatter));
		
		JLabel WedLabel = new JLabel();
		WedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WedLabel.setText("수");
		WedLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		WedLabel.setBounds(15, 28, 60, 15);
		homeScheduleColumn_4.add(WedLabel);
		printDate = printDate.plusDays(1);
		Thu.setText(printDate.format(formatter));
		
		JLabel ThuLabel = new JLabel();
		ThuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ThuLabel.setBounds(15, 28, 60, 15);
		ThuLabel.setText("목");
		ThuLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		homeScheduleColumn_5.add(ThuLabel);
		printDate = printDate.plusDays(1);
		Fri.setText(printDate.format(formatter));
		
		JLabel Fri_1 = new JLabel();
		Fri_1.setHorizontalAlignment(SwingConstants.CENTER);
		Fri_1.setText("금");
		Fri_1.setFont(new Font("나눔고딕", Font.BOLD, 13));
		Fri_1.setBounds(15, 28, 60, 15);
		homeScheduleColumn_6.add(Fri_1);
		printDate = printDate.plusDays(1);
		Sat.setText(printDate.format(formatter));
		
		JLabel SatLabel = new JLabel();
		SatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SatLabel.setText("토");
		SatLabel.setFont(new Font("나눔고딕", Font.BOLD, 13));
		SatLabel.setForeground(Color.BLUE);
		SatLabel.setBounds(15, 28, 60, 15);
		homeScheduleColumn_7.add(SatLabel);
		
		EndOfWeekFormat = printDate.plusDays(1).format(formatter);
		
		// -----------------------------------------------------------------
		// 스케줄 표현
		
		event = new EventUI[14][7];
		
		homeSchedulePanel = new JPanel();
		homeSchedulePanel.setBackground(new Color(255, 255, 255));
		homeSchedulePanel.setLayout(new GridLayout(14, 8));
		
		// 표 배치
		for(int i=0; i<14; i++) {
			
			// 시간 행 패널
			JPanel timeUI = new JPanel();
			timeUI.setPreferredSize(new Dimension(90, 80));
			timeUI.setBackground(Color.WHITE);
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
		
		new UpdateSchedule();
		
		homeCalendar.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent evt)
			{
				Y = homeCalendar.getYearChooser().getYear();
				M = homeCalendar.getMonthChooser().getMonth();
				D = homeCalendar.getDayChooser().getDay();
				
				LocalDate now = LocalDate.of(Y, M+1, D); 

				System.out.println(now);
				
				// determine country (Locale) specific first day of current week
				DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
				startOfCurrentWeek = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
				
				// determine last day of current week
				DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6); // or minus(1
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
				
				new UpdateSchedule();
				new ViewCompSchedule();
				
			}
		});
		//----------------------------------- 캘린더에서 년 월 일 받아오는 코드----------------------------------------\\
		
		homeScheduleScrollPane = new JScrollPane(homeSchedulePanel);
		homeScheduleScrollPane.setBorder(null);
		homeScheduleScrollPane.setBounds(211, 136, 750, 407);
		homeScheduleScrollPane.getVerticalScrollBar().setUnitIncrement(30);
		
		homePanel.add(homeScheduleScrollPane);	
		// -----------------------------------------------------------------
		
		// -----------------------------------------------------------------
		// 공통된 시간표들을 잠시 표현하는 버튼
		viewCompEventBtn = new JButton("공통 시간 켜기");
		viewCompEventBtn.setBounds(700, 30, 130, 30);
		homePanel.add(viewCompEventBtn);
		viewCompEventMode = false;
		viewCompEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 공통 시간 켜기
				if (viewCompEventMode == false) {
					viewCompEventBtn.setText("공통 시간 끄기");	
					viewCompEventMode = true;
					new ViewCompSchedule();
				}
				
				// 공통 시간 끄기
				else {
					viewCompEventBtn.setText("공통 시간 켜기");
					viewCompEventMode = false;
					new ViewCompSchedule();
				}
			}
		});
		
		
		// 홈 통합 일정 패널: 통합 일정 패널
		homeIntegrationPanel = new JPanel();
		homeIntegrationPanel.setBounds(0, 210, 200, 356);
		homeIntegrationPanel.setBackground(Color.WHITE);
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
		teamList.setFont(new Font("나눔고딕", Font.BOLD, 13));
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		try {
			// 유저_아이디 들고오는 sql 구문
			String sql = "SELECT 유저.유저_아이디, 유저.이름 "
					+ "FROM 소속, 유저 "
					+ "WHERE 소속.유저_아이디 = 유저.유저_아이디 AND "
					+ "팀_번호 = (SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = " + id + ")";
			
			ResultSet src = db.executeQuery(sql);
			while(src.next()) {
				listModel.addElement(src.getInt(1)+" "+src.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		teamList.setModel(listModel);
		
		teamList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	//이거 없으면 mouse 눌릴때, 뗄때 각각 한번씩 호출되서 총 두번 호출
					
					viewUser = teamList.getSelectedValue();
					String splitViewUser[] = viewUser.split(" ");
					viewUser = splitViewUser[0];
					new UpdateSchedule();
					// 만약 viewCompEventMode가 켜져있을 경우
					new ViewCompSchedule();
				}
			}
		});
		//--------------------------------------------------------------------------
		
		teamListScrollPane.setViewportView(teamList);
		homeIntegrationPanel.setLayout(gl_homeIntegrationPanel);
		
		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				new LoginUI();
				MainFrame.frame.setLocationRelativeTo(null);	//화면 중앙 배치
			}
		});
		logoutBtn.setBounds(212, 30, 130, 30);
		homePanel.add(logoutBtn);
		
	}
}