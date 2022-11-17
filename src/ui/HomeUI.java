package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;

public class HomeUI {
	
	// homePanel
	public JPanel homePanel;
	private JLabel homeUserLabel;
	private JLabel homeManagerAuthLabel;
	private JCalendar homeCalendar;
	
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
	private JTextField IntegrationSearch;
	private JScrollPane IntegrationscrollPane;
	private JList IntegrationStudentsList;
	private JButton IntegrationButton_edit;
	private JButton IntegrationButton_add;
	private JButton IntegrationButton_delete;
	
	public HomeUI() {
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
		
		
		// 홈 패널: 일정표 패널
		homeSchedulePanel = new JPanel();
		homeSchedulePanel.setLayout(null);
		homeSchedulePanel.setBackground(new Color(128, 128, 128));
		homeSchedulePanel.setBounds(HOME_SCHEDULE_PANEL_X, HOME_SCHEDULE_PANEL_Y,
				HOME_SCHEDULE_PANEL_WIDTH, HOME_SCHEDULE_PANEL_HEIGHT);
		homePanel.add(homeSchedulePanel);
		
		// 일정표 패널: 일정표 스크롤 팬
		homeScheduleScrollPane = new JScrollPane();
		homeScheduleScrollPane.setBounds(HOME_SCHEDULE_SCROLLPANE_X, HOME_SCHEDULE_SCROLLPANE_Y,
				HOME_SCHEDULE_SCROLLPANE_WIDTH, HOME_SCHEDULE_SCROLLPANE_HEIGHT);
		homeSchedulePanel.add(homeScheduleScrollPane);
		
		// 일정표 패널: 일정표 테이블
	      Object headers[] = {"월", "화", "수", "목", "금", "토", "일"};
	      Object[][] rows[]= {
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},
	         {null, null, null, null, null, null, null},   //9시 ~ 22시로 설정
	      };
	      homeScheduleTable = new JTable(rows,headers);
	      homeScheduleTable.setRowHeight(60);
	      homeScheduleTable.setRowSelectionAllowed(false);
	      homeScheduleTable.setCellSelectionEnabled(true);
	      homeScheduleScrollPane.setViewportView(homeScheduleTable);
	      
		
		//----------------------------------------------
		
		DB_Conn_Query db = new DB_Conn_Query();
		int id=20203089;	//-> 로그인한 id 넣어줘야됨.
		try {
			db.schedule_sqlrun(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		homeScheduleTable.updateUI();
		
		//----------------------------------------------
		
		
		// 홈 통합 일정 패널: 통합 일정 패널

		homeIntegrationPanel = new JPanel();
		homeIntegrationPanel.setBounds(0, 210, 200, 332);
		homePanel.add(homeIntegrationPanel);
		
		IntegrationSearch = new JTextField();
		IntegrationSearch.setText("");
		IntegrationSearch.setColumns(10);
		
		// 홈 통합 일정 패널: 학생 리스트 스크롤 패널
		IntegrationscrollPane = new JScrollPane();
		IntegrationscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		IntegrationButton_delete = new JButton("삭제");
		IntegrationButton_edit = new JButton("수정");
		IntegrationButton_add = new JButton("추가");
		IntegrationButton_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				Integration Integration = new Integration();
				Integration.Integration.setVisible(true);
			}
		});
		
		GroupLayout gl_homeIntegrationPanel = new GroupLayout(homeIntegrationPanel);
		gl_homeIntegrationPanel.setHorizontalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(IntegrationButton_add, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationButton_edit, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationButton_delete)
					.addContainerGap())
				.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
					.addGroup(gl_homeIntegrationPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(IntegrationSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(IntegrationscrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_homeIntegrationPanel.setVerticalGroup(
			gl_homeIntegrationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeIntegrationPanel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_homeIntegrationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(IntegrationButton_delete)
						.addComponent(IntegrationButton_edit)
						.addComponent(IntegrationButton_add))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationSearch, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(IntegrationscrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
		);
		
		IntegrationStudentsList = new JList();
		IntegrationStudentsList.setModel(new AbstractListModel() {
			String[] values = new String[] {"학생A", "학생B", "학생C"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		IntegrationscrollPane.setViewportView(IntegrationStudentsList);
		homeIntegrationPanel.setLayout(gl_homeIntegrationPanel);
		
		MainFrame.frame.setTitle("통합 일정 관리 프로그램");
		MainFrame.frame.setSize(HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	}
}