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

import java.awt.GridLayout;
import java.awt.Dimension;

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
	
	private String name;
	private String yoil;
	private int startTime;
	private int endTime;
	private String fix;
	private Date date;
	private String memo;
	
	private int s_row_index;
	private int e_row_index;
	private int col_index=0;
	
	private int count=0;

	public static String[] dayOfWeekColumn = { "", "월", "화", "수", "목", "금", "토", "일" };
	
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
		
		
		// 홈 패널: 일정표 패널
		
		// 일정표 패널: 일정표 GridLayout
		//---------------------------------------------
		JPanel homeScheduleGridPanel = new JPanel();
		homeScheduleGridPanel.setLayout(new GridLayout(15, 8, 5, 5));
		
		for(int k=0; k<8; k++) {
			JPanel dayOfWeekLabelPanel = new JPanel();
			dayOfWeekLabelPanel.add(new JLabel(dayOfWeekColumn[k]));
			homeScheduleGridPanel.add(dayOfWeekLabelPanel);
		}
		
		for(int i=0; i<14; i++) {
			JPanel timeLabelPanel = new JPanel();
			timeLabelPanel.add(new JLabel(AddEventUI.hourCb[i] + "시"), BorderLayout.CENTER);
			homeScheduleGridPanel.add(timeLabelPanel);
			
			for(int j=1; j<8; j++) {
				JButton ex = new JButton();
				ex.setPreferredSize(new Dimension(82, 70));
				homeScheduleGridPanel.add(ex);
			}
		}
		
		//----------------------------------------------
		
		// 일정표 패널: 일정표 스크롤 팬
		homeScheduleScrollPane = new JScrollPane(homeScheduleGridPanel);
		homeScheduleScrollPane.setBounds(HOME_SCHEDULE_PANEL_X, HOME_SCHEDULE_PANEL_Y,
				HOME_SCHEDULE_PANEL_WIDTH, HOME_SCHEDULE_PANEL_HEIGHT);
			
		homePanel.add(homeScheduleScrollPane);
		
		//----------------------------------------------
		
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
//	public void Get_Index() {	
//		//[시간][요일], 시간 -> 시작시간 ~ 종료시간
//		String week[] = {"월", "화", "수", "목", "금", "토", "일"};
//		
//		//col_index(요일) 구하기
//		for(int i=0;i<6;i++) {	
//			if(yoil.equals(week[i])) {
//				col_index=i;
//				break;
//			}
//		}
		//s_row_index(시작시간) 구하기
//		s_row_index=startTime-9; 	//9시부터 표시하기 때문
//		
//		//e_row_index(종료시간) 구하기
//		e_row_index=endTime-9-1;	//1 작게 인덱스 줘야됨
//		
//		index[count][0]=s_row_index;
//		index[count][1]=e_row_index;
//		index[count][2]=col_index;
//		count++;
		/*index[0].add(s_row_index);
		index[1].add(e_row_index);
		index[2].add(col_index);*/
		
		/*System.out.print(s_row_index+ " ");
		System.out.print(e_row_index+" ");
		System.out.print(col_index+"\n");*/
//	}
	
//	class ColorTable extends JTable {
//		public ColorTable(DefaultTableModel dtm) {
//			// TODO Auto-generated constructor stub
//			super(dtm);
//		}
//
//		@Override
//		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//			// TODO Auto-generated method stub
//			JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
//			
			/*for(int i=0;i<3;i++) {
				System.out.println(index[0].get(i)+" "+index[1].get(i)+" "+index[2].get(i));
				if(row>=index[0].get(i) && row<=index[1].get(i) && column == index[2].get(i)) { // 특정한 값을 가진 셀을 찾아서 그 셀만 배경색상을 변경한다
					component.setBackground(Color.lightGray);
				}else{
					component.setBackground(Color.white);
				}
				
			}*/
			
			
//			if(row>=index[0][0] && row<=index[0][1] && column == index[0][2]) { // 특정한 값을 가진 셀을 찾아서 그 셀만 배경색상을 변경한다
//				component.setBackground(Color.lightGray);
//			}
//			else if(row>=index[1][0] && row<=index[1][1] && column == index[1][2]) { // 특정한 값을 가진 셀을 찾아서 그 셀만 배경색상을 변경한다
//				component.setBackground(Color.lightGray);
//			}
//			else if(row>=index[2][0] && row<=index[2][1] && column == index[2][2]) { // 특정한 값을 가진 셀을 찾아서 그 셀만 배경색상을 변경한다
//				component.setBackground(Color.lightGray);
//			}
//			else {
//				component.setBackground(Color.WHITE);
//			}
//			
//			return component;
//		}
	
}


