package UI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Control.duplicatedCheck;
import DB.DB_Conn_Query;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonalUI extends JFrame{
	
	// 스케줄표 아이디를 불러오기 위한 변수
	private static String ID;
	// 등록에 쓰일 변수
	public static String WEEK = "";
	public static String FIX;
	DB_Conn_Query db = new DB_Conn_Query();
	duplicatedCheck dc = new duplicatedCheck();
	//----------------------------
	
	private JFrame subFrame;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel fixLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel endLabel;
	private JLabel memoLabel;
	
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	
	private JLabel stHourLabel;
	private JLabel stMinuteLabel;
	
	private JLabel edHourLabel;
	private JLabel edMinuteLabel;
	
	private JTextField titleField;
	public JTextField yearField;
	public JComboBox monthBox;
	public JComboBox dayBox;
	public JComboBox weekBox;
	private JCheckBox fixBox;
	public JComboBox stHourBox;
	public JComboBox edHourBox;
	private JTextArea memoArea;
	private JScrollPane memoScrollPane;
	private JButton delBtn;
	
	JList<String> personalList;
	
	public static String[] monthCb = {"01", "02", "03", "04", "05", "06"
			, "07", "08", "09", "10", "11", "12"};
	public static String[] hourCb = {"09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22"
	};
	protected static String selected = null;
	private JLabel weekLabel;
	
	private int START;
	private int END;
	
	public static void main(String[] args) {
		new PersonalUI(ID);
	}
	
	public PersonalUI(String id) {
		init(id);
		ID=id;
	}
	
	
	private void init(String id) {
		setLocationRelativeTo(null);	//화면 중앙 배치
		setResizable(false);			// 화면 사이즈 고정
		
		subFrame = new JFrame();
		
		titleLabel = new JLabel("일정 제목");
		dateLabel = new JLabel("일정 날짜");
		fixLabel = new JLabel("고정");
		startTimeLabel = new JLabel("시작 시간");
		endTimeLabel = new JLabel("종료 시간");
		memoLabel = new JLabel("일정 메모");
		
		yearLabel = new JLabel("년");
		monthLabel = new JLabel("월");
		dayLabel = new JLabel("일");
		stHourLabel = new JLabel("시");
		edHourLabel = new JLabel("시");
		titleField = new JTextField();
		yearField = new JTextField("2022");
		
		monthBox = new JComboBox(monthCb);
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
		stHourBox = new JComboBox(hourCb);
		stHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		edHourBox = new JComboBox(hourCb);
		
		//----------------------------------------------- 개인스케줄 삭제-------------------------------------//

		delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SC_NAME = titleField.getText();
				
				ResultSet rs = db.executeQuery("select 스케줄_번호 FROM 스케줄 where 스케줄_이름 = '"+SC_NAME+"'"+" AND 유저_아이디 = "+id);
				int SC_NUM=0;
				try {
					while(rs.next()) {
						SC_NUM = rs.getInt(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(selected == null) {	//예외 처리 1) 리스트가 선택되지 않았을 때
					JOptionPane.showMessageDialog(null,"삭제할 일정을 선택하세요.");
				}
				else if(SC_NUM<=0) {
					JOptionPane.showMessageDialog(null,"삭제 오류!");
				}
				else {
					String query = "DELETE FROM 스케줄 where 스케줄_번호= "+SC_NUM+" AND 유저_아이디 = "+id;
					
					System.out.print(query);
					int n = db.executeUpdate(query);
					if(n<0) 
						JOptionPane.showMessageDialog(null,"삭제 오류!");
					else {
						// ------------- 삭제 성공 후 스케줄 인덱스 재조정---------------
						ResultSet rs2 = db.executeQuery("SELECT COUNT(*) FROM 스케줄");
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
							String query2 = "UPDATE 스케줄 SET 스케줄_번호="+i+" WHERE 스케줄_번호= "+(i+1)+"";
							db.executeUpdate(query2);
						}
						JOptionPane.showMessageDialog(null,"스케줄이 삭제되었습니다.");
						// 삭제 성공 : 새로고침
						refresh();
					}
				}
				
			}
		});
		
		subFrame.getContentPane().setLayout(null);
		
		titleLabel.setBounds(342,66, 60, 25);
		dateLabel.setBounds(342,106,60,25);
		startTimeLabel.setBounds(342,181,60,25);
		endTimeLabel.setBounds(342,221,60,25);
		memoLabel.setBounds(342,262,60,25);
		
		titleField.setBounds(412, 66, 250, 25);
		yearField.setBounds(412, 106, 50, 25);
		yearLabel.setBounds(462, 106, 60, 25);
		monthBox.setBounds(482, 106, 50, 25);
		monthLabel.setBounds(532, 106, 60,25);
		dayLabel.setBounds(602, 106, 60, 25);
		fixLabel.setBounds(622, 106, 50, 25);
		fixBox.setBounds(652,106,32,25);
		stHourBox.setBounds(412, 181, 50,25);
		stHourLabel.setBounds(462, 181, 60, 25);
		edHourBox.setBounds(412, 221, 50,25);
		edHourLabel.setBounds(462, 221, 60, 25);
		memoScrollPane = new JScrollPane();
		memoScrollPane.setBounds(412, 261, 250, 95);
		delBtn.setBounds(602, 366, 60, 25);
		
		subFrame.getContentPane().add(titleLabel);
		subFrame.getContentPane().add(dateLabel);
		subFrame.getContentPane().add(fixLabel);
		subFrame.getContentPane().add(startTimeLabel);
		subFrame.getContentPane().add(endTimeLabel);
		subFrame.getContentPane().add(memoLabel);
		subFrame.getContentPane().add(yearLabel);
		subFrame.getContentPane().add(monthLabel);
		subFrame.getContentPane().add(dayLabel);
		
		subFrame.getContentPane().add(titleField);
		subFrame.getContentPane().add(yearField);
		subFrame.getContentPane().add(monthBox);
		subFrame.getContentPane().add(fixBox);
		subFrame.getContentPane().add(stHourBox);
		subFrame.getContentPane().add(stHourLabel);
		subFrame.getContentPane().add(edHourBox);
		subFrame.getContentPane().add(stHourBox);
		subFrame.getContentPane().add(edHourLabel);
		subFrame.getContentPane().add(memoScrollPane);
		memoArea = new JTextArea();
		memoScrollPane.setViewportView(memoArea);
		subFrame.getContentPane().add(delBtn);
		
		JScrollPane personalScrollPane = new JScrollPane();
		personalScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		personalScrollPane.setBounds(12, 66, 307, 290);
		subFrame.getContentPane().add(personalScrollPane);
		
		dayBox = new JComboBox(new Object[]{});
		
		Calendar cal = Calendar.getInstance();
		cal.set(2022, 0, 1);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=1;i<=endDay;i++) {
			dayBox.addItem(String.format("%02d", i));
		}
		
		dayBox.setBounds(550, 106, 50, 25);
		
		subFrame.getContentPane().add(dayBox);
		JLabel lblNewLabel = new JLabel("개인 일정 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 26, 181, 30);
		subFrame.getContentPane().add(lblNewLabel);
		
		JButton addBtn = new JButton("등록");
		// 등록 버튼을 눌렀을 시 쿼리에 데이터 저장
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.executeQuery("SELECT COUNT(*) FROM 스케줄");
				
				int SCNUM=0;
				try {
					while(rs.next()) {
						SCNUM = rs.getInt(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(SCNUM);
				String SCNAME = titleField.getText();
				
				int year = Integer.parseInt(yearField.getText());
				int month = Integer.parseInt(monthBox.getSelectedItem().toString());
				int day = Integer.parseInt(dayBox.getSelectedItem().toString());
						 
				START = Integer.parseInt(stHourBox.getSelectedItem().toString());
				END = Integer.parseInt(edHourBox.getSelectedItem().toString());
				String MEMO = memoArea.getText();
				
				LocalDate date = LocalDate.of(year, month, day);
				String date2 = "'"+date+"'";
				System.out.println(date);
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
				String week = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
				WEEK = week;
				String month2 = monthBox.getSelectedItem().toString();
				String day2 = (dayBox.getSelectedItem().toString());
						
				// 날짜 Date로 변환
				String Days1 = year+"-"+month2+"-"+day2;
				DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
				LocalDate DATE = LocalDate.parse(Days1, sdf);
				
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Date d = Date.from(DATE.atStartOfDay(defaultZoneId).toInstant());
				if(fixBox.isSelected()) {	//고정
					FIX = "1";
					date = null;
					WEEK = weekBox.getSelectedItem().toString();	//고정 체크했을 땐 사용자가 입력한 요일이 들어가야됨.
				}
				else {	//비고정
					FIX = "0";
				}
				//-------------------------------------------예외 조건--------------------------------------------
				//duplicatedCheck에 데이터 보내줌(이것들은 유저가 입력한 데이터)
				dc.getData(id, d, WEEK, FIX, START, END);
				
				//예외 1 : 일정 제목과 날짜를 입력하지 않았을 때
				if(SCNAME.length()==0||yearField.getText().isEmpty()) {	
					JOptionPane.showMessageDialog(null,"일정 정보를 모두 입력하세요.");
				}
				//예외 2 : 시작시간이 종료시간보다 늦을 경우
				else if(START>=END) { 
					JOptionPane.showMessageDialog(null,"잘못된 시작시간 입니다.");
				}
				//예외 3 : 일정이 중복될 경우
				else if(!dc.PersonalDC()) {	//duplicatedCheck에서 예외처리
					JOptionPane.showMessageDialog(null,"중복된 일정입니다.");
				}
				//-------------------------------------------예외 조건 end-----------------------------------------
				//등록
				else{
					SCNUM+=1;
					DB_Conn_Query db = new DB_Conn_Query();
					String query = "insert into 스케줄 values("+SCNUM+","+ID+",'"+SCNAME+"','"+WEEK+"',"+START+","+END+",'"+FIX+"',"+date2+",'"+MEMO+"')";
					System.out.print(query);
					int n = db.executeUpdate(query);
					
					if(n<0){
						JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
					}
					else {
						JOptionPane.showMessageDialog(null,"등록에 성공했습니다.");
						//등록 성공 : 새로고침
						refresh();
					}
				}
			}	
		});
		
		addBtn.setBounds(462, 366, 60, 25);
		subFrame.getContentPane().add(addBtn);
		
		subFrame.setTitle("개인 일정 관리");
		subFrame.setResizable(false);
			
		subFrame.setSize(700,440);
		subFrame.setVisible(true);
		
		personalList = new JList<String>();
		
		personalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 스케줄_이름 "
				+ "FROM 스케줄,유저 "
				+ "WHERE 스케줄.유저_아이디=유저.유저_아이디 "
				+ "AND 유저.유저_아이디="+id;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("스케줄_이름"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		personalList.setModel(listModel);
		
		personalList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					PersonalUI.selected = personalList.getSelectedValue();
					System.out.println("selected :"+selected);
					String sql = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 "
							+ "FROM 스케줄 "
							+ "WHERE 스케줄_이름 = '"+selected+"' AND 유저_아이디 = "+id;
					ResultSet rs = db.executeQuery(sql);
					try {
						while(rs.next()) {
							titleField.setText(selected);
							if(rs.getString("고정여부").equals("1")) {		//고정 : 날짜 입력 필요 x
								//yearField.setText((d.getYear()).toString());
								enabled("1");
								weekBox.setSelectedIndex(getWeek(rs.getString("요일")));
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
		JButton modifyBtn = new JButton("수정");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == null) 
					JOptionPane.showMessageDialog(null,"수정할 일정을 선택해주세요");
				else {
					String SCNAME = titleField.getText();
					ResultSet rs = db.executeQuery("select 스케줄_번호 FROM 스케줄 where 스케줄_이름 = '"+SCNAME+"'"+" AND 유저_아이디 = "+id);
					int SCNUM=0;
					try {
						while(rs.next()) {
							SCNUM = rs.getInt(1);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
					int year = Integer.parseInt(yearField.getText());
					int month = Integer.parseInt(monthBox.getSelectedItem().toString());
					int day = Integer.parseInt(dayBox.getSelectedItem().toString());
							 
					START = Integer.parseInt(stHourBox.getSelectedItem().toString());
					END = Integer.parseInt(edHourBox.getSelectedItem().toString());
					String MEMO = memoArea.getText();
					
					LocalDate date = LocalDate.of(year, month, day);
					String date2 = "'"+date+"'";
					System.out.println(date);
					DayOfWeek dayOfWeek = date.getDayOfWeek();
					System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
					String week = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
					WEEK = week;
					String month2 = monthBox.getSelectedItem().toString();
					String day2 = (dayBox.getSelectedItem().toString());
							
					// 날짜 Date로 변환
					String Days1 = year+"-"+month2+"-"+day2;
					DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
					LocalDate DATE = LocalDate.parse(Days1, sdf);
					
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Date d = Date.from(DATE.atStartOfDay(defaultZoneId).toInstant());
					
					if(fixBox.isSelected()) {	//고정
						FIX = "1";
						date2 = null;
						WEEK = weekBox.getSelectedItem().toString();	//고정 체크했을 땐 사용자가 입력한 요일이 들어가야됨.
					}
					else {	//비고정
						FIX = "0";
					}
					
					dc.getData(id, d, WEEK, FIX, START, END);
					
					if(START>=END) {  //예외 1 : 시작시간이 종료시간보다 늦을 경우
						JOptionPane.showMessageDialog(null,"잘못된 시작시간 입니다.");
					}
					//예외 2 : 일정이 중복될 경우
					else if(!dc.PersonalDC()) {	//duplicatedCheck에서 예외처리
						JOptionPane.showMessageDialog(null,"중복된 일정입니다.");
					}
					else {
						DB_Conn_Query db = new DB_Conn_Query();
						String query3 = "UPDATE 스케줄 SET 스케줄_이름 = '"+SCNAME+"', 요일 = '"+WEEK+"', 시작시간 = "+START+", 종료시간 = "+END+","
								+ "고정여부 = '"+FIX+"', 날짜 = "+date2+", 메모 = '"+MEMO+"'"
								+ "WHERE 스케줄_번호 = "+SCNUM+" AND 유저_아이디 = "+ID+"";
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
						}
					}
				}
			}
		});
	
		modifyBtn.setBounds(532, 366, 60, 25);
		subFrame.getContentPane().add(modifyBtn);
		
		personalScrollPane.setViewportView(personalList);
		
		weekLabel = new JLabel("요일");
		weekLabel.setBounds(342, 146, 60, 25);
		subFrame.getContentPane().add(weekLabel);
		
		JButton refreshBtn = new JButton("새로고침");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		refreshBtn.setBounds(565, 26, 97, 23);
		subFrame.getContentPane().add(refreshBtn);
		
		weekBox = new JComboBox();
		weekBox.setEnabled(false);
		weekBox.setModel(new DefaultComboBoxModel(new String[] {"일", "월", "화", "수", "목", "금", "토"}));
		weekBox.setBounds(412, 148, 50, 23);
		subFrame.getContentPane().add(weekBox);
		subFrame.setLocationRelativeTo(null);	//화면 중앙 배치
		
	}
	public void enabled(String b) {	//고정여부에 따라 컴포넌트 enable 설정하는 함수
		Boolean tf=true;
		if(b.equals("0"))tf=false;
		
		fixBox.setSelected(tf);
		weekBox.setEnabled(tf);
		yearField.setEnabled(!tf);
		monthBox.setEnabled(!tf);
		dayBox.setEnabled(!tf);
	}
	public void refresh() {	//새로고침 함수
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 스케줄_이름 "
				+ "FROM 스케줄,유저 "
				+ "WHERE 스케줄.유저_아이디=유저.유저_아이디 "
				+ "AND 유저.유저_아이디="+ID;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("스케줄_이름"));
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		personalList.setModel(listModel);
		
		titleField.setText("");
		weekBox.setSelectedIndex(0);
		yearField.setText("2022");
		monthBox.setSelectedIndex(0);
		dayBox.setSelectedIndex(0);
		stHourBox.setSelectedIndex(0);
		edHourBox.setSelectedIndex(0);
		memoArea.setText("");
		fixBox.setSelected(false);
		enabled("0");
	}
	public int getWeek(String week) {
		int w;
		switch(week) {
		case "일":
			w=0;	break;
		case "월":
			w=1;	break;
		case "화":
			w=2;	break;
		case "수":
			w=3;	break;
		case "목":
			w=4;	break;
		case "금":
			w=5;	break;
		case "토":
			w=6;	break;
		default:
			return -1;
		}
		return w;
	}
}