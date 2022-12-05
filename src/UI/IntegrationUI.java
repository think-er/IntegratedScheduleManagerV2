package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DB.DB_Conn_Query;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class IntegrationUI extends JFrame {
	private static String ID;
	protected static String selected;
	JFrame Integration;
	public static String FIX;
	private JTextField titleField;
	private JTextField yearField;
	private JComboBox monthBox;
	private JComboBox dayBox;
	private JCheckBox fixBox;
	private JComboBox stHourBox;
	private JComboBox edHourBox;
	private JTextArea memoArea;
	private JTextField yoilField;
	
	public static String StartOfWeekFormat;
	public static String EndOfWeekFormat;
	
	JList<String> integrationList;
	
	DB_Conn_Query db = new DB_Conn_Query();
	
	/**
	 * Create the application.
	 */
	public static void main(String[] args) {
		new PersonalUI(ID);
	}
	
	public IntegrationUI(String id) {
		initialize(id);
		ID=id;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) {
		Integration = new JFrame();
		Integration.setBounds(100, 100, 700, 440);
		Integration.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("일정 제목");
		titleLabel.setBounds(342, 66, 60, 25);
		Integration.getContentPane().add(titleLabel);
		
		JLabel dateLabel = new JLabel("일정 날짜");
		dateLabel.setBounds(342, 106, 60, 25);
		Integration.getContentPane().add(dateLabel);
		
		JLabel fixLabel = new JLabel("고정");
		fixLabel.setBounds(622, 106, 40, 25);
		Integration.getContentPane().add(fixLabel);
		
		JLabel startTimeLabel = new JLabel("시작 시간");
		startTimeLabel.setBounds(342, 186, 60, 25);
		Integration.getContentPane().add(startTimeLabel);
		
		JLabel endTimeLabel = new JLabel("종료 시간");
		endTimeLabel.setBounds(342, 229, 60, 25);
		Integration.getContentPane().add(endTimeLabel);
		
		JLabel memoLabel = new JLabel("일정 메모");
		memoLabel.setBounds(342, 268, 60, 25);
		Integration.getContentPane().add(memoLabel);
		
		JLabel yearLabel = new JLabel("년");
		yearLabel.setBounds(462, 106, 20, 25);
		Integration.getContentPane().add(yearLabel);
		
		JLabel monthLabel = new JLabel("월");
		monthLabel.setBounds(532, 106, 20, 25);
		Integration.getContentPane().add(monthLabel);
		
		JLabel dayLabel = new JLabel("일");
		dayLabel.setBounds(602, 106, 20, 25);
		Integration.getContentPane().add(dayLabel);
		
		titleField = new JTextField();
		titleField.setBounds(412, 66, 250, 25);
		Integration.getContentPane().add(titleField);
		
		yearField = new JTextField();
		yearField.setText("2022");
		yearField.setBounds(412, 106, 50, 25);
		Integration.getContentPane().add(yearField);
		
		monthBox = new JComboBox(new Object[]{});
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//월 선택시 dayBox 변경
				dayBox.removeAllItems();
				Calendar cal = Calendar.getInstance();
				int year = Integer.parseInt(yearField.getText());
				int month = Integer.parseInt((String) monthBox.getSelectedItem());
				
				cal.set(year, month - 1, 1);
				
				int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				for(int i=1;i<=endDay;i++) {
					dayBox.addItem(String.format("%02d", i));
				}
			}
		});
		monthBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthBox.setBounds(482, 106, 50, 25);
		Integration.getContentPane().add(monthBox);
		
		fixBox = new JCheckBox();
		fixBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fixBox.isSelected()==true) {	//고정 체크 : 요일 활성화, 날짜 비활성화
					enabled("1");
				}
				else {	//고정 체크 x : 요일 비활성화, 날짜 활성화
					enabled("0");
				}
			}
		});
		fixBox.setBounds(652, 106, 32, 25);
		Integration.getContentPane().add(fixBox);
		
		stHourBox = new JComboBox(new Object[]{});
		stHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		stHourBox.setBounds(414, 184, 50, 25);
		Integration.getContentPane().add(stHourBox);
		
		JLabel stHourLabel = new JLabel("시");
		stHourLabel.setBounds(462, 186, 60, 25);
		Integration.getContentPane().add(stHourLabel);
		
		edHourBox = new JComboBox(new Object[]{});
		edHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		edHourBox.setBounds(414, 229, 50, 25);
		Integration.getContentPane().add(edHourBox);
		
		JLabel edHourLabel = new JLabel("시");
		edHourLabel.setBounds(462, 229, 60, 25);
		Integration.getContentPane().add(edHourLabel);
		
		JScrollPane memoScrollPane = new JScrollPane((Component) null);
		memoScrollPane.setBounds(412, 267, 250, 89);
		Integration.getContentPane().add(memoScrollPane);
		
		memoArea = new JTextArea();
		memoScrollPane.setViewportView(memoArea);
		
		JButton delBtn = new JButton("삭제");
		delBtn.setBounds(602, 366, 60, 25);
		Integration.getContentPane().add(delBtn);
		
		JScrollPane IntegrationScrollPane = new JScrollPane();
		IntegrationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		IntegrationScrollPane.setBounds(12, 66, 307, 290);
		Integration.getContentPane().add(IntegrationScrollPane);
		
		integrationList = new JList<String>();
		integrationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		IntegrationScrollPane.setViewportView(integrationList);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 통합스케줄_이름 "
				+ "FROM 통합스케줄,소속 "
				+ "WHERE 통합스케줄.팀_번호=소속.팀_번호 "
				+ "AND 소속.유저_아이디="+id;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("통합스케줄_이름"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		integrationList.setModel(listModel);
		
		// 통합스케줄의 스케줄 클릭 시 
		
		integrationList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					IntegrationUI.selected = integrationList.getSelectedValue();
					String query = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 "
							+ "FROM 통합스케줄 "
							+ "WHERE 통합스케줄_이름 = '"+selected+"' AND "
									+ "통합스케줄.팀_번호 = (SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+id+")";
					ResultSet rs = db.executeQuery(query);
					try {
						while(rs.next()) {
							titleField.setText(selected);
							if(rs.getString("고정여부").equals("1")) {		//고정 : 날짜 입력 필요 x
								//yearField.setText((d.getYear()).toString());
								enabled("1");
								yoilField.setText(rs.getString("요일"));
							}
							else {	//비고정 : 날짜 입력 필요, 요일 자동 표시
								Date date = rs.getDate("날짜");
								enabled("0");
								SimpleDateFormat y_date = new SimpleDateFormat("yyyy");
								SimpleDateFormat m_date = new SimpleDateFormat("MM");
								SimpleDateFormat d_date = new SimpleDateFormat("dd");
								yearField.setText(y_date.format(date));
								monthBox.setSelectedIndex(Integer.parseInt(m_date.format(date))-1);
								dayBox.setSelectedIndex(Integer.parseInt(d_date.format(date))-1);
								
							}
							stHourBox.setSelectedIndex(rs.getInt("시작시간")-9);
							edHourBox.setSelectedIndex(rs.getInt("종료시간")-9);
							memoArea.setText(rs.getString("메모"));
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		//////////////////////////////
		JLabel lblNewLabel = new JLabel("통합 일정 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 26, 181, 30);
		Integration.getContentPane().add(lblNewLabel);
		
		JButton modifyBtn = new JButton("수정");
		modifyBtn.setBounds(532, 366, 60, 25);
		Integration.getContentPane().add(modifyBtn);
		
		
		// 통합스케줄 추가
		JButton addBtn = new JButton("등록");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs = db.executeQuery("SELECT 통합_번호, 팀_번호 from 통합스케줄");
				int SCNUM=0;
				int TEAM_NUM=0;
				try {
					while(rs.next()) {
						SCNUM = rs.getInt(1);
						TEAM_NUM = rs.getInt(2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String SCNAME = titleField.getText(); // 통합스케줄 이름
				int year = Integer.parseInt(yearField.getText()); 
				int month = Integer.parseInt(monthBox.getSelectedItem().toString());
				int day = Integer.parseInt(dayBox.getSelectedItem().toString());
				int START = Integer.parseInt(stHourBox.getSelectedItem().toString());
				int END = Integer.parseInt(edHourBox.getSelectedItem().toString());
				String MEMO = memoArea.getText();
//				System.out.println(year+"-"+String.format("%02d", month)+"-"+String.format("%02d", day));
				LocalDate date = LocalDate.of(year, month,day);
				System.out.println(date);
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
				String yoil = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
				
				if (titleField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"일정 제목을 입력하세요.");
				}
					else if (fixBox.isSelected()) {
						FIX = "1";
						date = null;
						
					}
					else {
						FIX = "0";
						
						if (yearField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,"년도를 입력해주세요.");
						}
						SCNUM+=1;
						String query = "INSERT INTO 통합스케줄 VALUES("+SCNUM+","+TEAM_NUM+",'"+SCNAME+"','"
								+date+"','"+yoil+"','"+FIX+"',"+START+","+END+",'"+MEMO+"')";
						
						System.out.print(query);
						db.executeUpdate(query);
						
						// 등록 성공 : 새로고침
						refresh();
					}
				
			}
		});
		addBtn.setBounds(462, 366, 60, 25);
		Integration.getContentPane().add(addBtn);
		
		JLabel yoilLabel = new JLabel("요일");
		yoilLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yoilLabel.setBounds(342, 156, 57, 15);
		Integration.getContentPane().add(yoilLabel);
		
		yoilField = new JTextField();
		yoilField.setEnabled(false);
		yoilField.setBounds(412, 149, 50, 25);
		Integration.getContentPane().add(yoilField);
		yoilField.setColumns(10);
		//------------------------------새로고침 버튼 이벤트-------------------------------
		JButton refreshBtn = new JButton("새로고침");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		
		//------------------------------새로고침 버튼 이벤트 end----------------------------
		refreshBtn.setBounds(565, 26, 97, 23);
		Integration.getContentPane().add(refreshBtn);
		
		dayBox = new JComboBox(new Object[]{});
		
		Calendar cal = Calendar.getInstance();
		cal.set(2022, 0, 1);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=1;i<=endDay;i++) {
			dayBox.addItem(String.format("%02d", i));
		}
		
		
		dayBox.setBounds(550, 106, 50, 25);
		Integration.getContentPane().add(dayBox);
		
		Integration.setResizable(false);
		Integration.setTitle("통합 일정 관리");
	}
	public void enabled(String b) {
		Boolean tf=true;
		if(b.equals("0"))tf=false;
		
		fixBox.setSelected(tf);
		yoilField.setEnabled(tf);
		yearField.setEnabled(!tf);
		monthBox.setEnabled(!tf);
		dayBox.setEnabled(!tf);
	}
	public void refresh() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 통합스케줄_이름 "
				+ "FROM 통합스케줄,소속 "
				+ "WHERE 통합스케줄.팀_번호=소속.팀_번호 "
				+ "AND 소속.유저_아이디="+ID;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("통합스케줄_이름"));
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		integrationList.setModel(listModel);
		
		titleField.setText("");
		yoilField.setText("");
		yearField.setText("2022");
		monthBox.setSelectedIndex(0);
		dayBox.setSelectedIndex(0);
		stHourBox.setSelectedIndex(0);
		edHourBox.setSelectedIndex(0);
		memoArea.setText("");
		fixBox.setSelected(false);
		enabled("0");
	}
}
