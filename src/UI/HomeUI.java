package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import Control.Show_Schedule;

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
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;
import Entity.Schedule;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class HomeUI {
	
	// homePanel
	public JPanel homePanel;
	private JLabel homeUserLabel;
	private JLabel homeManagerAuthLabel;
	private JCalendar homeCalendar;
	private JButton toAddEventBtn;
	
	// homeIntegrationPanel
	private JPanel homeIntegrationPanel;
	
	// homeScheduleListPanel
	private JPanel homeScheduleListPanel;
	
	// homeSchedulePanel
	private JPanel homeSchedulePanel;
	private JTable homeScheduleTable;
	private JScrollPane homeScheduleScrollPane;
	
	public static final int HOME_FRAME_X = 0;
	public static final int HOME_FRAME_Y = 0;
	public static final int HOME_FRAME_WIDTH = 1000;
	public static final int HOME_FRAME_HEIGHT = 600;
	
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
	
	private JTable table;
	private JScrollPane PersonalSchedulescrollPane;
	private JButton IntegrationButton_edit;
	private JButton IntegrationButton_add;
	private JButton IntegrationButton_delete;
	
	
	private GridBagLayout gbl;
	
//	private String name;
//	private String yoil;
//	private int startTime;
//	private int endTime;
//	private String fix;
//	private Date date;
//	private String memo;
//	
//	private int s_row_index;
//	private int e_row_index;
//	private int col_index=0;
//	
//	private int count=0;

	public static String[] dayOfWeekColumn = { "", "월", "화", "수", "목", "금", "토", "일" };
	private JLabel IntegrationLabel;
	private JScrollPane IntegrationscrollPane;
	
	//private ArrayList<Integer>[] index = new ArrayList[3];
//	private int[][]index=new int[3][3];
	
	
	public HomeUI() {
		/*for(int i=0;i<3;i++) {
			index[i]=new ArrayList<Integer>();
		}*/
		init();
	}
	
	private void init() {
		MainFrame.frame.getContentPane().setLayout(null);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 홈 패널 (화면 전환을 위한)
		homePanel = new JPanel();
		homePanel.setBounds(HOME_FRAME_X, HOME_FRAME_Y, 
				HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		homePanel.setLayout(null);
		MainFrame.frame.getContentPane().add(homePanel);
		
		// 홈 패널: 학번
		homeUserLabel = new JLabel(String.format("학번: %s", RegisterUI.ID));
		homeUserLabel.setBounds(HOME_USER_LABEL_X, HOME_USER_LABEL_Y, 
				HOME_USER_LABEL_WIDTH, HOME_USER_LABEL_HEIGHT);
		homePanel.add(homeUserLabel);
		
		// 홈 패널: 권한 
		String userManagerAuthstr = new String();
		if(RegisterUI.TF = true)
		{
			userManagerAuthstr = "(관리자)";
		}
		else
		{
			userManagerAuthstr = "(일반)";
		}
		homeManagerAuthLabel = new JLabel(String.format("등급: %s", userManagerAuthstr));
		homeManagerAuthLabel.setBounds(HOME_USER_LABEL_X, HOME_USER_LABEL_Y+20, 
				HOME_USER_LABEL_WIDTH, HOME_USER_LABEL_HEIGHT);
		homePanel.add(homeManagerAuthLabel);
		
		
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
		
		JPanel homeScheduleColumnPanel = new JPanel();
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
		homeScheduleColumnPanel.add(homeScheduleColumn_2);
		homeScheduleColumnPanel.add(homeScheduleColumn_3);
		homeScheduleColumnPanel.add(homeScheduleColumn_4);
		homeScheduleColumnPanel.add(homeScheduleColumn_5);
		homeScheduleColumnPanel.add(homeScheduleColumn_6);
		homeScheduleColumnPanel.add(homeScheduleColumn_7);
		
		homePanel.add(homeScheduleColumnPanel);
		
		JPanel homeSchedulePanel = new JPanel();
		homeSchedulePanel.setLayout(new GridLayout(14, 8));
		for(int i=0; i<14; i++) {
			JPanel timeUI = new JPanel();
			timeUI.setPreferredSize(new Dimension(90, 80));
			timeUI.setBorder(new LineBorder(new Color(0, 0, 0)));
			homeSchedulePanel.add(timeUI);
			timeUI.setLayout(new BorderLayout());
			JLabel timeLabel = new JLabel(AddEventUI.hourCb[i] + ":00");
			timeUI.add(timeLabel, BorderLayout.CENTER);
			
			for(int j=1; j<8; j++) {
				EventUI ex = new EventUI();
				ex.x = i;
				ex.y = j;
				homeSchedulePanel.add(ex);
			}
		}
		
		
		JScrollPane scrollPane = new JScrollPane(homeSchedulePanel);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(212, 136, 750, 407);
		homePanel.add(scrollPane);
		
		
//		// 홈 패널: 날짜 표시 패널
//		JPanel homeScheduleDatePanel = new JPanel();
//		homeScheduleDatePanel.setLayout(new GridBagLayout());
//		for(int i=0; i<8; i++) {
//			JPanel dateColumnPanel = new JPanel();
//			
//			// ------------------------------------------------------------------
//			// 나중에 날짜 표시 해야함
//			dateColumnPanel.add(new JLabel(dayOfWeekColumn[i]));
//			
//			// ------------------------------------------------------------------
//			homeScheduleDatePanel.add(dateColumnPanel);
//		}
//		
//		
//		// 일정표 패널: 일정표 행(시간) 부분
//		JPanel homeScheduleRowPanel = new JPanel();
//		homeScheduleRowPanel.setLayout(new GridLayout(14, 1, 5, 5));
//		for(int i=0; i<14; i++) {
//			// 컴포넌트의 레이아웃을 조절하기 위해서
//			JPanel rowPanel = new JPanel();
//			rowPanel.add(new JLabel(AddEventUI.hourCb[i] + "시"), BorderLayout.CENTER);
//			homeScheduleRowPanel.add(rowPanel);
//		}
//		
//		
//		// 일정표 패널: 일정표 데이터 부분
//		JPanel homeScheduleDataPanel = new JPanel();
//		homeScheduleDataPanel.setLayout(new GridLayout(14, 7, 5, 5));
//		
//		for(int i=0; i<14; i++) {
//			for(int j=0; j<7; j++) {
//				JButton ex = new JButton();
////				ex.setPreferredSize(new Dimension(100, 80));
//				homeScheduleDataPanel.add(ex);
//			}
//		}
//		
//		JPanel homeScheduleScrollPanel = new JPanel();
//		homeScheduleScrollPanel.add(homeScheduleRowPanel);
//		homeScheduleScrollPanel.add(homeScheduleDataPanel);
		
		// 일정표 패널: 일정표 스크롤 팬
//		homeScheduleScrollPane = new JScrollPane(homeScheduleScrollPanel);
		
//		homeScheduleScrollPanel.add(homeScheduleRowPanel);
//		homeScheduleScrollPanel.add(homeScheduleDataPanel);
		
		
//		Schedule s = new Schedule();
//		
//		int id=20203089;	//-> 로그인한 id 넣어줘야됨.
//		try {
//			DB_Conn_Query db = new DB_Conn_Query();
//			String sql = "SELECT 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 FROM 스케줄 WHERE 유저_아이디 = "+id;
//			ResultSet rs = db.executeQurey(sql);	//id에 해당하는 스케줄 데이터를 Schedule 클래스에 넣어줌
//			while (rs.next()) 
//			{
//				name = rs.getString(1);
//				yoil = rs.getString(2);
//				startTime = rs.getInt(3);
//				endTime = rs.getInt(4);
//				fix = rs.getString(5);
//				date = rs.getDate(6);
//				memo = rs.getString(7);
//				Get_Index();
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		Object headers[] = {"월", "화", "수", "목", "금", "토", "일"};
//		Object[][] colums[]= {
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},   //9시 ~ 22시로 설정
//		};
		
//		DefaultTableModel tm = new DefaultTableModel(colums, headers);
		
//		ColorTable homeScheduleTable = new ColorTable(tm);
//		//homeScheduleTable = new JTable(tm);
//		homeScheduleTable.setRowHeight(60);
//		homeScheduleTable.setRowSelectionAllowed(false);
//		homeScheduleTable.setCellSelectionEnabled(true);
//		homeScheduleScrollPane.setViewportView(homeScheduleTable);
		
		/*for(int i=0;i<3;i++) {
			for(int j=0;j<index[i].size();j++) {
				System.out.print(index[j].get(i)+" ");
			}
			System.out.println();
		}*/
		
		//homeScheduleTable.updateUI();	//테이블 업데이트
		
		//----------------------------------------------
		
		
		// 홈 통합 일정 패널: 통합 일정 패널

		homeIntegrationPanel = new JPanel();
		homeIntegrationPanel.setBounds(0, 210, 200, 332);
		homePanel.add(homeIntegrationPanel);
		
		// 홈 통합 일정 패널: 학생 리스트 스크롤 패널
		PersonalSchedulescrollPane = new JScrollPane();
		PersonalSchedulescrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		IntegrationButton_delete = new JButton("삭제");
		IntegrationButton_delete.setFont(new Font("굴림", Font.PLAIN, 11));
		IntegrationButton_edit = new JButton("수정");
		IntegrationButton_edit.setFont(new Font("굴림", Font.PLAIN, 11));
		IntegrationButton_add = new JButton("추가");
		IntegrationButton_add.setFont(new Font("굴림", Font.PLAIN, 11));
		IntegrationButton_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				Integration Integration = new Integration();
				Integration.Integration.setVisible(true);
			}
		});
		
		JLabel PersonalLabel = new JLabel("개인 일정");
		PersonalLabel.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		
		IntegrationLabel = new JLabel("통합 일정");
		IntegrationLabel.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		
		IntegrationscrollPane = new JScrollPane();
		IntegrationscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		GroupLayout gl_homeIntegrationPanel = new GroupLayout(homeIntegrationPanel);
		gl_homeIntegrationPanel.setHorizontalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
					.addGroup(gl_homeIntegrationPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_homeIntegrationPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(IntegrationscrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
							.addComponent(PersonalSchedulescrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
						.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(IntegrationButton_add, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IntegrationButton_edit, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IntegrationButton_delete))
						.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(PersonalLabel))
						.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(IntegrationLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_homeIntegrationPanel.setVerticalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_homeIntegrationPanel.createSequentialGroup()
					.addGroup(gl_homeIntegrationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(IntegrationButton_edit)
						.addComponent(IntegrationButton_add)
						.addComponent(IntegrationButton_delete))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(PersonalLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalSchedulescrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(IntegrationLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationscrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		
		JList Integrationlist = new JList();
		Integrationlist.setModel(new AbstractListModel() {
			String[] values = new String[] {"일정1", "일정2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		IntegrationscrollPane.setViewportView(Integrationlist);
		
		JList PersonalSchedulelist = new JList();
		PersonalSchedulelist.setModel(new AbstractListModel() {
			String[] values = new String[] {"학생A", "학생B", "학생C"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		PersonalSchedulescrollPane.setViewportView(PersonalSchedulelist);
		homeIntegrationPanel.setLayout(gl_homeIntegrationPanel);
		
		toAddEventBtn = new JButton("세부 일정 추가");
		toAddEventBtn.setBounds(710, 54, 130, 23);
		toAddEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEventUI();
			}
		});
		homePanel.add(toAddEventBtn);
		
		MainFrame.frame.setTitle("통합 일정 관리 프로그램");
		MainFrame.frame.setSize(HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	}
}