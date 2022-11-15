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

public class HomeUI extends JFrame {
	// homePanel
	private JPanel homePanel;
	private JLabel homeUserLabel;
	private JLabel homeManagerAuthLabel;

	// homeCalendarPanel
	private JPanel homeCalendarPanel;
	private JCalendar homeCalendar;
	
	// homeScheduleListPanel
	private JPanel homeScheduleListPanel;
	
	// homeSchedulePanel
	private JPanel homeSchedulePanel;
	private JTable homeScheduleTable;
	
	public static final int HOME_FRAME_X = 0;
	public static final int HOME_FRAME_Y = 0;
	public static final int HOME_FRAME_WIDTH = 0;
	public static final int HOME_FRAME_HEIGHT = 0;
	
	public static final int HOME_CALENDAR_PANEL_X = 0; 
	public static final int HOME_CALENDAR_PANEL_Y = 0; 
	public static final int HOME_CALENDAR_PANEL_WIDTH = 0; 
	public static final int HOME_CALENDAR_PANEL_HEIGHT = 0; 
	
	public static final int HOME_SCHEDULE_PANEL_X = 0; 
	public static final int HOME_SCHEDULE_PANEL_Y = 0; 
	public static final int HOME_SCHEDULE_PANEL_WIDTH = 0; 
	public static final int HOME_SCHEDULE_PANEL_HEIGHT = 0; 
	
	public static final int HOME_SCHEDULELIST_PANEL_X = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_Y = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_WIDTH = 0; 
	public static final int HOME_SCHEDULELIST_PANEL_HEIGHT = 0; 
	
	public HomeUI() {
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		homePanel = new JPanel();
		homePanel.setBounds(HOME_FRAME_X, HOME_FRAME_Y, 
				HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT);
		homePanel.setLayout(null);
		
		homeUserLabel = new JLabel("학번");
		homeManagerAuthLabel = new JLabel("등급");
		homeCalendarPanel = new JCalendar();
		
		homeCalendarPanel = new JPanel();
		homeCalendarPanel.setBounds(HOME_CALENDAR_PANEL_X, HOME_CALENDAR_PANEL_Y,
				HOME_CALENDAR_PANEL_WIDTH, HOME_CALENDAR_PANEL_HEIGHT);
		homeCalendarPanel.setLayout(null);
		
		homeScheduleListPanel = new JPanel();
		homeScheduleListPanel.setBounds(HOME_SCHEDULELIST_PANEL_X,
				HOME_SCHEDULELIST_PANEL_Y, HOME_SCHEDULELIST_PANEL_WIDTH,
				HOME_SCHEDULELIST_PANEL_HEIGHT);
		homeScheduleListPanel.setLayout(null);
		
		
		
		homeScheduleTable = new JTable();
	}
//	Home.setBounds(0, 0, 784, 481);
//	frame.getContentPane().add(Home);
//	Home.setLayout(null);
//	Home.setVisible(false);
//	
//	mainuser.setToolTipText("");
//	mainuser.setBounds(676, 49, 96, 23);
//	Home.add(mainuser);
//	
//	panel_1.setBounds(0, 0, 176, 184);
//	Home.add(panel_1);
//	panel_1.setLayout(null);
//	
//	calendar = new JCalendar();
//	calendar.setBounds(0, 0, 176, 184);
//	panel_1.add(calendar);
//	
//	JLabel lblNewLabel_5 = new JLabel("(관리자)");
//	lblNewLabel_5.setBounds(628, 49, 46, 23);
//	Home.add(lblNewLabel_5);
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
}
