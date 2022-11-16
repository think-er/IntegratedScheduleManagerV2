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

public class HomeUI {
	
	// homePanel
	public JPanel homePanel;
	private JLabel homeUserLabel;
	private JLabel homeManagerAuthLabel;
	private JCalendar homeCalendar;
	
	// homeScheduleListPanel
	private JPanel homeScheduleListPanel;
	
	// homeSchedulePanel
	private JPanel homeSchedulePanel;
	private JTable homeScheduleTable;
	
	public static final int HOME_FRAME_X = 0;
	public static final int HOME_FRAME_Y = 0;
	public static final int HOME_FRAME_WIDTH = 1000;
	public static final int HOME_FRAME_HEIGHT = 600;
	
	public static final int HOME_SCHEDULELIST_PANEL_X = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_Y = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_WIDTH = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_HEIGHT = 0; 
	
	public static final int HOME_SCHEDULE_PANEL_X = 0; 
	public static final int HOME_SCHEDULE_PANEL_Y = 0; 
	public static final int HOME_SCHEDULE_PANEL_WIDTH = 0; 
	public static final int HOME_SCHEDULE_PANEL_HEIGHT = 0; 
	
	public static final int HOME_USER_LABEL_X = 850;
	public static final int HOME_USER_LABEL_Y = 30;
	public static final int HOME_USER_LABEL_WIDTH = 100;
	public static final int HOME_USER_LABEL_HEIGHT = 30;
	
	public static final int HOME_CALENDAR_X = 0;
	public static final int HOME_CALENDAR_Y = 0;
	public static final int HOME_CALENDAR_WIDTH = 200;
	public static final int HOME_CALENDAR_HEIGHT = 200;
	private JTable table;
	
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
		homeScheduleTable = new JTable();
		MainFrame.frame.getContentPane().add(homePanel);
		
		JPanel homeSchedulePanel = new JPanel();
		homeSchedulePanel.setBounds(963, 86, -723, 486);
		homePanel.add(homeSchedulePanel);
		
		JScrollPane homeScheduleScrollPane = new JScrollPane();
		homeScheduleScrollPane.setBounds(371, 118, 374, 311);
		homePanel.add(homeScheduleScrollPane);
		
		table = new JTable();
		homeScheduleScrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		MainFrame.frame.setTitle("통합 일정 관리 프로그램");
		MainFrame.frame.setSize(HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	}
}
//	
//	JPanel panel = new JPanel();
//	panel.setBounds(10, 194, 166, 277);
//	Home.add(panel);
//	
//	textField_1 = new JTextField();
//	textField_1.setColumns(10);
//	
//	JButton btnNewButton_3 = new JButton("생성");
//	btnNewButton_3.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//		}
//	});
//	
//	JScrollPane scrollPane = new JScrollPane();
//	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//	GroupLayout gl_panel = new GroupLayout(panel);
//	gl_panel.setHorizontalGroup(
//		gl_panel.createParallelGroup(Alignment.TRAILING)
//			.addGroup(gl_panel.createSequentialGroup()
//				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
//					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
//						.addContainerGap(129, Short.MAX_VALUE)
//						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
//					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
//				.addContainerGap())
//	);
//	gl_panel.setVerticalGroup(
//		gl_panel.createParallelGroup(Alignment.LEADING)
//			.addGroup(gl_panel.createSequentialGroup()
//				.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
//				.addPreferredGap(ComponentPlacement.RELATED)
//				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
//				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
//	);
//	
//	JList list = new JList();
//	list.setModel(new AbstractListModel() {
//		String[] values = new String[] {"학생A", "학생B"};
//		public int getSize() {
//			return values.length;
//		}
//		public Object getElementAt(int index) {
//			return values[index];
//		}
//	});
//	scrollPane.setViewportView(list);
//	panel.setLayout(gl_panel);
//	lblNewLabel_5.setVisible(true);
