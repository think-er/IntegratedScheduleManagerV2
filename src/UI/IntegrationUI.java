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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

import Control.duplicatedCheck;
import DB.DB_Conn_Query;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import Control.UpdateSchedule;
import Control.ViewCompSchedule;


public class IntegrationUI extends JFrame {
	private static String ID;
	public static String WEEK = "";
	public static String FIX;
	private int START;
	private int END;
	
	protected static String selected = null;
	JFrame Integration;
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
	duplicatedCheck dc = new duplicatedCheck();
	
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
		
		//----------------------------------------------- 통합스케줄 삭제-------------------------------------//
		
		JButton delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SC_NAME = titleField.getText();
				int TEAM_NUM = 0;
				ResultSet rs3 = db.executeQuery("SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+id);
				System.out.println("SELECT 팀_번호 FROM 소속 WHRER 유저_아이디 = "+id);
				try {
					while(rs3.next()) {
						TEAM_NUM = rs3.getInt(1);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				ResultSet rs = db.executeQuery("select 통합_번호 FROM 통합스케줄 "
											 + "where 통합스케줄_이름 = '"+SC_NAME+"'"+" AND 팀_번호 = "+TEAM_NUM);
				int SC_NUM = 0;
				try {
					while(rs.next()) {
						SC_NUM = rs.getInt(1);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(selected == null) {	//예외 처리 1) 리스트가 선택되지 않았을 때
					JOptionPane.showMessageDialog(null,"삭제할 일정을 선택하세요.");
				}
				else if(SC_NUM<=0) {
					JOptionPane.showMessageDialog(null,"삭제 오류!");
				}
				else {
					String query = "DELETE FROM 통합스케줄 where 통합_번호= "+SC_NUM;
					
					System.out.print(query);
					int n = db.executeUpdate(query);
					if(n<0) 
						JOptionPane.showMessageDialog(null,"삭제 오류!");
					else {
						// ------------- 삭제 성공 후 통합스케줄 인덱스 재조정---------------
						ResultSet rs2 = db.executeQuery("SELECT COUNT(*) FROM 통합스케줄");
						// 실제 존재하고 있는 스케줄 갯수
						int actual_scCount = 0;
						try {
							while(rs2.next()) {
								actual_scCount = rs2.getInt(1);
							}
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						// 삭제된 인덱스 ~ 끝 스케줄_번호 1씩 당기기
						for (int i=SC_NUM; i<=actual_scCount; i++) {
							String query2 = "UPDATE 통합스케줄 SET 통합_번호="+i+" WHERE 통합_번호= "+(i+1)+"";
							db.executeUpdate(query2);
							}
						JOptionPane.showMessageDialog(null,"통합 스케줄을 삭제하였습니다.");
						// 삭제 성공 : 새로고침
						refresh();
						new UpdateSchedule();
						new ViewCompSchedule();
					}
				}
				
				
			}
		});
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
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == null) 
					JOptionPane.showMessageDialog(null,"수정할 일정을 선택해주세요");
				else {
					ResultSet rs2 = db.executeQuery("SELECT COUNT(*) from 통합스케줄");
					int SCNUM=0;
					try {
						while(rs2.next()) {
							SCNUM = rs2.getInt(1);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					String SCNAME = titleField.getText(); // 통합스케줄 이름
					int year = Integer.parseInt(yearField.getText()); 
					int month = Integer.parseInt(monthBox.getSelectedItem().toString());
					int day = Integer.parseInt(dayBox.getSelectedItem().toString());
					START = Integer.parseInt(stHourBox.getSelectedItem().toString());
					END = Integer.parseInt(edHourBox.getSelectedItem().toString());
					String MEMO = memoArea.getText();
//					System.out.println(year+"-"+String.format("%02d", month)+"-"+String.format("%02d", day));
					LocalDate date = LocalDate.of(year, month,day);
					String date2 = "'"+date+"'";
					System.out.println(date);
					DayOfWeek dayOfWeek = date.getDayOfWeek();
					System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
					String yoil = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
					WEEK = yoil;
					String month2 = monthBox.getSelectedItem().toString();
					String day2 = (dayBox.getSelectedItem().toString());
							
					// 날짜 Date로 변환
					String Days = year+"-"+month2+"-"+day2;
					DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
					LocalDate DATE = LocalDate.parse(Days, sdf);
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Date d = Date.from(DATE.atStartOfDay(defaultZoneId).toInstant());
					
					if(fixBox.isSelected()) {	//고정
						FIX = "1";
						date2 = null;
						WEEK = yoilField.getText();	//고정 체크했을 땐 사용자가 입력한 요일이 들어가야됨.
					}
					else {	//비고정
						FIX = "0";
					}
					
					//-------------------------------------------예외 조건--------------------------------------------
					dc.getData(id, d, WEEK, FIX, START, END);
					
					if(START>=END) {  //예외 1 : 시작시간이 종료시간보다 늦을 경우
						JOptionPane.showMessageDialog(null,"잘못된 시작시간 입니다.");
					}
					//예외 2 : 일정이 중복될 경우
					else if(!dc.PersonalDC()) {	//duplicatedCheck에서 예외처리
						JOptionPane.showMessageDialog(null,"중복된 일정입니다.");
					}
					else {
						int TEAM_NUM2=0;
						ResultSet rs3 = db.executeQuery("SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+ID);
						try {
							while(rs3.next()) {
								TEAM_NUM2 = rs3.getInt(1);
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						DB_Conn_Query db = new DB_Conn_Query();
						String query3 = "UPDATE 통합스케줄 SET 통합스케줄_이름 = '"+SCNAME+"', 요일 = '"+WEEK+"', 시작시간 = "+START+", 종료시간 = "+END+","
								+ "고정여부 = '"+FIX+"', 날짜 = "+date2+", 메모 = '"+MEMO+"'"
								+ "WHERE 통합_번호 = "+SCNUM+" AND 팀_번호 = "+TEAM_NUM2+"";
						db.executeUpdate(query3);
						System.out.print(query3);
						int n = db.executeUpdate(query3);
						if(n<0){
							JOptionPane.showMessageDialog(null,"수정을 실패했습니다.");
							}
						else {
							JOptionPane.showMessageDialog(null,"수정을 성공했습니다.");
							//수정 성공 : 새로고침
							refresh();
							
							new UpdateSchedule();
							new ViewCompSchedule();
						}
					}
				}
			}
		});
		
		modifyBtn.setBounds(532, 366, 60, 25);
		Integration.getContentPane().add(modifyBtn);
		
		
		// 통합스케줄 추가
		JButton addBtn = new JButton("등록");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.executeQuery("SELECT COUNT(*) from 통합스케줄");
				int SCNUM=0;
				try {
					while(rs.next()) {
						SCNUM = rs.getInt(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String SCNAME = titleField.getText(); // 통합스케줄 이름
				int year = Integer.parseInt(yearField.getText()); 
				int month = Integer.parseInt(monthBox.getSelectedItem().toString());
				int day = Integer.parseInt(dayBox.getSelectedItem().toString());
				START = Integer.parseInt(stHourBox.getSelectedItem().toString());
				END = Integer.parseInt(edHourBox.getSelectedItem().toString());
				String MEMO = memoArea.getText();
//				System.out.println(year+"-"+String.format("%02d", month)+"-"+String.format("%02d", day));
				LocalDate date = LocalDate.of(year, month,day);
				String date2 = "'"+date+"'";
				System.out.println(date);
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
				String yoil = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
				WEEK = yoil;
				String month2 = monthBox.getSelectedItem().toString();
				String day2 = (dayBox.getSelectedItem().toString());
						
				// 날짜 Date로 변환
				String Days = year+"-"+month2+"-"+day2;
				DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
				LocalDate DATE = LocalDate.parse(Days, sdf);
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Date d = Date.from(DATE.atStartOfDay(defaultZoneId).toInstant());
				
				if(fixBox.isSelected()) {	//고정
					FIX = "1";
					date2 = null;
					WEEK = yoilField.getText();	//고정 체크했을 땐 사용자가 입력한 요일이 들어가야됨.
				}
				else {	//비고정
					FIX = "0";
				}
				
				//-------------------------------------------예외 조건--------------------------------------------
				// 예외처리 - 통합스케줄 등록
				
				//duplicatedCheck에 데이터 보내줌(이것들은 유저가 입력한 데이터)
				dc.getData(id, d, WEEK, FIX, START, END);
				
				//예외 1 : 일정 제목과 날짜를 입력하지 않았을 때
				if(SCNAME.length()==0||yearField.getText().isEmpty()) {	
					JOptionPane.showMessageDialog(null,"일정 정보를 모두 입력하세요.");
				}
				//예외 2 : 시작시간이 종료시간보다 늦을 경우 경고창
				else if(START>=END) { 
					JOptionPane.showMessageDialog(null,"잘못된 시작시간 입니다.");
				}
				//예외 3 : 일정이 중복될 경우
				else if(!dc.IntegrationDC()) {//duplicatedCheck에서 예외처리
					JOptionPane.showMessageDialog(null,"중복된 일정입니다.");
				}
				//-------------------------------------------예외 조건 end-----------------------------------------
				//등록
				else{
					// 충족 조건 달성 시
					int TEAM_NUM2=0;
					ResultSet rs2 = db.executeQuery("SELECT 팀_번호 FROM 소속 WHERE 유저_아이디 = "+ID);
					try {
						while(rs2.next()) {
							TEAM_NUM2 = rs2.getInt(1);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					SCNUM+=1;
					String query = "INSERT INTO 통합스케줄 VALUES("+SCNUM+","+TEAM_NUM2+",'"+SCNAME+"','"
							+WEEK+"',"+START+","+END+",'"+FIX+"',"+date2+",'"+MEMO+"')";
										
					System.out.print(query);
					int n = db.executeUpdate(query);
					if(n<0){
						JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
					}
					else {
						JOptionPane.showMessageDialog(null,"등록에 성공했습니다.");
						//등록 성공 : 새로고침
						refresh();
						new UpdateSchedule();
						new ViewCompSchedule();
					}
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
		Integration.setLocationRelativeTo(null);	//화면 중앙 배치
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
